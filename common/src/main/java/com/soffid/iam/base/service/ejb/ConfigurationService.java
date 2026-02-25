//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB ConfigurationService
 */
public interface ConfigurationService

 {

	byte[] getBlob(
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Configuration create(
		final com.soffid.iam.base.api.Configuration configuracio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Configuration findParameterByNameAndNetworkName(
		final java.lang.String codiParametre, 
		final java.lang.String codiXarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Configuration update(
		final com.soffid.iam.base.api.Configuration configuracio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Configuration> findConfigurations(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String findTenantParameter(
		final java.lang.String tenant, 
		final java.lang.String parameter)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.Configuration> getParameters()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.base.api.Configuration configuracio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteBlob(
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void updateBlob(
		final java.lang.String name, 
		final byte[] data)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
