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

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.UserEntity;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity
 */
public class HostAdminEntityDaoImpl
		extends
		com.soffid.iam.model.HostAdminEntityDaoBase {
	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#toAutoritzacioAccesHostComAdministrador(es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity,
	 *      es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador)
	 */
	public void toAdministratorAuthorizationToAccessHost(com.soffid.iam.model.HostAdminEntity source, com.soffid.iam.api.AdministratorAuthorizationToAccessHost target) {
		// @todo verify behavior of toAutoritzacioAccesHostComAdministrador
		super.toAdministratorAuthorizationToAccessHost(source, target);
		// WARNING! No conversion for target.dataCaducitatAutoritzacioAcces
		// (can't convert
		// source.getDataCaducitatAutoritzacioAcces():java.util.Date to
		// java.util.Date
		toAutoritzacioAccesHostComAdministradorCustom(source, target);

	}

	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#toAutoritzacioAccesHostComAdministrador(es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity)
	 */
	public com.soffid.iam.api.AdministratorAuthorizationToAccessHost toAdministratorAuthorizationToAccessHost(final com.soffid.iam.model.HostAdminEntity entity) {
		// @todo verify behavior of toAutoritzacioAccesHostComAdministrador
		return super.toAdministratorAuthorizationToAccessHost(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.HostAdminEntity loadAutoritzacioAccesHostComAdministradorEntityFromAutoritzacioAccesHostComAdministrador(com.soffid.iam.api.AdministratorAuthorizationToAccessHost autoritzacioAccesHostComAdministrador) {

		com.soffid.iam.model.HostAdminEntity autoritzacioAccesHostComAdministradorEntity = null;
		
		if (autoritzacioAccesHostComAdministrador.getId()!=null) {
			autoritzacioAccesHostComAdministradorEntity = this.load(autoritzacioAccesHostComAdministrador.getId());
		}
		if (autoritzacioAccesHostComAdministradorEntity == null) {
			autoritzacioAccesHostComAdministradorEntity = newHostAdminEntity();
		}
		return autoritzacioAccesHostComAdministradorEntity;
		 
	}

	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#autoritzacioAccesHostComAdministradorToEntity(es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador)
	 */
	public com.soffid.iam.model.HostAdminEntity administratorAuthorizationToAccessHostToEntity(com.soffid.iam.api.AdministratorAuthorizationToAccessHost autoritzacioAccesHostComAdministrador) {
		com.soffid.iam.model.HostAdminEntity entity = this.loadAutoritzacioAccesHostComAdministradorEntityFromAutoritzacioAccesHostComAdministrador(autoritzacioAccesHostComAdministrador);
		this.administratorAuthorizationToAccessHostToEntity(autoritzacioAccesHostComAdministrador, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#autoritzacioAccesHostComAdministradorToEntity(es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador,
	 *      es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity)
	 */
	public void administratorAuthorizationToAccessHostToEntity(com.soffid.iam.api.AdministratorAuthorizationToAccessHost source, com.soffid.iam.model.HostAdminEntity target, boolean copyIfNull) {
		super.administratorAuthorizationToAccessHostToEntity(source, target, copyIfNull);
		autoritzacioAccesHostComAdministradorToEntityCustom(source, target);
	}

	public void toAutoritzacioAccesHostComAdministradorCustom(com.soffid.iam.model.HostAdminEntity source, com.soffid.iam.api.AdministratorAuthorizationToAccessHost target) {
		
		UserEntity usu = source.getUser();
		HostEntity maq = source.getHost();
		target.setUserCode(usu.getUserName());
		if (usu.getShortName() != null && usu.getMailDomain() != null) 
			target.setUserEmail(usu.getShortName() + "@" + usu.getMailDomain().getName()); //$NON-NLS-1$
		
		Calendar data = GregorianCalendar.getInstance();
		data.setTime(source.getExpirationDate());
		target.setAuthorizationAccessExpirationDate(data);
		target.setHostDescription(maq.getDescription());
		target.setHostIp(maq.getHostIP());
		target.setHostName(maq.getName());
		target.setUserName(usu.getFirstName() + " " + usu.getLastName() + " " + usu.getMiddleName()); //$NON-NLS-1$ //$NON-NLS-2$
		if (maq.getNetwork() != null)
			target.setHostNetwork(maq.getNetwork().getName());
		Calendar dataPeticio = GregorianCalendar.getInstance();
		if (source.getRequestDate() != null) dataPeticio.setTime(source.getRequestDate());
		target.setRequestDate(dataPeticio);

	}

	public void autoritzacioAccesHostComAdministradorToEntityCustom(com.soffid.iam.api.AdministratorAuthorizationToAccessHost source, com.soffid.iam.model.HostAdminEntity target) {
		
		// Hem de cercar: usuari i màquina
		UserEntity usuari = getUserEntityDao().findByUserName(source.getUserCode());
		HostEntity host = getHostEntityDao().findByName(source.getHostName());
		
		if (source.getRequestDate() != null) {
			target.setRequestDate(source.getRequestDate().getTime());
		}
		target.setExpirationDate(source.getAuthorizationAccessExpirationDate().getTime());
		target.setUser(usuari);
		target.setHost(host);
		target.setProcessWFID(source.getBpmProcessId());
		
	}

}