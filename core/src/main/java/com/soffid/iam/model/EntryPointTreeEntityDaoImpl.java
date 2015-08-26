// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.ApplicationAccessTree;
import es.caib.seycon.ng.model.*;

/**
 * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntity
 */
public class EntryPointTreeEntityDaoImpl
    extends com.soffid.iam.model.EntryPointTreeEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#toArbrePuntEntrada(es.caib.seycon.ng.model.ArbrePuntEntradaEntity, es.caib.seycon.ng.comu.ArbrePuntEntrada)
     */
    public void toApplicationAccessTree(com.soffid.iam.model.EntryPointTreeEntity source, com.soffid.iam.api.ApplicationAccessTree target) {
        // @todo verify behavior of toArbrePuntEntrada
        super.toApplicationAccessTree(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#toArbrePuntEntrada(es.caib.seycon.ng.model.ArbrePuntEntradaEntity)
     */
    public com.soffid.iam.api.ApplicationAccessTree toApplicationAccessTree(final com.soffid.iam.model.EntryPointTreeEntity entity) {
        ApplicationAccessTree arbre = super.toApplicationAccessTree(entity);
		arbre.setParentId(entity.getParent().getId());
		arbre.setParentName(entity.getParent().getName());
		arbre.setChildId(entity.getChildren().getId());
		arbre.setChildName(entity.getChildren().getName());
		return arbre;
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointTreeEntity loadArbrePuntEntradaEntityFromArbrePuntEntrada(com.soffid.iam.api.ApplicationAccessTree arbrePuntEntrada) {
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
    public com.soffid.iam.model.EntryPointTreeEntity applicationAccessTreeToEntity(com.soffid.iam.api.ApplicationAccessTree arbrePuntEntrada) {
        // @todo verify behavior of arbrePuntEntradaToEntity
        com.soffid.iam.model.EntryPointTreeEntity entity = this.loadArbrePuntEntradaEntityFromArbrePuntEntrada(arbrePuntEntrada);
        this.applicationAccessTreeToEntity(arbrePuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ArbrePuntEntradaEntityDao#arbrePuntEntradaToEntity(es.caib.seycon.ng.comu.ArbrePuntEntrada, es.caib.seycon.ng.model.ArbrePuntEntradaEntity)
     */
    public void applicationAccessTreeToEntity(com.soffid.iam.api.ApplicationAccessTree source, com.soffid.iam.model.EntryPointTreeEntity target, boolean copyIfNull) {
        // Ens arriba amb Id si ya existeix l'arbre o sense id (nou)
        super.applicationAccessTreeToEntity(source, target, copyIfNull);
        // Acabar...
        
    }

}