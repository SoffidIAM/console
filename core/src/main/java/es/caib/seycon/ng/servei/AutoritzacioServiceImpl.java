// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

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

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AutoritzacioRolEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.utils.AutoritzacioSEU;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.util.TipusDomini;

/**
 * @see es.caib.seycon.ng.servei.AutoritzacioService
 */
public class AutoritzacioServiceImpl extends
        es.caib.seycon.ng.servei.AutoritzacioServiceBase {
    
    /**
	 * 
	 */
	private static final String AUTORITZACIO_CACHE = "AutoritzacioCache"; //$NON-NLS-1$

	@SuppressWarnings("unchecked")
    public AutoritzacioServiceImpl () {
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
            	Enumeration<URL> urls = AutoritzacioService.class.getClassLoader().getResources("es/caib/seycon/autoritzacions.xml"); //$NON-NLS-1$
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
                            AutoritzacioSEU auto = new AutoritzacioSEU(
                                    (Element) nodeAutoritzacio);
                            // Si no té codi, l'ignorem
                            if (auto.getCodi() != null) {
                                xmlAutoritzacions.put(auto.getCodi(), auto);
                            }
                        }
        
                    }
                    is.close();
            	}
    //            System.out.println("Carregades autoritzacions XML");
            	xmlAuthorizations = xmlAutoritzacions;
            } catch (Throwable e) {
                System.err
                        .println(Messages.getString("AutoritzacioServiceImpl.ErrorObtainingAuthorizations") //$NON-NLS-1$
                                + e.getMessage());
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

    protected AutoritzacioRol handleCreate(AutoritzacioRol autoritzacio)
            throws Exception {
        AutoritzacioRolEntity auto = getAutoritzacioRolEntityDao()
                .autoritzacioRolToEntity(autoritzacio);
        getAutoritzacioRolEntityDao().create(auto);

        return getAutoritzacioRolEntityDao().toAutoritzacioRol(auto);
    }

    protected void handleDelete(AutoritzacioRol autoritzacio) throws Exception {
        // si ya tiene id, la borramos
        if (autoritzacio.getId() != null) {
            getAutoritzacioRolEntityDao().remove(autoritzacio.getId());
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
    protected Collection<AutoritzacioRol> handleGetRolsAutoritzacio(String autoritzacio)
            throws Exception {
        if (autoritzacio != null) {
            List<AutoritzacioRolEntity> autoritzacions = getAutoritzacioRolEntityDao()
                    .findByAutoritzacio(autoritzacio);
            if (autoritzacions != null && autoritzacions.size() != 0) {
                return getAutoritzacioRolEntityDao().toAutoritzacioRolList(
                        autoritzacions);
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
                Collection fills = getGrupEntityDao().findSubGrupsByCodi(
                        codiGrupAnalitzat);
                if (fills != null)
                    for (Iterator git = fills.iterator(); git.hasNext();) {
                        GrupEntity fg = (GrupEntity) git.next();
                        if (!grupsFills.contains(fg.getCodi())) // si no és ja
                                                                // analitzat
                            l_grupsUsuari.add(fg.getCodi());
                    }
            }
        }

        return grupsFills;
    }

    private Collection getCodiGrupsParesGrup(String codiGrup) {

        GrupEntity grupAnalitzar = getGrupEntityDao().findByCodi(codiGrup);

        Collection totsPares = new HashSet();
        GrupEntity pare = grupAnalitzar.getPare();
        while (pare != null) {
            totsPares.add(pare.getCodi());
            pare = pare.getPare();
        }

        return totsPares;
    }

    Log log = LogFactory.getLog(getClass());
    
    private List<AutoritzacioRol> getAutoritzacionsUsuari(Collection autoritzacionsRolVO,
            String codiUsuari) throws InternalErrorException {
    	
        List<AutoritzacioRol> autoritzacionsUsuari = new LinkedList<AutoritzacioRol>();

        // ROLS de l'usuari actual (heredats -senseDomini i atorgats -ambDomini)
        HashMap rolsUsuariSenseDomini = new HashMap(); // id_rol => rolEntity
        // id_rol => llistat de rolsusuariEntity (poden ser N)
        // Atorgació directa (RolsUsuarisEntity):amb domini
        AplicacioService appSvc = getAplicacioService();
        PasswordService passSvc = getPasswordService();
        AccountService acctSvc = getAccountService();
        
        Account account = acctSvc.findAccount(codiUsuari, passSvc.getDefaultDispatcher());
        
        if (account == null)
        	return new Vector<AutoritzacioRol>();
        
        Collection<RolGrant> grants;
        if (account instanceof UserAccount)
        {
        	Usuari usuari = getUsuariService().findUsuariByCodiUsuari(((UserAccount)account).getUser());
        	grants = appSvc.findEffectiveRolGrantByUser(usuari.getId());
        }
        else
        	grants = appSvc.findEffectiveRolGrantByAccount(account.getId());
        
        HashMap<Long,List<RolGrant>> rols = new HashMap<Long, List<RolGrant>>();
        // explorem rols de l'usuari (poden tindre domini)
        for (RolGrant rg: grants)
        {
        	Long rolId = rg.getIdRol();
        	if (! rols.containsKey(rolId))
        	{
        		LinkedList<RolGrant> grantList = new LinkedList<RolGrant>();
        		grantList.add(rg);
        		rols.put(rolId, grantList);
        	}
        	else
        	{
        		rols.get(rolId).add(rg);
        	}
        }

        // guardem les autoritzacions (per id)
        if (autoritzacionsRolVO != null && autoritzacionsRolVO.size() != 0) {
            for (Iterator it = autoritzacionsRolVO.iterator(); it.hasNext();) {
                // Una fila per cada rol que té atorgat aquesta autorització
                AutoritzacioRol autoRolVO = (AutoritzacioRol) it.next();
                AutoritzacioSEU autoSEU = (AutoritzacioSEU) getAuthorizations()
                        .get(autoRolVO.getAutoritzacio());
                
                // pot ésser que aquesta auto no existisca en el XML (antigues)
                // scope dels grups
                String scope = autoSEU != null ? autoSEU.getScope() : null;
                // si no té scope: ONE per defecte
                autoRolVO.setScope(scope != null ? scope
                        : Security.AUTO_SCOPE_ONE);
                if (autoRolVO.getValorDominiRolUsuari() == null) {
                    // si l'autorització no en té cap vdom creem el
                    // contenidor perquè no es  repetixquen
					autoRolVO.setValorDominiRolUsuari(new HashSet()); 
                }
                Long idRol = autoRolVO.getRol().getId();
                if (rols.containsKey(idRol)) {
                	for (RolGrant rg: rols.get(idRol))
                	{
                    	String tipusDomini = null;
                   		RolEntity role = getRolEntityDao().load(idRol);
                   		if (role != null)
                    		tipusDomini = role.getTipusDomini();
                   		
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
                            autoRolVO.getValorDominiRolUsuari().add(
                            	new ValorDomini(rg.getDomainValue(),tipusDomini));
                            
                            if (TipusDomini.GRUPS.equals(tipusDomini) ||
                            	TipusDomini.GRUPS_USUARI.equals(tipusDomini))
                            {
                                // Obtenim els grups segons el scope de
                                // l'autorització:
                                Collection grupsAutoritzacio = null;
                                if (Security.AUTO_SCOPE_PARES
                                        .equals(autoRolVO.getScope())) {
                                    // Obtenim el codi dels grups pare
                                    grupsAutoritzacio = getCodiGrupsParesGrup(rg.getDomainValue());

                                } else if (Security.AUTO_SCOPE_FILLS
                                        .equals(autoRolVO.getScope())) {
                                    // Obtenim el codi dels grups fills
                                    grupsAutoritzacio = getCodiGrupsFillsGrup(rg.getDomainValue());
                                } else if (Security.AUTO_SCOPE_BOTH
                                        .equals(autoRolVO.getScope())) {
                                    // OBtenim els pares i els fills del
                                    // grup
                                    Collection pares = getCodiGrupsParesGrup(rg.getDomainValue());
                                    Collection fills = getCodiGrupsFillsGrup(rg.getDomainValue());
                                    grupsAutoritzacio = new HashSet();
                                    grupsAutoritzacio.addAll(pares);
                                    grupsAutoritzacio.addAll(fills);
                                } else { // Per defecte (només el grup)
                                    grupsAutoritzacio = new ArrayList();
                                    grupsAutoritzacio.add(rg.getDomainValue());
                                }

                                // Afegim un valor de domini per cada
                                // grup on corresponga l'autorització
                                for (Iterator git = grupsAutoritzacio.iterator(); git.hasNext();) 
                                {
                                    String codiGrup = (String) git.next();
                                    autoRolVO.getValorDominiRolUsuari()
                                            .add(new ValorDomini(
                                                    codiGrup,
                                                    tipusDomini));
                                }

                            }
                            
                		} else {
                            ValorDomini estrelleta = new ValorDomini(
                                            "*", TIPUS_DOMINI_ESTRELLETA); //$NON-NLS-1$
                                    autoRolVO.getValorDominiRolUsuari().add(
                                            estrelleta);
                		}
                		
                	}

                    // L'usuari té el rol (amb o sense domini, l'afegim a la
                    // llista d'autoritzacio-rol)
                    // VO amb els valors de domini i el rol
                    autoritzacionsUsuari.add(autoRolVO);

                    // Analitzem l'HERÈNCIA de d'altres autoritzacions i les
                    // afegim:
                    // NOTA: l'herència NO és transitiva (només primer nivell)
                    // [IMPORTANT]
                    // Només hem de copiar els valors de domini del mateix tipus
                    // de DOMINI

                    addInheriedAuthorizations(autoritzacionsUsuari, autoRolVO, autoSEU);
                }
            }

        }
        
//        log.info("******************* AUTHORIZATIONS: ");
//        for ( AutoritzacioRol au: autoritzacionsUsuari)
//        {
//        	log.info (" "+au.toString());
//        }
        return autoritzacionsUsuari;

    }

	private void addInheriedAuthorizations (List<AutoritzacioRol> autoritzacionsUsuari,
					AutoritzacioRol autoRolVO, AutoritzacioSEU autoSEU)
	{
		if (autoSEU != null
		        && autoSEU.getAutoritzacionsHereta() != null) {

		    // Obtenim les autoritzacions que hereta
		    Collection autosHereta = autoSEU
		            .getAutoritzacionsHereta();

		    // Mirem si hereta totes (autorització global)
		    // si és així, afegim TOTES les autoritzacions del xml
		    if (autosHereta.contains("*")) // afegim totes //$NON-NLS-1$
		        autosHereta = getAuthorizations().keySet();

		    for (Iterator ait = autosHereta.iterator(); ait
		            .hasNext();) {
		        String codiAutoHereta = (String) ait.next();
		        // Obtenim les autoritzacions que hereta
		        AutoritzacioSEU autoHereta = (AutoritzacioSEU) getAuthorizations()
		                .get(codiAutoHereta);

		        // Pot ésser que no existisca aquesta autorització
		        if (autoHereta == null)
		            continue;
		        // Obtenim els tipus de domini de l'autorització que
		        // hereta
		        String tipusDominiHereta = autoHereta
		                .getTipusDomini(); // pot ésser nul
		        // clonem l'autorització actual (per mantindre els
		        // dominis)
		        // AutoritzacioRol novaAutoHereta = new
		        // AutoritzacioRol(autoRolVO);
		        AutoritzacioRol novaAutoHereta = new AutoritzacioRol(
		                null/* id */,
		                autoHereta.getCodi(),
		                autoRolVO.getRol(),
		                autoRolVO.getValorDominiRolUsuari() != null ? new ArrayList(
		                        autoRolVO.getValorDominiRolUsuari())
		                        : new ArrayList() /* el clonem */,
		                autoHereta.getDescripcio(),
		                tipusDominiHereta, autoHereta.getScope(),
		                autoHereta.getAmbit(), null);
		        // Posem el codi d'autorització corresponent
		        // novaAutoHereta.setAutoritzacio(autoHereta.getCodi());
		        // I la resta de valors
		        // I revisem els seus valors de domini atorgats (si
		        // són compatibles)
		        // Si l'autorització té el tipusDomini a null:
		        // SENSE_DOMINI_AUTO (valor domini estrelleta)
		        // NO heretem cap valor de domini d'altres
		        // autoritzacions
		        /*
		         * if (tipusDominiHereta==null) {
		         * novaAutoHereta.setValorDominiRolUsuari(new
		         * ArrayList()); //buida } else //{
		         */
		        
				// llevem les que no ens corresponen
		        if (novaAutoHereta.getValorDominiRolUsuari() != null) {
		            for (Iterator vit = novaAutoHereta
		                    .getValorDominiRolUsuari().iterator(); vit
		                    .hasNext();) {
		                ValorDomini vdc = (ValorDomini) vit.next();

		                // TODO: Falta analitzar si l'autorització
		                // que heretem és de tipus grups
		                // i té un scope diferent al de
		                // l'autorització que estem heretant.

		                // Si es estrelleta [AUTORITZACIÓ
		                // SENSE_DOMINI_AUTO] o si
		                // és de domini COMPATIBLE (mateix tipus) el
		                // mantenim
		                // si és null el que hereta, només heretem
		                // estrelleta ???
		                // TODO: Comprovar l'herència a dominis de
		                // rols SENSE_DOMINI
		                
		                // This removes, for example, a application domain value from the role assignment when the authorization needs a group domain value.
		                if (!TIPUS_DOMINI_ESTRELLETA.equals(vdc
		                        .getNomDomini())
		                        && (tipusDominiHereta != null && tipusDominiHereta
		                                .indexOf(vdc.getNomDomini()) == -1)) {
		                    vit.remove();
		                }
		            }
		        }
		        boolean found = isAuthorizationAlreadyPresent(autoritzacionsUsuari,
								novaAutoHereta);
		        if (! found)
		        {
		        	autoritzacionsUsuari.add(novaAutoHereta);
		        	addInheriedAuthorizations(autoritzacionsUsuari, novaAutoHereta, (AutoritzacioSEU) getAuthorizations().get(novaAutoHereta.getAutoritzacio()));
		        }
		    }
		}
	}

	private boolean isAuthorizationAlreadyPresent (
					List<AutoritzacioRol> autoritzacionsUsuari,
					AutoritzacioRol novaAutoHereta)
	{
		boolean found = false;
		for (AutoritzacioRol au: autoritzacionsUsuari)
		{
			if (au.getAutoritzacio().equals(novaAutoHereta.getAutoritzacio()))
			{
				if (au.getValorDominiRolUsuari() == null || au.getValorDominiRolUsuari().isEmpty())
				{
					found = true;
					break;
				}
				boolean hasNewDomainValues = false;
				for (ValorDomini vdNew: novaAutoHereta.getValorDominiRolUsuari())
				{
					boolean isNewDomainValue =  true;
					for (ValorDomini vdOld: au.getValorDominiRolUsuari())
					{
						if (vdOld.getValor().equals(vdNew.getValor()))
						{
							isNewDomainValue = false;
							break;
						}
					}
					if (isNewDomainValue)
					{
						hasNewDomainValues = true;
						break;
					}
				}
				if (! hasNewDomainValues)
				{
					found = true; 
					break;
				}
			}
		}
		return found;
	}

    protected Collection<AutoritzacioRol>  handleGetUserAuthorizations(String codiUsuari)
            throws Exception {
        AutoritzacioCache autoritzacions = (AutoritzacioCache) getSessionCacheService().getObject(AUTORITZACIO_CACHE);
        if (autoritzacions != null && autoritzacions.isValid())
            return autoritzacions.getAutoritzacions();
        

        // Obtenim totes les autoritzacions (Entities)
        // amb els diferents rols que inclou
        List<AutoritzacioRolEntity> autoritzacionsRol = getAutoritzacioRolEntityDao().loadAll();

        // Si no es troba cap autorització: sortim
        if (autoritzacionsRol == null || autoritzacionsRol.size() == 0)
            return new ArrayList();

        // Pasem a VO

        List<AutoritzacioRol> auts = getAutoritzacionsUsuari(getAutoritzacioRolEntityDao()
                .toAutoritzacioRolList(autoritzacionsRol), codiUsuari);
        autoritzacions = new AutoritzacioCache (auts);
        getSessionCacheService().putObject(AUTORITZACIO_CACHE, autoritzacions);
        return auts;

    }

    protected Collection<AutoritzacioRol>  handleGetUserAuthorization(String codiAutoritzacio,
            String codiUsuari) throws Exception {

        Collection<AutoritzacioRol> autoritzacions = handleGetUserAuthorizations(codiUsuari);
        Collection<AutoritzacioRol> effectivAutoritzacions = new LinkedList<AutoritzacioRol>();
        
        for (AutoritzacioRol a: autoritzacions) {
            if (a.getAutoritzacio().equals(codiAutoritzacio))
                effectivAutoritzacions.add(a);
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

            for (Iterator it = autoritzacioRols.iterator(); it.hasNext();) {
                AutoritzacioRol autoVO = (AutoritzacioRol) it.next();

                // Key del HashMap
                String codiAutoritzacio = autoVO.getAutoritzacio();

                // key: codi_autoritzacio, values: dominis

                // obtenim els dominis d'aquesta autorització
                HashSet dominis = (HashSet) autoVD.get(codiAutoritzacio);

                // si és nova, li creem el set de dominis
                if (dominis == null)
                    dominis = new HashSet();

                // Afegim l'autorització GENÈRICA (sense valor domini):
                dominis.add(autoVO.getAutoritzacio());

                // Afegim els diferents VALORS DE DOMINI de l'autorització
                Collection vdom = autoVO.getValorDominiRolUsuari();
                if (vdom != null && vdom.size() != 0) {
                    for (Iterator vit = vdom.iterator(); vit.hasNext();) {
                        ValorDomini vd = (ValorDomini) vit.next();
                        dominis.add(autoVO.getAutoritzacio() + "/" //$NON-NLS-1$
                                + vd.getValor());
                    }
                }

                // Guardem els dominis actualitzats de l'autorització:
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
        return Security.getCurrentAccount();
    }

    protected Collection<AutoritzacioRol> handleGetDescriptionUserAuthorizations()
            throws Exception {
        return this.getDescriptionUserAuthorizations(getCodiUsuari());
    }

    protected Collection<AutoritzacioRol> handleGetDescriptionUserAuthorizations(
            String codiUsuari) throws Exception {

        Collection autoritzacionsRolUsuari = handleGetUserAuthorizations(codiUsuari);

        // IMPORTANT: la seva clau es el nom del rol + codiAutoritzacio +
        // descripció [valor_domini]
        HashMap<String,AutoritzacioRol> autoritzacionsSenseRepeticions = new HashMap();

        // Afegim informació addicional:
        if (autoritzacionsRolUsuari != null) {
            for (Iterator it = autoritzacionsRolUsuari.iterator(); it.hasNext();) {
                AutoritzacioRol auto = (AutoritzacioRol) it.next();
                AutoritzacioSEU autoSEU = (AutoritzacioSEU) getAuthorizations()
                        .get(auto.getAutoritzacio());
                if (autoSEU != null) {
                    // formategem els valor de domini
                    String valorDominiUsuari = ""; //$NON-NLS-1$
                    if (auto.getValorDominiRolUsuari() != null
                            && auto.getValorDominiRolUsuari().size() > 0) {
                        HashSet valors = new HashSet();
                        for (Iterator vit = auto.getValorDominiRolUsuari()
                                .iterator(); vit.hasNext();) {
                            ValorDomini vd = (ValorDomini) vit.next();
                            valors.add(vd.getValor());
                        }
                        if (valors.size() == 1 && valors.contains("*")) //$NON-NLS-1$
                            ;
                        else
                            valorDominiUsuari = " " + valors.toString(); //$NON-NLS-1$
                    }

                    auto.setDescripcio(autoSEU.getDescripcio() //$NON-NLS-1$
                            + valorDominiUsuari);
                    auto.setTipusDomini(autoSEU.getTipusDomini());
                    auto.setScope(autoSEU.getScope());
                    auto.setAmbit(autoSEU.getAmbit());
                    auto.setHereta(autoSEU.getHereta()); // separat per comes
                    autoritzacionsSenseRepeticions.put(auto.getRol().getNom()
                            + auto.getAutoritzacio() + auto.getDescripcio(),
                            auto);
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
            if (o1 instanceof AutoritzacioRol && o2 instanceof AutoritzacioRol) {
                AutoritzacioRol a1 = (AutoritzacioRol) o1;
                AutoritzacioRol a2 = (AutoritzacioRol) o2;
                if (!a1.getAmbit().equals(a2.getAmbit()))
                    return a1.getAmbit().compareTo(a2.getAmbit());
                else if (!a1.getDescripcio().equals(a2.getDescripcio()))
                    return a1.getDescripcio().compareTo(a2.getDescripcio());
            }
            else if(o1 instanceof AutoritzacioSEU && o2 instanceof AutoritzacioSEU) {
                AutoritzacioSEU a1 = (AutoritzacioSEU) o1;
                AutoritzacioSEU a2 = (AutoritzacioSEU) o2;
                if (!a1.getAmbit().equals(a2.getAmbit()))
                    return a1.getAmbit().compareTo(a2.getAmbit());
                else if (!a1.getDescripcio().equals(a2.getDescripcio()))
                    return a1.getDescripcio().compareTo(a2.getDescripcio());
            }
            return 0;
        }

    }

    @Override
    protected Collection<Object> handleGetInformacioAutoritzacio(String autoritzacio)
            throws Exception {

        Object obj = getAuthorizations().get(autoritzacio);
        LinkedList<Object> res = new LinkedList();

        if (obj != null && obj instanceof AutoritzacioSEU) {
            AutoritzacioSEU auto = (AutoritzacioSEU) obj;
            res.add(auto);
            return res;
        }
        // No l'hem trobada: tornem una buida (no hauria d'ocorrer)
        res.add(new AutoritzacioSEU(autoritzacio, autoritzacio, "", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return res;
    }

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AutoritzacioServiceBase#handleFindAuthorizations(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected Collection<AutoritzacioSEU> handleFindAuthorizations (String ambit,
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
			
		Collection<AutoritzacioSEU> allAuthorizations = getAuthorizations().values();
		List<AutoritzacioSEU> authorizations = new LinkedList<AutoritzacioSEU>();
				
		if(ambit == null && descripcio == null && codi == null)
		{
			authorizations = sort(allAuthorizations);
			return authorizations;
		}
		else
		{
    		List<AutoritzacioSEU> ambitList = findAmbitAuthorizations(allAuthorizations, ambit);
    		List<AutoritzacioSEU> descripcioList = findDescripcioAuthorizations(allAuthorizations, descripcio);
    		List<AutoritzacioSEU> codiList = findCodiAuthorizations(allAuthorizations, codi);
    		
    		if((ambitList != null && !ambitList.isEmpty()) || (descripcioList != null && !descripcioList.isEmpty()))
    			authorizations = compareLists(ambitList, descripcioList);
    		if((authorizations != null && !authorizations.isEmpty()) || (codiList != null && !codiList.isEmpty()))
    			authorizations = compareLists(authorizations, codiList);
    		 
    		System.out.println(authorizations);
    		Collections.sort(authorizations, new ComparaAutos()); 
    		return authorizations;
		}
	}

	/**
	 * @param allAuthorizations
	 * @return
	 */
	private List<AutoritzacioSEU> sort (
					Collection<AutoritzacioSEU> allAuthorizations)
	{
		List<AutoritzacioSEU> list = new LinkedList<AutoritzacioSEU>();
		for(AutoritzacioSEU auto: allAuthorizations){
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
	private List<AutoritzacioSEU> findCodiAuthorizations (
					Collection<AutoritzacioSEU> allAuthorizations, String codi)
	{
		List<AutoritzacioSEU> list = new LinkedList<AutoritzacioSEU>();
		
		if(codi != null)
		{
    		for(AutoritzacioSEU auto: allAuthorizations)
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
	private List<AutoritzacioSEU> findDescripcioAuthorizations (
					Collection<AutoritzacioSEU> allAuthorizations, String descripcio)
	{
		List<AutoritzacioSEU> list = new LinkedList<AutoritzacioSEU>();
		
		if(descripcio != null)
		{
    		for(AutoritzacioSEU auto: allAuthorizations)
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
	private List<AutoritzacioSEU> findAmbitAuthorizations (
					Collection<AutoritzacioSEU> allAuthorizations, String ambit)
	{
		List<AutoritzacioSEU> list = new LinkedList<AutoritzacioSEU>();
		
		if(ambit != null)
		{
    		for(AutoritzacioSEU auto: allAuthorizations)
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
	
	private List<AutoritzacioSEU> compareLists(List<AutoritzacioSEU> first, List<AutoritzacioSEU> second){
		List<AutoritzacioSEU> endList = new LinkedList<AutoritzacioSEU>();
		if(first != null && second != null && !first.isEmpty() && !second.isEmpty()){
			for(AutoritzacioSEU auto: first){
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
		Collection<AutoritzacioSEU> allAuthorizations = getAuthorizations().values();
		for(AutoritzacioSEU auto: allAuthorizations){
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
		if (Security.isUserInRole(action+Security.AUTO_ALL))
			return true;
		
		if (object != null && object instanceof SecurityScopeEntity)
			return ((SecurityScopeEntity)object).isAllowed(action);
			
		return false;
	}
	
}

class AutoritzacioCache {
    private Collection<AutoritzacioRol> autoritzacions;
    Date expirationDate;

    public AutoritzacioCache(Collection<AutoritzacioRol> autoritzacions) {
        expirationDate = new Date(System.currentTimeMillis() + 600000); // 10 mins cache
        this.autoritzacions =  autoritzacions;
    }

    public boolean isValid() {
        return expirationDate.after(new Date());
    }

    public Collection<AutoritzacioRol> getAutoritzacions() {
        return autoritzacions;
    }

    public void setAutoritzacions(Collection<AutoritzacioRol> autoritzacions) {
        this.autoritzacions = autoritzacions;
    }

    
}