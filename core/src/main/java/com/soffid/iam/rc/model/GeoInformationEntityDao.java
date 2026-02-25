//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity GeoInformationEntity
 * @see com.soffid.iam.rc.model.GeoInformationEntity
 */
public interface GeoInformationEntityDao

{
	/**
	 * Operation findByIp
	 * @param ip
	 * @return
	**/
	public com.soffid.iam.rc.model.GeoInformationEntity findByIp(
		java.lang.String ip)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity findByIp(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.GeoInformation} object 
	 */
	public void toGeoInformation(com.soffid.iam.rc.model.GeoInformationEntity source, com.soffid.iam.rc.api.GeoInformation target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.GeoInformation} object 
	 */
	public com.soffid.iam.rc.api.GeoInformation toGeoInformation(com.soffid.iam.rc.model.GeoInformationEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.GeoInformation} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.GeoInformation> toGeoInformationList (java.util.Collection<com.soffid.iam.rc.model.GeoInformationEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.GeoInformation} object 
	 */
	public void geoInformationToEntity (com.soffid.iam.rc.api.GeoInformation source, com.soffid.iam.rc.model.GeoInformationEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.GeoInformation} object 
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity geoInformationToEntity (com.soffid.iam.rc.api.GeoInformation instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.GeoInformation} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity>  geoInformationToEntityList (java.util.Collection<com.soffid.iam.rc.api.GeoInformation> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} .
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity newGeoInformationEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.GeoInformationEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.GeoInformationEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.GeoInformationEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.GeoInformationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.GeoInformationEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.GeoInformationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.GeoInformationEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.GeoInformationEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
