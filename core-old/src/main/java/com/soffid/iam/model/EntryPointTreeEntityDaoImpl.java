// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import es.caib.seycon.ng.comu.ArbrePuntEntrada;

/**
 * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntity
 */
public class EntryPointTreeEntityDaoImpl
    extends com.soffid.iam.model.EntryPointTreeEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#toArbrePuntEntrada(es.caib.seycon.ng.model.ArbrePuntEntradaEntity, es.caib.seycon.ng.comu.ArbrePuntEntrada)
     */
    public void toArbrePuntEntrada(com.soffid.iam.model.EntryPointTreeEntity source, es.caib.seycon.ng.comu.ArbrePuntEntrada target) {
        // @todo verify behavior of toArbrePuntEntrada
        super.toArbrePuntEntrada(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#toArbrePuntEntrada(es.caib.seycon.ng.model.ArbrePuntEntradaEntity)
     */
    public es.caib.seycon.ng.comu.ArbrePuntEntrada toArbrePuntEntrada(final com.soffid.iam.model.EntryPointTreeEntity entity) {
        ArbrePuntEntrada arbre  =  super.toArbrePuntEntrada(entity);
		arbre.setIdPare(entity.getParent().getId());
		arbre.setNomPare(entity.getParent().getName());
		arbre.setIdFill(entity.getChildren().getId());
		arbre.setNomFill(entity.getChildren().getName());
		return arbre;
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointTreeEntity loadArbrePuntEntradaEntityFromArbrePuntEntrada(es.caib.seycon.ng.comu.ArbrePuntEntrada arbrePuntEntrada) {
        com.soffid.iam.model.EntryPointTreeEntity arbrePuntEntradaEntity = this.load(arbrePuntEntrada.getId());
        if (arbrePuntEntradaEntity == null)
        {
            arbrePuntEntradaEntity = newEntryPointTreeEntity();
        }
        return arbrePuntEntradaEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada)
     */
    public com.soffid.iam.model.EntryPointTreeEntity arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada arbrePuntEntrada) {
        // @todo verify behavior of arbrePuntEntradaToEntity
        com.soffid.iam.model.EntryPointTreeEntity entity = this.loadArbrePuntEntradaEntityFromArbrePuntEntrada(arbrePuntEntrada);
        this.arbrePuntEntradaToEntity(arbrePuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada, es.caib.seycon.ng.model.ArbrePuntEntradaEntity)
     */
    public void arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada source, com.soffid.iam.model.EntryPointTreeEntity target, boolean copyIfNull) {
        // Ens arriba amb Id si ya existeix l'arbre o sense id (nou)
        super.arbrePuntEntradaToEntity(source, target, copyIfNull);
        // Acabar...
        
    }

}