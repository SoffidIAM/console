//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject GeoInformation
 **/
public class GeoInformation

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute ip

	 */
	private java.lang.String ip;

	/**
	 * Attribute date

	 */
	private java.util.Date date;

	/**
	 * Attribute country

	 */
	private java.lang.String country;

	/**
	 * Attribute countryDivision1

	 */
	private java.lang.String countryDivision1;

	/**
	 * Attribute countryDivision2

	 */
	private java.lang.String countryDivision2;

	/**
	 * Attribute city

	 */
	private java.lang.String city;

	/**
	 * Attribute latitude

	 */
	private java.lang.Double latitude;

	/**
	 * Attribute longitude

	 */
	private java.lang.Double longitude;

	/**
	 * Attribute accuracy

	 */
	private java.lang.Double accuracy;

	/**
	 * Attribute domain

	 */
	private java.lang.String domain;

	/**
	 * Attribute isp

	 */
	private java.lang.String isp;

	/**
	 * Attribute userType

	 */
	private java.lang.String userType;

	/**
	 * Attribute anonymous

	 */
	private java.lang.Double anonymous;

	public GeoInformation()
	{
	}

	public GeoInformation(java.lang.String ip, java.util.Date date, java.lang.String country, java.lang.String countryDivision1, java.lang.String countryDivision2, java.lang.String city, java.lang.Double latitude, java.lang.Double longitude, java.lang.Double accuracy, java.lang.String domain, java.lang.String isp, java.lang.String userType, java.lang.Double anonymous)
	{
		super();
		this.ip = ip;
		this.date = date;
		this.country = country;
		this.countryDivision1 = countryDivision1;
		this.countryDivision2 = countryDivision2;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.domain = domain;
		this.isp = isp;
		this.userType = userType;
		this.anonymous = anonymous;
	}

	public GeoInformation(java.lang.String ip, java.util.Date date)
	{
		super();
		this.ip = ip;
		this.date = date;
	}

	public GeoInformation(GeoInformation otherBean)
	{
		this(otherBean.ip, otherBean.date, otherBean.country, otherBean.countryDivision1, otherBean.countryDivision2, otherBean.city, otherBean.latitude, otherBean.longitude, otherBean.accuracy, otherBean.domain, otherBean.isp, otherBean.userType, otherBean.anonymous);
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[ip: ");
		b.append (this.ip);
		b.append (", date: ");
		b.append (this.date);
		b.append (", country: ");
		b.append (this.country);
		b.append (", countryDivision1: ");
		b.append (this.countryDivision1);
		b.append (", countryDivision2: ");
		b.append (this.countryDivision2);
		b.append (", city: ");
		b.append (this.city);
		b.append (", latitude: ");
		b.append (this.latitude);
		b.append (", longitude: ");
		b.append (this.longitude);
		b.append (", accuracy: ");
		b.append (this.accuracy);
		b.append (", domain: ");
		b.append (this.domain);
		b.append (", isp: ");
		b.append (this.isp);
		b.append (", userType: ");
		b.append (this.userType);
		b.append (", anonymous: ");
		b.append (this.anonymous);
		b.append ("]");
		return b.toString();
	}

}
