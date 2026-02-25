//
// (C) 2013 Soffid
//
//

package com.soffid.iam.service.impl;
/**
 * Service AttributeValidationService
 */
public interface AttributeValidationService {
	public final static String SERVICE_NAME = "com.soffid.iam.service.impl.AttributeValidationService";

	/**
	 * Operation validate

	 * @param metadata 
	 * @param value 
	 */
	void validate(
		final com.soffid.iam.base.model.AccountMetadataEntity metadata, 
		final java.lang.Object value)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validate

	 * @param metadata 
	 * @param value 
	 */
	void validate(
		final com.soffid.iam.iga.model.MetaDataEntity metadata, 
		final java.lang.Object value)
			throws com.soffid.iam.exception.InternalErrorException;

}
