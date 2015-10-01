// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

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
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.ArbrePuntEntradaEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity;
import es.caib.seycon.ng.model.AutoritzacioPUERolEntity;
import es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity;
import es.caib.seycon.ng.model.ExecucioPuntEntradaEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.IconaEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.PuntEntradaEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

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

        PuntEntradaEntity pareE = getPuntEntradaEntityDao().findById(puntEntrada.getIdPare());

        if (pareE == null)
            throw new CreateException(Messages.getString("PuntEntradaServiceImpl.ParentMenuNotFounded")); //$NON-NLS-1$
        if (!"S".equals(pareE.getMenu())) { //$NON-NLS-1$
            throw new CreateException(Messages.getString("PuntEntradaServiceImpl.ParentNotAMenu")); //$NON-NLS-1$
        }
        // Verificamos autorización del padre
        PuntEntrada pare = getPuntEntradaEntityDao().toPuntEntrada(pareE);
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
        PuntEntradaEntity entity = getPuntEntradaEntityDao().puntEntradaToEntity(puntEntrada);

        // CREEM L'ENTITAT (!!)
        getPuntEntradaEntityDao().create(entity);

        // Creem l'ARBRE del punt d'entrada
        int ordre = 0; //$NON-NLS-1$	//String ordre = "0";
        // Obtenim L'ORDRE DE L'ARBRE des dels fills del pare (estan ordenats
        // per ordre ascendent)
        List fills = (List) getArbrePuntEntradaEntityDao().findByPare(puntEntrada.getIdPare());
        if (fills != null) {// Ens quedem en el fill de major ordre
            if (fills.size() == 0) // Para nodes menú sense fills
                ordre = 0; //$NON-NLS-1$	//ordre = "0";
            else { // Obtenim el seu fill "major"
                ArbrePuntEntradaEntity fill = (ArbrePuntEntradaEntity) fills.get(fills.size() - 1);
                int ordreFillMajor = fill.getOrdre();	//Integer.parseInt(fill.getOrdre());
                ordre = ordreFillMajor + 1; //$NON-NLS-1$	//"" + (ordreFillMajor + 1);
            }
        }
        ArbrePuntEntradaEntity arbre = getArbrePuntEntradaEntityDao().newArbrePuntEntradaEntity();
        arbre.setOrdre(ordre);
        arbre.setFill(entity);
        arbre.setPare(pareE);
        HashSet<ArbrePuntEntradaEntity> monArbre = new HashSet<ArbrePuntEntradaEntity>();
        monArbre.add(arbre);
        // Establim l'arbre
        entity.setArbrePuntEntradaSocFill(monArbre);

        // Creem les relacions del punt d'entrada
        // Arbre
        getArbrePuntEntradaEntityDao().create(arbre);

        // Creem les icones
        IconaEntity icona1 = null;
        if (puntEntrada.getImgIcona1() != null && puntEntrada.getImgIcona1().length != 0) {
            // Creem l'icona
            icona1 = createIcona(puntEntrada.getImgIcona1());
            entity.setIcona1(icona1.getId());
        }
        IconaEntity icona2 = null;
        if (puntEntrada.getImgIcona2() != null && puntEntrada.getImgIcona2().length != 0) {
            // S'ha actualitzat l'icona: creem una nova
            icona2 = createIcona(puntEntrada.getImgIcona2());
            entity.setIcona2(icona2.getId());
        }

        // Actualitzem l'entitat (amb les relacions)
        getPuntEntradaEntityDao().update(entity);

        // Afegim id del pare (per poder moure'l ara mateix)
        PuntEntrada res = getPuntEntradaEntityDao().toPuntEntrada(entity);
        res.setIdPare(idPare);

        // Assignem iconas (en el toVO encara no poden estar en la BD)
        if (icona1 != null) {
            res.setIdIcona1(icona1.getId());
            res.setImgIcona1(icona1.getIcona());
        }
        if (icona2 != null) {
            res.setIdIcona2(icona2.getId());
            res.setImgIcona2(icona2.getIcona());
        }

        // Posem la ruta que s'ha obtingut en el ZUL a partir del pare
        if (puntEntrada.getRutaArbre() != null)
            res.setRutaArbre(puntEntrada.getRutaArbre());

        auditarPuntEntrada("C", res.getNom() + Messages.getString("PuntEntradaServiceImpl.15") + pareE.getNom()); //$NON-NLS-1$ //$NON-NLS-2$

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
        PuntEntradaEntity entity = getPuntEntradaEntityDao().load(puntEntrada.getId());
        boolean updatingRoot = entity != null && ROOT_TAG.equals(entity.getCodi()); 
        getPuntEntradaEntityDao().puntEntradaToEntity(puntEntrada, entity, true);
        if (updatingRoot) {
            entity.setCodi(ROOT_TAG);
        } else {
            if (ROOT_TAG.equals(puntEntrada.getCodi())) {
                entity.setCodi(null);
            }
        }

        // Si és e tipus menú, esborrem execucions:
        if ("S".equals(puntEntrada.getMenu())) { //$NON-NLS-1$
            entity.setMetodesExecucio(new HashSet<ExecucioPuntEntradaEntity>()); // esborrem
                                                                                 // execucions
        }

        // Verifiquem les icones:
        // ACTUALITZACIONS
        // UPDATE: Ja té icona, i s'ha posta una nova
        if (entity.getIcona1() != null && puntEntrada.getImgIcona1() != null
                && puntEntrada.getImgIcona1().length != 0 && puntEntrada.getIdIcona1() == null) {
            // Esborrem l'icona anterior
            getIconaEntityDao().remove(entity.getIcona1()); // Per id
            // S'ha actualitzat l'icona: creem una nova
            IconaEntity icona1 = createIcona(puntEntrada.getImgIcona1());
            entity.setIcona1(icona1.getId());
        }
        if (entity.getIcona2() != null && puntEntrada.getImgIcona2() != null
                && puntEntrada.getImgIcona2().length != 0 && puntEntrada.getIdIcona2() == null) {
            // Esborrem l'icona anterior
            getIconaEntityDao().remove(entity.getIcona2()); // Per id
            // S'ha actualitzat l'icona: creem una nova
            IconaEntity icona2 = createIcona(puntEntrada.getImgIcona2());
            entity.setIcona2(icona2.getId());
        }
        // ADD: NOVES ICONES (no existien abans)
        if (entity.getIcona1() == null && puntEntrada.getImgIcona1() != null
                && puntEntrada.getImgIcona1().length != 0) {
            // Creem l'icona
            IconaEntity icona1 = createIcona(puntEntrada.getImgIcona1());
            entity.setIcona1(icona1.getId());
        }
        if (entity.getIcona2() == null && puntEntrada.getImgIcona2() != null
                && puntEntrada.getImgIcona2().length != 0) {
            // S'ha actualitzat l'icona: creem una nova
            IconaEntity icona2 = createIcona(puntEntrada.getImgIcona2());
            entity.setIcona2(icona2.getId());
        }
        // DELETE: Esborrem l'icona assignada
        if (entity.getIcona1() != null && puntEntrada.getImgIcona1() == null) {
            // Esborrem l'icona anterior
            getIconaEntityDao().remove(entity.getIcona1()); // Per id
            entity.setIcona1(null);
        }
        if (entity.getIcona2() != null && puntEntrada.getImgIcona2() == null) {
            // Esborrem l'icona anterior
            getIconaEntityDao().remove(entity.getIcona2()); // Per id
            entity.setIcona2(null);
        }

        
        getPuntEntradaEntityDao().update(entity);

        auditarPuntEntrada("U", entity.getNom()); //$NON-NLS-1$

        return getPuntEntradaEntityDao().toPuntEntrada(entity);
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
        PuntEntradaEntity entity = getPuntEntradaEntityDao().puntEntradaToEntity(puntEntrada);

        // Analizamos los "enlaces" para saber si sólo tenemos un padre o varios
        // Si sólo hay uno, se borra el punt d'entrada, si hay varios: se borra
        // en enlace(link)
        Collection mosPares = entity.getArbrePuntEntradaSocFill();

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
            Collection mosFills = entity.getArbrePuntEntradaSocPare();
            if (mosFills.size() != 0) {
                throw new RemoveException(String.format(Messages.getString("PuntEntradaServiceImpl.ChildEntryPointError"), //$NON-NLS-1$
                        puntEntrada.getNom()));
            }
        }

        // Ajustem l'ordre de l'arbre del pare
        boolean mhetrobat = false;
        // Trobem els fills (estan ordenats pel camp ordre) (!!)
        Collection<ArbrePuntEntradaEntity> arbrePare = getArbrePuntEntradaEntityDao().findByPare(
                idPare);
        ArbrePuntEntradaEntity arbreEsborrar = null;
        for (Iterator<ArbrePuntEntradaEntity> it = arbrePare.iterator(); it.hasNext();) {
            ArbrePuntEntradaEntity actual = it.next();
            if (mhetrobat) {
                int ordre = actual.getOrdre() - 1; // restem //Integer.parseInt(actual.getOrdre()) - 1;
                                                                     // una
                                                                     // posicio
                actual.setOrdre(ordre); //$NON-NLS-1$	//actual.setOrdre("" + ordre);
            } else if (actual.getFill().getId().equals(entity.getId())) {
                mhetrobat = true;
                it.remove(); // Ens esborrem
                arbreEsborrar = actual;
            }
        }

        // Actualiztem l'arbre del pare (reordenació de nodes i esborrat del pue
        // actual)
        if (arbreEsborrar != null)
            getArbrePuntEntradaEntityDao().remove(arbreEsborrar);
        getArbrePuntEntradaEntityDao().update(arbrePare);

        if (!enlazado) {
            // Ens esborrem de l'arbre
            // getArbrePuntEntradaEntityDao().remove(entity.getArbrePuntEntradaSocFill()); // només
                                                                                        // serà
                                                                                        // 1
            entity.setArbrePuntEntradaSocFill(new HashSet<ArbrePuntEntradaEntity>());
            // Actualitzem l'arbre del pare
            // getArbrePuntEntradaEntityDao().update(arbrePare);

            // AUTORITZACIONS
            Collection autoUsu = entity.getAutoritzaUsuari();
            Collection autoRol = entity.getAutoritzaRol();
            Collection autoGrup = entity.getAutoritzaGrup();
            // les esborrem
            getAutoritzacioPUEUsuariEntityDao().remove(autoUsu);
            getAutoritzacioPUERolEntityDao().remove(autoRol);
            getAutoritzacioPUEGrupEntityDao().remove(autoGrup);
            entity.setAutoritzaUsuari(null);
            entity.setAutoritzaRol(null);
            entity.setAutoritzaGrup(null);

            // EJECUCIONS
            Collection<ExecucioPuntEntradaEntity> execucions = entity.getMetodesExecucio();
            getExecucioPuntEntradaEntityDao().remove(execucions);
            entity.setMetodesExecucio(null);

            // ICONAS
            if (entity.getIcona1() != null) {
                getIconaEntityDao().remove(entity.getIcona1());
                entity.setIcona1(null);
            }
            if (entity.getIcona2() != null) {
                getIconaEntityDao().remove(entity.getIcona2());
                entity.setIcona2(null);
            }

            getPuntEntradaEntityDao().remove(entity);
            auditarPuntEntrada("D", "Esborrat punt d'entrada '" + entity.getNom() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
            auditarPuntEntrada("D", "Esborrat enllaç del punt d'entrada '" + entity.getNom() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

    }

    /**
     * @see es.caib.seycon.ng.servei.PuntEntradaService#findRoot()
     */
    protected es.caib.seycon.ng.comu.PuntEntrada handleFindRoot() throws java.lang.Exception {

        PuntEntradaEntity entity = getPuntEntradaEntityDao().findById(ROOT_ID);
        if (entity == null) {
            List<PuntEntradaEntity> entities = getPuntEntradaEntityDao().findByCriteris("%", ROOT_TAG); //$NON-NLS-1$
            if (entities.size() > 0)
                entity = entities.get(0);
        }
        if (entity == null) {
            PuntEntradaEntity root = getPuntEntradaEntityDao().newPuntEntradaEntity(); 
            root.setId(ROOT_ID);
            root.setCodi(ROOT_TAG);
            root.setEsPublic("S"); //$NON-NLS-1$
            AplicacioEntity app = getAplicacioEntityDao().findByCodi("SEU"); //$NON-NLS-1$
            if (app == null)
                root.setIdAplicacio(new Long(0)); 
            else
                root.setIdAplicacio(app.getId());
            root.setMenu("S"); //$NON-NLS-1$
            root.setNom("Acme Company"); //$NON-NLS-1$
            root.setTipusMenu("L"); //$NON-NLS-1$
            root.setVisible("S"); //$NON-NLS-1$
            getPuntEntradaEntityDao().create(root);
            return getPuntEntradaEntityDao().toPuntEntrada(root);
        } else
            return getPuntEntradaEntityDao().toPuntEntrada(entity);
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

        Collection<ArbrePuntEntradaEntity> arbre = getArbrePuntEntradaEntityDao().findByPare(
                puntEntrada.getId());
        if (arbre != null && arbre.size() != 0) {// Verificamos permisos
            Collection<PuntEntrada> fills = new LinkedList<PuntEntrada>();
            for (Iterator<ArbrePuntEntradaEntity> it = arbre.iterator(); it.hasNext();) {
                ArbrePuntEntradaEntity a = it.next();
                // Només si tenim permis
                PuntEntrada pue = getPuntEntradaEntityDao().toPuntEntrada(a.getFill());
                // Establim la posició a l'arbre del punt d'entrada (per poder
                // moure'l)
                pue.setIdPare(a.getPare().getId());
                pue.setOrdre("" + a.getOrdre());		//pue.setOrdre(a.getOrdre()); //$NON-NLS-1$
                // Formen la ruta a partir del pare
                String rutaPare = puntEntrada.getRutaArbre() != null ? puntEntrada.getRutaArbre()
                        + " > " : ""; //$NON-NLS-1$ //$NON-NLS-2$
                pue.setRutaArbre(rutaPare + puntEntrada.getNom());
                if (canView(pue))
                    fills.add(pue);
            }
            return fills;

        }
        return new LinkedList<PuntEntrada>();
    }

    protected Collection<TipusExecucioPuntEntrada> handleGetAllTipusMimeExecucio() throws Exception {
        List<TipusExecucioPuntEntradaEntity> tipusMime = getTipusExecucioPuntEntradaEntityDao()
                .loadAll();
        if (tipusMime.isEmpty()) {
            TipusExecucioPuntEntradaEntity punt =
            		getTipusExecucioPuntEntradaEntityDao().
            			newTipusExecucioPuntEntradaEntity();
            punt.setCodi("URL"); //$NON-NLS-1$
            punt.setPlantilla("http://"); //$NON-NLS-1$
            punt.setTipusMime("text/html"); //$NON-NLS-1$
            getTipusExecucioPuntEntradaEntityDao().create(punt);
            tipusMime.add(punt);
            punt = getTipusExecucioPuntEntradaEntityDao().
            		newTipusExecucioPuntEntradaEntity();
            punt.setCodi("MZN"); //$NON-NLS-1$
            punt.setPlantilla("exec ( ... );"); //$NON-NLS-1$
            punt.setTipusMime("x-application/x-mazinger-script"); //$NON-NLS-1$
            getTipusExecucioPuntEntradaEntityDao().create(punt);
            tipusMime.add(punt);
        }
        return getTipusExecucioPuntEntradaEntityDao().toTipusExecucioPuntEntradaList(tipusMime); // toVO
    }

    protected Collection<Aplicacio> handleGetAllAplicacions(Boolean aplicacioBuida)
            throws Exception {
        // D'aquesta manera estan ordenades per nom
        Collection<AplicacioEntity> aplicacions = getAplicacioEntityDao()
                .query("select aplicacioEntity from es.caib.seycon.ng.model.AplicacioEntity aplicacioEntity order by aplicacioEntity.codi, aplicacioEntity.nom", //$NON-NLS-1$
                        new Parameter[] {});
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
        for (Iterator<AplicacioEntity> it = aplicacions.iterator(); it.hasNext();) {
            AplicacioEntity ap = it.next();
            Aplicacio apvo = getAplicacioEntityDao().toAplicacio(ap);
            apvo.setNom(apvo.getCodi() + " - " + apvo.getNom()); // transformació //$NON-NLS-1$
                                                                 // específica..
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

        UsuariEntity usuari = getUsuariEntityDao().findByCodi(user);
        if (usuari != null) {
            PermissionsCache entry = new PermissionsCache();

            // Grups de l'usuari
            GrupEntity gprimari = usuari.getGrupPrimari();
            Collection<UsuariGrupEntity> grups = usuari.getGrupsSecundaris();
            if (gprimari != null) {
                entry.getGrupsUsuariPUE().add(gprimari.getCodi());// ,gprimari);
            }
            if (grups != null) {
                for (Iterator<UsuariGrupEntity> it = grups.iterator(); it.hasNext();) {
                    UsuariGrupEntity uge = it.next();
                    GrupEntity g = uge.getGrup();
                    // getCodi ens dóna un identificador únic del grup
                    entry.getGrupsUsuariPUE().add(g.getCodi());// ,g);
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
        List<ArbrePuntEntradaEntity> paresDesti = getArbrePuntEntradaEntityDao()
                .findByFill(destiId);

        if (paresDesti != null) {
            for (Iterator<ArbrePuntEntradaEntity> it = paresDesti.iterator(); it.hasNext();) {
                ArbrePuntEntradaEntity arbreDesti = it.next();
                Long p = arbreDesti.getPare().getId();
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
            List<ArbrePuntEntradaEntity> branca = getArbrePuntEntradaEntityDao().findByPare(
                    idPareArbreOrigen);
            // Ja està ordenada pel camp ordre (SEMPRE tindrá fills)
            if (branca != null && branca.size() != 0) {
                // És l'arbre de fills del mateix pare ordenat per Ordre
                int posOrigen = -1;
                int posDesti = -1;
                int pos = -1;
                // Obtenim la posició de l'element a moure i la nova posició
                // (destí)
                for (Iterator<ArbrePuntEntradaEntity> it = branca.iterator(); it.hasNext();) {
                    pos++;
                    ArbrePuntEntradaEntity actual = it.next();
                    if (actual.getFill().getId().equals(idPueOrigen)) {
                        posOrigen = pos;
                    } else if (actual.getFill().getId().equals(idPueDesti)) {
                        posDesti = pos;
                    }
                }
                if (posOrigen == -1 || posDesti == -1)
                    return false; // No trobats
                // Reordenem
                if (posOrigen < posDesti) { // pugem l'aplicació
                    int i = -1;
                    for (Iterator<ArbrePuntEntradaEntity> it = branca.iterator(); it.hasNext();) {
                        i++;
                        ArbrePuntEntradaEntity actual = it.next();

                        // Modifiquem l'ordre
                        if (i == posOrigen) {
                            actual.setOrdre(posDesti - 1); //$NON-NLS-1$	// actual.setOrdre("" + (posDesti - 1)); 
                        } else if (i < posDesti) { // Abans de l'origen,
                            // retrocedim 1 posició
                            int posActual = (i - 1);// Integer.parseInt(actual.getOrdre())
                                                    // - 1;
                            actual.setOrdre(posActual); //$NON-NLS-1$	//actual.setOrdre("" + posActual); 
                        } /*
                           * else { // >= destí, es manté ; }
                           */
                    }
                } else { // posOrigen > posDesti : baixem l'aplicació
                    int i = -1;
                    for (Iterator<ArbrePuntEntradaEntity> it = branca.iterator(); it.hasNext();) {
                        i++;
                        ArbrePuntEntradaEntity actual = it.next();
                        // Modifiquem l'ordre
                        if (i == posOrigen) {
                            actual.setOrdre(posDesti); //$NON-NLS-1$ actual.setOrdre("" + posDesti);
                        } else if (i > posOrigen) { // Després de l'origen ,
                                                    // mantenim l'arbre
                            ;
                        } else { // < origen, els avancem una posició
                            int posActual = (i + 1);// Integer.parseInt(actual.getOrdre())
                                                    // + 1;
                            actual.setOrdre(posActual); //$NON-NLS-1$  actual.setOrdre("" + posActual);
                        }
                    }
                }
                getArbrePuntEntradaEntityDao().update(branca);
                return true;
            } else
                return false; // cas no possible (origen i desti mateixa branca
                              // = té fills el pare)

        } else { // són de branques diferents
            List<ArbrePuntEntradaEntity> brancaDesti = getArbrePuntEntradaEntityDao().findByPare(
                    idPareArbreDesti);

            // Hem de verificar que en la branca destí no existisca ja el node
            // actual [cas de que siga un enllaç]
            if (brancaDesti != null)
                for (Iterator<ArbrePuntEntradaEntity> it = brancaDesti.iterator(); it.hasNext();) {
                    ArbrePuntEntradaEntity arbreActual = it.next();
                    if (arbreActual.getFill().getId().equals(idPueOrigen))
                        throw new SeyconException(
                                Messages.getString("PuntEntradaServiceImpl.EntryPointDuplicated")); //$NON-NLS-1$
                }

            // Creen la nova entrada a l'arbre
            PuntEntradaEntity nouPare = getPuntEntradaEntityDao().findById(idPareArbreDesti);
            PuntEntradaEntity pueMogut = getPuntEntradaEntityDao().findById(idPueOrigen);

            ArbrePuntEntradaEntity nouArbre = getArbrePuntEntradaEntityDao().newArbrePuntEntradaEntity();
            nouArbre.setOrdre(Integer.parseInt(puntEntradaSeguent.getOrdre())); //nouArbre.setOrdre(puntEntradaSeguent.getOrdre());
            nouArbre.setFill(pueMogut);
            nouArbre.setPare(nouPare);

            // Obtenim la branca origen on estava el node a moure (el pare del
            // node mogut)
            List<ArbrePuntEntradaEntity> brancaOrigen = getArbrePuntEntradaEntityDao().findByPare(
                    idPareArbreOrigen);
            ArbrePuntEntradaEntity arbreAntic = null;
            boolean trobat = false;
            for (Iterator<ArbrePuntEntradaEntity> it = brancaOrigen.iterator(); !trobat
                    && it.hasNext();) {
                ArbrePuntEntradaEntity a = it.next();
                if (a.getFill().getId().equals(idPueOrigen)) { // En principi
                    // it.remove(); // ja l'eliminem després (!!)
                    arbreAntic = a; // l'eliminem després
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
                for (Iterator<ArbrePuntEntradaEntity> it = brancaDesti.iterator(); it.hasNext();) { // Reordenem
                    ArbrePuntEntradaEntity actual = it.next();
                    i++;
                    if (reordenar) { // després de destí
                        int posActual = i + 1;// Integer.parseInt(actual.getOrdre())
                                              // + 1;
                        actual.setOrdre(posActual); //$NON-NLS-1$ actual.setOrdre("" + posActual); 
                    } else if (actual.getFill().getId().equals(idPueDesti)) {
                        reordenar = true; // movem destí una posició
                        int posActual = actual.getOrdre() + 1; //Integer.parseInt(actual.getOrdre()) + 1;
                        actual.setOrdre(posActual); //$NON-NLS-1$ actual.setOrdre("" + posActual); 
                    }
                }
            }
            if (brancaDesti == null)
                brancaDesti = new LinkedList<ArbrePuntEntradaEntity>();

            // Fem els canvis
            // 1) Esborrem l'arbre antic
            if (arbreAntic != null)
                getArbrePuntEntradaEntityDao().remove(arbreAntic);
            // 2) Creem l'accés a la nova branca
            getArbrePuntEntradaEntityDao().create(nouArbre);
            brancaDesti.add(nouArbre);
            // 3) Actualitzem la branca destí (hem reordenat els fills de la
            // branca)
            getArbrePuntEntradaEntityDao().update(brancaDesti);
            // 4) Actualitzcem el menú destí
            nouPare.setArbrePuntEntradaSocPare(brancaDesti);
            getPuntEntradaEntityDao().update(nouPare);

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
        Long idParePuntEntradaDesti = (!ROOT_TAG.equals(puntEntradaMenuDesti.getCodi())) ?
        		puntEntradaMenuDesti.getIdPare() : puntEntradaMenuDesti.getId();
        if (idPueOrigen == null || idParePuntEntradaMoure == null || idPueDesti == null
                || idParePuntEntradaDesti == null)
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.EntryPointConfirmChanges")); //$NON-NLS-1$

        // Analitzem l'arbre dels pares del node destí per verificar que no es
        // mou
        // un node origen dintre de la seua branca
        if (isOrigenAncestorDesti(idPueOrigen, idPueDesti))
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NotNodeMoviment")); //$NON-NLS-1$

        PuntEntradaEntity pareOrigenE = getPuntEntradaEntityDao().findById(idParePuntEntradaMoure);
        PuntEntrada pareOrigen = getPuntEntradaEntityDao().toPuntEntrada(pareOrigenE);
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
        List<ArbrePuntEntradaEntity> brancaOrigen = getArbrePuntEntradaEntityDao().findByPare(
                idParePuntEntradaMoure);
        ArbrePuntEntradaEntity arbreAntic = null;
        int pos = 0; // per reindexar elements (ja estàn ordenats pel camp
                     // ordre)
        for (Iterator<ArbrePuntEntradaEntity> it = brancaOrigen.iterator(); it.hasNext();) {
            ArbrePuntEntradaEntity a = it.next();
            if (a.getFill().getId().equals(idPueOrigen)) {
                arbreAntic = a;
                it.remove();
            } else {
                a.setOrdre(pos); // Establim nova posició (reindexem tots //$NON-NLS-1$ a.setOrdre("" + pos); 
                                      // els elements)
                pos++; // Si el trobem no augmentem la posició origen
            }
        }
        // Creen la nova entrada a l'arbre destí
        PuntEntradaEntity nouPare = getPuntEntradaEntityDao().findById(idPueDesti);
        PuntEntradaEntity pueMogut = getPuntEntradaEntityDao().findById(idPueOrigen);

        // Obtenim L'ORDRE DE L'ARBRE destí
        String ordre = "0"; //$NON-NLS-1$
        Collection<ArbrePuntEntradaEntity> fillsDesti = getArbrePuntEntradaEntityDao().findByPare(
                idPueDesti);
        if (fillsDesti != null) {// Ens quedem en el fill de major ordre
            if (fillsDesti.size() == 0) // Para nodes menú sense fills
                ordre = "0"; //$NON-NLS-1$
            else { // Obtenim el seu fill "major" (de tipus List i estan
                   // ordenats per query en ordre ascendent)
                ArbrePuntEntradaEntity fill = ((List<ArbrePuntEntradaEntity>) fillsDesti)
                        .get(fillsDesti.size() - 1);
                int ordreFillMajor = fill.getOrdre(); //int ordreFillMajor = Integer.parseInt(fill.getOrdre());
                ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
            }
        }

        // Creem el accés al punt d'entrada mogut
        ArbrePuntEntradaEntity nouArbre = getArbrePuntEntradaEntityDao().newArbrePuntEntradaEntity();
        nouArbre.setOrdre(Integer.parseInt(ordre)); //nouArbre.setOrdre(ordre);
        nouArbre.setFill(pueMogut);
        nouArbre.setPare(nouPare);

        // 1) Esborrem l'arbre antic
        if (arbreAntic != null)
            getArbrePuntEntradaEntityDao().remove(arbreAntic);
        // 2) Creem l'accés a la nova branca
        getArbrePuntEntradaEntityDao().create(nouArbre);
        if (fillsDesti == null) {
            fillsDesti = new HashSet<ArbrePuntEntradaEntity>();
        }
        fillsDesti.add(nouArbre);
        nouPare.setArbrePuntEntradaSocPare(new HashSet<ArbrePuntEntradaEntity>(fillsDesti));
        // 3) Actualitzem la branca destí (hem afegit un fill)
        getPuntEntradaEntityDao().update(nouPare);

        // 4) Actualitzem la branca origen (hem mogut un fill)
        getArbrePuntEntradaEntityDao().update(brancaOrigen);
        return true;
    }

    private PuntEntradaEntity clonaPuntEntrada(PuntEntradaEntity pueClonar) throws InternalErrorException {
        PuntEntradaEntity nouPUEClonat = null;

        // Copiamos iconos
        Long icona1 = null;
        Long icona2 = null;

        // Creamos la copia y obtenemos el id de la misma
        if (pueClonar.getIcona1() != null) {
            IconaEntity ie = getIconaEntityDao().findById(pueClonar.getIcona1()); // se
                                                                                  // guarda
                                                                                  // id
                                                                                  // del
                                                                                  // icono
                                                                                  // en
                                                                                  // el
                                                                                  // pue
            if (ie != null) {
                IconaEntity icona = createIcona(ie.getIcona());
                icona1 = icona.getId();
            }
        }
        if (pueClonar.getIcona2() != null) {
            IconaEntity ie = getIconaEntityDao().findById(pueClonar.getIcona2()); // se
                                                                                  // guarda
                                                                                  // id
                                                                                  // del
                                                                                  // icono
                                                                                  // en
                                                                                  // el
                                                                                  // pue
            if (ie != null) {
                IconaEntity icona = createIcona(ie.getIcona());
                icona2 = icona.getId();
            }
        }
        // Creamos el nuevo punto de entrada
        nouPUEClonat = getPuntEntradaEntityDao().newPuntEntradaEntity();
        nouPUEClonat.setCodi(pueClonar.getCodi());
        nouPUEClonat.setNom(pueClonar.getNom());
        nouPUEClonat.setVisible(pueClonar.getVisible());
        nouPUEClonat.setMenu(pueClonar.getMenu());
        nouPUEClonat.setNumcolumnes(pueClonar.getNumcolumnes());
        nouPUEClonat.setEsPublic(pueClonar.getEsPublic());
        nouPUEClonat.setTipusMenu(pueClonar.getTipusMenu());
        nouPUEClonat.setEsPublic(pueClonar.getEsPublic());
        nouPUEClonat.setIcona1(icona1);
        nouPUEClonat.setIcona2(icona2);
        nouPUEClonat.setIdAplicacio(pueClonar.getIdAplicacio());
        nouPUEClonat.setXmlPUE(pueClonar.getXmlPUE());
        getPuntEntradaEntityDao().create(nouPUEClonat);

        // Clonamos sus AUTORIZACIONES
        Collection<AutoritzacioPUERolEntity> _autoRol = pueClonar.getAutoritzaRol();
        Collection<AutoritzacioPUEGrupEntity> _autoGrup = pueClonar.getAutoritzaGrup();
        Collection<AutoritzacioPUEUsuariEntity> _autoUsu = pueClonar.getAutoritzaUsuari();
        Collection autoRol = new HashSet(), autoGrup = new HashSet(), autoUsu = new HashSet();
        // ROL
        if (_autoRol != null && _autoRol.size() != 0) {
            for (Iterator<AutoritzacioPUERolEntity> it = _autoRol.iterator(); it.hasNext();) {
                AutoritzacioPUERolEntity auto = (AutoritzacioPUERolEntity) it.next();
                AutoritzacioPUERolEntity apu = 
                		getAutoritzacioPUERolEntityDao().newAutoritzacioPUERolEntity();
                apu.setRole(auto.getRole());
                apu.setNivellAutoritzacio(auto.getNivellAutoritzacio());
                apu.setPuntEntrada(nouPUEClonat);
                getAutoritzacioPUERolEntityDao().create(apu);
                autoRol.add(apu);
            }
        }
        nouPUEClonat.setAutoritzaRol(autoRol);
        // GRUP
        if (_autoGrup != null && _autoGrup.size() != 0) {
            for (Iterator<AutoritzacioPUEGrupEntity> it = _autoGrup.iterator(); it.hasNext();) {
                AutoritzacioPUEGrupEntity auto = (AutoritzacioPUEGrupEntity) it.next();
                AutoritzacioPUEGrupEntity apu = 
                		getAutoritzacioPUEGrupEntityDao().newAutoritzacioPUEGrupEntity();
                apu.setGroup(auto.getGroup());
                apu.setNivellAutoritzacio(auto.getNivellAutoritzacio());
                apu.setPuntEntrada(nouPUEClonat);
                getAutoritzacioPUEGrupEntityDao().create(apu);
                autoGrup.add(apu);

            }
        }
        nouPUEClonat.setAutoritzaGrup(autoGrup);
        // USUARI
        if (_autoUsu != null && _autoUsu.size() != 0) {
            for (Iterator<AutoritzacioPUEUsuariEntity> it = _autoUsu.iterator(); it.hasNext();) {
                AutoritzacioPUEUsuariEntity auto = (AutoritzacioPUEUsuariEntity) it.next();
                AutoritzacioPUEUsuariEntity apu = 
                		getAutoritzacioPUEUsuariEntityDao().newAutoritzacioPUEUsuariEntity();
                apu.setUser(auto.getUser());
                apu.setNivellAutoritzacio(auto.getNivellAutoritzacio());
                apu.setPuntEntrada(nouPUEClonat);
                getAutoritzacioPUEUsuariEntityDao().create(apu);
                autoUsu.add(apu);
            }
        }
        nouPUEClonat.setAutoritzaUsuari(autoUsu);
        // Clonamos sus EJECUCIONES:
        Collection _execs = pueClonar.getMetodesExecucio();
        Collection<ExecucioPuntEntradaEntity> execs = new HashSet<ExecucioPuntEntradaEntity>();

        if (_execs != null && _execs.size() != 0) {
            for (Iterator it = _execs.iterator(); it.hasNext();) {
                ExecucioPuntEntradaEntity exe = (ExecucioPuntEntradaEntity) it.next();
                // Tenemos que crear la nueva instancia (para indicar el pue)
                
                ExecucioPuntEntradaEntity nou = getExecucioPuntEntradaEntityDao(). 
                		newExecucioPuntEntradaEntity();
                nou.setAmbit(exe.getAmbit());
                nou.setCodiExecucio(exe.getCodiExecucio());
                nou.setContingut(exe.getContingut());
                nou.setPuntEntrada(nouPUEClonat);
                getExecucioPuntEntradaEntityDao().create(nou);
                execs.add(nou);
            }
        }
        nouPUEClonat.setMetodesExecucio(execs);

        // Clonamos los HIJOS si los tiene:
        Collection _fills = pueClonar.getArbrePuntEntradaSocPare();
        Collection<ArbrePuntEntradaEntity> fills = new HashSet<ArbrePuntEntradaEntity>();
        if (_fills != null && _fills.size() != 0) {
            for (Iterator it = _fills.iterator(); it.hasNext();) {
                ArbrePuntEntradaEntity actual = (ArbrePuntEntradaEntity) it.next();
                // Clonamos nuestro hijo
                PuntEntradaEntity monFill = clonaPuntEntrada(actual.getFill());
                // Y lo añadimos al árbol
                ArbrePuntEntradaEntity arbre = getArbrePuntEntradaEntityDao().
                		newArbrePuntEntradaEntity();
                arbre.setFill(monFill);
                arbre.setOrdre(actual.getOrdre());
                arbre.setPare(nouPUEClonat);
                getArbrePuntEntradaEntityDao().create(arbre);
                fills.add(arbre);
            }
        }
        nouPUEClonat.setArbrePuntEntradaSocPare(fills);
        // Actualizamos los cambios (árbol, ejecuciones, autorizaciones)
        getPuntEntradaEntityDao().update(nouPUEClonat);

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
        Long idParePuntEntradaDesti = (!ROOT_TAG.equals(puntEntradaMenuDesti.getCodi())) ? 
        		puntEntradaMenuDesti.getIdPare() : puntEntradaMenuDesti.getId();
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
        PuntEntradaEntity pueClonar = getPuntEntradaEntityDao().puntEntradaToEntity(
                puntEntradaCopiar);
        // Fem una còpia (iconas, autoritzacions, execucions, fills)
        PuntEntradaEntity nouPUEClonat = clonaPuntEntrada(pueClonar);

        // Hem d'afegir el nou fill clonat al pue destí
        String ordre = "0"; //$NON-NLS-1$
        Collection fillsDesti = getArbrePuntEntradaEntityDao().findByPare(idPueDesti);
        if (fillsDesti != null) {// Ens quedem en el fill de major ordre
            if (fillsDesti.size() == 0) // Para nodes menú sense fills
                ordre = "0"; //$NON-NLS-1$
            else { // Obtenim el seu fill "major" (de tipus List i estan
                   // ordenats per query en ordre ascendent)
                ArbrePuntEntradaEntity fill = (ArbrePuntEntradaEntity) ((List) fillsDesti)
                        .get(fillsDesti.size() - 1);
                int ordreFillMajor = fill.getOrdre(); //Integer.parseInt(fill.getOrdre());
                ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
            }
        }
        // Obtenim el pare
        PuntEntradaEntity pueDesti = getPuntEntradaEntityDao().findById(idPueDesti);
        Collection<ArbrePuntEntradaEntity> fillsNouPare = pueDesti.getArbrePuntEntradaSocPare();
        
        ArbrePuntEntradaEntity nouArbre = getArbrePuntEntradaEntityDao().newArbrePuntEntradaEntity();
        nouArbre.setFill(nouPUEClonat);
        nouArbre.setOrdre(Integer.parseInt(ordre)); //nouArbre.setOrdre(ordre);
        nouArbre.setPare(pueDesti);
        getArbrePuntEntradaEntityDao().create(nouArbre);
        fillsNouPare.add(nouArbre);
        pueDesti.setArbrePuntEntradaSocPare(fillsNouPare);
        // Actualitzem el pare (hem modificat el seu arbre)
        getPuntEntradaEntityDao().update(pueDesti);

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
        Long idParePuntEntradaDesti = (!ROOT_TAG.equals(puntEntradaMenuDesti.getCodi())) ? 
        		puntEntradaMenuDesti.getIdPare() : puntEntradaMenuDesti.getId();
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

        PuntEntradaEntity pareOrigenE = getPuntEntradaEntityDao().findById(idParePuntEntradaCopiar);
        PuntEntrada pareOrigen = getPuntEntradaEntityDao().toPuntEntrada(pareOrigenE);
        // Si el origen NO es menú tiene que tener permisos en el menú
        // contenedor padre del origen (para copiar)
        if (!"S".equals(puntEntradaCopiar.getMenu()) && !canAdmin(pareOrigen)) //$NON-NLS-1$
            throw new SeyconException(
                    Messages.getString("PuntEntradaServiceImpl.NoAuthorizedToCopyEntryPointNoPermission")); //$NON-NLS-1$

        // Verificamos que no exista ya en el destino una copia del mismo
        Collection<ArbrePuntEntradaEntity> fillsDesti = getArbrePuntEntradaEntityDao().findByPare(
                idPueDesti);

        if (fillsDesti != null)
            for (Iterator<ArbrePuntEntradaEntity> it = fillsDesti.iterator(); it.hasNext();) {
                ArbrePuntEntradaEntity arbreActual = it.next();
                if (arbreActual.getFill().getId().equals(idPueOrigen))
                    throw new SeyconException(
                            Messages.getString("PuntEntradaServiceImpl.EntryPointDuplicated")); //$NON-NLS-1$
            }

        // Obtenim L'ORDRE DE L'ARBRE destí (estan ordenats per ordre ascendent)
        String ordre = "0"; //$NON-NLS-1$
        if (fillsDesti != null) {// Ens quedem en el fill de major ordre
            if (fillsDesti.size() == 0) // Para nodes menú sense fills
                ordre = "0"; //$NON-NLS-1$
            else { // Obtenim el seu fill "major" (es de tipus List ordenat)
                ArbrePuntEntradaEntity fill = ((List<ArbrePuntEntradaEntity>) fillsDesti)
                        .get(fillsDesti.size() - 1);
                int ordreFillMajor = fill.getOrdre(); //int ordreFillMajor = Integer.parseInt(fill.getOrdre());
                ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
            }
        }

        // Creamos una copia del árbol en el menú destino:
        PuntEntradaEntity nouPare = getPuntEntradaEntityDao().findById(idPueDesti);
        PuntEntradaEntity pueCopiat = getPuntEntradaEntityDao().findById(idPueOrigen);

        // Creen la nova entrada a l'arbre destí
        ArbrePuntEntradaEntity nouArbre = getArbrePuntEntradaEntityDao().newArbrePuntEntradaEntity();
        nouArbre.setFill(pueCopiat);
        nouArbre.setOrdre(Integer.parseInt(ordre)); //nouArbre.setOrdre(ordre);
        nouArbre.setPare(nouPare);
        getArbrePuntEntradaEntityDao().create(nouArbre);

        // 1) Creem l'accés a la branca destí
        if (fillsDesti == null) {
            fillsDesti = new HashSet<ArbrePuntEntradaEntity>();
        }
        fillsDesti.add(nouArbre);
        nouPare.setArbrePuntEntradaSocPare(new HashSet<ArbrePuntEntradaEntity>(fillsDesti));

        // 2) Actualitzem el menú destí (hem afegit un fill)
        getPuntEntradaEntityDao().update(nouPare);

        return true;
    }

    protected AutoritzacioPuntEntrada handleCreateAutoritzacio(PuntEntrada puntEntrada,
            AutoritzacioPuntEntrada autoritzacio) throws Exception {

        PuntEntradaEntity puntEntradaE = null;
        Long idEntitat = null;

        if (puntEntrada == null || puntEntrada.getId() == null)
            throw new SeyconException(Messages.getString("PuntEntradaServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$
        else {
            puntEntradaE = getPuntEntradaEntityDao().puntEntradaToEntity(puntEntrada);
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
            AutoritzacioPUERolEntity autoRol =
            		getAutoritzacioPUERolEntityDao(). 
            			newAutoritzacioPUERolEntity();
            autoRol.setNivellAutoritzacio(nivell);
            autoRol.setRole (getRolEntityDao().load(idEntitat));
            autoRol.setPuntEntrada(puntEntradaE);
            getAutoritzacioPUERolEntityDao().create(autoRol);

            auditarAutoritzacioPuntEntrada(
                    "C", //$NON-NLS-1$
                    tipusAutoritzacio,
                    autoritzacio.getDescripcioNivellAutoritzacio() + " - " //$NON-NLS-1$
                            + autoritzacio.getDescripcioEntitatAutoritzada() + " - " //$NON-NLS-1$
                            + puntEntrada.getNom());

            return getAutoritzacioPUERolEntityDao().toAutoritzacioPuntEntrada(autoRol);
        } else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipusAutoritzacio)) {
            // GRUP: Creamos autorización
        	AutoritzacioPUEGrupEntity autoGrup =
            		getAutoritzacioPUEGrupEntityDao(). 
            			newAutoritzacioPUEGrupEntity();
        	autoGrup.setNivellAutoritzacio(nivell);
        	autoGrup.setGroup(getGrupEntityDao().load(idEntitat));
        	autoGrup.setPuntEntrada(puntEntradaE);
            getAutoritzacioPUEGrupEntityDao().create(autoGrup);

            auditarAutoritzacioPuntEntrada(
                    "C", //$NON-NLS-1$
                    tipusAutoritzacio,
                    autoritzacio.getDescripcioNivellAutoritzacio() + " - " //$NON-NLS-1$
                            + autoritzacio.getDescripcioEntitatAutoritzada() + " - " //$NON-NLS-1$
                            + puntEntrada.getNom());

            return getAutoritzacioPUEGrupEntityDao().toAutoritzacioPuntEntrada(autoGrup);
        } else if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipusAutoritzacio)) {
            // USUARI: Creamos autorización
        	AutoritzacioPUEUsuariEntity autoUsu =
            		getAutoritzacioPUEUsuariEntityDao(). 
            			newAutoritzacioPUEUsuariEntity();
        	autoUsu.setNivellAutoritzacio(nivell);
        	autoUsu.setUser(getUsuariEntityDao().load(idEntitat));
        	autoUsu.setPuntEntrada(puntEntradaE);
            getAutoritzacioPUEUsuariEntityDao().create(autoUsu);

            auditarAutoritzacioPuntEntrada(
                    "C", //$NON-NLS-1$
                    tipusAutoritzacio,
                    autoritzacio.getDescripcioNivellAutoritzacio() + " - " //$NON-NLS-1$
                            + autoritzacio.getDescripcioEntitatAutoritzada() + " - " //$NON-NLS-1$
                            + puntEntrada.getNom());

            return getAutoritzacioPUEUsuariEntityDao().toAutoritzacioPuntEntrada(autoUsu);
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
            getAutoritzacioPUERolEntityDao().remove(autoritzacio.getId());
        } else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipusAutoritzacio)) {
            // GRUP: Borramos autorización
            getAutoritzacioPUEGrupEntityDao().remove(autoritzacio.getId());
        } else if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipusAutoritzacio)) {
            // USUARI: Borramos autorización
            getAutoritzacioPUEUsuariEntityDao().remove(autoritzacio.getId());
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

        ExecucioPuntEntradaEntity entity = getExecucioPuntEntradaEntityDao()
                .execucioPuntEntradaToEntity(execucio);
        getExecucioPuntEntradaEntityDao().create(entity);

        auditarExecucioPuntEntrada("C", //$NON-NLS-1$
                puntEntrada.getNom() + " tipus " + execucio.getTipusMimeExecucio() + " ambit " //$NON-NLS-1$ //$NON-NLS-2$
                        + execucio.getAmbit());

        return getExecucioPuntEntradaEntityDao().toExecucioPuntEntrada(entity);
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
        ExecucioPuntEntradaEntity entity = getExecucioPuntEntradaEntityDao()
                .execucioPuntEntradaToEntity(execucio);
        getExecucioPuntEntradaEntityDao().remove(entity);

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
        ExecucioPuntEntradaEntity entity = getExecucioPuntEntradaEntityDao()
                .execucioPuntEntradaToEntity(execucio);
        getExecucioPuntEntradaEntityDao().update(entity);

        auditarExecucioPuntEntrada("U", //$NON-NLS-1$
                puntEntrada.getNom() + " tipus " + execucio.getTipusMimeExecucio() + " ambit " //$NON-NLS-1$ //$NON-NLS-2$
                        + execucio.getAmbit());

        return getExecucioPuntEntradaEntityDao().toExecucioPuntEntrada(entity);
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
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
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
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
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
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    protected java.util.Collection<PuntEntrada> handleFindMenuChildren(
            es.caib.seycon.ng.comu.PuntEntrada puntEntrada) throws java.lang.Exception {
        // Comprovem autorització
        if (!canView(puntEntrada))
            return new LinkedList<PuntEntrada>();// throw new
                                                 // SeyconException("no autoritzat");

        Collection<ArbrePuntEntradaEntity> arbre = getArbrePuntEntradaEntityDao().findByPare(
                puntEntrada.getId());
        if (arbre != null && arbre.size() != 0) {// Verificamos permisos
            Collection<PuntEntrada> fills = new LinkedList<PuntEntrada>();
            for (Iterator<ArbrePuntEntradaEntity> it = arbre.iterator(); it.hasNext();) {
                ArbrePuntEntradaEntity a = it.next();
                // Només si tenim permis
                PuntEntrada pue = getPuntEntradaEntityDao().toPuntEntrada(a.getFill());
                // Establim la posició a l'arbre del punt d'entrada (per poder
                // moure'l)
                pue.setIdPare(a.getPare().getId());
                pue.setOrdre(""+a.getOrdre()); // pue.setOrdre(a.getOrdre()); //$NON-NLS-1$
                if ("S".equals(pue.getMenu()) && canAdmin(pue)) // Només de //$NON-NLS-1$
                                                                // tipus menú i
                                                                // som
                                                                // administradors
                                                                // del punt menú
                                                                // desti
                    fills.add(pue);
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
            AplicacioEntity aplicacio = null;
            if (codiAplicacio != null) {
                aplicacio = getAplicacioEntityDao().findByCodi(codiAplicacio);
                if (aplicacio == null) {
                    throw new SeyconException(String.format(
                            Messages.getString("PuntEntradaServiceImpl.ApplicationNotFounded"), codiAplicacio)); //$NON-NLS-1$
                }
            }
            Collection<PuntEntradaEntity> cerca = getPuntEntradaEntityDao().findByCriteris(nomPUE,
                    codiPUE);
            if (aplicacio != null) {
                Collection<PuntEntradaEntity> resFiltrats = new LinkedList<PuntEntradaEntity>();
                for (Iterator<PuntEntradaEntity> it = cerca.iterator(); it.hasNext();) {
                    PuntEntradaEntity pue = it.next();
                    if (pue.getIdAplicacio().equals(aplicacio.getId()))
                        resFiltrats.add(pue);
                }
                cerca = resFiltrats; // Filtrem
            }

            List<PuntEntrada> cercaVO = getPuntEntradaEntityDao().toPuntEntradaList(cerca);

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
        private Map<PuntEntradaEntity, LinkedHashSet<PuntEntradaEntity>> map = new HashMap<PuntEntradaEntity, LinkedHashSet<PuntEntradaEntity>>();

        public void addEdge(PuntEntradaEntity node1, PuntEntradaEntity node2) {
            LinkedHashSet<PuntEntradaEntity> adjacent = map.get(node1);
            if (adjacent == null) {
                adjacent = new LinkedHashSet<PuntEntradaEntity>();
                map.put(node1, adjacent);
            }
            adjacent.add(node2);
        }

        public void addTwoWayVertex(PuntEntradaEntity node1, PuntEntradaEntity node2) {
            addEdge(node1, node2);
            addEdge(node2, node1);
        }

        public boolean isConnected(PuntEntradaEntity node1, PuntEntradaEntity node2) {
            Set adjacent = map.get(node1);
            if (adjacent == null) {
                return false;
            }
            return adjacent.contains(node2);
        }

        public LinkedList<PuntEntradaEntity> adjacentNodes(PuntEntradaEntity last) {
            LinkedHashSet<PuntEntradaEntity> adjacent = map.get(last);
            if (adjacent == null) {
                return new LinkedList<PuntEntradaEntity>();
            }
            return new LinkedList<PuntEntradaEntity>(adjacent);
        }
    }

    class Search {

        private void breadthFirst(Graph graph, LinkedList<PuntEntradaEntity> visited,
                LinkedList<String> rutes) {
            LinkedList<PuntEntradaEntity> nodes = graph.adjacentNodes(visited.getLast());
            // examine adjacent nodes
            for (PuntEntradaEntity node : nodes) {
                if (visited.contains(node)) {
                    continue;
                }
                if (ROOT_TAG.equals(node.getCodi())) {
                    visited.add(node);
                    rutes.add(printPath(visited));
                    visited.removeLast();
                    break;
                }
            }
            // in breadth-first, recursion needs to come after visiting adjacent
            // nodes
            for (PuntEntradaEntity node : nodes) {
                if (visited.contains(node) || ROOT_TAG.equals(node.getCodi())) {
                    continue;
                }
                visited.addLast(node);
                breadthFirst(graph, visited, rutes);
                visited.removeLast();
            }
        }

        public String printPath(LinkedList<PuntEntradaEntity> visited) {
            String res = ""; //$NON-NLS-1$

            // Fem el cami invers
            /*
             * for (PuntEntradaEntity node : visited) { res+= node.getNom()
             * +" < "; } if (res.endsWith(" < ")) res =
             * res.substring(0,res.lastIndexOf(" < "));
             */
            for (int i = (visited.size() - 1); i >= 0; i--) {
                res += visited.get(i).getNom() + " > "; //$NON-NLS-1$
            }
            if (res.endsWith(" > ")) //$NON-NLS-1$
                res = res.substring(0, res.lastIndexOf(" > ")); //$NON-NLS-1$
            return res;
        }

    }

    private void getGraphInversPUE(PuntEntradaEntity pue, Graph arbre) {

        Collection pares = getArbrePuntEntradaEntityDao().findByFill(pue.getId());

        for (Iterator it = pares.iterator(); it.hasNext();) {
            ArbrePuntEntradaEntity a = (ArbrePuntEntradaEntity) it.next();
            PuntEntradaEntity pare = a.getPare();
            arbre.addEdge(pue, pare);
            getGraphInversPUE(pare, arbre);
        }

    }

    protected Collection<String> handleGetArbreInversPuntEntrada(PuntEntrada puntEntrada)
            throws Exception {

        Graph arbreInvers = new Graph();
        PuntEntradaEntity pue = getPuntEntradaEntityDao().puntEntradaToEntity(puntEntrada);
        getGraphInversPUE(pue, arbreInvers);

        LinkedList<PuntEntradaEntity> visited = new LinkedList<PuntEntradaEntity>();
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
        PuntEntradaEntity puntEntradaE = getPuntEntradaEntityDao().findById(idPuntEntrada);
        if (puntEntradaE == null)
            return false;
        PuntEntrada puntEntrada = getPuntEntradaEntityDao().toPuntEntrada(puntEntradaE);

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
        Collection autoRol = getAutoritzacioPUERolEntityDao()
                .query(
                        "from es.caib.seycon.ng.model.AutoritzacioPUERolEntity where nivellAutoritzacio='A'", //$NON-NLS-1$
                        new Parameter[0]); //$NON-NLS-1$
        for (Iterator it = autoRol.iterator(); it.hasNext();) {
            // ho mirem per entity (per reduir les cerques): només als rols
            AutoritzacioPUERolEntity auto = (AutoritzacioPUERolEntity) it.next();
            if (permisos.getRolsUsuariPUE().contains(auto.getRole().getId())) {
                // Només administradors. NOTA: al entity pot ésser A o C
                // (!!)
                if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(auto.getNivellAutoritzacio()))
                    return true;
            }
        }

        // GRUP: només els de permís d'aministrador (!!)
        List<AutoritzacioPUEGrupEntity> autoGrup = getAutoritzacioPUEGrupEntityDao()
                .query(
                        "from es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity where nivellAutoritzacio='A'", //$NON-NLS-1$
                        new Parameter[0]); //$NON-NLS-1$
        List<AutoritzacioPuntEntrada> autoGrupVO = getAutoritzacioPUEGrupEntityDao()
                .toAutoritzacioPuntEntradaList(autoGrup);
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
        List<AutoritzacioPUEUsuariEntity> autoUsu = getAutoritzacioPUEUsuariEntityDao()
                .query(
                        "from es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity where nivellAutoritzacio='A'", //$NON-NLS-1$
                        new Parameter[0]); //$NON-NLS-1$
        List<AutoritzacioPuntEntrada> autoUsuVO = getAutoritzacioPUEUsuariEntityDao()
                .toAutoritzacioPuntEntradaList(autoUsu);
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
        PuntEntradaEntity pueEntity = getPuntEntradaEntityDao().findById(id);
        PuntEntrada pue = getPuntEntradaEntityDao().toPuntEntrada(pueEntity);

        if (canView(pue))
            return pue;
        else
            return null;
    }

	protected IconaEntity createIcona (byte b[]) throws InternalErrorException
	{
		try
		{
			IconaEntity icona = getIconaEntityDao().newIconaEntity();
			icona.setIcona(b);
			getIconaEntityDao().create(icona);
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
