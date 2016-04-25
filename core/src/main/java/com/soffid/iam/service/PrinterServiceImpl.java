// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service;

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.NetworkAuthorization;
import com.soffid.iam.api.Printer;
import com.soffid.iam.api.PrinterGroup;
import com.soffid.iam.api.PrinterUser;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.PrinterEntity;
import com.soffid.iam.model.PrinterGroupEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserPrinterEntity;
import com.soffid.iam.service.NetworkServiceImpl;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @see es.caib.seycon.ng.servei.ImpressoraService
 */
public class PrinterServiceImpl extends
        com.soffid.iam.service.PrinterServiceBase {

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#getImpressores()
     */
    protected java.util.Collection<Printer> handleGetPrinters() throws java.lang.Exception {
        List<PrinterEntity> c = getPrinterEntityDao().loadAll();
        return getPrinterEntityDao().toPrinterList(c);
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#create(es.caib.seycon.ng.comu.Impressora)
     */
    protected com.soffid.iam.api.Printer handleCreate(com.soffid.iam.api.Printer impressora) throws java.lang.Exception {
		PrinterEntity printersSameCode = getPrinterEntityDao().findByName(impressora.getCode());
		if(printersSameCode != null)
			throw new SeyconException(String.format(Messages.getString("PrinterServiceImpl.CodePrinterExists"), impressora.getCode())); 
        PrinterEntity entity = getPrinterEntityDao().printerToEntity(impressora);
        getPrinterEntityDao().create(entity);
        impressora.setId(entity.getId());
        impressora = getPrinterEntityDao().toPrinter(entity);
        return impressora;
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#delete(es.caib.seycon.ng.comu.Impressora)
     */
    protected void handleDelete(com.soffid.iam.api.Printer impressora) throws java.lang.Exception {
        PrinterEntity entity = getPrinterEntityDao().printerToEntity(impressora);
        if(!entity.getGroups().isEmpty() || !entity.getUsers().isEmpty())
        	throw new SeyconException(String.format(Messages.getString("ImpressoraService.IntegrityExceptionGrups"), new Object[]{entity.getName()})); //$NON-NLS-1$
        getPrinterEntityDao().remove(entity);
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#update(es.caib.seycon.ng.comu.Impressora)
     */
    protected Printer handleUpdate(com.soffid.iam.api.Printer impressora) throws java.lang.Exception {

        PrinterEntity entity = getPrinterEntityDao().printerToEntity(impressora);
        getPrinterEntityDao().update(entity);
        impressora = getPrinterEntityDao().toPrinter(entity);
        return impressora;
    }

    protected Collection<Printer> handleFindPrintersByPrinterName(String codiImpressora) throws Exception {
        return findPrintersByFilter(codiImpressora, null, null, null);
    }

    protected Collection<Printer> handleFindPrintersByFilter(String codi, String model, String local, String maquina) throws Exception {
    	int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
    	if (codi != null &&
			(codi.trim().compareTo("") == 0 || codi.trim() //$NON-NLS-1$
				.compareTo("%") == 0)) { //$NON-NLS-1$
    		codi = null;
		}
    	
        if (model != null &&
			(model.trim().compareTo("") == 0 ||  //$NON-NLS-1$
			model.trim().compareTo("%") == 0)) { //$NON-NLS-1$
        	model = null;
    	}
        
        if (maquina != null && (maquina.trim().compareTo("") == 0 || //$NON-NLS-1$
			maquina.trim().compareTo("%") == 0)) { //$NON-NLS-1$
        	maquina = null;
        }
        
        if (local != null && (local.trim().compareTo("") == 0 || //$NON-NLS-1$
			local.trim().compareTo("%") == 0)) { //$NON-NLS-1$
        	local = null;
    	}

        // Aquí filtrem les impressores (per ACL)
        if (AutoritzacionsUsuari.hasQueryAllPrinter()) {
            // Limitat a 201 registres per consulta (rownum)..
            Collection<PrinterEntity> impressores = getPrinterEntityDao().findPrintersByCriteria(model, codi, local, maquina);
            
        	// Check maximum number of results
            if (impressores.size() > limitResults)
            {
            	return getPrinterEntityDao().toPrinterList(impressores).subList(0, limitResults);
            }
            
            return getPrinterEntityDao().toPrinterList(impressores);
        } else if (AutoritzacionsUsuari.hasQueryACLPrinter()) {
            // Li llevem el rownum... (s'ha de filtrar)
            Collection<PrinterEntity> impressores = getPrinterEntityDao().findPrintersByCriteria(model, codi, local, maquina);
            // Les filtrem per ACL de l'usuari amb permis >= CONSULTA
            Collection<PrinterEntity> imp_permis = filtraImpressoresACL(impressores, NetworkServiceImpl.CONSULTA);
            
            // Check maximum number of results
            if (imp_permis.size() > limitResults)
            {
            	return getPrinterEntityDao().toPrinterList(impressores).subList(0, limitResults);
            }
            
            return getPrinterEntityDao().toPrinterList(imp_permis);
        }

        return new Vector();
    }

    /*
     * MÈTODES PER COMPROVAR ELS ACL A LES MÀQUINES
     */
    private Collection<PrinterEntity> filtraImpressoresACL(Collection<PrinterEntity> impressores, int accessLevel) throws InternalErrorException {

        // Mirem si té permis per veure totes les xarxes o per fer VNC
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canQueryAllHosts()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()) {
            return impressores;
        }
        String codiUsuari = Security.getCurrentUser();
        // Obtenim TOTES LES Network Authorizations de l'usuari:
        Collection networkAuthorizations = getNetworkService().findALLNetworkAuthorizationsByUserName(codiUsuari);
        Collection<PrinterEntity> impresoresPermeses = new LinkedList<PrinterEntity>();
        Iterator<PrinterEntity> iterator = impressores.iterator();
        while (iterator.hasNext()) {
            PrinterEntity impressora = (PrinterEntity) iterator.next();
            if (maquinaPermesa(networkAuthorizations, impressora.getServer(), accessLevel)) {
                impresoresPermeses.add(impressora);
            }
        }
        return impresoresPermeses;
    }

    private boolean maquinaPermesa(Collection networkAuthorizations, HostEntity maquina, int accessLevel) {
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization networkAuthorization = (NetworkAuthorization) iterator
                    .next();
            if (maquina.getNetwork().getName().compareTo(networkAuthorization.getNetworkCode()) == 0) {
                if (teAccesMaquina(maquina.getName(), networkAuthorization.getMask())) {
                    // Cerquem qualque autorització >= accessLevel
                    if (networkAuthorization.getLevel() >= accessLevel)
                        return true;
                }
            }
        }
        // no s'ha trobat cap autorització amb nivell >= accessLevel
        return false;
    }

    private static boolean teAccesMaquina(String codiMaquina, String expresio) {
        Pattern pattern = Pattern.compile("^" + expresio + "$"); //$NON-NLS-1$ //$NON-NLS-2$
        Matcher matcher = pattern.matcher(codiMaquina);
        boolean matches = matcher.find();
        return matches;
    }

    /*
     * FI DE MÈTODES PER COMPROVAR ELS ACL A LES MÀQUINES
     */

    protected Printer handleFindPrinterByPrinterName(String codiImpressora) throws Exception {
        PrinterEntity impressoraEntity = getPrinterEntityDao().findByName(codiImpressora);
        if (impressoraEntity != null) {
            Printer impressora = getPrinterEntityDao().toPrinter(impressoraEntity);
            return impressora;
        }
        return null;
    }

    protected PrinterUser handleCreate(PrinterUser usuariImpressora) throws Exception {
        UserPrinterEntity usuariImpressoraEntity = getUserPrinterEntityDao().printerUserToEntity(usuariImpressora);

        UserEntity usuariEntity = usuariImpressoraEntity.getUser();

        // Un usuari no pot afegir-se a si mateix impressores
        if (getPrincipal() != null && usuariEntity.getUserName().compareTo(Security.getCurrentUser()) == 0) {
            throw new SeyconException(
                    Messages.getString("PrinterServiceImpl.1")); //$NON-NLS-1$
        }

        // Verifiquem les autoritzacions
        if (getAuthorizationService().hasPermission(Security.AUTO_USER_PRINTER_CREATE, usuariImpressoraEntity))
        {

            // creem l'associació entre usuari i impressora
            getUserPrinterEntityDao().create(usuariImpressoraEntity);
            usuariImpressora.setId(usuariImpressoraEntity.getId());
            usuariImpressora = getUserPrinterEntityDao().toPrinterUser(usuariImpressoraEntity);

            // s'actualitzen els camps de "Modificat per" i "Data de darrera
            // modificació
            usuariEntity.setLastModificationDate(Calendar.getInstance().getTime());
            usuariEntity.setLastUserModification(getPrincipal().getName());
            getUserEntityDao().update(usuariEntity); // guardem els canvis

            return usuariImpressora;
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "create (UsuariImpressora)", //$NON-NLS-1$
                    "user:printer:create, user:printer:acl:create", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to create printers for this user" //$NON-NLS-1$
                            + " or not have the requiered access to the printer server (newtork ACLs)"); //$NON-NLS-1$
        }
    }

    protected PrinterGroup handleCreate(PrinterGroup grupImpressora) throws Exception {
        PrinterGroupEntity grupImpressoraEntity = getPrinterGroupEntityDao().printerGroupToEntity(grupImpressora);
        if (getAuthorizationService().hasPermission(Security.AUTO_GROUP_PRINTER_CREATE, grupImpressoraEntity))
        {
            getPrinterGroupEntityDao().create(grupImpressoraEntity);
            grupImpressora.setId(grupImpressoraEntity.getId());
            grupImpressora = getPrinterGroupEntityDao().toPrinterGroup(grupImpressoraEntity);
            return grupImpressora;
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "create (GrupImpressora)", //$NON-NLS-1$
                    "group:printer:create", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to create printers for this group"); //$NON-NLS-1$
        }
    }

    protected void handleDelete(PrinterUser usuariImpressora) throws Exception {
        UserPrinterEntity usuariImpressoraEntity = getUserPrinterEntityDao().printerUserToEntity(usuariImpressora);

        // Verifiquem les autoritzacions (usuari:printer:delete o
        // usuari:printer:acl:delete)
        if (getAuthorizationService().hasPermission(Security.AUTO_USER_PRINTER_DELETE, usuariImpressoraEntity))
        {
            UserEntity usuariEntity = usuariImpressoraEntity.getUser();
            // l'esborrem (amb auditoria)
            getUserPrinterEntityDao().remove(usuariImpressoraEntity);

            // s'actualitzen els camps de "Modificat per" i "Data de darrera
            // modificació
            usuariEntity.setLastModificationDate(Calendar.getInstance().getTime());
            usuariEntity.setLastUserModification(Security.getCurrentAccount());
            getUserEntityDao().update(usuariEntity); // guardem els canvis
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "delete (UsuariImpressora)", //$NON-NLS-1$
                    "user:printer:delete", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to delete printers for this user " //$NON-NLS-1$
                            + " or not have the requiered access to the printer server (newtork ACLs)"); //$NON-NLS-1$
        }
    }

    protected void handleDelete(PrinterGroup grupImpressora) throws Exception {

        PrinterGroupEntity grupImpressoraEntity = getPrinterGroupEntityDao().printerGroupToEntity(grupImpressora);
        if (getAuthorizationService().hasPermission(Security.AUTO_GROUP_PRINTER_DELETE, grupImpressoraEntity)) {
            getPrinterGroupEntityDao().remove(grupImpressoraEntity);
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "delete (GrupImpressora)", //$NON-NLS-1$
                    "group:printer:delete", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to delete printers for this group"); //$NON-NLS-1$
        }
    }

    protected PrinterGroup handleFindPrinterGroupByGroupNameAndPrinterName(String codiGrup, String codiImpressora) throws Exception {
        PrinterGroupEntity grupImpressoraEntity = getPrinterGroupEntityDao().findByGroupAndPrinter(codiGrup, codiImpressora);
        if (grupImpressoraEntity != null) {
            PrinterGroup grupImpressora = getPrinterGroupEntityDao().toPrinterGroup(grupImpressoraEntity);
            return grupImpressora;
        }
        return null;
    }

    protected PrinterUser handleFindPrinterUserByUserNameAndPrinterName(String codiUsuari, String codiImpressora) throws Exception {
        UserPrinterEntity usuariImpressoraEntity = this.getUserPrinterEntityDao().findUserByUserAndPrinter(codiUsuari, codiImpressora);
        if (usuariImpressoraEntity != null) {
            PrinterUser usuariImpressora = this.getUserPrinterEntityDao().toPrinterUser(usuariImpressoraEntity);
            return usuariImpressora;
        }
        return null;
    }

    protected Collection<PrinterGroup> handleGetPrintersGroupByPrinterName(String codiImpressora) throws Exception {
        List<PrinterGroupEntity> grupImpressores = getPrinterGroupEntityDao().findByPrinter(codiImpressora);
        if (grupImpressores != null) {
            return getPrinterGroupEntityDao().toPrinterGroupList(grupImpressores);
        }
        return new Vector<PrinterGroup>();
    }

    protected Collection<PrinterUser> handleGetUserPrintersByPrinterName(String codiImpressora) throws Exception {
        List<UserPrinterEntity> usuariImpressores = getUserPrinterEntityDao().findByPrinter(codiImpressora);
        if (usuariImpressores != null) {
            return getUserPrinterEntityDao().toPrinterUserList(usuariImpressores);
        }
        return new LinkedList<PrinterUser>();
    }

    protected PrinterUser handleUpdate(PrinterUser usuariImpressora) throws Exception { // Per marcar com a impressora per defecte
        UserPrinterEntity usuariImpressoraEntity = getUserPrinterEntityDao().printerUserToEntity(usuariImpressora);

        UserEntity usuariEntity = usuariImpressoraEntity.getUser();

        // Verifiquem les autoritzacions
        if (getAuthorizationService().hasPermission(Security.AUTO_USER_PRINTER_CREATE, usuariImpressoraEntity))
        {
            getUserPrinterEntityDao().update(usuariImpressoraEntity);
            usuariImpressora = getUserPrinterEntityDao().toPrinterUser(usuariImpressoraEntity);
            usuariImpressora.setId(usuariImpressoraEntity.getId());

            // s'actualitzen els camps de "Modificat per" i "Data de darrera
            // modificació
            usuariEntity.setLastModificationDate(Calendar.getInstance().getTime());
            usuariEntity.setLastUserModification(Security.getCurrentAccount());
            getUserEntityDao().update(usuariEntity); // guardem els canvis

            return usuariImpressora;
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "update (UsuariImpressora)", //$NON-NLS-1$
                    "user:printer:create", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to update printers for this user" //$NON-NLS-1$
                            + " or not have the requiered access to the printer server (newtork ACLs)"); //$NON-NLS-1$
        }
    }

    protected Collection<PrinterGroup> handleFindPrintersGroupByGroupName(String codiGrup) throws Exception {
        List<PrinterGroupEntity> grupImpressores = getPrinterGroupEntityDao().findByGroup(codiGrup);
        if (grupImpressores != null) {
            return getPrinterGroupEntityDao().toPrinterGroupList(grupImpressores);
        }
        return new Vector<PrinterGroup>();
    }

    protected PrinterGroup handleUpdate(PrinterGroup grupImpressora) throws Exception {
        PrinterGroupEntity grupImpressoraEntity = getPrinterGroupEntityDao().printerGroupToEntity(grupImpressora);
        getPrinterGroupEntityDao().update(grupImpressoraEntity);
        grupImpressora = getPrinterGroupEntityDao().toPrinterGroup(grupImpressoraEntity);
        return grupImpressora;
    }

}
