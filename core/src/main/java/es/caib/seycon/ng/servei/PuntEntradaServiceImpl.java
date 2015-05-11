// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.EntryPointEntity;
import com.soffid.iam.model.EntryPointExecutableEntity;
import com.soffid.iam.model.EntryPointExecutionTypeEntity;
import com.soffid.iam.model.EntryPointGroupEntity;
import com.soffid.iam.model.EntryPointIconEntity;
import com.soffid.iam.model.EntryPointRoleEntity;
import com.soffid.iam.model.EntryPointTreeEntity;
import com.soffid.iam.model.EntryPointUserEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.ArbrePuntEntrada;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.AutoritzacioPuntEntrada;
import es.caib.seycon.ng.comu.ExecucioPuntEntrada;
import es.caib.seycon.ng.comu.PuntEntrada;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.TipusExecucioPuntEntrada;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import com.soffid.iam.model.Parameter;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import org.apache.commons.collections.map.LRUMap;
import org.dom4j.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @see es.caib.seycon.ng.servei.PuntEntradaService
 */
public class PuntEntradaServiceImpl extends es.caib.seycon.ng.servei.PuntEntradaServiceBase {

    private static final String ROOT_TAG = "Root"; //$NON-NLS-1$
    Map<String, PermissionsCache> permisosCache;

    public PuntEntradaServiceImpl() {
        permisosCache = Collections.synchronizedMap(new LRUMap(50));
    }

    private static final Long ROOT_ID = new Long(0);

    // Informació de l'usuari actual (per comprovar les autoritzacions)

    /**
     * @see es.caib.seycon.ng.servei.PuntEntradaService#create(es.caib.seycon.ng.comu.PuntEntrada)
     */
    protected es.caib.seycon.ng.comu.PuntEntrada handleCreate(
            es.caib.seycon.ng.comu.PuntEntrada puntEntrada) throws java.lang.Exception {
        //
        // VERIFICACIONS:
        //
        Long idPare = puntEntrada.getIdPare();

        // Verificamos que el padre sea de tipo menú:
        if (puntEntrada.getIdPare() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.ObtaintParentPointEntryError")); //$NON-NLS-1$

        EntryPointEntity pareE = getEntryPointEntityDao().findById(puntEntrada.getIdPare());

        if (pareE == null)
            throw new CreateException(Messages.getString("PuntEntradaServiceImpl.ParentMenuNotFounded")); //$NON-NLS-1$
        if (!"S".equals(pareE.getMenu())) { //$NON-NLS-1$
            throw new CreateException(Messages.getString("PuntEntradaServiceImpl.ParentNotAMenu")); //$NON-NLS-1$
        }
        // Verificamos autorización del padre
        PuntEntrada pare = getEntryPointEntityDao().toPuntEntrada(pareE);
        if (!canAdmin(pare)) {
            throw new SecurityException(Messages.getString("PuntEntradaServiceImpl.UnauthorizedtForAdminParentMenu")); //$NON-NLS-1$
        }

        // Si el nou node és de tipus menú, verifiquem que tinga indicat el
        // tipus de menu
        // i esborrem les execucions (si existeixen)
        if ("S".equals(puntEntrada.getMenu())) { //$NON-NLS-1$
            if (puntEntrada.getTipusMenu() == null)
                throw new CreateException(Messages.getString("PuntEntradaServiceImpl.MenuTypeMessage")); //$NON-NLS-1$
            puntEntrada.setExecucions(new HashSet()); // esborrem execucions
                                                      // abans de crear entitat
        }

        // Validem el XML si no és buit
        if (puntEntrada.getXmlPUE() != null && !"".equals(puntEntrada.getXmlPUE())) { //$NON-NLS-1$
            String resValida = validaXMLPUE(puntEntrada);
            if (resValida != null && !"".equals(resValida.trim())) //$NON-NLS-1$
                throw new SeyconException(String.format(
                        Messages.getString("PuntEntradaServiceImpl.XMLValidationError"), //$NON-NLS-1$
                        puntEntrada.getNom(), resValida));
        }

        //
        // OBTENIM L'ENTITAT
        //
        EntryPointEntity entity = getEntryPointEntityDao().puntEntradaToEntity(puntEntrada);

        // CREEM L'ENTITAT (!!)
        getEntryPointEntityDao().create(entity);

        // Creem l'ARBRE del punt d'entrada
        int ordre = 0; //$NON-NLS-1$	//String ordre = "0";
        // Obtenim L'ORDRE DE L'ARBRE des dels fills del pare (estan ordenats
        // per ordre ascendent)
        List fills = (List) getEntryPointTreeEntityDao().findByParent(puntEntrada.getIdPare());
        if (fills != null) {// Ens quedem en el fill de major ordre
            if (fills.size() == 0) // Para nodes menú sense fills
                ordre = 0; //$NON-NLS-1$	//ordre = "0";
            else { // Obtenim el seu fill "major"
                EntryPointTreeEntity fill = (EntryPointTreeEntity) fills.get(fills.size() - 1);
                int ordreFillMajor = fill.getOrder();	//Integer.parseInt(fill.getOrdre());
                ordre = ordreFillMajor + 1; //$NON-NLS-1$	//"" + (ordreFillMajor + 1);
            }
        }
        EntryPointTreeEntity arbre = getEntryPointTreeEntityDao().newEntryPointTreeEntity();
        arbre.setOrder(ordre);
        arbre.setChildren(entity);
        arbre.setParent(pareE);
        HashSet<EntryPointTreeEntity> monArbre = new HashSet<EntryPointTreeEntity>();
        monArbre.add(arbre);
        // Establim l'arbre
        entity.setChildrenEntryPointTree(monArbre);

        // Creem les relacions del punt d'entrada
        // Arbre
        getEntryPointTreeEntityDao().create(arbre);

        // Creem les icones
        EntryPointIconEntity icona1 = null;
        if (puntEntrada.getImgIcona1() != null && puntEntrada.getImgIcona1().length != 0) {
            // Creem l'icona
            icona1 = createIcona(puntEntrada.getImgIcona1());
            entity.setIcon1(icona1.getId());
        }
        EntryPointIconEntity icona2 = null;
        if (puntEntrada.getImgIcona2() != null && puntEntrada.getImgIcona2().length != 0) {
            // S'ha actualitzat l'icona: creem una nova
            icona2 = createIcona(puntEntrada.getImgIcona2());
            entity.setIcon2(icona2.getId());
        }

        // Actualitzem l'entitat (amb les relacions)
        getEntryPointEntityDao().update(entity);

        // Afegim id del pare (per poder moure'l ara mateix)
        PuntEntrada res = getEntryPointEntityDao().toPuntEntrada(entity);
        res.setIdPare(idPare);

        // Assignem iconas (en el toVO encara no poden estar en la BD)
        if (icona1 != null) {
            res.setIdIcona1(icona1.getId());
            res.setImgIcona1(icona1.getIcon());
        }
        if (icona2 != null) {
            res.setIdIcona2(icona2.getId());
            res.setImgIcona2(icona2.getIcon());
        }

        // Posem la ruta que s'ha obtingut en el ZUL a partir del pare
        if (puntEntrada.getRutaArbre() != null)
            res.setRutaArbre(puntEntrada.getRutaArbre());

        auditarPuntEntrada("C", res.getNom() + Messages.getString("PuntEntradaServiceImpl.15") + pareE.getName()); //$NON-NLS-1$ //$NON-NLS-2$

        return res;
    }

    /**
     * @see es.caib.seycon.ng.servei.PuntEntradaService#update(es.caib.seycon.ng.comu.PuntEntrada)
     */
    protected es.caib.seycon.ng.comu.PuntEntrada handleUpdate(
            es.caib.seycon.ng.comu.PuntEntrada puntEntrada) throws java.lang.Exception {

        if (!canAdmin(puntEntrada))
            throw new SecurityException(Messages.getString("PuntEntradaServiceImpl.UnauthorizedToUpdate")); //$NON-NLS-1$

        // Validem el XML si no és buit
        if (puntEntrada.getXmlPUE() != null && !"".equals(puntEntrada.getXmlPUE())) { //$NON-NLS-1$
            String resValida = validaXMLPUE(puntEntrada);
            if (resValida != null && !"".equals(resValida.trim())) //$NON-NLS-1$
                throw new SeyconException(String.format(
                        Messages.getString("PuntEntradaServiceImpl.XMLValidationError"), //$NON-NLS-1$
                        puntEntrada.getNom(), resValida));
        }

        // Transformem a Entity
        EntryPointEntity entity = getEntryPointEntityDao().load(puntEntrada.getId());
        boolean updatingRoot = entity != null && ROOT_TAG.equals(entity.getCode()); 
        getEntryPointEntityDao().puntEntradaToEntity(puntEntrada, entity, true);
        if (updatingRoot) {
            entity.setCode(ROOT_TAG);
        } else {
            if (ROOT_TAG.equals(puntEntrada.getCodi())) {
                entity.setCode(null);
            }
        }

        // Si és e tipus menú, esborrem execucions:
        if ("S".equals(puntEntrada.getMenu())) { //$NON-NLS-1$
            entity.setExecutionMethod(new HashSet<EntryPointExecutableEntity>()); // esborrem
                                                                                 // execucions
        }

        // Verifiquem les icones:
        // ACTUALITZACIONS
        // UPDATE: Ja té icona, i s'ha posta una nova
        if (entity.getIcon1() != null && puntEntrada.getImgIcona1() != null && puntEntrada.getImgIcona1().length != 0 && puntEntrada.getIdIcona1() == null) {
            // Esborrem l'icona anterior
            getEntryPointIconEntityDao().remove(entity.getIcon1()); // Per id
            // S'ha actualitzat l'icona: creem una nova
            EntryPointIconEntity icona1 = createIcona(puntEntrada.getImgIcona1());
            entity.setIcon1(icona1.getId());
        }
        if (entity.getIcon2() != null && puntEntrada.getImgIcona2() != null && puntEntrada.getImgIcona2().length != 0 && puntEntrada.getIdIcona2() == null) {
            // Esborrem l'icona anterior
            getEntryPointIconEntityDao().remove(entity.getIcon2()); // Per id
            // S'ha actualitzat l'icona: creem una nova
            EntryPointIconEntity icona2 = createIcona(puntEntrada.getImgIcona2());
            entity.setIcon2(icona2.getId());
        }
        // ADD: NOVES ICONES (no existien abans)
        if (entity.getIcon1() == null && puntEntrada.getImgIcona1() != null && puntEntrada.getImgIcona1().length != 0) {
            // Creem l'icona
            EntryPointIconEntity icona1 = createIcona(puntEntrada.getImgIcona1());
            entity.setIcon1(icona1.getId());
        }
        if (entity.getIcon2() == null && puntEntrada.getImgIcona2() != null && puntEntrada.getImgIcona2().length != 0) {
            // S'ha actualitzat l'icona: creem una nova
            EntryPointIconEntity icona2 = createIcona(puntEntrada.getImgIcona2());
            entity.setIcon2(icona2.getId());
        }
        // DELETE: Esborrem l'icona assignada
        if (entity.getIcon1() != null && puntEntrada.getImgIcona1() == null) {
            // Esborrem l'icona anterior
            getEntryPointIconEntityDao().remove(entity.getIcon1()); // Per id
            entity.setIcon1(null);
        }
        if (entity.getIcon2() != null && puntEntrada.getImgIcona2() == null) {
            // Esborrem l'icona anterior
            getEntryPointIconEntityDao().remove(entity.getIcon2()); // Per id
            entity.setIcon2(null);
        }

        
        getEntryPointEntityDao().update(entity);

        auditarPuntEntrada("U", entity.getName()); //$NON-NLS-1$

        return getEntryPointEntityDao().toPuntEntrada(entity);
    }

    /**
     * @see es.caib.seycon.ng.servei.PuntEntradaService#delete(es.caib.seycon.ng.comu.PuntEntrada)
     */
    protected void handleDelete(es.caib.seycon.ng.comu.PuntEntrada puntEntrada)
            throws java.lang.Exception {

        if (!canAdmin(puntEntrada))
            throw new RemoveException("no autoritzat"); //$NON-NLS-1$

        if (puntEntrada.getId() == null)
            // Correcte per als nous //TODO: verificar
            throw new RemoveException(
                    Messages.getString("PuntEntradaServiceImpl.NotIDEntryPoint")); //$NON-NLS-1$

        // OBTENIM L'ENTITAT
        EntryPointEntity entity = getEntryPointEntityDao().puntEntradaToEntity(puntEntrada);

        // Analizamos los "enlaces" para saber si sólo tenemos un padre o varios
        // Si sólo hay uno, se borra el punt d'entrada, si hay varios: se borra
        // en enlace(link)
        Collection mosPares = entity.getChildrenEntryPointTree();

        // Par saber si hem de esborrar el punt d'entrada o només el link
        // (l'arbre)
        boolean enlazado = false;
        if (mosPares.size() == 0) { // no se pot esborrar el node root
            throw new RemoveException(Messages.getString("PuntEntradaServiceImpl.DeleteNotAllowed")); //$NON-NLS-1$
        } else if (mosPares.size() > 1) { // té més d'un pare: la deslincamos
                                          // del padre actual
            // throw new
            // RemoveException("Error: Té més d'un punt d'entrada pare");
            enlazado = true; // hem d'esborrar l'enllaç
        }
        // Ens quedem en el id del pare del punt d'entrada actual
        Long idPare = puntEntrada.getIdPare(); // ((ArbrePuntEntradaEntity)
                                               // mosPares.iterator().next()).getPare().getId();

        // ARBRE DE PUE: si no estamos enlazados, no permitimos borrarnos si
        // tenemos hijos
        if (!enlazado) {
            Collection mosFills = entity.getParentEntryPointTree();
            if (mosFills.size() != 0) {
                throw new RemoveException(String.format(Messages.getString("PuntEntradaServiceImpl.ChildEntryPointError"), //$NON-NLS-1$
                        puntEntrada.getNom()));
            }
        }

        // Ajustem l'ordre de l'arbre del pare
        boolean mhetrobat = false;
        // Trobem els fills (estan ordenats pel camp ordre) (!!)
        Collection<EntryPointTreeEntity> arbrePare = getEntryPointTreeEntityDao().findByParent(idPare);
        EntryPointTreeEntity arbreEsborrar = null;
        for (Iterator<EntryPointTreeEntity> it = arbrePare.iterator(); it.hasNext(); ) {
            EntryPointTreeEntity actual = it.next();
            if (mhetrobat) {
                int ordre = actual.getOrder() - 1;
                actual.setOrder(ordre);
            } else if (actual.getChildren().getId().equals(entity.getId())) {
                mhetrobat = true;
                it.remove();
                arbreEsborrar = actual;
            }
        }

        // Actualiztem l'arbre del pare (reordenació de nodes i esborrat del pue
        // actual)
        if (arbreEsborrar != null)
            getEntryPointTreeEntityDao().remove(arbreEsborrar);
        getEntryPointTreeEntityDao().update(arbrePare);

        if (!enlazado) {
            // Ens esborrem de l'arbre
            // getArbrePuntEntradaEntityDao().remove(entity.getArbrePuntEntradaSocFill()); // només
                                                                                        // serà
                                                                                        // 1
            entity.setChildrenEntryPointTree(new HashSet<EntryPointTreeEntity>());
            // Actualitzem l'arbre del pare
            // getArbrePuntEntradaEntityDao().update(arbrePare);

            // AUTORITZACIONS
            Collection autoUsu = entity.getAuthorizedUsers();
            Collection autoRol = entity.getAuthorizedRoles();
            Collection autoGrup = entity.getAuthorizedGroups();
            // les esborrem
            getEntryPointUserEntityDao().remove(autoUsu);
            getEntryPointRoleEntityDao().remove(autoRol);
            getEntryPointGroupEntityDao().remove(autoGrup);
            entity.setAuthorizedUsers(null);
            entity.setAuthorizedRoles(null);
            entity.setAuthorizedGroups(null);

            // EJECUCIONS
            Collection<EntryPointExecutableEntity> execucions = entity.getExecutionMethod();
            getEntryPointExecutableEntityDao().remove(execucions);
            entity.setExecutionMethod(null);

            // ICONAS
            if (entity.getIcon1() != null) {
                getEntryPointIconEntityDao().remove(entity.getIcon1());
                entity.setIcon1(null);
            }
            if (entity.getIcon2() != null) {
                getEntryPointIconEntityDao().remove(entity.getIcon2());
                entity.setIcon2(null);
            }

            getEntryPointEntityDao().remove(entity);
            auditarPuntEntrada("D", "Esborrat punt d\'entrada \'" + entity.getName() + "\'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
            auditarPuntEntrada("D", "Esborrat enlla\u00e7 del punt d\'entrada \'" + entity.getName() + "\'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

    }

    /**
     * @see es.caib.seycon.ng.servei.PuntEntradaService#findRoot()
     */
    protected es.caib.seycon.ng.comu.PuntEntrada handleFindRoot() throws java.lang.Exception {

        EntryPointEntity entity = getEntryPointEntityDao().findById(ROOT_ID);
        if (entity == null) {
            List<EntryPointEntity> entities = getEntryPointEntityDao().findByCriteria("%", ROOT_TAG); //$NON-NLS-1$
            if (entities.size() > 0)
                entity = entities.get(0);
        }
        if (entity == null) {
            EntryPointEntity root = getEntryPointEntityDao().newEntryPointEntity(); 
            root.setId(ROOT_ID);
            root.setCode(ROOT_TAG);
            root.setPublicAccess("S"); //$NON-NLS-1$
            InformationSystemEntity app = getInformationSystemEntityDao().findByCode("SEU"); //$NON-NLS-1$
            if (app == null)
                root.setApplicationID(new Long(0)); 
            else
                root.setApplicationID(app.getId());
            root.setMenu("S"); //$NON-NLS-1$
            root.setName("Acme Company"); //$NON-NLS-1$
            root.setMenuType("L"); //$NON-NLS-1$
            root.setVisible("S"); //$NON-NLS-1$
            getEntryPointEntityDao().create(root);
            return getEntryPointEntityDao().toPuntEntrada(root);
        } else
            return getEntryPointEntityDao().toPuntEntrada(entity);
    }

    /**
     * @see es.caib.seycon.ng.servei.PuntEntradaService#findChildren(es.caib.seycon.ng.comu.PuntEntrada)
     */
    protected java.util.Collection<PuntEntrada> handleFindChildren(
            es.caib.seycon.ng.comu.PuntEntrada puntEntrada) throws java.lang.Exception {
        // Comprovem autorització
        if (!canView(puntEntrada)) // No donem error
            return new LinkedList<PuntEntrada>();// throw new
                                                 // SeyconException("no autoritzat");

        Collection<EntryPointTreeEntity> arbre = getEntryPointTreeEntityDao().findByParent(puntEntrada.getId());
        if (arbre != null && arbre.size() != 0) {// Verificamos permisos
            Collection<PuntEntrada> fills = new LinkedList<PuntEntrada>();
            for (Iterator<EntryPointTreeEntity> it = arbre.iterator(); it.hasNext(); ) {
                EntryPointTreeEntity a = it.next();
                PuntEntrada pue = getEntryPointEntityDao().toPuntEntrada(a.getChildren());
                pue.setIdPare(a.getParent().getId());
                pue.setOrdre("" + a.getOrder());
                String rutaPare = puntEntrada.getRutaArbre() != null ? puntEntrada.getRutaArbre() + " > " : "";
                pue.setRutaArbre(rutaPare + puntEntrada.getNom());
                if (canView(pue)) fills.add(pue);
            }
            return fills;

        }
        return new LinkedList<PuntEntrada>();
    }

    protected Collection<TipusExecucioPuntEntrada> handleGetAllTipusMimeExecucio() throws Exception {
        List<EntryPointExecutionTypeEntity> tipusMime = getEntryPointExecutionTypeEntityDao().loadAll();
        if (tipusMime.isEmpty()) {
            EntryPointExecutionTypeEntity punt = getEntryPointExecutionTypeEntityDao().newEntryPointExecutionTypeEntity();
            punt.setName("URL"); //$NON-NLS-1$
            punt.setTemplate("http://"); //$NON-NLS-1$
            punt.setMimeType("text/html"); //$NON-NLS-1$
            getEntryPointExecutionTypeEntityDao().create(punt);
            tipusMime.add(punt);
            punt = getEntryPointExecutionTypeEntityDao().newEntryPointExecutionTypeEntity();
            punt.setName("MZN"); //$NON-NLS-1$
            punt.setTemplate("exec ( ... );"); //$NON-NLS-1$
            punt.setMimeType("x-application/x-mazinger-script"); //$NON-NLS-1$
            getEntryPointExecutionTypeEntityDao().create(punt);
            tipusMime.add(punt);
        }
        return getEntryPointExecutionTypeEntityDao().toTipusExecucioPuntEntradaList(tipusMime); // toVO
    }

    protected Collection<Aplicacio> handleGetAllAplicacions(Boolean aplicacioBuida)
            throws Exception {
        // D'aquesta manera estan ordenades per nom
        Collection<InformationSystemEntity> aplicacions = getInformationSystemEntityDao().
        		findByFilter(null, null, null, null, null, null, null);
        // getAplicacioEntityDao().toAplicacioCollection(aplicacions);//toVO
        // Les transformem "manualment"
        Collection<Aplicacio> appsVO = new LinkedList<Aplicacio>();

        if (aplicacioBuida.booleanValue()) {
            // Añadimos una aplicación vacía al principio
            Aplicacio buida = new Aplicacio(); //$NON-NLS-1$

            buida.setNom(""); //$NON-NLS-1$
            buida.setCodi(null); // Sense codi d'aplicació
            appsVO.add(buida);
        }
        for (Iterator<InformationSystemEntity> it = aplicacions.iterator(); it.hasNext(); ) {
            InformationSystemEntity ap = it.next();
            Aplicacio apvo = getInformationSystemEntityDao().toAplicacio(ap);
            apvo.setNom(apvo.getCodi() + " - " + apvo.getNom());
            appsVO.add(apvo);
        }
        return appsVO;
    }

    protected Collection<AutoritzacioPuntEntrada> handleGetAutoritzacionsPUE(PuntEntrada puntEntrada)
            throws Exception {
        return puntEntrada.getAutoritzacions();
    }

    protected boolean handleCanAdmin(PuntEntrada puntEntrada) throws Exception {
        // return authorized (entityContext, Authorization.admin);
    	
        return esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_ADMIN_DESCRIPCIO);
    }

    protected boolean handleCanExecute(PuntEntrada puntEntrada) throws Exception {
        // return acl.canQuery (entityContext) && getExecutionMethod
        // (enviroment) != null;
        return canQuery(puntEntrada) && puntEntrada.getExecucions() != null;
    }

    protected boolean handleCanQuery(PuntEntrada puntEntrada) throws Exception {
        // return publicAccess || authorized (entityContext,
        // Authorization.query);
        if ("S".equals(puntEntrada.getEsPublic()) //$NON-NLS-1$
                || AutoritzacionsUsuari.canQueryAllMenusIntranet() // nova
                                                                   // autorització
                                                                   // per veure
                                                                   // tots
                || esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_QUERY_DESCRIPCIO))
            return true;
        return false;
    }

    protected boolean handleCanView(PuntEntrada puntEntrada) throws Exception {
        // return visible || publicAccess || authorized (entityContext,
        // Authorization.query);
        if ("S".equals(puntEntrada.getVisible()) || "S".equals(puntEntrada.getEsPublic()) //$NON-NLS-1$ //$NON-NLS-2$
                || AutoritzacionsUsuari.canQueryAllMenusIntranet() // nova
                                                                   // autorització
                                                                   // per veure
                                                                   // tots
                || esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_QUERY_DESCRIPCIO))
            return true;
        return false;
    }

    protected boolean handleEsAutoritzat(PuntEntrada puntEntrada, String nivell) throws Exception {

        // Hacemos que los administradores de los menús de la
        // intranet puedan hacerlo todo (!!)
        if (AutoritzacionsUsuari.canAdminMenusIntranet())
            return true;

        Principal principal = Security.getPrincipal();
        if (principal == null)
        	return false;
        String user = principal.getName();
        Collection<AutoritzacioPuntEntrada> autoritzacions = puntEntrada.getAutoritzacions();
        if (autoritzacions == null)
            return false;
        Iterator<AutoritzacioPuntEntrada> iterator = autoritzacions.iterator();
        PermissionsCache permisos = getCurrentAuthorizations();

        boolean trobat = false;

        while (!trobat && iterator.hasNext()) {
            AutoritzacioPuntEntrada auto = iterator.next();
            // Puede ser de 3 tipos: usuario, rol o grupo
            String tipus = auto.getTipusEntitatAutoritzada();
            String codiAuto = auto.getCodiEntitatAutoritzada(); // és ÚNIC !!
            if (tipus.equals(TipusAutoritzacioPuntEntrada.USUARI)) {
                if (user.equals(codiAuto)) {
                    // Comprovem el nivell d'autorització
                    String nivellAuto = auto.getDescripcioNivellAutoritzacio();
                    trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(nivellAuto) || nivellAuto
                            .equals(nivell));
                }
            } else if (tipus.equals(TipusAutoritzacioPuntEntrada.ROL)) {
                // Lo buscamos en la hash
                if (permisos.getRolsUsuariPUE().contains(auto.getIdEntitatAutoritzada())) {// codiAuto.toUpperCase()))
                    // {
                    // Comprovem el nivell d'autorització
                    String nivellAuto = auto.getDescripcioNivellAutoritzacio();
                    trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(nivellAuto) || nivellAuto
                            .equals(nivell));
                }

            } else if (tipus.equals(TipusAutoritzacioPuntEntrada.GRUP)) {
                // Lo buscamos en la hash
                if (permisos.getGrupsUsuariPUE().contains(codiAuto)) {
                    // Comprovem el nivell d'autorització
                    String nivellAuto = auto.getDescripcioNivellAutoritzacio();
                    trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(nivellAuto) || nivellAuto
                            .equals(nivell));
                }
            }
        }

        return trobat;
    }

    private PermissionsCache getCurrentAuthorizations() throws InternalErrorException {
        String user = Security.getCurrentUser();
        PermissionsCache entry = permisosCache.get(user);
        if (entry != null && !entry.isValid())
            permisosCache.remove(user);

        return calculateAuthorizations(user);
    }

    private PermissionsCache calculateAuthorizations(String user) throws InternalErrorException {

        UserEntity usuari = getUserEntityDao().findByUserName(user);
        if (usuari != null) {
            PermissionsCache entry = new PermissionsCache();

            // Grups de l'usuari
            GroupEntity gprimari = usuari.getPrimaryGroup();
            Collection<UserGroupEntity> grups = usuari.getSecondaryGroups();
            if (gprimari != null) {
                entry.getGrupsUsuariPUE().add(gprimari.getName());// ,gprimari);
            }
            if (grups != null) {
                for (Iterator<UserGroupEntity> it = grups.iterator(); it.hasNext(); ) {
                    UserGroupEntity uge = it.next();
                    GroupEntity g = uge.getGroup();
                    entry.getGrupsUsuariPUE().add(g.getName());
                }
            }

            // Rols de l'usuari: jerarquia
            Collection<Rol> rolsJerarquia = getUsuariService().findJerarquiaRolsUsuariByCodiUsuari(
                    user);
            if (rolsJerarquia != null) {
                for (Iterator<Rol> it = rolsJerarquia.iterator(); it.hasNext();) {
                    Rol rol = it.next();
                    // La clau és el id del rol (!!)
                    entry.getRolsUsuariPUE().add(rol.getId());// , rol);
                }
            }
            // Guardem les dades de l'usuari actual
            permisosCache.put(user, entry);
            return entry;
        } else {
            return new PermissionsCache();
        }
    }

    private boolean isOrigenAncestorDesti(Long origenId, Long destiId) {

        if (origenId.equals(destiId))
            return true;
        List<EntryPointTreeEntity> paresDesti = getEntryPointTreeEntityDao().findByChildren(destiId);

        if (paresDesti != null) {
            for (Iterator<EntryPointTreeEntity> it = paresDesti.iterator(); it.hasNext(); ) {
                EntryPointTreeEntity arbreDesti = it.next();
                Long p = arbreDesti.getParent().getId();
                return isOrigenAncestorDesti(origenId, p);
            }
        } else
            return false; // Ok, no tiene padre

        return false; // no deberíamos llegar aquí
    }

    /*
     * Reordena un punt d'entrada: Pot ésser moure d'una brana a una altra o
     * reordenar (mateixa branca) El punt d'entrada origen (puntEntradaOrdenar)
     * pot estar a la mateixa branca (menú) = REORDENAR o a una branca diferent
     * = MOURE
     */
    protected boolean handleReordenaPuntEntrada(PuntEntrada puntEntradaOrdenar,
            PuntEntrada puntEntradaSeguent) throws Exception {
        // 1) Obtenim la informació del l'arbre origen
        Long idPareArbreOrigen = puntEntradaOrdenar.getIdPare();
        Long idPueOrigen = puntEntradaOrdenar.getId();

        // 2) Obtenim la informació de l'arbre destí
        Long idPareArbreDesti = puntEntradaSeguent.getIdPare();
        Long idPueDesti = puntEntradaSeguent.getId();

        // Verifiquem autoritzacions a l'origen i al destí
        if (!canAdmin(puntEntradaOrdenar) || !canAdmin(puntEntradaSeguent))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAdminPermission")); //$NON-NLS-1$

        // Analitzem l'arbre dels pares del node destí per verificar que no es
        // mou
        // un node origen dintre de la seua branca
        if (isOrigenAncestorDesti(idPueOrigen, idPareArbreDesti))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NotNodeMoviment")); //$NON-NLS-1$

        // Podem tenir 2 casos: mateix pare (REORDENAR) o pare distint (MOURE)
        // (mateix pare = ordenar l'arbre destí)
        if (idPareArbreOrigen.equals(idPareArbreDesti)) {
            // Mateixa branca, només hem de reordenar
            List<EntryPointTreeEntity> branca = getEntryPointTreeEntityDao().findByParent(idPareArbreOrigen);
            // Ja està ordenada pel camp ordre (SEMPRE tindrá fills)
            if (branca != null && branca.size() != 0) {
                // És l'arbre de fills del mateix pare ordenat per Ordre
                int posOrigen = -1;
                int posDesti = -1;
                int pos = -1;
                // Obtenim la posició de l'element a moure i la nova posició
                // (destí)
                for (Iterator<EntryPointTreeEntity> it = branca.iterator(); it.hasNext(); ) {
                    pos++;
                    EntryPointTreeEntity actual = it.next();
                    if (actual.getChildren().getId().equals(idPueOrigen)) {
                        posOrigen = pos;
                    } else if (actual.getChildren().getId().equals(idPueDesti)) {
                        posDesti = pos;
                    }
                }
                if (posOrigen == -1 || posDesti == -1)
                    return false; // No trobats
                // Reordenem
                if (posOrigen < posDesti) { // pugem l'aplicació
                    int i = -1;
                    for (Iterator<EntryPointTreeEntity> it = branca.iterator(); it.hasNext(); ) {
                        i++;
                        EntryPointTreeEntity actual = it.next();
                        if (i == posOrigen) {
                            actual.setOrder(posDesti - 1);
                        } else if (i < posDesti) {
                            int posActual = (i - 1);
                            actual.setOrder(posActual);
                        }
                    }
                } else { // posOrigen > posDesti : baixem l'aplicació
                    int i = -1;
                    for (Iterator<EntryPointTreeEntity> it = branca.iterator(); it.hasNext(); ) {
                        i++;
                        EntryPointTreeEntity actual = it.next();
                        if (i == posOrigen) {
                            actual.setOrder(posDesti);
                        } else if (i > posOrigen) {
                            ;
                        } else {
                            int posActual = (i + 1);
                            actual.setOrder(posActual);
                        }
                    }
                }
                getEntryPointTreeEntityDao().update(branca);
                return true;
            } else
                return false; // cas no possible (origen i desti mateixa branca
                              // = té fills el pare)

        } else { // són de branques diferents
            List<EntryPointTreeEntity> brancaDesti = getEntryPointTreeEntityDao().findByParent(idPareArbreDesti);

            // Hem de verificar que en la branca destí no existisca ja el node
            // actual [cas de que siga un enllaç]
            if (brancaDesti != null)
                for (Iterator<EntryPointTreeEntity> it = brancaDesti.iterator(); it.hasNext(); ) {
                EntryPointTreeEntity arbreActual = it.next();
                if (arbreActual.getChildren().getId().equals(idPueOrigen)) throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.EntryPointDuplicated"));
            }

            // Creen la nova entrada a l'arbre
            EntryPointEntity nouPare = getEntryPointEntityDao().findById(idPareArbreDesti);
            EntryPointEntity pueMogut = getEntryPointEntityDao().findById(idPueOrigen);

            EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao().newEntryPointTreeEntity();
            nouArbre.setOrder(Integer.parseInt(puntEntradaSeguent.getOrdre())); //nouArbre.setOrdre(puntEntradaSeguent.getOrdre());
            nouArbre.setChildren(pueMogut);
            nouArbre.setParent(nouPare);

            // Obtenim la branca origen on estava el node a moure (el pare del
            // node mogut)
            List<EntryPointTreeEntity> brancaOrigen = getEntryPointTreeEntityDao().findByParent(idPareArbreOrigen);
            EntryPointTreeEntity arbreAntic = null;
            boolean trobat = false;
            for (Iterator<EntryPointTreeEntity> it = brancaOrigen.iterator(); !trobat && it.hasNext(); ) {
                EntryPointTreeEntity a = it.next();
                if (a.getChildren().getId().equals(idPueOrigen)) {
                    arbreAntic = a;
                    trobat = true;
                }
            }
            // Hem de reordenar les entrades de la nova branca
            if (brancaDesti != null && brancaDesti.size() != 0) { // Pot ésser
                                                                  // que la nova
                                                                  // branca siga
                                                                  // buida
                // Si la seua posició es >= posDesti, sumem 1 a l'ordre
                boolean reordenar = false;
                int i = -1;
                for (Iterator<EntryPointTreeEntity> it = brancaDesti.iterator(); it.hasNext(); ) {
                    EntryPointTreeEntity actual = it.next();
                    i++;
                    if (reordenar) {
                        int posActual = i + 1;
                        actual.setOrder(posActual);
                    } else if (actual.getChildren().getId().equals(idPueDesti)) {
                        reordenar = true;
                        int posActual = actual.getOrder() + 1;
                        actual.setOrder(posActual);
                    }
                }
            }
            if (brancaDesti == null)
                brancaDesti = new LinkedList<EntryPointTreeEntity>();

            // Fem els canvis
            // 1) Esborrem l'arbre antic
            if (arbreAntic != null)
                getEntryPointTreeEntityDao().remove(arbreAntic);
            // 2) Creem l'accés a la nova branca
            getEntryPointTreeEntityDao().create(nouArbre);
            brancaDesti.add(nouArbre);
            // 3) Actualitzem la branca destí (hem reordenat els fills de la
            // branca)
            getEntryPointTreeEntityDao().update(brancaDesti);
            // 4) Actualitzcem el menú destí
            nouPare.setParentEntryPointTree(brancaDesti);
            getEntryPointEntityDao().update(nouPare);

            return true;
        }

    }

    /*
     * Mou un punt d'entrada d'una branca a una altra El puntEntradaMenuDesti ha
     * de ser de tipus menú, i el punt d'entrada origen pot ésser menú o de
     * tipus acció i es posa a la darrera posició del menú destí
     * (puntEtnradaMenuDesti)
     */
    protected boolean handleMoureMenusPuntEntrada(PuntEntrada puntEntradaMoure,
            PuntEntrada puntEntradaMenuDesti) throws Exception {// NOTA: El
                                                                // destino
                                                                // SIEMPRE es un
                                                                // menú
        // 1) Verifiquem que el destí siga de tipus menú
        if (!"S".equals(puntEntradaMenuDesti.getMenu())) //$NON-NLS-1$
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.EntryPointTypeError")); //$NON-NLS-1$

        // 2) Verifiquem autoritzacions: origen, desti i pare del destí
        Long idParePuntEntradaMoure = puntEntradaMoure.getIdPare();
        Long idPueOrigen = puntEntradaMoure.getId();
        Long idPueDesti = puntEntradaMenuDesti.getId();
        Long idParePuntEntradaDesti = (!idPueDesti.equals(ROOT_ID)) ? puntEntradaMenuDesti
                .getIdPare() : ROOT_ID;
        if (idPueOrigen == null || idParePuntEntradaMoure == null || idPueDesti == null
                || idParePuntEntradaDesti == null)
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.EntryPointConfirmChanges")); //$NON-NLS-1$

        // Analitzem l'arbre dels pares del node destí per verificar que no es
        // mou
        // un node origen dintre de la seua branca
        if (isOrigenAncestorDesti(idPueOrigen, idPueDesti))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NotNodeMoviment")); //$NON-NLS-1$

        EntryPointEntity pareOrigenE = getEntryPointEntityDao().findById(idParePuntEntradaMoure);
        PuntEntrada pareOrigen = getEntryPointEntityDao().toPuntEntrada(pareOrigenE);
        // PuntEntradaEntity pareDestiE =
        // getPuntEntradaEntityDao().findById(idParePuntEntradaDesti);
        // PuntEntrada pareDesti =
        // getPuntEntradaEntityDao().toPuntEntrada(pareDestiE);
        // Tiene que tener permisos en el punto de entrada origen y destino (es
        // menú)
        if (!canAdmin(puntEntradaMoure) || !canAdmin(puntEntradaMenuDesti))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NotAuthorizedToMoveEntryPoint")); //$NON-NLS-1$

        // Si el origen NO es menú tiene que tener permisos en el menú
        // contenedor padre del origen (para mover)
        if (!"S".equals(puntEntradaMoure.getMenu()) && !canAdmin(pareOrigen)) //$NON-NLS-1$
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.NotAuthorizedToMoveEntryPointNoPermission")); //$NON-NLS-1$

        // Obtenim l'arbre del punt d'entrada origen i destí
        List<EntryPointTreeEntity> brancaOrigen = getEntryPointTreeEntityDao().findByParent(idParePuntEntradaMoure);
        EntryPointTreeEntity arbreAntic = null;
        int pos = 0; // per reindexar elements (ja estàn ordenats pel camp
                     // ordre)
        for (Iterator<EntryPointTreeEntity> it = brancaOrigen.iterator(); it.hasNext(); ) {
            EntryPointTreeEntity a = it.next();
            if (a.getChildren().getId().equals(idPueOrigen)) {
                arbreAntic = a;
                it.remove();
            } else {
                a.setOrder(pos);
                pos++;
            }
        }
        // Creen la nova entrada a l'arbre destí
        EntryPointEntity nouPare = getEntryPointEntityDao().findById(idPueDesti);
        EntryPointEntity pueMogut = getEntryPointEntityDao().findById(idPueOrigen);

        // Obtenim L'ORDRE DE L'ARBRE destí
        String ordre = "0"; //$NON-NLS-1$
        Collection<EntryPointTreeEntity> fillsDesti = getEntryPointTreeEntityDao().findByParent(idPueDesti);
        if (fillsDesti != null) {// Ens quedem en el fill de major ordre
            if (fillsDesti.size() == 0) // Para nodes menú sense fills
                ordre = "0"; //$NON-NLS-1$
            else { // Obtenim el seu fill "major" (de tipus List i estan
                   // ordenats per query en ordre ascendent)
                EntryPointTreeEntity fill = ((List<EntryPointTreeEntity>) fillsDesti).get(fillsDesti.size() - 1);
                int ordreFillMajor = fill.getOrder(); //int ordreFillMajor = Integer.parseInt(fill.getOrdre());
                ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
            }
        }

        // Creem el accés al punt d'entrada mogut
        EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao().newEntryPointTreeEntity();
        nouArbre.setOrder(Integer.parseInt(ordre)); //nouArbre.setOrdre(ordre);
        nouArbre.setChildren(pueMogut);
        nouArbre.setParent(nouPare);

        // 1) Esborrem l'arbre antic
        if (arbreAntic != null)
            getEntryPointTreeEntityDao().remove(arbreAntic);
        // 2) Creem l'accés a la nova branca
        getEntryPointTreeEntityDao().create(nouArbre);
        if (fillsDesti == null) {
            fillsDesti = new HashSet<EntryPointTreeEntity>();
        }
        fillsDesti.add(nouArbre);
        nouPare.setParentEntryPointTree(new HashSet<EntryPointTreeEntity>(fillsDesti));
        // 3) Actualitzem la branca destí (hem afegit un fill)
        getEntryPointEntityDao().update(nouPare);

        // 4) Actualitzem la branca origen (hem mogut un fill)
        getEntryPointTreeEntityDao().update(brancaOrigen);
        return true;
    }

    private EntryPointEntity clonaPuntEntrada(EntryPointEntity pueClonar) throws InternalErrorException {
        EntryPointEntity nouPUEClonat = null;

        // Copiamos iconos
        Long icona1 = null;
        Long icona2 = null;

        // Creamos la copia y obtenemos el id de la misma
        if (pueClonar.getIcon1() != null) {
            EntryPointIconEntity ie = getEntryPointIconEntityDao().findById(pueClonar.getIcon1()); // se
                                                                                  // guarda
                                                                                  // id
                                                                                  // del
                                                                                  // icono
                                                                                  // en
                                                                                  // el
                                                                                  // pue
            if (ie != null) {
                EntryPointIconEntity icona = createIcona(ie.getIcon());
                icona1 = icona.getId();
            }
        }
        if (pueClonar.getIcon2() != null) {
            EntryPointIconEntity ie = getEntryPointIconEntityDao().findById(pueClonar.getIcon2()); // se
                                                                                  // guarda
                                                                                  // id
                                                                                  // del
                                                                                  // icono
                                                                                  // en
                                                                                  // el
                                                                                  // pue
            if (ie != null) {
                EntryPointIconEntity icona = createIcona(ie.getIcon());
                icona2 = icona.getId();
            }
        }
        // Creamos el nuevo punto de entrada
        nouPUEClonat = getEntryPointEntityDao().newEntryPointEntity();
        nouPUEClonat.setCode(pueClonar.getCode());
        nouPUEClonat.setName(pueClonar.getName());
        nouPUEClonat.setVisible(pueClonar.getVisible());
        nouPUEClonat.setMenu(pueClonar.getMenu());
        nouPUEClonat.setNumberOfColumns(pueClonar.getNumberOfColumns());
        nouPUEClonat.setPublicAccess(pueClonar.getPublicAccess());
        nouPUEClonat.setMenuType(pueClonar.getMenuType());
        nouPUEClonat.setPublicAccess(pueClonar.getPublicAccess());
        nouPUEClonat.setIcon1(icona1);
        nouPUEClonat.setIcon2(icona2);
        nouPUEClonat.setApplicationID(pueClonar.getApplicationID());
        nouPUEClonat.setXmlEntryPoint(pueClonar.getXmlEntryPoint());
        getEntryPointEntityDao().create(nouPUEClonat);

        // Clonamos sus AUTORIZACIONES
        Collection<EntryPointRoleEntity> _autoRol = pueClonar.getAuthorizedRoles();
        Collection<EntryPointGroupEntity> _autoGrup = pueClonar.getAuthorizedGroups();
        Collection<EntryPointUserEntity> _autoUsu = pueClonar.getAuthorizedUsers();
        Collection autoRol = new HashSet(), autoGrup = new HashSet(), autoUsu = new HashSet();
        // ROL
        if (_autoRol != null && _autoRol.size() != 0) {
            for (Iterator<EntryPointRoleEntity> it = _autoRol.iterator(); it.hasNext(); ) {
                EntryPointRoleEntity auto = (EntryPointRoleEntity) it.next();
                EntryPointRoleEntity apu = getEntryPointRoleEntityDao().newEntryPointRoleEntity();
                apu.setRoleId(auto.getRoleId());
                apu.setAuthorizationLevel(auto.getAuthorizationLevel());
                apu.setEntryPoint(nouPUEClonat);
                getEntryPointRoleEntityDao().create(apu);
                autoRol.add(apu);
            }
        }
        nouPUEClonat.setAuthorizedRoles(autoRol);
        // GRUP
        if (_autoGrup != null && _autoGrup.size() != 0) {
            for (Iterator<EntryPointGroupEntity> it = _autoGrup.iterator(); it.hasNext(); ) {
                EntryPointGroupEntity auto = (EntryPointGroupEntity) it.next();
                EntryPointGroupEntity apu = getEntryPointGroupEntityDao().newEntryPointGroupEntity();
                apu.setGroupId(auto.getGroupId());
                apu.setAuhtorizationLevel(auto.getAuhtorizationLevel());
                apu.setEntryPoint(nouPUEClonat);
                getEntryPointGroupEntityDao().create(apu);
                autoGrup.add(apu);
            }
        }
        nouPUEClonat.setAuthorizedGroups(autoGrup);
        // USUARI
        if (_autoUsu != null && _autoUsu.size() != 0) {
            for (Iterator<EntryPointUserEntity> it = _autoUsu.iterator(); it.hasNext(); ) {
                EntryPointUserEntity auto = (EntryPointUserEntity) it.next();
                EntryPointUserEntity apu = getEntryPointUserEntityDao().newEntryPointUserEntity();
                apu.setUserId(auto.getUserId());
                apu.setAuthorizationLevel(auto.getAuthorizationLevel());
                apu.setEntryPoint(nouPUEClonat);
                getEntryPointUserEntityDao().create(apu);
                autoUsu.add(apu);
            }
        }
        nouPUEClonat.setAuthorizedUsers(autoUsu);
        // Clonamos sus EJECUCIONES:
        Collection _execs = pueClonar.getExecutionMethod();
        Collection<EntryPointExecutableEntity> execs = new HashSet<EntryPointExecutableEntity>();

        if (_execs != null && _execs.size() != 0) {
            for (Iterator it = _execs.iterator(); it.hasNext(); ) {
                EntryPointExecutableEntity exe = (EntryPointExecutableEntity) it.next();
                EntryPointExecutableEntity nou = getEntryPointExecutableEntityDao().newEntryPointExecutableEntity();
                nou.setScope(exe.getScope());
                nou.setExecutionCode(exe.getExecutionCode());
                nou.setContent(exe.getContent());
                nou.setEntryPoint(nouPUEClonat);
                getEntryPointExecutableEntityDao().create(nou);
                execs.add(nou);
            }
        }
        nouPUEClonat.setExecutionMethod(execs);

        // Clonamos los HIJOS si los tiene:
        Collection _fills = pueClonar.getParentEntryPointTree();
        Collection<EntryPointTreeEntity> fills = new HashSet<EntryPointTreeEntity>();
        if (_fills != null && _fills.size() != 0) {
            for (Iterator it = _fills.iterator(); it.hasNext(); ) {
                EntryPointTreeEntity actual = (EntryPointTreeEntity) it.next();
                EntryPointEntity monFill = clonaPuntEntrada(actual.getChildren());
                EntryPointTreeEntity arbre = getEntryPointTreeEntityDao().newEntryPointTreeEntity();
                arbre.setChildren(monFill);
                arbre.setOrder(actual.getOrder());
                arbre.setParent(nouPUEClonat);
                getEntryPointTreeEntityDao().create(arbre);
                fills.add(arbre);
            }
        }
        nouPUEClonat.setParentEntryPointTree(fills);
        // Actualizamos los cambios (árbol, ejecuciones, autorizaciones)
        getEntryPointEntityDao().update(nouPUEClonat);

        return nouPUEClonat;
    }

    protected boolean handleCopiaPuntEntrada(PuntEntrada puntEntradaCopiar,
            PuntEntrada puntEntradaMenuDesti) throws Exception {
        // 1) Verifiquem que el destí siga de tipus menú
        if (!"S".equals(puntEntradaMenuDesti.getMenu())) //$NON-NLS-1$
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.EntryPointTypeError")); //$NON-NLS-1$

        // 2) Verifiquem autoritzacions: origen, desti i pare del destí
        Long idParePuntEntradaClonar = puntEntradaCopiar.getIdPare();
        Long idPueOrigen = puntEntradaCopiar.getId();
        Long idPueDesti = puntEntradaMenuDesti.getId();
        Long idParePuntEntradaDesti = (!idPueDesti.equals(ROOT_ID)) ? puntEntradaMenuDesti
                .getIdPare() : ROOT_ID;
        if (idPueOrigen == null || idParePuntEntradaClonar == null || idPueDesti == null
                || idParePuntEntradaDesti == null)
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.CopyEntryPointConfirmChanges")); //$NON-NLS-1$

        // Analitzem l'arbre dels pares del node destí per verificar que no es
        // mou
        // un node origen dintre de la seua branca
        if (isOrigenAncestorDesti(idPueOrigen, idPueDesti))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NodeCopyError")); //$NON-NLS-1$

        // Obtenim l'entitat a clonar
        EntryPointEntity pueClonar = getEntryPointEntityDao().puntEntradaToEntity(puntEntradaCopiar);
        // Fem una còpia (iconas, autoritzacions, execucions, fills)
        EntryPointEntity nouPUEClonat = clonaPuntEntrada(pueClonar);

        // Hem d'afegir el nou fill clonat al pue destí
        String ordre = "0"; //$NON-NLS-1$
        Collection fillsDesti = getEntryPointTreeEntityDao().findByParent(idPueDesti);
        if (fillsDesti != null) {// Ens quedem en el fill de major ordre
            if (fillsDesti.size() == 0) // Para nodes menú sense fills
                ordre = "0"; //$NON-NLS-1$
            else { // Obtenim el seu fill "major" (de tipus List i estan
                   // ordenats per query en ordre ascendent)
                EntryPointTreeEntity fill = (EntryPointTreeEntity) ((List) fillsDesti).get(fillsDesti.size() - 1);
                int ordreFillMajor = fill.getOrder(); //Integer.parseInt(fill.getOrdre());
                ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
            }
        }
        // Obtenim el pare
        EntryPointEntity pueDesti = getEntryPointEntityDao().findById(idPueDesti);
        Collection<EntryPointTreeEntity> fillsNouPare = pueDesti.getParentEntryPointTree();
        
        EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao().newEntryPointTreeEntity();
        nouArbre.setChildren(nouPUEClonat);
        nouArbre.setOrder(Integer.parseInt(ordre)); //nouArbre.setOrdre(ordre);
        nouArbre.setParent(pueDesti);
        getEntryPointTreeEntityDao().create(nouArbre);
        fillsNouPare.add(nouArbre);
        pueDesti.setParentEntryPointTree(fillsNouPare);
        // Actualitzem el pare (hem modificat el seu arbre)
        getEntryPointEntityDao().update(pueDesti);

        return true;
    }

    protected boolean handleCopiaEnlacePuntEntrada(PuntEntrada puntEntradaCopiar,
            PuntEntrada puntEntradaMenuDesti) throws Exception {// NOTA: El
                                                                // destino
                                                                // SIEMPRE es un
                                                                // menú
        // 1) Verifiquem que el destí siga de tipus menú
        if (!"S".equals(puntEntradaMenuDesti.getMenu())) //$NON-NLS-1$
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.EntryPointTypeError")); //$NON-NLS-1$

        // 2) Verifiquem autoritzacions: origen, desti i pare del destí
        Long idParePuntEntradaCopiar = puntEntradaCopiar.getIdPare();
        Long idPueOrigen = puntEntradaCopiar.getId();
        Long idPueDesti = puntEntradaMenuDesti.getId();
        Long idParePuntEntradaDesti = (!idPueDesti.equals(ROOT_ID)) ? puntEntradaMenuDesti
                .getIdPare() : ROOT_ID;
        if (idPueOrigen == null || idParePuntEntradaCopiar == null || idPueDesti == null
                || idParePuntEntradaDesti == null)
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.CopyEntryPointConfirmChanges")); //$NON-NLS-1$

        // Analitzem l'arbre dels pares del node destí per verificar que no es
        // mou
        // un node origen dintre de la seua branca
        if (isOrigenAncestorDesti(idPueOrigen, idPueDesti))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NodeCopyError")); //$NON-NLS-1$

        // Tiene que tener permisos en el punto de entrada origen y destino (es
        // menú)
        if (!canAdmin(puntEntradaCopiar) || !canAdmin(puntEntradaMenuDesti))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToCopyEntryPoint")); //$NON-NLS-1$

        EntryPointEntity pareOrigenE = getEntryPointEntityDao().findById(idParePuntEntradaCopiar);
        PuntEntrada pareOrigen = getEntryPointEntityDao().toPuntEntrada(pareOrigenE);
        // Si el origen NO es menú tiene que tener permisos en el menú
        // contenedor padre del origen (para copiar)
        if (!"S".equals(puntEntradaCopiar.getMenu()) && !canAdmin(pareOrigen)) //$NON-NLS-1$
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToCopyEntryPointNoPermission")); //$NON-NLS-1$

        // Verificamos que no exista ya en el destino una copia del mismo
        Collection<EntryPointTreeEntity> fillsDesti = getEntryPointTreeEntityDao().findByParent(idPueDesti);

        if (fillsDesti != null)
            for (Iterator<EntryPointTreeEntity> it = fillsDesti.iterator(); it.hasNext(); ) {
            EntryPointTreeEntity arbreActual = it.next();
            if (arbreActual.getChildren().getId().equals(idPueOrigen)) throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.EntryPointDuplicated"));
        }

        // Obtenim L'ORDRE DE L'ARBRE destí (estan ordenats per ordre ascendent)
        String ordre = "0"; //$NON-NLS-1$
        if (fillsDesti != null) {// Ens quedem en el fill de major ordre
            if (fillsDesti.size() == 0) // Para nodes menú sense fills
                ordre = "0"; //$NON-NLS-1$
            else { // Obtenim el seu fill "major" (es de tipus List ordenat)
                EntryPointTreeEntity fill = ((List<EntryPointTreeEntity>) fillsDesti).get(fillsDesti.size() - 1);
                int ordreFillMajor = fill.getOrder(); //int ordreFillMajor = Integer.parseInt(fill.getOrdre());
                ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
            }
        }

        // Creamos una copia del árbol en el menú destino:
        EntryPointEntity nouPare = getEntryPointEntityDao().findById(idPueDesti);
        EntryPointEntity pueCopiat = getEntryPointEntityDao().findById(idPueOrigen);

        // Creen la nova entrada a l'arbre destí
        EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao().newEntryPointTreeEntity();
        nouArbre.setChildren(pueCopiat);
        nouArbre.setOrder(Integer.parseInt(ordre)); //nouArbre.setOrdre(ordre);
        nouArbre.setParent(nouPare);
        getEntryPointTreeEntityDao().create(nouArbre);

        // 1) Creem l'accés a la branca destí
        if (fillsDesti == null) {
            fillsDesti = new HashSet<EntryPointTreeEntity>();
        }
        fillsDesti.add(nouArbre);
        nouPare.setParentEntryPointTree(new HashSet<EntryPointTreeEntity>(fillsDesti));

        // 2) Actualitzem el menú destí (hem afegit un fill)
        getEntryPointEntityDao().update(nouPare);

        return true;
    }

    protected AutoritzacioPuntEntrada handleCreateAutoritzacio(PuntEntrada puntEntrada,
            AutoritzacioPuntEntrada autoritzacio) throws Exception {

        EntryPointEntity puntEntradaE = null;
        Long idEntitat = null;

        if (puntEntrada == null || puntEntrada.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$
        else {
            puntEntradaE = getEntryPointEntityDao().puntEntradaToEntity(puntEntrada);
        }

        if (autoritzacio.getIdEntitatAutoritzada() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAssignedEntityID")); //$NON-NLS-1$
        else
            idEntitat = autoritzacio.getIdEntitatAutoritzada();

        // Verificamos autoritzación
        if (!esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToMakeAutoritations")); //$NON-NLS-1$
        }

        // Obtenemos el nivel de autorización
        String nivell = null;
        if (autoritzacio.getDescripcioNivellAutoritzacio().equals(
                TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
            nivell = TipusAutoritzacioPuntEntrada.NIVELL_A;
        } else
            nivell = TipusAutoritzacioPuntEntrada.NIVELL_ALTRES;

        // Creamos la autorización
        String tipusAutoritzacio = autoritzacio.getTipusEntitatAutoritzada();
        if (TipusAutoritzacioPuntEntrada.ROL.equals(tipusAutoritzacio)) {
            // ROL: Creamos autorización
            EntryPointRoleEntity autoRol = getEntryPointRoleEntityDao().newEntryPointRoleEntity();
            autoRol.setAuthorizationLevel(nivell);
            autoRol.setRoleId(idEntitat);
            autoRol.setEntryPoint(puntEntradaE);
            getEntryPointRoleEntityDao().create(autoRol);

            auditarAutoritzacioPuntEntrada(
                    "C", //$NON-NLS-1$
                    tipusAutoritzacio,
                    autoritzacio.getDescripcioNivellAutoritzacio() + " - " //$NON-NLS-1$
                            + autoritzacio.getDescripcioEntitatAutoritzada() + " - " //$NON-NLS-1$
                            + puntEntrada.getNom());

            return getEntryPointRoleEntityDao().toAutoritzacioPuntEntrada(autoRol);
        } else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipusAutoritzacio)) {
            // GRUP: Creamos autorización
        	EntryPointGroupEntity autoGrup = getEntryPointGroupEntityDao().newEntryPointGroupEntity();
        	autoGrup.setAuhtorizationLevel(nivell);
        	autoGrup.setGroupId(idEntitat);
        	autoGrup.setEntryPoint(puntEntradaE);
            getEntryPointGroupEntityDao().create(autoGrup);

            auditarAutoritzacioPuntEntrada(
                    "C", //$NON-NLS-1$
                    tipusAutoritzacio,
                    autoritzacio.getDescripcioNivellAutoritzacio() + " - " //$NON-NLS-1$
                            + autoritzacio.getDescripcioEntitatAutoritzada() + " - " //$NON-NLS-1$
                            + puntEntrada.getNom());

            return getEntryPointGroupEntityDao().toAutoritzacioPuntEntrada(autoGrup);
        } else if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipusAutoritzacio)) {
            // USUARI: Creamos autorización
        	EntryPointUserEntity autoUsu = getEntryPointUserEntityDao().newEntryPointUserEntity();
        	autoUsu.setAuthorizationLevel(nivell);
        	autoUsu.setUserId(idEntitat);
        	autoUsu.setEntryPoint(puntEntradaE);
            getEntryPointUserEntityDao().create(autoUsu);

            auditarAutoritzacioPuntEntrada(
                    "C", //$NON-NLS-1$
                    tipusAutoritzacio,
                    autoritzacio.getDescripcioNivellAutoritzacio() + " - " //$NON-NLS-1$
                            + autoritzacio.getDescripcioEntitatAutoritzada() + " - " //$NON-NLS-1$
                            + puntEntrada.getNom());

            return getEntryPointUserEntityDao().toAutoritzacioPuntEntrada(autoUsu);
        }

        return null;
    }

    protected void handleDeleteAutoritzacio(PuntEntrada puntEntrada,
            AutoritzacioPuntEntrada autoritzacio) throws Exception {

        if (puntEntrada == null || puntEntrada.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$

        // Verificamos autoritzación
        if (!esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToDeleteAuthoritations")); //$NON-NLS-1$
        }

        if (autoritzacio.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NullAuthoritationID")); // Cas //$NON-NLS-1$
                                                                                 // de
                                                                                 // noves??

        // Borramos la autorización
        String tipusAutoritzacio = autoritzacio.getTipusEntitatAutoritzada();
        if (TipusAutoritzacioPuntEntrada.ROL.equals(tipusAutoritzacio)) {
            // ROL: Borramos autorización
            getEntryPointRoleEntityDao().remove(autoritzacio.getId());
        } else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipusAutoritzacio)) {
            // GRUP: Borramos autorización
            getEntryPointGroupEntityDao().remove(autoritzacio.getId());
        } else if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipusAutoritzacio)) {
            // USUARI: Borramos autorización
            getEntryPointUserEntityDao().remove(autoritzacio.getId());
        }

        auditarAutoritzacioPuntEntrada(
                "D", //$NON-NLS-1$
                tipusAutoritzacio,
                autoritzacio.getDescripcioNivellAutoritzacio() + " - " //$NON-NLS-1$
                        + autoritzacio.getDescripcioEntitatAutoritzada() + " - " //$NON-NLS-1$
                        + puntEntrada.getNom());

    }

    protected ExecucioPuntEntrada handleCreateExecucio(PuntEntrada puntEntrada,
            ExecucioPuntEntrada execucio) throws Exception {
        if (puntEntrada == null || puntEntrada.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$

        execucio.setIdPuntEntrada(puntEntrada.getId()); // Guardem id del punt
                                                        // d'entrada
        // Verificamos autoritzación
        if (!esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToMakeExecutionMethods")); //$NON-NLS-1$
        }

        // Les aplicacions de tipus menu no tenen execucions
        if ("S".equals(puntEntrada.getMenu())) { //$NON-NLS-1$
            return execucio; // No la creem
        }

        EntryPointExecutableEntity entity = getEntryPointExecutableEntityDao().execucioPuntEntradaToEntity(execucio);
        getEntryPointExecutableEntityDao().create(entity);

        auditarExecucioPuntEntrada("C", //$NON-NLS-1$
                puntEntrada.getNom() + " tipus " + execucio.getTipusMimeExecucio() + " ambit " //$NON-NLS-1$ //$NON-NLS-2$
                        + execucio.getAmbit());

        return getEntryPointExecutableEntityDao().toExecucioPuntEntrada(entity);
    }

    protected void handleDeleteExecucio(PuntEntrada puntEntrada, ExecucioPuntEntrada execucio)
            throws Exception { // Necesari?? Ja és fa al punt d'entrada
        if (puntEntrada == null || puntEntrada.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$

        // Verificamos autoritzación
        if (!esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToDeleteExecutionMethods")); //$NON-NLS-1$
        }

        if (execucio.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NullExecutionID")); // Cas //$NON-NLS-1$
                                                                             // de
                                                                             // noves??

        // Transformem a Entity
        EntryPointExecutableEntity entity = getEntryPointExecutableEntityDao().execucioPuntEntradaToEntity(execucio);
        getEntryPointExecutableEntityDao().remove(entity);

        auditarExecucioPuntEntrada("D", //$NON-NLS-1$
                puntEntrada.getNom() + " tipus " + execucio.getTipusMimeExecucio() + " ambit " //$NON-NLS-1$ //$NON-NLS-2$
                        + execucio.getAmbit());

    }

    protected ExecucioPuntEntrada handleUpdateExecucio(PuntEntrada puntEntrada,
            ExecucioPuntEntrada execucio) throws Exception {
        if (puntEntrada == null || puntEntrada.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$

        // Verificamos autoritzación
        if (!esAutoritzat(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToUpdateExecMethods")); //$NON-NLS-1$
        }

        if (execucio.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NullExecutionID")); // Cas //$NON-NLS-1$
                                                                              // de
                                                                              // noves??

        // Transformem a Entity
        EntryPointExecutableEntity entity = getEntryPointExecutableEntityDao().execucioPuntEntradaToEntity(execucio);
        getEntryPointExecutableEntityDao().update(entity);

        auditarExecucioPuntEntrada("U", //$NON-NLS-1$
                puntEntrada.getNom() + " tipus " + execucio.getTipusMimeExecucio() + " ambit " //$NON-NLS-1$ //$NON-NLS-2$
                        + execucio.getAmbit());

        return getEntryPointExecutableEntityDao().toExecucioPuntEntrada(entity);
    }

    protected Collection<ExecucioPuntEntrada> handleGetExecucions(PuntEntrada puntEntrada)
            throws Exception {
        // Si es vacío, devolvemos un linkedlist nuevo
        return puntEntrada.getExecucions() == null ? new LinkedList() : puntEntrada.getExecucions();
    }

    private void auditarPuntEntrada(String accio, String nomPuntEntrada) {
        Principal principal = Security.getPrincipal();
        String codiUsuari = principal.getName();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setAplicacio(nomPuntEntrada.length() >= 100 ? nomPuntEntrada.substring(0, 100)
                : nomPuntEntrada);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_PUNENT"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    private void auditarExecucioPuntEntrada(String accio, String execucio) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setAplicacio(execucio.length() >= 100 ? execucio.substring(0, 100) : execucio);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_EXEPUE"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    private void auditarAutoritzacioPuntEntrada(String accio, String tipus, String autoritzacio) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setAplicacio(autoritzacio.length() > 100 ? autoritzacio.substring(0, 100)
                : autoritzacio);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        String taula = ""; //$NON-NLS-1$
        if (tipus.equals(TipusAutoritzacioPuntEntrada.ROL))
            taula = "SC_ROLPUE"; //$NON-NLS-1$
        else if (tipus.equals(TipusAutoritzacioPuntEntrada.USUARI))
            taula = "SC_USUPUE"; //$NON-NLS-1$
        else if (tipus.equals(TipusAutoritzacioPuntEntrada.GRUP))
            taula = "SC_GRUPUE"; //$NON-NLS-1$
        auditoria.setObjecte(taula);
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    protected java.util.Collection<PuntEntrada> handleFindMenuChildren(
            es.caib.seycon.ng.comu.PuntEntrada puntEntrada) throws java.lang.Exception {
        // Comprovem autorització
        if (!canView(puntEntrada))
            return new LinkedList<PuntEntrada>();// throw new
                                                 // SeyconException("no autoritzat");

        Collection<EntryPointTreeEntity> arbre = getEntryPointTreeEntityDao().findByParent(puntEntrada.getId());
        if (arbre != null && arbre.size() != 0) {// Verificamos permisos
            Collection<PuntEntrada> fills = new LinkedList<PuntEntrada>();
            for (Iterator<EntryPointTreeEntity> it = arbre.iterator(); it.hasNext(); ) {
                EntryPointTreeEntity a = it.next();
                PuntEntrada pue = getEntryPointEntityDao().toPuntEntrada(a.getChildren());
                pue.setIdPare(a.getParent().getId());
                pue.setOrdre("" + a.getOrder());
                if ("S".equals(pue.getMenu()) && canAdmin(pue)) fills.add(pue);
            }
            return fills;

        }
        return new LinkedList<PuntEntrada>();
    }

    protected String handleValidaXMLPUE(PuntEntrada puntEntrada) throws Exception {
        String contingut = puntEntrada.getXmlPUE();

        if (!"".equals(contingut)) { //$NON-NLS-1$

            try {
                // Validem el document
                // new
                // es.caib.seycon.mazinger.compiler.Compile().parse(contingut);
                org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader(true);
                // set the validation feature to true to report validation
                // errors
                reader.setFeature("http://xml.org/sax/features/validation", true); //$NON-NLS-1$

                // set the validation/schema feature to true to report
                // validation errors
                // against a schema
                reader.setFeature("http://apache.org/xml/features/validation/schema", true); //$NON-NLS-1$
                // set the validation/schema-full-checking feature to true to
                // enable
                // full schema, grammar-constraint checking
                reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", //$NON-NLS-1$
                        true);
                // set the schema
                reader.setProperty(
                        "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", //$NON-NLS-1$
                        "/es/caib/seycon/mazinger/Mazinger.xsd"); //$NON-NLS-1$
                // set the entity resolver (to load the schema with
                // getResourceAsStream)
                reader.getXMLReader().setEntityResolver(new SchemaLoader());
                reader.setEntityResolver(new SchemaLoader());

                Document doc = reader.read(new ByteArrayInputStream(contingut.getBytes("UTF-8"))); //$NON-NLS-1$

            } catch (Exception ex) {
                return ex.getMessage(); // Retornem l'excepció com error de
                                        // Validació
            }
        }
        return ""; //$NON-NLS-1$
    }

    public class SchemaLoader implements EntityResolver {
        public static final String FILE_SCHEME = "file://"; //$NON-NLS-1$

        public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
            InputStream input = SchemaLoader.class
                    .getResourceAsStream("/es/caib/seycon/mazinger/Mazinger.xsd"); //$NON-NLS-1$
            return new InputSource(input);
        }
    }

    protected Collection<PuntEntrada> handleFindPuntsEntrada(java.lang.String nomPUE,
            java.lang.String codiPUE, java.lang.String codiAplicacio, java.lang.String codiRol,
            java.lang.String codiGrup, java.lang.String codiUsuari) throws Exception {
        if ("%".equals(nomPUE) && "%".equals(codiPUE) && "%".equals(codiAplicacio) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                && "%".equals(codiRol) && "%".equals(codiGrup) && "%".equals(codiUsuari)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            LinkedList<PuntEntrada> root = new LinkedList<PuntEntrada>();
            root.add(findRoot());
            return root;
        } else {
            if ("%".equals(nomPUE)) //$NON-NLS-1$
                nomPUE = null;
            if ("%".equals(codiPUE)) //$NON-NLS-1$
                codiPUE = null;
            if ("%".equals(codiAplicacio)) //$NON-NLS-1$
                codiAplicacio = null;
            if ("%".equals(codiRol)) //$NON-NLS-1$
                codiRol = null;
            if ("%".equals(codiGrup)) //$NON-NLS-1$
                codiGrup = null;
            if ("%".equals(codiUsuari)) //$NON-NLS-1$
                codiUsuari = null;

            // Ara filtrem
            InformationSystemEntity aplicacio = null;
            if (codiAplicacio != null) {
                aplicacio = getInformationSystemEntityDao().findByCode(codiAplicacio);
                if (aplicacio == null) {
                    throw new SeyconException(String.format(
                            Messages.getString("PuntEntradaServiceImpl.ApplicationNotFounded"), codiAplicacio)); //$NON-NLS-1$
                }
            }
            Collection<EntryPointEntity> cerca = getEntryPointEntityDao().findByCriteria(nomPUE, codiPUE);
            if (aplicacio != null) {
                Collection<EntryPointEntity> resFiltrats = new LinkedList<EntryPointEntity>();
                for (Iterator<EntryPointEntity> it = cerca.iterator(); it.hasNext(); ) {
                    EntryPointEntity pue = it.next();
                    if (pue.getApplicationID().equals(aplicacio.getId())) resFiltrats.add(pue);
                }
                cerca = resFiltrats; // Filtrem
            }

            List<PuntEntrada> cercaVO = getEntryPointEntityDao().toPuntEntradaList(cerca);

            // Mirem les autoritzacions dels resultats de la cerca
            for (Iterator<PuntEntrada> it = cercaVO.iterator(); it.hasNext();) {
                PuntEntrada pue = it.next();
                if (!canView(pue))
                    it.remove();
            }
            return cercaVO;
        }
    }

    class Graph {
        private Map<EntryPointEntity, LinkedHashSet<EntryPointEntity>> map = new HashMap<EntryPointEntity, LinkedHashSet<EntryPointEntity>>();

        public void addEdge(EntryPointEntity node1, EntryPointEntity node2) {
            LinkedHashSet<EntryPointEntity> adjacent = map.get(node1);
            if (adjacent == null) {
                adjacent = new LinkedHashSet<EntryPointEntity>();
                map.put(node1, adjacent);
            }
            adjacent.add(node2);
        }

        public void addTwoWayVertex(EntryPointEntity node1, EntryPointEntity node2) {
            addEdge(node1, node2);
            addEdge(node2, node1);
        }

        public boolean isConnected(EntryPointEntity node1, EntryPointEntity node2) {
            Set adjacent = map.get(node1);
            if (adjacent == null) {
                return false;
            }
            return adjacent.contains(node2);
        }

        public LinkedList<EntryPointEntity> adjacentNodes(EntryPointEntity last) {
            LinkedHashSet<EntryPointEntity> adjacent = map.get(last);
            if (adjacent == null) {
                return new LinkedList<EntryPointEntity>();
            }
            return new LinkedList<EntryPointEntity>(adjacent);
        }
    }

    class Search {

        private void breadthFirst(Graph graph, LinkedList<EntryPointEntity> visited, LinkedList<String> rutes) {
            LinkedList<EntryPointEntity> nodes = graph.adjacentNodes(visited.getLast());
            // examine adjacent nodes
            for (EntryPointEntity node : nodes) {
                if (visited.contains(node)) {
                    continue;
                }
                if (node.getId().equals(ROOT_ID)) {
                    visited.add(node);
                    rutes.add(printPath(visited));
                    visited.removeLast();
                    break;
                }
            }
            // in breadth-first, recursion needs to come after visiting adjacent
            // nodes
            for (EntryPointEntity node : nodes) {
                if (visited.contains(node) || node.getId().equals(ROOT_ID)) {
                    continue;
                }
                visited.addLast(node);
                breadthFirst(graph, visited, rutes);
                visited.removeLast();
            }
        }

        public String printPath(LinkedList<EntryPointEntity> visited) {
            String res = ""; //$NON-NLS-1$

            // Fem el cami invers
            /*
             * for (PuntEntradaEntity node : visited) { res+= node.getNom()
             * +" < "; } if (res.endsWith(" < ")) res =
             * res.substring(0,res.lastIndexOf(" < "));
             */
            for (int i = (visited.size() - 1); i >= 0; i--) {
                res += visited.get(i).getName() + " > ";
            }
            if (res.endsWith(" > ")) //$NON-NLS-1$
                res = res.substring(0, res.lastIndexOf(" > ")); //$NON-NLS-1$
            return res;
        }

    }

    private void getGraphInversPUE(EntryPointEntity pue, Graph arbre) {

        Collection pares = getEntryPointTreeEntityDao().findByChildren(pue.getId());

        for (Iterator it = pares.iterator(); it.hasNext(); ) {
            EntryPointTreeEntity a = (EntryPointTreeEntity) it.next();
            EntryPointEntity pare = a.getParent();
            arbre.addEdge(pue, pare);
            getGraphInversPUE(pare, arbre);
        }

    }

    protected Collection<String> handleGetArbreInversPuntEntrada(PuntEntrada puntEntrada)
            throws Exception {

        Graph arbreInvers = new Graph();
        EntryPointEntity pue = getEntryPointEntityDao().puntEntradaToEntity(puntEntrada);
        getGraphInversPUE(pue, arbreInvers);

        LinkedList<EntryPointEntity> visited = new LinkedList<EntryPointEntity>();
        visited.add(pue);
        LinkedList<String> rutes = new LinkedList<String>();
        new Search().breadthFirst(arbreInvers, visited, rutes);
        return rutes;
    }

    /**
     * Fem un mètode especific per a l'accés des de la Intranet (!!) - s'ha de
     * probar
     * 
     * @param idPuntEntrada
     * @param nivell
     * @return
     * @throws Exception
     */
    protected boolean handleEsAutoritzat(String codiUsuari, Long idPuntEntrada, String nivell)
            throws Exception {

        // Carreguem el punt d'entrada
        EntryPointEntity puntEntradaE = getEntryPointEntityDao().findById(idPuntEntrada);
        if (puntEntradaE == null)
            return false;
        PuntEntrada puntEntrada = getEntryPointEntityDao().toPuntEntrada(puntEntradaE);

        String user = getPrincipal().getName();
        Collection<AutoritzacioPuntEntrada> autoritzacions = puntEntrada.getAutoritzacions();
        if (autoritzacions == null)
            return false;
        Iterator<AutoritzacioPuntEntrada> iterator = autoritzacions.iterator();
        PermissionsCache permisos = getCurrentAuthorizations();
        boolean trobat = false;

        while (!trobat && iterator.hasNext()) {
            AutoritzacioPuntEntrada auto = iterator.next();
            // Puede ser de 3 tipos: usuario, rol o grupo
            String tipus = auto.getTipusEntitatAutoritzada();
            String codiAuto = auto.getCodiEntitatAutoritzada(); // és ÚNIC !!
            if (tipus.equals(TipusAutoritzacioPuntEntrada.USUARI)) {
                if (user.equals(codiAuto)) {
                    // Comprovem el nivell d'autorització
                    String nivellAuto = auto.getDescripcioNivellAutoritzacio();
                    trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(nivellAuto) || nivellAuto
                            .equals(nivell));
                }
            } else if (tipus.equals(TipusAutoritzacioPuntEntrada.ROL)) {
                // Lo buscamos en la hash
                if (permisos.getRolsUsuariPUE().contains(auto.getIdEntitatAutoritzada())) {// codiAuto.toUpperCase()))
                    // {
                    // Comprovem el nivell d'autorització
                    String nivellAuto = auto.getDescripcioNivellAutoritzacio();
                    trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(nivellAuto) || nivellAuto
                            .equals(nivell));
                }

            } else if (tipus.equals(TipusAutoritzacioPuntEntrada.GRUP)) {
                // Lo buscamos en la hash
                if (permisos.getGrupsUsuariPUE().contains(codiAuto)) {
                    // Comprovem el nivell d'autorització
                    String nivellAuto = auto.getDescripcioNivellAutoritzacio();
                    trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(nivellAuto) || nivellAuto
                            .equals(nivell));
                }
            }
        }

        return trobat;
    }

    protected boolean handleHasAnyACLPUE(String codiUsuari) throws Exception {
        // Cerquem per ACLs (només d'administració)
        // and you're faster than the shoot from a gun
        // comencem per número de files de major a menor: rol - grup - usu

        PermissionsCache permisos = getCurrentAuthorizations();

        // ROL: només els de permís d'aministrador (!!)
        Collection autoRol = getEntryPointRoleEntityDao().
        		query("from com.soffid.iam.model.EntryPointRoleEntity where authorizationLevel=\'A\'", new Parameter[0]); //$NON-NLS-1$
        for (Iterator it = autoRol.iterator(); it.hasNext(); ) {
            EntryPointRoleEntity auto = (EntryPointRoleEntity) it.next();
            if (permisos.getRolsUsuariPUE().contains(auto.getRoleId())) {
                if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(auto.getAuthorizationLevel())) return true;
            }
        }

        // GRUP: només els de permís d'aministrador (!!)
        List<EntryPointGroupEntity> autoGrup = getEntryPointGroupEntityDao().
        		query("from com.soffid.iam.model.EntryPointGroupEntity where authorizationLevel=\'A\'", new Parameter[0]); //$NON-NLS-1$
        List<AutoritzacioPuntEntrada> autoGrupVO = getEntryPointGroupEntityDao().toAutoritzacioPuntEntradaList(autoGrup);
        for (Iterator<AutoritzacioPuntEntrada> it = autoGrupVO.iterator(); it.hasNext();) {
            // ho mirem per entity (per optimitzar)
            AutoritzacioPuntEntrada auto = it.next();
            if (permisos.getGrupsUsuariPUE().contains(auto.getCodiEntitatAutoritzada())) {
                // Només administradors
                if (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(auto
                        .getDescripcioNivellAutoritzacio()))
                    return true;
            }
        }

        // USUARI: només els de permís d'aministrador (!!)
        List<EntryPointUserEntity> autoUsu = getEntryPointUserEntityDao().
        		query("from com.soffid.iam.model.EntryPointUserEntity where authorizationLevel=\'A\'", new Parameter[0]); //$NON-NLS-1$
        List<AutoritzacioPuntEntrada> autoUsuVO = getEntryPointUserEntityDao().toAutoritzacioPuntEntradaList(autoUsu);
        for (Iterator<AutoritzacioPuntEntrada> it = autoUsuVO.iterator(); it.hasNext();) {
            AutoritzacioPuntEntrada auto = it.next();
            if (codiUsuari.equals(auto.getCodiEntitatAutoritzada())) {
                // Només administradors
                if (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO.equals(auto
                        .getDescripcioNivellAutoritzacio()))
                    return true;
            }
        }

        // alea jacta est
        return false;
    }

    @Override
    protected PuntEntrada handleFindPuntEntradaById(long id) throws Exception {
        EntryPointEntity pueEntity = getEntryPointEntityDao().findById(id);
        PuntEntrada pue = getEntryPointEntityDao().toPuntEntrada(pueEntity);

        if (canView(pue))
            return pue;
        else
            return null;
    }

	protected EntryPointIconEntity createIcona(byte[] b) throws InternalErrorException {
		try
		{
			EntryPointIconEntity icona = getEntryPointIconEntityDao().newEntryPointIconEntity();
			icona.setIcon(b);
			getEntryPointIconEntityDao().create(icona);
			return icona;
		}

		catch (Exception e)
		{
			throw new InternalErrorException(
							Messages.getString("PuntEntradaServiceImpl.ImageTooBigError")); //$NON-NLS-1$
		}
	}

    @Override
	protected java.security.Principal getPrincipal()
	{
		return Security.getPrincipal();
	}
}

class PermissionsCache {
    private Set<String> grupsUsuariPUE;
    private Set<Long> rolsUsuariPUE;
    Date expirationDate;

    public PermissionsCache() {
        expirationDate = new Date(System.currentTimeMillis() + 600000); // 10
                                                                        // mins
                                                                        // cache
        grupsUsuariPUE = new HashSet<String>();
        rolsUsuariPUE = new HashSet<Long>();
    }

    public boolean isValid() {
        return expirationDate.after(new Date());
    }

    public Set<String> getGrupsUsuariPUE() {
        return grupsUsuariPUE;
    }

    public void setGrupsUsuariPUE(Set<String> grupsUsuariPUE) {
        this.grupsUsuariPUE = grupsUsuariPUE;
    }

    public Set<Long> getRolsUsuariPUE() {
        return rolsUsuariPUE;
    }

    public void setRolsUsuariPUE(Set<Long> rolsUsuariPUE) {
        this.rolsUsuariPUE = rolsUsuariPUE;
    }
    
}
