// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import java.util.Calendar;

/**
 * @see es.caib.seycon.ng.model.ServerPluginEntity
 */
public class ServerPluginEntityDaoImpl extends com.soffid.iam.model.ServerPluginEntityDaoBase {
	@Override
    public void remove(com.soffid.iam.model.ServerPluginEntity serverPluginEntity) {
		super.remove(serverPluginEntity);
		getSession().flush();
	}

	/**
	 * @see es.caib.seycon.ng.model.ServerPluginEntityDao#toServerPlugin(es.caib.seycon.ng.model.ServerPluginEntity,
	 *      es.caib.seycon.ng.comu.ServerPlugin)
	 */
	public void toServerPlugin(com.soffid.iam.model.ServerPluginEntity source, com.soffid.iam.api.ServerPlugin target) {
		super.toServerPlugin(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.ServerPluginEntityDao#toServerPlugin(es.caib.seycon.ng.model.ServerPluginEntity)
	 */
	public com.soffid.iam.api.ServerPlugin toServerPlugin(final com.soffid.iam.model.ServerPluginEntity entity) {
		return super.toServerPlugin(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.ServerPluginEntity loadServerPluginEntityFromServerPlugin(com.soffid.iam.api.ServerPlugin serverPlugin) {
		com.soffid.iam.model.ServerPluginEntity serverPluginEntity = null;
		if (serverPlugin.getId() != null) {
			serverPluginEntity = this.load(serverPlugin.getId());
		}
		if (serverPluginEntity == null) {
			serverPluginEntity = newServerPluginEntity();
		}
		return serverPluginEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ServerPluginEntityDao#serverPluginToEntity(es.caib.seycon.ng.comu.ServerPlugin)
	 */
	public com.soffid.iam.model.ServerPluginEntity serverPluginToEntity(com.soffid.iam.api.ServerPlugin serverPlugin) {
		com.soffid.iam.model.ServerPluginEntity entity = this.loadServerPluginEntityFromServerPlugin(serverPlugin);
		this.serverPluginToEntity(serverPlugin, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ServerPluginEntityDao#serverPluginToEntity(es.caib.seycon.ng.comu.ServerPlugin,
	 *      es.caib.seycon.ng.model.ServerPluginEntity)
	 */
	public void serverPluginToEntity(com.soffid.iam.api.ServerPlugin source, com.soffid.iam.model.ServerPluginEntity target, boolean copyIfNull) {
		super.serverPluginToEntity(source, target, copyIfNull);
	}

    @Override
    public void create(com.soffid.iam.model.ServerPluginEntity serverPluginEntity) {
        super.create(serverPluginEntity);
        getSession().flush();
    }

    @Override
    public void update(com.soffid.iam.model.ServerPluginEntity serverPluginEntity) {
        super.update(serverPluginEntity);
        getSession().flush();
   }

}