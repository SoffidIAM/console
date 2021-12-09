// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import java.util.Date;

import org.hibernate.Query;

/**
 * @see es.caib.seycon.ng.model.ServerEntity
 */
public class ServerInstanceEntityDaoImpl
    extends com.soffid.iam.model.ServerInstanceEntityDaoBase
{

	@Override
	public void remove(ServerInstanceEntity entity) {
		Query q = getSession().createQuery(
				"update com.soffid.iam.model.TaskEntityImpl as si "
				+ "set si.hash = null, si.server = null, si.serverInstance = null "
				+ "where si.server = :server and si.serverInstance = :serverInstance");
		q.setParameter("server", entity.getServer().getName());
		q.setParameter("serverInstance", entity.getName());
		q.executeUpdate();
		super.remove(entity);
	}
}