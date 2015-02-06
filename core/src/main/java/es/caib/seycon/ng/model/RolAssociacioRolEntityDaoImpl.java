// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.TipusContenidorRol;

/**
 * @see es.caib.seycon.ng.model.RolAssociacioRolEntity
 */
public class RolAssociacioRolEntityDaoImpl extends
        es.caib.seycon.ng.model.RolAssociacioRolEntityDaoBase {

    @Override
    public void create(RolAssociacioRolEntity rolAssociacioRolEntity) {
        super.create(rolAssociacioRolEntity);
        generateTask(rolAssociacioRolEntity.getRolContenidor());
        generateTask(rolAssociacioRolEntity.getRolContingut());
        getSession().flush ();
    }

    @Override
    public void update(RolAssociacioRolEntity rolAssociacioRolEntity) {
        RolAssociacioRolEntity old = load (rolAssociacioRolEntity.getId());
        super.update(rolAssociacioRolEntity);
        generateTask(rolAssociacioRolEntity.getRolContenidor());
        generateTask(rolAssociacioRolEntity.getRolContingut());
        if (! old.getRolContenidor().getId().equals(rolAssociacioRolEntity.getRolContenidor().getId())) {
            generateTask(old.getRolContenidor());
        }
        if (! old.getRolContenidor().getId().equals(rolAssociacioRolEntity.getRolContingut().getId())) {
            generateTask(old.getRolContingut());
        }
        getSession().flush ();
    }

    @Override
    public void remove(RolAssociacioRolEntity rolAssociacioRolEntity) {
        super.remove(rolAssociacioRolEntity);
        generateTask(rolAssociacioRolEntity.getRolContenidor());
        generateTask(rolAssociacioRolEntity.getRolContingut());
        getSession().flush ();
    }

    private void generateTask(RolEntity rol) {
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rol.getNom());
        tasque.setBd(rol.getBaseDeDades().getCodi());
        getTasqueEntityDao().create(tasque);
    }

    public List<RolAssociacioRolEntity> find(
            final java.lang.String queryString,
            final es.caib.seycon.ng.model.Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this,
                    queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    public static boolean verificaAssociacioSenseCicles(
            RolAssociacioRolEntity associacio, StringBuffer cami) {
        RolEntity contingut = associacio.getRolContingut();
        RolEntity pare = associacio.getRolContenidor();

        // Método: Para todo T,D / T & D son RolEntity
        // no existe C(D,D1): D está contenido en D1 (contenedor) tal que
        // (versión breve)
        // exista un camino C(D1, T): D1 está contenido en T
        //
        // Obtenemos dónde está contenido el padre (el contenedor del rol)
        // return true;
        cami.append(contingut.getNom() + " => "); //$NON-NLS-1$
        return verificaAssociacioSenseCicles(contingut, pare, cami);
    }

    public static boolean verificaAssociacioSenseCicles(RolEntity fill,
            RolEntity pare, StringBuffer cami) {
        Collection pareEsContingut = pare.getRolAssociacioRolSocContingut();
        boolean senseCicles = true;
        cami.append(pare.getNom() + " => "); //$NON-NLS-1$
        for (Iterator it = pareEsContingut.iterator(); senseCicles
                && it.hasNext();) {
            RolAssociacioRolEntity relacio = (RolAssociacioRolEntity) it.next();
            RolEntity parePare = relacio.getRolContenidor();
            if (parePare.equals(fill)) {
                senseCicles = false;
                cami.append(parePare.getNom());
                return false; // S'ha trobat un cicle
            } else {
                // Verificamos la descendencia del contenedor (padre)
                senseCicles = verificaAssociacioSenseCicles(fill, parePare,
                        cami);
            }
        }
        return senseCicles;
    }

    public RolAssociacioRolEntity contenidorRolToEntity(
            ContenidorRol contenidorRol) {
        return null;
    }

    public ContenidorRol toContenidorRol(
            RolAssociacioRolEntity rolAssocRolEntity) {
        ContenidorRol contenidorRol = super.toContenidorRol(rolAssocRolEntity); // Pasamos
                                                                                // el
                                                                                // id
        contenidorRol.setTipus(TipusContenidorRol.ROL_ROL);
        RolEntity rcontenidor = rolAssocRolEntity.getRolContenidor(); // rol
                                                                      // atorgat
                                                                      // (si lo
                                                                      // tienes
                                                                      // tienes
                                                                      // el rol
                                                                      // contingut)
        ContenidorRol contenidor = getRolEntityDao().toContenidorRol(
                rcontenidor);
        // Afegim informació del domini:
        String infoDomini = ""; //$NON-NLS-1$
        // Si es nulo o valor SENSE_DOMINI no ponemos valor de dominio
        if (!TipusDomini.SENSE_DOMINI
                .equals(rolAssocRolEntity.getRolContingut().getTipusDomini())) {
            String tipusDominiAsoc = rolAssocRolEntity.getRolContingut().getTipusDomini(); // Tipo
                                                                         // de
                                                                         // dominio
                                                                         // de
                                                                         // la
                                                                         // asociación
            String tipusDominiRol = rcontenidor.getTipusDomini();
            // "{"+tipusDomini+":"+valorDomini+"["+descripcioValorDomini+"]}"
            if (TipusDomini.APLICACIONS.equals(tipusDominiAsoc)) {
                AplicacioEntity app = rolAssocRolEntity.getGrantedApplicationDomain();
                if (app != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + app.getCodi() //$NON-NLS-1$ //$NON-NLS-2$
                            + "[" + app.getNom() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "[" //$NON-NLS-1$
                            + TipusDomini.Descripcio.QUALQUE_VALOR_DOMINI
                            + "]}"; //$NON-NLS-1$
                }
            } else if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
                    || TipusDomini.GRUPS_USUARI.equals(tipusDominiAsoc)) {
                GrupEntity gr = rolAssocRolEntity.getGrantedGroupDomain();
                if (gr != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + gr.getCodi() //$NON-NLS-1$ //$NON-NLS-2$
                            + "[" + gr.getDescripcio() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "[" //$NON-NLS-1$
                            + TipusDomini.Descripcio.QUALQUE_VALOR_DOMINI
                            + "]}"; //$NON-NLS-1$
                }
            } else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDominiAsoc)) {
                ValorDominiAplicacioEntity vd = rolAssocRolEntity
                        .getGrantedDomainValue();
                if (vd != null) {
                    infoDomini = "{" + tipusDominiAsoc + ":" + vd.getValor() //$NON-NLS-1$ //$NON-NLS-2$
                            + "[" + vd.getDomini().getNom() + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    infoDomini = "{" + tipusDominiAsoc + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + TipusDomini.QUALQUE_VALOR_DOMINI + "[" //$NON-NLS-1$
                            + TipusDomini.Descripcio.QUALQUE_VALOR_DOMINI
                            + "]}"; //$NON-NLS-1$
                }
            }
            // Casos de nous: pot tindre tipusDominiAsoc a null (és un camp nou)
            else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipusDominiAsoc)
                    || (tipusDominiAsoc == null && tipusDominiRol != null && !TipusDomini.SENSE_DOMINI
                            .equals(tipusDominiRol))) {
                // IMPORTANT: Aquí posam el tipus de domini del Rol original (no
                // de l'associació)
                infoDomini = "{" + rcontenidor.getTipusDomini() + ":" //$NON-NLS-1$ //$NON-NLS-2$
                        + TipusDomini.QUALQUE_VALOR_DOMINI + "[" //$NON-NLS-1$
                        + TipusDomini.Descripcio.QUALQUE_VALOR_DOMINI + "]}"; //$NON-NLS-1$
            }
        }
        // Retornem informació del contenidor-pare (rol que el té atorgat)
        contenidorRol.setInfoContenidor(contenidor.getInfoContenidor()
                + infoDomini);

        return contenidorRol;
    }

    public RolAssociacioRolEntity rolGrantToEntity(RolGrant rolGrant) {
        return load(rolGrant.getId());
    }

    @Override
    public void toRolGrant(RolAssociacioRolEntity source, RolGrant target) {
    	// Translate granted domain
        String tipus = source.getRolContingut().getTipusDomini();
        if (TipusDomini.APLICACIONS.equals(tipus) && 
                source.getGrantedApplicationDomain() != null) {
            target.setDomainValue(source.getGrantedApplicationDomain().getCodi());
            target.setHasDomain(true);
        } else if (( TipusDomini.GRUPS.equals(tipus)
                || TipusDomini.GRUPS_USUARI.equals(tipus) ) &&
                source.getGrantedGroupDomain() != null) {
            target.setDomainValue(source.getGrantedGroupDomain().getCodi());
            target.setHasDomain(true);
        } else if (TipusDomini.DOMINI_APLICACIO.equals(tipus) &&
                source.getGrantedDomainValue() != null) {
            target.setDomainValue(source.getGrantedDomainValue().getValor());
            target.setHasDomain(true);
        } else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus) ) {
            target.setHasDomain(true);
            target.setDomainValue(null);
        } else {
            target.setHasDomain(false);
            target.setDomainValue(null);
        }
    	// Translate grantee domain
        tipus = source.getRolContenidor().getTipusDomini();
        if (TipusDomini.APLICACIONS.equals(tipus) && 
                source.getGranteeApplicationDomain() != null) {
            target.setOwnerRolDomainValue(source.getGranteeApplicationDomain().getCodi());
        } else if (( TipusDomini.GRUPS.equals(tipus)
                || TipusDomini.GRUPS_USUARI.equals(tipus) ) &&
                source.getGranteeGroupDomain() != null) {
            target.setOwnerRolDomainValue(source.getGranteeGroupDomain().getCodi());
        } else if (TipusDomini.DOMINI_APLICACIO.equals(tipus) &&
                source.getGranteeDomainValue() != null) {
            target.setOwnerRolDomainValue(source.getGranteeDomainValue().getValor());
        } else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus) ) {
            target.setOwnerRolDomainValue(null);
        } else {
            target.setOwnerRolDomainValue(null);
        }
        target.setOwnerRol(source.getRolContenidor().getId());
        target.setOwnerRolName(source.getRolContenidor().getNom());
        target.setOwnerGroup(null);
        target.setOwnerAccountName(null);
        target.setOwnerDispatcher(source.getRolContenidor().getBaseDeDades().getCodi());
        target.setId(source.getId());
        target.setIdRol(source.getRolContingut().getId());
        target.setRolName(source.getRolContingut().getNom());
        target.setDispatcher(source.getRolContingut().getBaseDeDades().getCodi());
    }

}
