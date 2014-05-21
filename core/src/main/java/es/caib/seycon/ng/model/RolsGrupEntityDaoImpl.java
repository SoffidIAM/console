// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.sql.Timestamp;

import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.RolsGrup;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.TipusContenidorRol;
import es.caib.seycon.ng.utils.TipusDomini;

/**
 * @see es.caib.seycon.ng.model.RolsGrupEntity
 */
/**
 * @author u88683
 * 
 */
public class RolsGrupEntityDaoImpl extends
        es.caib.seycon.ng.model.RolsGrupEntityDaoBase {

    public RolsGrupEntity rolsGrupToEntity(RolsGrup rolsGrup) {
        return null;
    }

    public void rolsGrupToEntity(RolsGrup source, RolsGrupEntity target,
            boolean copyIfNull) {
        super.rolsGrupToEntity(source, target, copyIfNull);
    }

    @Override
    public void create(RolsGrupEntity rolsGrupEntity) {
        super.create(rolsGrupEntity);
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getRolOtorgat().getNom());
        tasque.setBd(rolsGrupEntity.getRolOtorgat().getBaseDeDades().getCodi());
        getTasqueEntityDao().create(tasque);
        getSession().flush();
    }

    @Override
    public void update(RolsGrupEntity rolsGrupEntity) {
        RolsGrupEntity old = load(rolsGrupEntity.getId());
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ROLE);
        tasque.setRole(old.getRolOtorgat().getNom());
        tasque.setBd(old.getRolOtorgat().getBaseDeDades().getCodi());
        getTasqueEntityDao().create(tasque);
        super.update(rolsGrupEntity);
        tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getRolOtorgat().getNom());
        tasque.setBd(rolsGrupEntity.getRolOtorgat().getBaseDeDades().getCodi());
        getTasqueEntityDao().create(tasque);
        getSession().flush();
    }

    @Override
    public void remove(RolsGrupEntity rolsGrupEntity) {
        super.remove(rolsGrupEntity);
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ROLE);
        tasque.setRole(rolsGrupEntity.getRolOtorgat().getNom());
        tasque.setBd(rolsGrupEntity.getRolOtorgat().getBaseDeDades().getCodi());
        getTasqueEntityDao().create(tasque);
        getSession().flush();
    }

    public void toRolsGrup(RolsGrupEntity source, RolsGrup target) {
        super.toRolsGrup(source, target);
        toRolsGrupCustom(source, target);
    }

    public RolsGrup toRolsGrup(RolsGrupEntity entity) {
        RolsGrup rolGrup = super.toRolsGrup(entity);
        toRolsGrupCustom(entity, rolGrup);
        return rolGrup;
    }

    private void toRolsGrupCustom(RolsGrupEntity sourceEntity, RolsGrup targetVO) {
        // TODO: Revisar transformaciones: se utilizan en roles de grupos de
        // seyconweb
        String tipusDomini = sourceEntity.getRolOtorgat().getTipusDomini();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
            DominiAplicacioEntity dominiAplicacio = sourceEntity
                    .getRolOtorgat().getDominiAplicacio();
            ValorDomini valorDomini = new ValorDomini();
            // Le asignamos como nombre la descripción del dominio de aplicación
            valorDomini.setNomDomini(sourceEntity.getRolOtorgat()
                    .getDominiAplicacio().getNom());
            // Sin descripción (puede ser de distintos valores de dominio)
            valorDomini.setDescripcio(null);
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            // valorDomini.setDescripcio(sourceEntity.getGrupPosseidor().getDescripcio());
            valorDomini.setDescripcio(""); // no el mostrem //$NON-NLS-1$
            if (tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                // valorDomini.setCodiExternDomini(sourceEntity.getUsuari().getCodi());
                valorDomini.setNomDomini(TipusDomini.GRUPS_USUARI);
            } else {
                valorDomini.setCodiExternDomini(null);
                valorDomini.setNomDomini(TipusDomini.GRUPS);
            }
            valorDomini.setValor(sourceEntity.getGrupPosseidor().getCodi());
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            // valorDomini.setDescripcio(sourceEntity.getRolOtorgat().getAplicacio().getNom());
            valorDomini.setDescripcio("");// No el mostrem //$NON-NLS-1$
            valorDomini.setNomDomini(TipusDomini.APLICACIONS);
            valorDomini.setValor(sourceEntity.getRolOtorgat().getAplicacio()
                    .getCodi());
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setDescripcio(TipusDomini.Descripcio.SENSE_DOMINI);
            valorDomini.setNomDomini(TipusDomini.SENSE_DOMINI);
            valorDomini.setValor(""); //$NON-NLS-1$
            // targetVO.setValorDomini(valorDomini); // No se muestra
        }

        targetVO.setNomRol(sourceEntity.getRolOtorgat().getNom());
        targetVO.setDescripcioRol(sourceEntity.getRolOtorgat().getDescripcio());
        targetVO.setBaseDeDadesRol(sourceEntity.getRolOtorgat()
                .getBaseDeDades().getCodi());
        AplicacioEntity aplicacio = sourceEntity.getRolOtorgat().getAplicacio();
        if (aplicacio != null) {
            targetVO.setCodiAplicacio(aplicacio.getCodi());
        }
        targetVO.setCodiGrup(sourceEntity.getGrupPosseidor().getCodi());
        targetVO.setDescripcioGrup(sourceEntity.getGrupPosseidor()
                .getDescripcio());

    }

    public RolsGrupEntity contenidorRolToEntity(ContenidorRol contenidorRol) {
        // TODO Auto-generated method stub
        return null;
    }

    public ContenidorRol toContenidorRol(RolsGrupEntity entity) {
        ContenidorRol contenidorRol = super.toContenidorRol(entity); // Pasamos
                                                                     // el id
        contenidorRol.setTipus(TipusContenidorRol.ROL_GRUP);
        // Información específica:
        RolEntity rol = entity.getRolOtorgat();
        GrupEntity grup = entity.getGrupPosseidor();
        contenidorRol.setInfoContenidor(String.format(Messages.getString("RolsGrupEntityDaoImpl.0"), //$NON-NLS-1$
        								rol.getNom(),
        									grup.getCodi())) ;
        return contenidorRol;
    }

    public RolsGrupEntity rolGrantToEntity(RolGrant rolGrant) {
        return load(rolGrant.getId());
    }

    @Override
    public void toRolGrant(RolsGrupEntity source, RolGrant target) {
        target.setDispatcher(source.getRolOtorgat().getBaseDeDades()
                .getCodi());
        String tipus = source.getRolOtorgat().getTipusDomini();
        if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus)) {
            target.setHasDomain(true);
            target.setDomainValue(null);
        } else {
            target.setHasDomain(false);
            target.setDomainValue(null);
        }
        target.setOwnerRol(null);
        target.setOwnerRolName(null);
        target.setOwnerGroup(source.getGrupPosseidor().getCodi());
        target.setOwnerDispatcher(null);
        target.setOwnerAccountName(null);
        target.setId(source.getId());
        target.setIdRol(source.getRolOtorgat().getId());
        target.setRolName(source.getRolOtorgat().getNom());
    }

}
