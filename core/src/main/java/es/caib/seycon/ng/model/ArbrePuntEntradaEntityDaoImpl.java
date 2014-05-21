// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.ArbrePuntEntrada;

/**
 * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntity
 */
public class ArbrePuntEntradaEntityDaoImpl
    extends es.caib.seycon.ng.model.ArbrePuntEntradaEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#toArbrePuntEntrada(es.caib.seycon.ng.model.ArbrePuntEntradaEntity, es.caib.seycon.ng.comu.ArbrePuntEntrada)
     */
    public void toArbrePuntEntrada(
        es.caib.seycon.ng.model.ArbrePuntEntradaEntity source,
        es.caib.seycon.ng.comu.ArbrePuntEntrada target)
    {
        // @todo verify behavior of toArbrePuntEntrada
        super.toArbrePuntEntrada(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#toArbrePuntEntrada(es.caib.seycon.ng.model.ArbrePuntEntradaEntity)
     */
    public es.caib.seycon.ng.comu.ArbrePuntEntrada toArbrePuntEntrada(final es.caib.seycon.ng.model.ArbrePuntEntradaEntity entity)
    {
        ArbrePuntEntrada arbre  =  super.toArbrePuntEntrada(entity);
		arbre.setIdPare(entity.getPare().getId());
		arbre.setNomPare(entity.getPare().getNom());
		arbre.setIdFill(entity.getFill().getId());
		arbre.setNomFill(entity.getFill().getNom());
		return arbre;
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.ArbrePuntEntradaEntity loadArbrePuntEntradaEntityFromArbrePuntEntrada(es.caib.seycon.ng.comu.ArbrePuntEntrada arbrePuntEntrada)
    {
        es.caib.seycon.ng.model.ArbrePuntEntradaEntity arbrePuntEntradaEntity = this.load(arbrePuntEntrada.getId());
        if (arbrePuntEntradaEntity == null)
        {
            arbrePuntEntradaEntity = newArbrePuntEntradaEntity();
        }
        return arbrePuntEntradaEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada)
     */
    public es.caib.seycon.ng.model.ArbrePuntEntradaEntity arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada arbrePuntEntrada)
    {
        // @todo verify behavior of arbrePuntEntradaToEntity
        es.caib.seycon.ng.model.ArbrePuntEntradaEntity entity = this.loadArbrePuntEntradaEntityFromArbrePuntEntrada(arbrePuntEntrada);
        this.arbrePuntEntradaToEntity(arbrePuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada, es.caib.seycon.ng.model.ArbrePuntEntradaEntity)
     */
    public void arbrePuntEntradaToEntity(
        es.caib.seycon.ng.comu.ArbrePuntEntrada source,
        es.caib.seycon.ng.model.ArbrePuntEntradaEntity target,
        boolean copyIfNull)
    {
        // Ens arriba amb Id si ya existeix l'arbre o sense id (nou)
        super.arbrePuntEntradaToEntity(source, target, copyIfNull);
        // Acabar...
        
    }

}