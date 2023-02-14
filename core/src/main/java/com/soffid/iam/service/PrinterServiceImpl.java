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

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.NetworkAuthorization;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Printer;
import com.soffid.iam.api.PrinterGroup;
import com.soffid.iam.api.PrinterUser;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.NetworkEntityDao;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.PrinterEntity;
import com.soffid.iam.model.PrinterEntityDao;
import com.soffid.iam.model.PrinterGroupEntity;
import com.soffid.iam.model.QueryBuilder;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserPrinterEntity;
import com.soffid.iam.model.UserPrinterEntityDao;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.NetworkServiceImpl;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.InternalErrorException;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
			throw new InternalErrorException(String.format(Messages.getString("PrinterServiceImpl.CodePrinterExists"), impressora.getCode())); 
        PrinterEntity entity = getPrinterEntityDao().printerToEntity(impressora);
        getPrinterEntityDao().create(entity);
        updateUsersAndGroups(entity, impressora);
        impressora.setId(entity.getId());
        impressora = getPrinterEntityDao().toPrinter(entity);
        return impressora;
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#delete(es.caib.seycon.ng.comu.Impressora)
     */
    protected void handleDelete(com.soffid.iam.api.Printer impressora) throws java.lang.Exception {
        PrinterEntity entity = getPrinterEntityDao().printerToEntity(impressora);
        
        getPrinterGroupEntityDao().remove(entity.getGroups());
        getUserPrinterEntityDao().remove(entity.getUsers());
        getPrinterEntityDao().remove(entity);
    }

    /**
     * @see es.caib.seycon.ng.servei.ImpressoraService#update(es.caib.seycon.ng.comu.Impressora)
     */
    protected Printer handleUpdate(com.soffid.iam.api.Printer impressora) throws java.lang.Exception {

        PrinterEntity entity = getPrinterEntityDao().printerToEntity(impressora);
        getPrinterEntityDao().update(entity);
        updateUsersAndGroups(entity, impressora);
        impressora = getPrinterEntityDao().toPrinter(entity);
        return impressora;
    }

    private void updateUsersAndGroups(PrinterEntity entity, Printer impressora) throws InternalErrorException {
    	updateUsers(entity, impressora);
    	updateGroups(entity, impressora);
	}

	public void updateUsers(PrinterEntity entity, Printer impressora) throws InternalErrorException {
		Set<String> u = new HashSet<String>();
    	if (impressora.getUsers() != null) u.addAll(impressora.getUsers());
    	
    	for (UserPrinterEntity up: new LinkedList<UserPrinterEntity>(entity.getUsers())) {
    		if (u.contains(up.getUser().getUserName()))
    			u.remove(up.getUser().getUserName());
    		else {
    			getUserPrinterEntityDao().remove(up);
    			entity.getUsers().remove(up);
    		}
    	}
    	
    	for ( String uu: u) {
    		UserEntity user = getUserEntityDao().findByUserName(uu);
    		if (user == null)
    			throw new InternalErrorException ("Unable to find user "+uu);
    		UserPrinterEntity upe = getUserPrinterEntityDao().newUserPrinterEntity();
    		upe.setOrder(2L);
    		upe.setPrinter(entity);
    		upe.setUser(user);
    		getUserPrinterEntityDao().create(upe);
    	}
	}

	public void updateGroups(PrinterEntity entity, Printer impressora) throws InternalErrorException {
		Set<String> g = new HashSet<String>();
    	if (impressora.getGroups() != null) g.addAll(impressora.getGroups());
    	
    	for (PrinterGroupEntity pg: new LinkedList<PrinterGroupEntity>(entity.getGroups())) {
    		if (g.contains(pg.getGroup().getName()))
    			g.remove(pg.getGroup().getName());
    		else {
    			getPrinterGroupEntityDao().remove(pg);
    			entity.getGroups().remove(pg);
    		}
    	}
    	
    	for ( String uu: g) {
    		GroupEntity group = getGroupEntityDao().findByName(uu);
    		if (group == null)
    			throw new InternalErrorException ("Unable to find group "+uu);
    		PrinterGroupEntity upe = getPrinterGroupEntityDao().newPrinterGroupEntity();
    		upe.setOrder(2L);
    		upe.setPrinter(entity);
    		upe.setGroup(group);
    		getPrinterGroupEntityDao().create(upe);
    	}
	}

	protected Collection<Printer> handleFindPrintersByPrinterName(String codiImpressora) throws Exception {
        return findPrintersByFilter(codiImpressora, null, null, null);
    }

    protected Collection<Printer> handleFindPrintersByFilter(String codi, String model, String local, String maquina) throws Exception {
    	int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
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
            throw new InternalErrorException(
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

	@Override
	protected AsyncList<Printer> handleFindPrinterByTextAndJsonQueryAsync(final String text, final String jsonQuery)
			throws Exception {
		final AsyncList<Printer> result = new AsyncList<Printer>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindPrinterByTextAndJsonQuery(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private void doFindPrinterByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			Collection<Printer> result) throws Exception {
		final PrinterEntityDao dao = getPrinterEntityDao();
		ScimHelper h = new ScimHelper(Printer.class);
		h.setPrimaryAttributes(new String[] { "name", "description", "model"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");

		final List<String> printers = new LinkedList<>();

		h.setGenerator((entity) -> {
			PrinterEntity ne = (PrinterEntity) entity;
			return dao.toPrinter((PrinterEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
	}
	

	@Override
	protected List<Printer> handleFindPrinterByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<Printer> result = new LinkedList<Printer>();
		doFindPrinterByTextAndJsonQuery(text, jsonQuery, start, pageSize, result);
		return result;
	}



	@Override
	protected AsyncList<PrinterUser> handleFindPrinterUserByTextAndJsonQueryAsync(final String text, final String jsonQuery)
			throws Exception {
		final AsyncList<PrinterUser> result = new AsyncList<PrinterUser>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindPrinterUserByTextAndJsonQuery(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private PagedResult<PrinterUser> doFindPrinterUserByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<PrinterUser> result) throws Exception {
		final UserPrinterEntityDao dao = getUserPrinterEntityDao();
		ScimHelper h = new ScimHelper(PrinterUser.class);
		h.setPrimaryAttributes(new String[] { "printer", "user"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("user.tenant.id");

		h.setGenerator((entity) -> {
			UserPrinterEntity ne = (UserPrinterEntity) entity;
			if (ne.isAllowed("user:query"))
				return dao.toPrinterUser(ne);
			else 
				return null;
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<PrinterUser> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}
	

	@Override
	protected PagedResult<PrinterUser> handleFindPrinterUserByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<PrinterUser> result = new LinkedList<PrinterUser>();
		return doFindPrinterUserByTextAndJsonQuery(text, jsonQuery, start, pageSize, result);
	}

}
