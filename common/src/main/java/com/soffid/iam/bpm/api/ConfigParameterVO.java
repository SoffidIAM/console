//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject ConfigParameterVO
 **/
public class ConfigParameterVO

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute app

	 */
	private java.lang.String app;

	/**
	 * Attribute key

	 */
	private java.lang.String key;

	/**
	 * Attribute value

	 */
	private java.lang.String value;

	public ConfigParameterVO()
	{
	}

	public ConfigParameterVO(java.lang.Long id, java.lang.String app, java.lang.String key, java.lang.String value)
	{
		super();
		this.id = id;
		this.app = app;
		this.key = key;
		this.value = value;
	}

	public ConfigParameterVO(ConfigParameterVO otherBean)
	{
		this(otherBean.id, otherBean.app, otherBean.key, otherBean.value);
	}

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
	 * Gets value for attribute app
	 */
	public java.lang.String getApp() {
		return this.app;
	}

	/**
	 * Sets value for attribute app
	 */
	public void setApp(java.lang.String app) {
		this.app = app;
	}

	/**
	 * Gets value for attribute key
	 */
	public java.lang.String getKey() {
		return this.key;
	}

	/**
	 * Sets value for attribute key
	 */
	public void setKey(java.lang.String key) {
		this.key = key;
	}

	/**
	 * Gets value for attribute value
	 */
	public java.lang.String getValue() {
		return this.value;
	}

	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", app: ");
		b.append (this.app);
		b.append (", key: ");
		b.append (this.key);
		b.append (", value: ");
		b.append (this.value);
		b.append ("]");
		return b.toString();
	}

}
