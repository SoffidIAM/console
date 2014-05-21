// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Calendar;

/**
 * @see es.caib.seycon.ng.model.ServerPluginEntity
 */
public class ServerPluginEntityDaoImpl extends es.caib.seycon.ng.model.ServerPluginEntityDaoBase {
	@Override
	public void remove(ServerPluginEntity serverPluginEntity) {
		super.remove(serverPluginEntity);
		getSession().flush();
	}

	/**
	 * @see es.caib.seycon.ng.model.ServerPluginEntityDao#toServerPlugin(es.caib.seycon.ng.model.ServerPluginEntity,
	 *      es.caib.seycon.ng.comu.ServerPlugin)
	 */
	public void toServerPlugin(es.caib.seycon.ng.model.ServerPluginEntity source, es.caib.seycon.ng.comu.ServerPlugin target) {
		super.toServerPlugin(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.ServerPluginEntityDao#toServerPlugin(es.caib.seycon.ng.model.ServerPluginEntity)
	 */
	public es.caib.seycon.ng.comu.ServerPlugin toServerPlugin(final es.caib.seycon.ng.model.ServerPluginEntity entity) {
		return super.toServerPlugin(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.ServerPluginEntity loadServerPluginEntityFromServerPlugin(
			es.caib.seycon.ng.comu.ServerPlugin serverPlugin) {
		es.caib.seycon.ng.model.ServerPluginEntity serverPluginEntity = null;
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
	public es.caib.seycon.ng.model.ServerPluginEntity serverPluginToEntity(es.caib.seycon.ng.comu.ServerPlugin serverPlugin) {
		es.caib.seycon.ng.model.ServerPluginEntity entity = this.loadServerPluginEntityFromServerPlugin(serverPlugin);
		this.serverPluginToEntity(serverPlugin, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ServerPluginEntityDao#serverPluginToEntity(es.caib.seycon.ng.comu.ServerPlugin,
	 *      es.caib.seycon.ng.model.ServerPluginEntity)
	 */
	public void serverPluginToEntity(es.caib.seycon.ng.comu.ServerPlugin source, es.caib.seycon.ng.model.ServerPluginEntity target,
			boolean copyIfNull) {
		super.serverPluginToEntity(source, target, copyIfNull);
	}

    @Override
    public void create(ServerPluginEntity serverPluginEntity) {
        super.create(serverPluginEntity);
        getSession().flush();
    }

    @Override
    public void update(ServerPluginEntity serverPluginEntity) {
        super.update(serverPluginEntity);
        getSession().flush();
   }

}