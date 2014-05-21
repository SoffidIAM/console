// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Collection;
import java.util.Iterator;

import es.caib.seycon.ng.comu.ParaulaProhibida;
import es.caib.seycon.ng.comu.PoliticaContrasenya;

/**
 * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity
 */
public class ParaulaProhibidaPoliticaContrasenyaEntityDaoImpl
    extends es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#toParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity, es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya)
     */
    public void toParaulaProhibidaPoliticaContrasenya(
        es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity source,
        es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya target)
    {
        // @todo verify behavior of toParaulaProhibidaPoliticaContrasenya
        super.toParaulaProhibidaPoliticaContrasenya(source, target);
        // WARNING! No conversion for target.paraulaProhibida (can't convert source.getParaulaProhibida():es.caib.seycon.ng.model.ParaulesProhibidesEntity to java.lang.String
        
        // Fem els canvis necessaris
        if (source.getParaulaProhibida()!=null) {
        	ParaulaProhibida p= getParaulesProhibidesEntityDao().toParaulaProhibida(source.getParaulaProhibida());
        	target.setParaulaProhibida(p);
        }
        
        if (source.getPoliticaContrasenya()!=null) {
        	PoliticaContrasenya pc = getPoliticaContrasenyaEntityDao().toPoliticaContrasenya(source.getPoliticaContrasenya());
        	target.setPoliticaContrasenyaDomini(pc);
        }
        
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#toParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity)
     */
    public es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya toParaulaProhibidaPoliticaContrasenya(final es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity entity)
    {
        // @todo verify behavior of toParaulaProhibidaPoliticaContrasenya
        return super.toParaulaProhibidaPoliticaContrasenya(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity loadParaulaProhibidaPoliticaContrasenyaEntityFromParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenyaDomini)
    {
        es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity paraulaProhibidaContrasenyaDominiEntity = null;
        if (paraulaProhibidaContrasenyaDomini.getId() !=null) {
        	paraulaProhibidaContrasenyaDominiEntity = this.load(paraulaProhibidaContrasenyaDomini.getId());
        }
        if (paraulaProhibidaContrasenyaDominiEntity == null)
        {
            paraulaProhibidaContrasenyaDominiEntity = newParaulaProhibidaPoliticaContrasenyaEntity();
        }
        return paraulaProhibidaContrasenyaDominiEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#paraulaProhibidaContrasenyaDominiToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya)
     */
    public es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity paraulaProhibidaPoliticaContrasenyaToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenyaDomini)
    {
        // @todo verify behavior of paraulaProhibidaContrasenyaDominiToEntity
        es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity entity = this.loadParaulaProhibidaPoliticaContrasenyaEntityFromParaulaProhibidaPoliticaContrasenya(paraulaProhibidaContrasenyaDomini);
        this.paraulaProhibidaPoliticaContrasenyaToEntity(paraulaProhibidaContrasenyaDomini, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#paraulaProhibidaContrasenyaDominiToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya, es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity)
     */
    public void paraulaProhibidaPoliticaContrasenyaToEntity(
        es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya source,
        es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of paraulaProhibidaContrasenyaDominiToEntity
        super.paraulaProhibidaPoliticaContrasenyaToEntity(source, target, copyIfNull);
        
        // Fem els canvis necessaris
        //TODO: revisar
        if (source.getParaulaProhibida()!=null) {
        	ParaulesProhibidesEntity p =  getParaulesProhibidesEntityDao().load(source.getParaulaProhibida().getId());
        	if (p!=null) target.setParaulaProhibida(p);
        }
        
        if (source.getPoliticaContrasenyaDomini() !=null) {
        	PoliticaContrasenya pcd = source.getPoliticaContrasenyaDomini();
        	PoliticaContrasenyaEntity pc = null;
        	if (pcd.getId()!=null) {
        		pc = getPoliticaContrasenyaEntityDao().load(source.getPoliticaContrasenyaDomini().getId());
        	} else {
        		Collection<PoliticaContrasenyaEntity> pcdc = getPoliticaContrasenyaEntityDao().findByDominiContrasenya(pcd.getCodiDominiContrasenya());
        		boolean trobat = false;
        		if (pcdc!=null) for (Iterator<PoliticaContrasenyaEntity> it = pcdc.iterator(); !trobat && it.hasNext(); ) {
        		        PoliticaContrasenyaEntity p = it.next();
        			if (p.getTipusUsuariDomini().getCodi().equals(pcd.getTipusUsuari())) {
        				trobat = true;
        				pc = p;
        			} 
        		}
        		
        	}
        	if (pc!=null) target.setPoliticaContrasenya(pc);
        }
        
    }

}