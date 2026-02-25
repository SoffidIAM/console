//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
/**
 * Service GeoInformationService
 */
public interface GeoInformationService {
	public final static String SERVICE_NAME = "com.soffid.iam.rc.service.GeoInformationService";

	/**
	 * Operation getGeoInformation

	 * @param ip 
	 * @return 
	 */
	com.soffid.iam.rc.api.GeoInformation getGeoInformation(
		final java.lang.String ip)
			throws com.soffid.iam.exception.InternalErrorException;

}
