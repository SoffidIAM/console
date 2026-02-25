//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity GeoInformationEntity
 */

public abstract class GeoInformationEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
	/**
	 * Gets value for attribute id
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	/**
	 * Sets value for attribute id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	/**
	 * Attribute ip
	 */
	private java.lang.String ip;
	/**
	 * Gets value for attribute ip
	 */
	public java.lang.String getIp() {
		return this.ip;
	}
	/**
	 * Sets value for attribute ip
	 */
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	/**
	 * Attribute date
	 */
	private java.util.Date date;
	/**
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}
	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	/**
	 * Attribute country
	 */
	private java.lang.String country;
	/**
	 * Gets value for attribute country
	 */
	public java.lang.String getCountry() {
		return this.country;
	}
	/**
	 * Sets value for attribute country
	 */
	public void setCountry(java.lang.String country) {
		this.country = country;
	}
	/**
	 * Attribute countryDivision1
	 */
	private java.lang.String countryDivision1;
	/**
	 * Gets value for attribute countryDivision1
	 */
	public java.lang.String getCountryDivision1() {
		return this.countryDivision1;
	}
	/**
	 * Sets value for attribute countryDivision1
	 */
	public void setCountryDivision1(java.lang.String countryDivision1) {
		this.countryDivision1 = countryDivision1;
	}
	/**
	 * Attribute countryDivision2
	 */
	private java.lang.String countryDivision2;
	/**
	 * Gets value for attribute countryDivision2
	 */
	public java.lang.String getCountryDivision2() {
		return this.countryDivision2;
	}
	/**
	 * Sets value for attribute countryDivision2
	 */
	public void setCountryDivision2(java.lang.String countryDivision2) {
		this.countryDivision2 = countryDivision2;
	}
	/**
	 * Attribute city
	 */
	private java.lang.String city;
	/**
	 * Gets value for attribute city
	 */
	public java.lang.String getCity() {
		return this.city;
	}
	/**
	 * Sets value for attribute city
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	/**
	 * Attribute latitude
	 */
	private java.lang.Double latitude;
	/**
	 * Gets value for attribute latitude
	 */
	public java.lang.Double getLatitude() {
		return this.latitude;
	}
	/**
	 * Sets value for attribute latitude
	 */
	public void setLatitude(java.lang.Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * Attribute longitude
	 */
	private java.lang.Double longitude;
	/**
	 * Gets value for attribute longitude
	 */
	public java.lang.Double getLongitude() {
		return this.longitude;
	}
	/**
	 * Sets value for attribute longitude
	 */
	public void setLongitude(java.lang.Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * Attribute accuracy
	 */
	private java.lang.Double accuracy;
	/**
	 * Gets value for attribute accuracy
	 */
	public java.lang.Double getAccuracy() {
		return this.accuracy;
	}
	/**
	 * Sets value for attribute accuracy
	 */
	public void setAccuracy(java.lang.Double accuracy) {
		this.accuracy = accuracy;
	}
	/**
	 * Attribute domain
	 */
	private java.lang.String domain;
	/**
	 * Gets value for attribute domain
	 */
	public java.lang.String getDomain() {
		return this.domain;
	}
	/**
	 * Sets value for attribute domain
	 */
	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}
	/**
	 * Attribute isp
	 */
	private java.lang.String isp;
	/**
	 * Gets value for attribute isp
	 */
	public java.lang.String getIsp() {
		return this.isp;
	}
	/**
	 * Sets value for attribute isp
	 */
	public void setIsp(java.lang.String isp) {
		this.isp = isp;
	}
	/**
	 * Attribute userType
	 */
	private java.lang.String userType;
	/**
	 * Gets value for attribute userType
	 */
	public java.lang.String getUserType() {
		return this.userType;
	}
	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}
	/**
	 * Attribute anonymous
	 */
	private java.lang.Double anonymous;
	/**
	 * Gets value for attribute anonymous
	 */
	public java.lang.Double getAnonymous() {
		return this.anonymous;
	}
	/**
	 * Sets value for attribute anonymous
	 */
	public void setAnonymous(java.lang.Double anonymous) {
		this.anonymous = anonymous;
	}
	/**
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Returns <code>true</code> if the argument is an GeoInformationEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof GeoInformationEntity))
		{
			return false;
		}
		final GeoInformationEntity that = (GeoInformationEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
