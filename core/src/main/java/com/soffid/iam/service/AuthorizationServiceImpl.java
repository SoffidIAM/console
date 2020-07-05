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

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.model.AuthorizationEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.service.ApplicationService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.utils.SoffidAuthorization;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.util.TipusDomini;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @see es.caib.seycon.ng.servei.AutoritzacioService
 */
public class AuthorizationServiceImpl extends
        com.soffid.iam.service.AuthorizationServiceBase {
    
    /**
	 * 
	 */
	private static final String AUTORITZACIO_CACHE = "AutoritzacioCache"; //$NON-NLS-1$

	@SuppressWarnings("unchecked")
    public AuthorizationServiceImpl () {
    }

    // Afegim este valor de domini per a atorgar l'autorització a tots els
    // possibles valor de domini de l'autorització
    private final String TIPUS_DOMINI_ESTRELLETA = "TIPUS_DOMINI_ESTRELLETA"; //$NON-NLS-1$

    // El guarden de forma estàtica
    private static HashMap xmlAuthorizations = null;

    // Es carrega en el començament
    private Map getAuthorizations()
    {
    	if (xmlAuthorizations == null)
    	{
    		HashMap xmlAutoritzacions = new HashMap();
            InputStream is = null;
            try {
                // Obtenim el fitxer d'autoritzacions:
            	Enumeration<URL> urls = AuthorizationService.class.getClassLoader().getResources("es/caib/seycon/autoritzacions.xml"); //$NON-NLS-1$
            	while (urls.hasMoreElements())
            	{
            		URL url = urls.nextElement();
                    is = url.openStream();
        
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(is);
                    doc.getDocumentElement().normalize();
        
                    // System.out.println("Root element "+
                    // doc.getDocumentElement().getNodeName());
                    NodeList elementsAutoritzacio = doc
                            .getElementsByTagName("autoritzacio"); //$NON-NLS-1$
                    // System.out.println("Information of all autoritzacions");
        
                    for (int s = 0; s < elementsAutoritzacio.getLength(); s++) {
        
                        Node nodeAutoritzacio = elementsAutoritzacio.item(s);
        
                        if (nodeAutoritzacio.getNodeType() == Node.ELEMENT_NODE) {
                            // Construim l'autoritzacióVO a partir del XML
                            SoffidAuthorization auto = new SoffidAuthorization(
                                    (Element) nodeAutoritzacio);
                            // Si no té codi, l'ignorem
                            if (auto.getCodi() != null) {
                                xmlAutoritzacions.put(auto.getCodi(), auto);
                            }
                        }
        
                    }
                    is.close();
            	}
            	xmlAuthorizations = xmlAutoritzacions;
            } catch (Throwable e) {
                System.err
                        .println(Messages.getString("AuthorizationServiceImpl.ErrorObtainingAuthorizations") //$NON-NLS-1$
                                + e.getMessage());
                e.printStackTrace();
                return new HashMap();
            } finally {
                try {
                    if (is != null)
                        is.close();
                } catch (Throwable th) {
                }
            }
    	}
    	return xmlAuthorizations;
    }

    protected AuthorizationRole handleCreate(AuthorizationRole autoritzacio) throws Exception {
        AuthorizationEntity auto = getAuthorizationEntityDao().authorizationRoleToEntity(autoritzacio);
        getAuthorizationEntityDao().create(auto);

        return getAuthorizationEntityDao().toAuthorizationRole(auto);
    }

    protected void handleDelete(AuthorizationRole autoritzacio) throws Exception {
        // si ya tiene id, la borramos
        if (autoritzacio.getId() != null) {
            getAuthorizationEntityDao().remove(autoritzacio.getId());
        }
    }

    /*
     * Obtenim els rols que conté l'autorització (llistat des de SEU)
     * 
     * (non-Javadoc)
     * 
     * @see
     * es.caib.seycon.ng.servei.AutoritzacioServiceBase#handleGetRolsAutoritzacio
     * (java.lang.String)
     */
    protected Collection<AuthorizationRole> handleGetAuthorizationRoles(String autoritzacio) throws Exception {
        if (autoritzacio != null) {
            List<AuthorizationEntity> autoritzacions = getAuthorizationEntityDao().findByAuthorization(autoritzacio);
            if (autoritzacions != null && autoritzacions.size() != 0) {
                return getAuthorizationEntityDao().toAuthorizationRoleList(autoritzacions);
            }
        }
        return new ArrayList();
    }

    private Collection getCodiGrupsFillsGrup(String codiGrup) {

        LinkedList l_grupsUsuari = new LinkedList();
        l_grupsUsuari.add(codiGrup);
        HashSet grupsFills = new HashSet();

        String codiGrupAnalitzat = null;
        while ((codiGrupAnalitzat = (String) l_grupsUsuari.poll()) != null) {
            if (!grupsFills.contains(codiGrupAnalitzat)) { // si no l'hem
                                                           // analitzat ja
                grupsFills.add(codiGrupAnalitzat);
                Collection fills = getGroupEntityDao().findByParent(codiGrupAnalitzat);
                if (fills != null)
                    for (Iterator git = fills.iterator(); git.hasNext(); ) {
                    GroupEntity fg = (GroupEntity) git.next();
                    if (!grupsFills.contains(fg.getName())) l_grupsUsuari.add(fg.getName());
                }
            }
        }

        return grupsFills;
    }

    private Collection getCodiGrupsParesGrup(String codiGrup) {

        GroupEntity grupAnalitzar = getGroupEntityDao().findByName(codiGrup);

        Collection totsPares = new HashSet();
        GroupEntity pare = grupAnalitzar.getParent();
        while (pare != null) {
            totsPares.add(pare.getName());
            pare = pare.getParent();
        }

        return totsPares;
    }

    Log log = LogFactory.getLog(getClass());
    
    private List<AuthorizationRole> getAutoritzacionsUsuari(Collection autoritzacionsRolVO, String codiUsuari, String holderGroup) throws InternalErrorException {

        List<AuthorizationRole> autoritzacionsUsuari = new LinkedList<AuthorizationRole>();

        // ROLS de l'usuari actual (heredats -senseDomini i atorgats -ambDomini)
        HashMap rolsUsuariSenseDomini = new HashMap(); // id_rol => rolEntity
        // id_rol => llistat de rolsusuariEntity (poden ser N)
        // Atorgació directa (RolsUsuarisEntity):amb domini
        ApplicationService appSvc = getApplicationService();
        com.soffid.iam.service.PasswordService passSvc = getPasswordService();
        com.soffid.iam.service.AccountService acctSvc = getAccountService();
        
        Account account = acctSvc.findAccount(codiUsuari, passSvc.getDefaultDispatcher());
        
        if (account == null)
        	return new Vector<AuthorizationRole>();
        
        Collection<RoleGrant> grants = new LinkedList<RoleGrant>();
        if (account instanceof UserAccount)
        {
        	User usuari = getUserService().findUserByUserName(((UserAccount) account).getUser());
        	if ( holderGroup != null)
        	{
        		GroupEntity group = getGroupEntityDao().findByName(holderGroup);
        		if (group != null)
        			grants = appSvc.findEffectiveRoleGrantByUserAndHolderGroup(usuari.getId(), group.getId());
        	}
        	else
        		grants = appSvc.findEffectiveRoleGrantByUser(usuari.getId());
        }
        else
        	grants = appSvc.findEffectiveRoleGrantByAccount(account.getId());
        
        HashMap<Long, List<RoleGrant>> rols = new HashMap<Long, List<RoleGrant>>();
        // explorem rols de l'usuari (poden tindre domini)
        for (RoleGrant rg : grants) {
            Long rolId = rg.getRoleId();
            if (!rols.containsKey(rolId)) {
                LinkedList<RoleGrant> grantList = new LinkedList<RoleGrant>();
                grantList.add(rg);
                rols.put(rolId, grantList);
            } else {
                rols.get(rolId).add(rg);
            }
        }

        // guardem les autoritzacions (per id)
        if (autoritzacionsRolVO != null && autoritzacionsRolVO.size() != 0) {
            for (Iterator it = autoritzacionsRolVO.iterator(); it.hasNext(); ) {
                AuthorizationRole autoRolVO = (AuthorizationRole) it.next();
                SoffidAuthorization autoSEU = (SoffidAuthorization) getAuthorizations().get(autoRolVO.getAuthorization());
                if (autoSEU != null)
                {
	                String scope = autoSEU != null ? autoSEU.getScope() : null;
	                autoRolVO.setBusinessGroupScope(scope != null ? scope : Security.AUTO_SCOPE_ONE);
	                if (autoRolVO.getUserRoleValueDomain() == null) {
	                    autoRolVO.setUserRoleValueDomain(new HashSet());
	                }
	                Long idRol = autoRolVO.getRole().getId();
	                if (rols.containsKey(idRol)) {
	                    for (RoleGrant rg : rols.get(idRol)) {
	                        String tipusDomini = null;
	                        RoleEntity role = getRoleEntityDao().load(idRol);
	                        if (role != null) tipusDomini = role.getDomainType();
	                   		boolean compatibleDomain;
	
	                   		if ( autoSEU.getTipusDomini() == null || autoSEU.getTipusDomini().trim().isEmpty() )
	                   			compatibleDomain = false;
	                   		else if (tipusDomini == null || tipusDomini.trim().isEmpty())
	                   			compatibleDomain = false;
	                   		else
	                   		{
	                   			compatibleDomain = false;
	                   			for (String s: autoSEU.getTipusDomini().split("[, ]+"))
	                   			{
	                   				if (tipusDomini.startsWith(s))
	                   				{
	                   					compatibleDomain = true;
	                   					break;
	                   				}
	                   			}
	                   		}
	                   		
	                    	if (compatibleDomain)
	                        {
	                    		DomainValue dv = new DomainValue();
	                    		dv.setValue(rg.getDomainValue());
	                    		dv.setDomainName(tipusDomini);
	                            autoRolVO.getUserRoleValueDomain().add(dv);
	                            if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)) {
	                                Collection grupsAutoritzacio = null;
	                                if (Security.AUTO_SCOPE_PARES.equals(autoRolVO.getBusinessGroupScope())) {
	                                    grupsAutoritzacio = getCodiGrupsParesGrup(rg.getDomainValue());
	                                } else if (Security.AUTO_SCOPE_FILLS.equals(autoRolVO.getBusinessGroupScope())) {
	                                    grupsAutoritzacio = getCodiGrupsFillsGrup(rg.getDomainValue());
	                                } else if (Security.AUTO_SCOPE_BOTH.equals(autoRolVO.getBusinessGroupScope())) {
	                                    Collection pares = getCodiGrupsParesGrup(rg.getDomainValue());
	                                    Collection fills = getCodiGrupsFillsGrup(rg.getDomainValue());
	                                    grupsAutoritzacio = new HashSet();
	                                    grupsAutoritzacio.addAll(pares);
	                                    grupsAutoritzacio.addAll(fills);
	                                } else {
	                                    grupsAutoritzacio = new ArrayList();
	                                    grupsAutoritzacio.add(rg.getDomainValue());
	                                }
	                                for (Iterator git = grupsAutoritzacio.iterator(); git.hasNext(); ) {
	                                    String codiGrup = (String) git.next();
	    	                    		DomainValue dv2 = new DomainValue();
	    	                    		dv.setValue(codiGrup);
	    	                    		dv.setDomainName(tipusDomini);
	                                    autoRolVO.getUserRoleValueDomain().add(dv2);
	                                }
	                            }
	                        } else {
	                    		DomainValue dv = new DomainValue();
	                    		dv.setValue("*");
	                    		dv.setDomainName(TIPUS_DOMINI_ESTRELLETA);
	                            autoRolVO.getUserRoleValueDomain().add(dv);
	                        }
	                    }
	                    autoritzacionsUsuari.add(autoRolVO);
	                    addInheriedAuthorizations(autoritzacionsUsuari, autoRolVO, autoSEU);
	                }
                }
            }

        }
        // Now, remove authorizations disabled for this tenant
        
        Tenant tenant = getTenantService().getTenant(Security.getCurrentTenantName());
        
        HashSet<String> disabled = new HashSet<String> (getTenantService().getDisabledPermissions(tenant));
        
        for (Iterator<AuthorizationRole> it = autoritzacionsUsuari.iterator(); it.hasNext(); )
        {
        	AuthorizationRole auth = it.next();
        	String name = auth.getAuthorization();
        	int i = name.indexOf('/');
        	if (i > 0)
        		name = name.substring(0, i);
        	if (disabled.contains(name))
        		it.remove();
        }
        return autoritzacionsUsuari;

    }

	private void addInheriedAuthorizations(List<AuthorizationRole> autoritzacionsUsuari, AuthorizationRole autoRolVO, SoffidAuthorization autoSEU) {
		if (autoSEU != null
		        && autoSEU.getAutoritzacionsHereta() != null) {

		    // Obtenim les autoritzacions que hereta
		    Collection autosHereta = autoSEU
		            .getAutoritzacionsHereta();

		    // Mirem si hereta totes (autorització global)
		    // si és així, afegim TOTES les autoritzacions del xml
		    if (autosHereta.contains("*")) // afegim totes //$NON-NLS-1$
		        autosHereta = getAuthorizations().keySet();

		    for (Iterator ait = autosHereta.iterator(); ait.hasNext(); ) {
                String codiAutoHereta = (String) ait.next();
                SoffidAuthorization autoHereta = (SoffidAuthorization) getAuthorizations().get(codiAutoHereta);
                if (autoHereta == null) continue;
                String tipusDominiHereta = autoHereta.getTipusDomini();
                AuthorizationRole novaAutoHereta = new AuthorizationRole(null, autoHereta.getCodi(), autoRolVO.getRole(), autoRolVO.getUserRoleValueDomain() != null ? new ArrayList(autoRolVO.getUserRoleValueDomain()) : new ArrayList(), autoHereta.getDescripcio(), tipusDominiHereta, autoHereta.getScope(), autoHereta.getAmbit(), null);
                if (novaAutoHereta.getUserRoleValueDomain() != null) {
                    for (Iterator vit = novaAutoHereta.getUserRoleValueDomain().iterator(); vit.hasNext(); ) {
                        DomainValue vdc = (DomainValue) vit.next();
                        if (!TIPUS_DOMINI_ESTRELLETA.equals(vdc.getDomainName()) && (tipusDominiHereta != null && tipusDominiHereta.indexOf(vdc.getDomainName()) == -1)) {
                            vit.remove();
                        }
                    }
                }
                boolean found = isAuthorizationAlreadyPresent(autoritzacionsUsuari, novaAutoHereta);
                if (!found) {
                    autoritzacionsUsuari.add(novaAutoHereta);
                    addInheriedAuthorizations(autoritzacionsUsuari, novaAutoHereta, (SoffidAuthorization) getAuthorizations().get(novaAutoHereta.getAuthorization()));
                }
            }
		}
	}

	private boolean isAuthorizationAlreadyPresent(List<AuthorizationRole> autoritzacionsUsuari, AuthorizationRole novaAutoHereta) {
		boolean found = false;
		for (AuthorizationRole au : autoritzacionsUsuari) {
            if (au.getAuthorization().equals(novaAutoHereta.getAuthorization())) {
                if (au.getUserRoleValueDomain() == null || au.getUserRoleValueDomain().isEmpty()) {
                    found = true;
                    break;
                }
                boolean hasNewDomainValues = false;
                for (DomainValue vdNew : novaAutoHereta.getUserRoleValueDomain()) {
                    boolean isNewDomainValue = true;
                    for (DomainValue vdOld : au.getUserRoleValueDomain()) {
                        if (vdOld.getValue() == null ? vdNew.getValue() == null : vdOld.getValue().equals(vdNew.getValue()) ) {
                            isNewDomainValue = false;
                            break;
                        }
                    }
                    if (isNewDomainValue) {
                        hasNewDomainValues = true;
                        break;
                    }
                }
                if (!hasNewDomainValues) {
                    found = true;
                    break;
                }
            }
        }
		return found;
	}

    protected Collection<AuthorizationRole> handleGetUserAuthorizations(String codiUsuari) throws Exception {
        AutoritzacioCache autoritzacions = (AutoritzacioCache) getSessionCacheService().getObject(AUTORITZACIO_CACHE);
        if (autoritzacions != null && autoritzacions.isValid())
            return autoritzacions.getAutoritzacions();
        

        // Obtenim totes les autoritzacions (Entities)
        // amb els diferents rols que inclou
        List<AuthorizationEntity> autoritzacionsRol = getAuthorizationEntityDao().loadAll();

        // Si no es troba cap autorització: sortim
        if (autoritzacionsRol == null || autoritzacionsRol.size() == 0)
            return new ArrayList();

        // Pasem a VO

        List<AuthorizationRole> auts = getAutoritzacionsUsuari(getAuthorizationEntityDao().toAuthorizationRoleList(autoritzacionsRol), codiUsuari, null);
        autoritzacions = new AutoritzacioCache (auts);
        getSessionCacheService().putObject(AUTORITZACIO_CACHE, autoritzacions);
        return auts;

    }

    protected Collection<AuthorizationRole> handleGetUserAuthorization(String codiAutoritzacio, String codiUsuari) throws Exception {

        Collection<AuthorizationRole> autoritzacions = handleGetUserAuthorizations(codiUsuari);
        Collection<AuthorizationRole> effectivAutoritzacions = new LinkedList<AuthorizationRole>();
        
        for (AuthorizationRole a : autoritzacions) {
            if (a.getAuthorization().equals(codiAutoritzacio)) effectivAutoritzacions.add(a);
        }
        
        return effectivAutoritzacions;
    }

    protected String[] handleGetUserAuthorizationString(String codiAutoritzacio)
            throws Exception {
        return handleGetUserAuthorizationString(codiAutoritzacio,
                getCodiUsuari());
    }

    private String[] autoritzacionsToString(Collection autoritzacioRols) {

        if (autoritzacioRols != null && autoritzacioRols.size() != 0) {
            /*
             * Collection autoVD = new HashSet(); // perquè no es repetixquen
             * for (Iterator it = autoritzacioRols.iterator(); it.hasNext();) {
             * AutoritzacioRol autoVO = (AutoritzacioRol) it.next();
             * 
             * // Afegim l'autorització GENÈRICA (sense valor domini):
             * autoVD.add(autoVO.getAutoritzacio());
             * 
             * // Afegim els diferents VALORS DE DOMINI de l'autorització
             * Collection vdom = autoVO.getValorDominiRolUsuari(); if (vdom !=
             * null && vdom.size() != 0) { for (Iterator vit = vdom.iterator();
             * vit.hasNext();) { ValorDomini vd = (ValorDomini) vit.next();
             * autoVD.add(autoVO.getAutoritzacio() + "/" + vd.getValor()); } } }
             * return (String[]) autoVD.toArray(new String[0]);
             */
            // Fem que si té domini ESTRELLETA, englobe els altres
            // valors de domini
            // La seva key serà el codi d'autoritzacio
            // amb HashSet de valor de domini
            HashMap autoVD = new HashMap();
            // aquí posarem les autoritzacions finals:
            ArrayList autoritzacionsFiltrades = new ArrayList();

            for (Iterator it = autoritzacioRols.iterator(); it.hasNext(); ) {
                AuthorizationRole autoVO = (AuthorizationRole) it.next();
                String codiAutoritzacio = autoVO.getAuthorization();
                HashSet dominis = (HashSet) autoVD.get(codiAutoritzacio);
                if (dominis == null) dominis = new HashSet();
                dominis.add(autoVO.getAuthorization());
                Collection vdom = autoVO.getUserRoleValueDomain();
                if (vdom != null && vdom.size() != 0) {
                    for (Iterator vit = vdom.iterator(); vit.hasNext(); ) {
                        DomainValue vd = (DomainValue) vit.next();
                        dominis.add(autoVO.getAuthorization() + "/" + vd.getValue());
                    }
                }
                autoVD.put(codiAutoritzacio, dominis);
            }

            // Ara hem de netejar els dominis: si tenim ESTRELLETA
            // ens quedem només en aquest domini i el genèric
            for (Iterator it = autoVD.keySet().iterator(); it.hasNext();) {
                String autoActual = (String) it.next();
                HashSet dominisAct = (HashSet) autoVD.get(autoActual);
                if (dominisAct.contains(autoActual + "/*")) {// VALOR DOMINI //$NON-NLS-1$
                                                             // ESTRELLETA
                    autoritzacionsFiltrades.add(autoActual); // generic
                    autoritzacionsFiltrades.add(autoActual + "/*"); // valor //$NON-NLS-1$
                                                                    // ESTRELLETA
                } else {
                    // Els afegim tots
                    autoritzacionsFiltrades.addAll(dominisAct);
                }
            }

            // Haurem de recòrrer
            return (String[]) autoritzacionsFiltrades.toArray(new String[0]);

        } else
            return new String[0];
    }

    protected String[] handleGetUserAuthorizationString(
            String codiAutoritzacio, String codiUsuari) throws Exception {

        Collection rolsAutoritzacio = handleGetUserAuthorization(
                codiAutoritzacio, codiUsuari);

        return autoritzacionsToString(rolsAutoritzacio);
    }

    protected Collection handleGetUserAuthorizations() throws Exception {
        return handleGetUserAuthorizations(getCodiUsuari());
    }

    protected Collection handleGetUserAuthorization(String codiAutoritzacio)
            throws Exception {
        return handleGetUserAuthorization(codiAutoritzacio, getCodiUsuari());
    }

    protected String[] handleGetUserAuthorizationsString() throws Exception {
        return handleGetUserAuthorizationsString(getCodiUsuari());
    }

    protected String[] handleGetUserAuthorizationsString(String codiUsuari)
            throws Exception {
        Collection autosUsu = handleGetUserAuthorizations(codiUsuari);
        return autoritzacionsToString(autosUsu);
    }

    /**
     * Obtenim el codi de l'usuari actual de l'aplicació
     * 
     * @return
     */
    private String getCodiUsuari() {
        return Security.getCurrentUser();
    }

    protected Collection<AuthorizationRole> handleGetDescriptionUserAuthorizations() throws Exception {
        return this.getDescriptionUserAuthorizations(getCodiUsuari());
    }

    protected Collection<AuthorizationRole> handleGetDescriptionUserAuthorizations(String codiUsuari) throws Exception {

        Collection autoritzacionsRolUsuari = handleGetUserAuthorizations(codiUsuari);

        // IMPORTANT: la seva clau es el nom del rol + codiAutoritzacio +
        // descripció [valor_domini]
        HashMap<String, AuthorizationRole> autoritzacionsSenseRepeticions = new HashMap();

        // Afegim informació addicional:
        if (autoritzacionsRolUsuari != null) {
            for (Iterator it = autoritzacionsRolUsuari.iterator(); it.hasNext(); ) {
                AuthorizationRole auto = (AuthorizationRole) it.next();
                SoffidAuthorization autoSEU = (SoffidAuthorization) getAuthorizations().get(auto.getAuthorization());
                if (autoSEU != null) {
                    String valorDominiUsuari = "";
                    if (auto.getUserRoleValueDomain() != null && auto.getUserRoleValueDomain().size() > 0) {
                        HashSet valors = new HashSet();
                        for (Iterator vit = auto.getUserRoleValueDomain().iterator(); vit.hasNext(); ) {
                            DomainValue vd = (DomainValue) vit.next();
                            valors.add(vd.getValue());
                        }
                        if (valors.size() == 1 && valors.contains("*")) ; else valorDominiUsuari = " " + valors.toString();
                    }
                    auto.setDescription(autoSEU.getDescripcio() + valorDominiUsuari);
                    auto.setDomainType(autoSEU.getTipusDomini());
                    auto.setBusinessGroupScope(autoSEU.getScope());
                    auto.setScope(autoSEU.getAmbit());
                    auto.setInherit(autoSEU.getHereta());
                    autoritzacionsSenseRepeticions.put(auto.getRole().getName() + auto.getAuthorization() + auto.getDescription(), auto);
                }
            }
            // Les ordenem
            LinkedList autosOrdenades = new LinkedList(
                    autoritzacionsSenseRepeticions.values());
            Collections.sort(autosOrdenades, new ComparaAutos());
            return autosOrdenades;
        }

        return autoritzacionsSenseRepeticions.values();
    }

    private class ComparaAutos implements Comparator {

        public int compare(Object o1, Object o2) {
            if (o1 instanceof AuthorizationRole && o2 instanceof AuthorizationRole) {
                AuthorizationRole a1 = (AuthorizationRole) o1;
                AuthorizationRole a2 = (AuthorizationRole) o2;
                if (!a1.getScope().equals(a2.getScope()))
                    return a1.getScope().compareTo(a2.getScope());
                else if (!a1.getDescription().equals(a2.getDescription()))
                    return a1.getDescription().compareTo(a2.getDescription());
            }
            else if(o1 instanceof SoffidAuthorization && o2 instanceof SoffidAuthorization) {
                SoffidAuthorization a1 = (SoffidAuthorization) o1;
                SoffidAuthorization a2 = (SoffidAuthorization) o2;
                if (!a1.getAmbit().equals(a2.getAmbit()))
                    return a1.getAmbit().compareTo(a2.getAmbit());
                else if (!a1.getDescripcio().equals(a2.getDescripcio()))
                    return a1.getDescripcio().compareTo(a2.getDescripcio());
            }
            return 0;
        }

    }

    @Override
    protected Collection<Object> handleGetAuthorizationInfo(String autoritzacio) throws Exception {

        Object obj = getAuthorizations().get(autoritzacio);
        LinkedList<Object> res = new LinkedList();

        if (obj != null && obj instanceof SoffidAuthorization) {
            SoffidAuthorization auto = (SoffidAuthorization) obj;
            res.add(auto);
            return res;
        }
        // No l'hem trobada: tornem una buida (no hauria d'ocorrer)
        res.add(new SoffidAuthorization(autoritzacio, autoritzacio, "", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return res;
    }

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AutoritzacioServiceBase#handleFindAuthorizations(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected Collection<SoffidAuthorization> handleFindAuthorizations (String ambit,
					String descripcio, String codi)
					throws Exception
	{
		descripcio = removeWildcard(descripcio);
		codi = removeWildcard(codi);
		
		if (ambit != null && ambit.trim().compareTo("") == 0) //$NON-NLS-1$ 
		{ 
            ambit = null;
        }
		if ( descripcio != null && descripcio.trim().compareTo("") == 0) //$NON-NLS-1$ 
		{ 
            descripcio = null;
        }
		if (codi != null && codi.trim().compareTo("") == 0) //$NON-NLS-1$ 
		{ 
            codi = null;
        }
			
		Collection<SoffidAuthorization> allAuthorizations = getAuthorizations().values();
		List<SoffidAuthorization> authorizations = new LinkedList<SoffidAuthorization>();
				
		if(ambit == null && descripcio == null && codi == null)
		{
			authorizations = sort(allAuthorizations);
			return authorizations;
		}
		else
		{
    		List<SoffidAuthorization> ambitList = findAmbitAuthorizations(allAuthorizations, ambit);
    		List<SoffidAuthorization> descripcioList = findDescripcioAuthorizations(allAuthorizations, descripcio);
    		List<SoffidAuthorization> codiList = findCodiAuthorizations(allAuthorizations, codi);
    		
    		if((ambitList != null && !ambitList.isEmpty()) || (descripcioList != null && !descripcioList.isEmpty()))
    			authorizations = compareLists(ambitList, descripcioList);
    		if((authorizations != null && !authorizations.isEmpty()) || (codiList != null && !codiList.isEmpty()))
    			authorizations = compareLists(authorizations, codiList);
    		 
    		Collections.sort(authorizations, new ComparaAutos()); 
    		return authorizations;
		}
	}

	/**
	 * @param allAuthorizations
	 * @return
	 */
	private List<SoffidAuthorization> sort (
					Collection<SoffidAuthorization> allAuthorizations)
	{
		List<SoffidAuthorization> list = new LinkedList<SoffidAuthorization>();
		for(SoffidAuthorization auto: allAuthorizations){
			list.add(auto);
		}
		
		Collections.sort(list, new ComparaAutos());
		return list;
	}

	/**
	 * @param ambit
	 * @return
	 */
	private String removeWildcard (String ambit)
	{
		if(ambit != null && (ambit.startsWith("%") || ambit.startsWith("*") || ambit.endsWith("%")  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						|| ambit.endsWith("*"))) //$NON-NLS-1$
		{
			ambit = ambit.replace('%', ' ');
			ambit = ambit.replace('*', ' ');
		}
		if(ambit != null)
			ambit = ambit.trim();
		return ambit;
	}

	
	/**
	 * @param allAuthorizations
	 * @param ambit
	 * @return
	 */
	private List<SoffidAuthorization> findCodiAuthorizations (
					Collection<SoffidAuthorization> allAuthorizations, String codi)
	{
		List<SoffidAuthorization> list = new LinkedList<SoffidAuthorization>();
		
		if(codi != null)
		{
    		for(SoffidAuthorization auto: allAuthorizations)
    		{
    			String autoCodi = auto.getCodi();
    			if(autoCodi != null && autoCodi.contains(codi))
    			{
    				list.add(auto);
    			}
    		}
		}
		return list;
	}

	/**
	 * @param allAuthorizations
	 * @param ambit
	 * @return
	 */
	private List<SoffidAuthorization> findDescripcioAuthorizations (
					Collection<SoffidAuthorization> allAuthorizations, String descripcio)
	{
		List<SoffidAuthorization> list = new LinkedList<SoffidAuthorization>();
		
		if(descripcio != null)
		{
    		for(SoffidAuthorization auto: allAuthorizations)
    		{
    			String autoDesc = auto.getDescripcio();
    			if(autoDesc != null && autoDesc.contains(descripcio))
    			{
    				list.add(auto);
    			}
    		}
		}
		return list;
	}

	/**
	 * @param allAuthorizations
	 * @param ambit
	 * @return
	 */
	private List<SoffidAuthorization> findAmbitAuthorizations (
					Collection<SoffidAuthorization> allAuthorizations, String ambit)
	{
		List<SoffidAuthorization> list = new LinkedList<SoffidAuthorization>();
		
		if(ambit != null)
		{
    		for(SoffidAuthorization auto: allAuthorizations)
    		{
    			String autoAmbit = auto.getAmbit();
    			if(autoAmbit.equals(ambit))
    			{
    				list.add(auto);
    			}
    		}
		}
		return list;
	}
	
	private List<SoffidAuthorization> compareLists(List<SoffidAuthorization> first, List<SoffidAuthorization> second){
		List<SoffidAuthorization> endList = new LinkedList<SoffidAuthorization>();
		if(first != null && second != null && !first.isEmpty() && !second.isEmpty()){
			for(SoffidAuthorization auto: first){
				if(second.contains(auto)){
					endList.add(auto);
				}
			}
			return endList;
		}
		else if((first == null || first.isEmpty()) && second != null)
			return second;
		else 
			return first;
	}
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AutoritzacioServiceBase#handleGetScopeList()
	 */
	@Override
	protected List<String> handleGetScopeList () throws Exception
	{
		Set<String> set = new HashSet<String>();
		Collection<SoffidAuthorization> allAuthorizations = getAuthorizations().values();
		for(SoffidAuthorization auto: allAuthorizations){
			set.add(auto.getAmbit());
		}
		List<String> list = new LinkedList<String>();
		for(String ambit: set){
			list.add(ambit);
		}
		
		Collections.sort(list);
		list.add(0, " "); //$NON-NLS-1$
		
		return list;
	}

	@Override
	protected String[] handleGetUserAuthorizationsString(String user,
			Map<String, String> loginProperties) throws Exception {
		return handleGetUserAuthorizationsString(user);
	}

	@Override
	protected boolean handleHasPermission(String action, Object object)
			throws Exception {
		if (object != null && object instanceof SecurityScopeEntity)
			return ((SecurityScopeEntity)object).isAllowed(action);
			
		if (Security.isUserInRole(action+Security.AUTO_ALL))
			return true;
		
		return false;
	}


	protected Collection handleGetUserGroupAuthorizations(String user, String holderGroup) throws Exception {
        // Obtenim totes les autoritzacions (Entities)
        // amb els diferents rols que inclou
        List<AuthorizationEntity> autoritzacionsRol = getAuthorizationEntityDao().loadAll();

        // Si no es troba cap autorització: sortim
        if (autoritzacionsRol == null || autoritzacionsRol.size() == 0)
            return new ArrayList();

        // Pasem a VO

        List<AuthorizationRole> auts = getAutoritzacionsUsuari(getAuthorizationEntityDao().toAuthorizationRoleList(autoritzacionsRol), user, holderGroup);
        return auts;
	}

	protected String[] handleGetUserGroupAuthorizationString(String user, String holderGroup) throws Exception {
        Collection c = handleGetUserGroupAuthorizations(user, holderGroup);
        return autoritzacionsToString(c);
	}

}

class AutoritzacioCache {
    private Collection<AuthorizationRole> autoritzacions;
    Date expirationDate;

    public AutoritzacioCache(Collection<AuthorizationRole> autoritzacions) {
        expirationDate = new Date(System.currentTimeMillis() + 600000); // 10 mins cache
        this.autoritzacions =  autoritzacions;
    }

    public boolean isValid() {
        return expirationDate.after(new Date());
    }

    public Collection<AuthorizationRole> getAutoritzacions() {
        return autoritzacions;
    }

    public void setAutoritzacions(Collection<AuthorizationRole> autoritzacions) {
        this.autoritzacions = autoritzacions;
    }

    
}
