//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject NetworkAuthorization
 **/
public class NetworkAuthorization

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute identity

	 */
	private com.soffid.iam.base.api.Identity identity;

	/**
	 * Attribute level

	 */
	private java.lang.Integer level;

	/**
	 * Attribute mask

	 */
	private java.lang.String mask;

	/**
	 * Attribute networkCode

	 */
	private java.lang.String networkCode;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	public NetworkAuthorization()
	{
	}

	public NetworkAuthorization(com.soffid.iam.base.api.Identity identity, java.lang.Integer level, java.lang.String mask, java.lang.String networkCode, java.lang.Long id)
	{
		super();
		this.identity = identity;
		this.level = level;
		this.mask = mask;
		this.networkCode = networkCode;
		this.id = id;
	}

	public NetworkAuthorization(com.soffid.iam.base.api.Identity identity, java.lang.Integer level, java.lang.String networkCode)
	{
		super();
		this.identity = identity;
		this.level = level;
		this.networkCode = networkCode;
	}

	public NetworkAuthorization(NetworkAuthorization otherBean)
	{
		this(otherBean.identity, otherBean.level, otherBean.mask, otherBean.networkCode, otherBean.id);
	}

	/**
	 * Gets value for attribute identity
	 */
	public com.soffid.iam.base.api.Identity getIdentity() {
		return this.identity;
	}

	/**
	 * Sets value for attribute identity
	 */
	public void setIdentity(com.soffid.iam.base.api.Identity identity) {
		this.identity = identity;
	}

	/**
	 * Gets value for attribute level
	 */
	public java.lang.Integer getLevel() {
		return this.level;
	}

	/**
	 * Sets value for attribute level
	 */
	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}

	/**
	 * Gets value for attribute mask
	 */
	public java.lang.String getMask() {
		return this.mask;
	}

	/**
	 * Sets value for attribute mask
	 */
	public void setMask(java.lang.String mask) {
		this.mask = mask;
	}

	/**
	 * Gets value for attribute networkCode
	 */
	public java.lang.String getNetworkCode() {
		return this.networkCode;
	}

	/**
	 * Sets value for attribute networkCode
	 */
	public void setNetworkCode(java.lang.String networkCode) {
		this.networkCode = networkCode;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[identity: ");
		b.append (this.identity);
		b.append (", level: ");
		b.append (this.level);
		b.append (", mask: ");
		b.append (this.mask);
		b.append (", networkCode: ");
		b.append (this.networkCode);
		b.append (", id: ");
		b.append (this.id);
		b.append ("]");
		return b.toString();
	}

}
