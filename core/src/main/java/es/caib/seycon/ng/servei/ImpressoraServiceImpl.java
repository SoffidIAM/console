// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.caib.seycon.ng.comu.GrupImpressora;
import es.caib.seycon.ng.comu.Impressora;
import es.caib.seycon.ng.comu.NetworkAuthorization;
import es.caib.seycon.ng.comu.UsuariImpressora;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.GrupImpressoraEntity;
import es.caib.seycon.ng.model.ImpressoraEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariImpressoraEntity;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;

/**
 * @see es.caib.seycon.ng.servei.ImpressoraService
 */
public class ImpressoraServiceImpl extends
        es.caib.seycon.ng.servei.ImpressoraServiceBase {

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#getImpressores()
     */
    protected java.util.Collection<Impressora> handleGetImpressores()
            throws java.lang.Exception {
        List<ImpressoraEntity> c = getImpressoraEntityDao().loadAll();
        return getImpressoraEntityDao().toImpressoraList(c);
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#create(es.caib.seycon.ng.comu.Impressora)
     */
    protected es.caib.seycon.ng.comu.Impressora handleCreate(
            es.caib.seycon.ng.comu.Impressora impressora)
            throws java.lang.Exception {
		ImpressoraEntity printersSameCode = getImpressoraEntityDao().findByCodi(impressora.getCodi());
		if(printersSameCode != null)
			throw new SeyconException(String.format(Messages.getString("ImpressoraServiceImpl.CodePrinterExists"),  //$NON-NLS-1$
							impressora.getCodi())); 
        ImpressoraEntity entity = getImpressoraEntityDao().impressoraToEntity(
                impressora);
        getImpressoraEntityDao().create(entity);
        impressora.setId(entity.getId());
        impressora = getImpressoraEntityDao().toImpressora(entity);
        return impressora;
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#delete(es.caib.seycon.ng.comu.Impressora)
     */
    protected void handleDelete(es.caib.seycon.ng.comu.Impressora impressora)
            throws java.lang.Exception {
        ImpressoraEntity entity = getImpressoraEntityDao().impressoraToEntity(
                impressora);
        if(!entity.getGrups().isEmpty() || !entity.getUsuaris().isEmpty())
        	throw new SeyconException(String.format(Messages.getString("ImpressoraService.IntegrityExceptionGrups"), new Object[]{entity.getCodi()})); //$NON-NLS-1$
        getImpressoraEntityDao().remove(entity);
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#update(es.caib.seycon.ng.comu.Impressora)
     */
    protected Impressora handleUpdate(
            es.caib.seycon.ng.comu.Impressora impressora)
            throws java.lang.Exception {

        ImpressoraEntity entity = getImpressoraEntityDao().impressoraToEntity(
                impressora);
        getImpressoraEntityDao().update(entity);
        impressora = getImpressoraEntityDao().toImpressora(entity);
        return impressora;
    }

    protected Collection<Impressora> handleFindImpressoresByCodiImpressora(
            String codiImpressora) throws Exception {
        return findImpressoresByCriteri(codiImpressora, null,
                null, null);
    }

    protected Collection<Impressora> handleFindImpressoresByCriteri(String codi,
		String model, String local, String maquina) throws Exception
	{
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
            Collection<ImpressoraEntity> impressores = getImpressoraEntityDao()
                    .findImpressoresByCriteri(model, codi, local, maquina);
            
        	// Check maximum number of results
            if (impressores.size() > limitResults)
            {
            	return getImpressoraEntityDao().toImpressoraList(impressores)
            		.subList(0, limitResults);
            }
            
            return getImpressoraEntityDao().toImpressoraList(impressores);
        } else if (AutoritzacionsUsuari.hasQueryACLPrinter()) {
            // Li llevem el rownum... (s'ha de filtrar)
            Collection<ImpressoraEntity> impressores = getImpressoraEntityDao()
                    .query(
                            "select impressora from es.caib.seycon.ng.model.ImpressoraEntity impressora where " //$NON-NLS-1$
                                    + "(:codi is null or impressora.codi like :codi) and (:model is null or impressora.model like :model) and " //$NON-NLS-1$
                                    + "(:local is null or impressora.local = :local) and " //$NON-NLS-1$
                                    + "(:maquina is null or impressora.servidor.nom like :maquina) order by impressora.codi", //$NON-NLS-1$
                new Parameter[] {
                    new Parameter ("model", model), //$NON-NLS-1$
                    new Parameter ("codi", codi), //$NON-NLS-1$
                    new Parameter ("local", local), //$NON-NLS-1$
                    new Parameter ("maquina", maquina) //$NON-NLS-1$
                });
            // Les filtrem per ACL de l'usuari amb permis >= CONSULTA
            Collection<ImpressoraEntity> imp_permis = filtraImpressoresACL(impressores,
                    XarxaServiceImpl.CONSULTA);
            
            // Check maximum number of results
            if (imp_permis.size() > limitResults)
            {
            	return getImpressoraEntityDao().toImpressoraList(impressores)
					.subList(0, limitResults);
            }
            
            return getImpressoraEntityDao().toImpressoraList(imp_permis);
        }

        return new Vector();
    }

    /*
     * MÈTODES PER COMPROVAR ELS ACL A LES MÀQUINES
     */
    private Collection<ImpressoraEntity> filtraImpressoresACL(
            Collection<ImpressoraEntity> impressores, int accessLevel) throws InternalErrorException {

        // Mirem si té permis per veure totes les xarxes o per fer VNC
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canQueryAllHosts()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()) {
            return impressores;
        }
        String codiUsuari = getPrincipal().getName();
        // Obtenim TOTES LES Network Authorizations de l'usuari:
        Collection networkAuthorizations = getXarxaService()
                .findALLNetworkAuthorizationsByCodiUsuari(codiUsuari);
        Collection<ImpressoraEntity> impresoresPermeses = new LinkedList<ImpressoraEntity>();
        Iterator<ImpressoraEntity> iterator = impressores.iterator();
        while (iterator.hasNext()) {
            ImpressoraEntity impressora = (ImpressoraEntity) iterator.next();
            if (maquinaPermesa(networkAuthorizations, impressora.getServidor(),
                    accessLevel)) {
                impresoresPermeses.add(impressora);
            }
        }
        return impresoresPermeses;
    }

    private boolean maquinaPermesa(Collection networkAuthorizations,
            MaquinaEntity maquina, int accessLevel) {
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization networkAuthorization = (NetworkAuthorization) iterator
                    .next();
            if (maquina.getXarxa().getCodi()
                    .compareTo(networkAuthorization.getCodiXarxa()) == 0) {
                if (teAccesMaquina(maquina.getNom(),
                        networkAuthorization.getMascara())) {
                    // Cerquem qualque autorització >= accessLevel
                    if (networkAuthorization.getNivell() >= accessLevel)
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

    protected Impressora handleFindImpressoraByCodiImpressora(
            String codiImpressora) throws Exception {
        ImpressoraEntity impressoraEntity = getImpressoraEntityDao()
                .findByCodi(codiImpressora);
        if (impressoraEntity != null) {
            Impressora impressora = getImpressoraEntityDao().toImpressora(
                    impressoraEntity);
            return impressora;
        }
        return null;
    }

    protected UsuariImpressora handleCreate(UsuariImpressora usuariImpressora)
            throws Exception {
        UsuariImpressoraEntity usuariImpressoraEntity = getUsuariImpressoraEntityDao()
                .usuariImpressoraToEntity(usuariImpressora);

        UsuariEntity usuariEntity = usuariImpressoraEntity.getUsuari();

        // Un usuari no pot afegir-se a si mateix impressores
        if (getPrincipal() != null
                && usuariEntity.getCodi().compareTo(getPrincipal().getName()) == 0) {
            throw new SeyconException(
                    Messages.getString("ImpressoraServiceImpl.1")); //$NON-NLS-1$
        }

        // Verifiquem les autoritzacions
        if (AutoritzacionsUsuari.canCreateUserPrinter(usuariImpressoraEntity,
                getXarxaService())) {

            // creem l'associació entre usuari i impressora
            getUsuariImpressoraEntityDao().create(usuariImpressoraEntity);
            usuariImpressora.setId(usuariImpressoraEntity.getId());
            usuariImpressora = getUsuariImpressoraEntityDao()
                    .toUsuariImpressora(usuariImpressoraEntity);

            // s'actualitzen els camps de "Modificat per" i "Data de darrera
            // modificació
            usuariEntity.setDataDarreraModificacio(Calendar.getInstance()
                    .getTime());
            usuariEntity.setUsuariDarreraModificacio(getPrincipal().getName());
            getUsuariEntityDao().update(usuariEntity); // guardem els canvis

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

    protected GrupImpressora handleCreate(GrupImpressora grupImpressora)
            throws Exception {
        if (AutoritzacionsUsuari.canCreateGroupPrinter(grupImpressora
                .getCodiGrup())) {
            GrupImpressoraEntity grupImpressoraEntity = getGrupImpressoraEntityDao()
                    .grupImpressoraToEntity(grupImpressora);
            getGrupImpressoraEntityDao().create(grupImpressoraEntity);
            grupImpressora.setId(grupImpressoraEntity.getId());
            grupImpressora = getGrupImpressoraEntityDao().toGrupImpressora(
                    grupImpressoraEntity);
            return grupImpressora;
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "create (GrupImpressora)", //$NON-NLS-1$
                    "group:printer:create", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to create printers for this group"); //$NON-NLS-1$
        }
    }

    protected void handleDelete(UsuariImpressora usuariImpressora)
            throws Exception {
        UsuariImpressoraEntity usuariImpressoraEntity = getUsuariImpressoraEntityDao()
                .usuariImpressoraToEntity(usuariImpressora);

        // Verifiquem les autoritzacions (usuari:printer:delete o
        // usuari:printer:acl:delete)
        if (AutoritzacionsUsuari.canDeleteUserPrinter(usuariImpressoraEntity,
                getXarxaService())) {
            UsuariEntity usuariEntity = usuariImpressoraEntity.getUsuari();
            // l'esborrem (amb auditoria)
            getUsuariImpressoraEntityDao().remove(usuariImpressoraEntity);

            // s'actualitzen els camps de "Modificat per" i "Data de darrera
            // modificació
            usuariEntity.setDataDarreraModificacio(Calendar.getInstance()
                    .getTime());
            usuariEntity.setUsuariDarreraModificacio(getPrincipal().getName());
            getUsuariEntityDao().update(usuariEntity); // guardem els canvis
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "delete (UsuariImpressora)", //$NON-NLS-1$
                    "user:printer:delete", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to delete printers for this user " //$NON-NLS-1$
                            + " or not have the requiered access to the printer server (newtork ACLs)"); //$NON-NLS-1$
        }
    }

    protected void handleDelete(GrupImpressora grupImpressora) throws Exception {

        if (AutoritzacionsUsuari.canDeleteGroupPrinter(grupImpressora
                .getCodiGrup())) {

            GrupImpressoraEntity grupImpressoraEntity = getGrupImpressoraEntityDao()
                    .grupImpressoraToEntity(grupImpressora);
            getGrupImpressoraEntityDao().remove(grupImpressoraEntity);
        } else {
            throw new SeyconAccessLocalException(
                    "impresoraService", //$NON-NLS-1$
                    "delete (GrupImpressora)", //$NON-NLS-1$
                    "group:printer:delete", //$NON-NLS-1$
                    "User's group-based authorization: probably not authorized to delete printers for this group"); //$NON-NLS-1$
        }
    }

    protected GrupImpressora handleFindGrupImpressoraByCodiGrupAndCodiImpressora(
            String codiGrup, String codiImpressora) throws Exception {
        GrupImpressoraEntity grupImpressoraEntity = getGrupImpressoraEntityDao()
                .findGrupImpressoraByCodiGrupAndCodiImpressora(codiGrup,
                        codiImpressora);
        if (grupImpressoraEntity != null) {
            GrupImpressora grupImpressora = getGrupImpressoraEntityDao()
                    .toGrupImpressora(grupImpressoraEntity);
            return grupImpressora;
        }
        return null;
    }

    protected UsuariImpressora handleFindUsuariImpressoraByCodiUsuariAndCodiImpressora(
            String codiUsuari, String codiImpressora) throws Exception {
        UsuariImpressoraEntity usuariImpressoraEntity = this
                .getUsuariImpressoraEntityDao()
                .findUsuariImpressoraByCodiUsuariAndCodiImpressora(codiUsuari,
                        codiImpressora);
        if (usuariImpressoraEntity != null) {
            UsuariImpressora usuariImpressora = this
                    .getUsuariImpressoraEntityDao().toUsuariImpressora(
                            usuariImpressoraEntity);
            return usuariImpressora;
        }
        return null;
    }

    protected Collection<GrupImpressora> handleGetGrupImpressoresByCodiImpressora(
            String codiImpressora) throws Exception {
        List<GrupImpressoraEntity> grupImpressores = getGrupImpressoraEntityDao()
                .findGrupImpressoresByCodiImpressora(codiImpressora);
        if (grupImpressores != null) {
            return getGrupImpressoraEntityDao().toGrupImpressoraList(
                    grupImpressores);
        }
        return new Vector<GrupImpressora>();
    }

    protected Collection<UsuariImpressora> handleGetUsuariImpressoresByCodiImpressora(
            String codiImpressora) throws Exception {
        List<UsuariImpressoraEntity> usuariImpressores = getUsuariImpressoraEntityDao()
                .findUsuariImpressoresByCodiImpressora(codiImpressora);
        if (usuariImpressores != null) {
            return getUsuariImpressoraEntityDao().toUsuariImpressoraList(
                    usuariImpressores);
        }
        return new LinkedList<UsuariImpressora>();
    }

    protected UsuariImpressora handleUpdate(UsuariImpressora usuariImpressora)
            throws Exception { // Per marcar com a impressora per defecte
        UsuariImpressoraEntity usuariImpressoraEntity = getUsuariImpressoraEntityDao()
                .usuariImpressoraToEntity(usuariImpressora);

        UsuariEntity usuariEntity = usuariImpressoraEntity.getUsuari();

        // Verifiquem les autoritzacions
        if (AutoritzacionsUsuari.canCreateUserPrinter(usuariImpressoraEntity,
                getXarxaService())) {
            getUsuariImpressoraEntityDao().update(usuariImpressoraEntity);
            usuariImpressora = getUsuariImpressoraEntityDao()
                    .toUsuariImpressora(usuariImpressoraEntity);
            usuariImpressora.setId(usuariImpressoraEntity.getId());

            // s'actualitzen els camps de "Modificat per" i "Data de darrera
            // modificació
            usuariEntity.setDataDarreraModificacio(Calendar.getInstance()
                    .getTime());
            usuariEntity.setUsuariDarreraModificacio(getPrincipal().getName());
            getUsuariEntityDao().update(usuariEntity); // guardem els canvis

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

    protected Collection<GrupImpressora> handleFindGrupImpressoresByCodiGrup(String codiGrup)
            throws Exception {
        List<GrupImpressoraEntity> grupImpressores = getGrupImpressoraEntityDao()
                .findGrupImpressoresByCodiGrup(codiGrup);
        if (grupImpressores != null) {
            return getGrupImpressoraEntityDao().toGrupImpressoraList(
                    grupImpressores);
        }
        return new Vector<GrupImpressora>();
    }

    protected GrupImpressora handleUpdate(GrupImpressora grupImpressora)
            throws Exception {
        GrupImpressoraEntity grupImpressoraEntity = getGrupImpressoraEntityDao()
                .grupImpressoraToEntity(grupImpressora);
        getGrupImpressoraEntityDao().update(grupImpressoraEntity);
        grupImpressora = getGrupImpressoraEntityDao().toGrupImpressora(
                grupImpressoraEntity);
        return grupImpressora;
    }

}
