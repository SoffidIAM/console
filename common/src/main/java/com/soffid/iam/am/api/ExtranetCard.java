//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject ExtranetCard
 **/
public class ExtranetCard

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
	 * Attribute userCode

	 */
	private java.lang.String userCode;

	/**
	 * Attribute code

	 */
	private java.lang.String code;

	/**
	 * Attribute outputDate

	 */
	private java.util.Calendar outputDate;

	/**
	 * Attribute expirationDate

	 */
	private java.util.Calendar expirationDate;

	/**
	 * Attribute active

	 */
	private java.lang.String active;

	/**
	 * Attribute content

	 */
	private java.util.Collection<com.soffid.iam.am.api.ExtranetCardContent> content;

	public ExtranetCard()
	{
	}

	public ExtranetCard(java.lang.Long id, java.lang.String userCode, java.lang.String code, java.util.Calendar outputDate, java.util.Calendar expirationDate, java.lang.String active, java.util.Collection<com.soffid.iam.am.api.ExtranetCardContent> content)
	{
		super();
		this.id = id;
		this.userCode = userCode;
		this.code = code;
		this.outputDate = outputDate;
		this.expirationDate = expirationDate;
		this.active = active;
		this.content = content;
	}

	public ExtranetCard(ExtranetCard otherBean)
	{
		this(otherBean.id, otherBean.userCode, otherBean.code, otherBean.outputDate, otherBean.expirationDate, otherBean.active, otherBean.content);
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
	 * Gets value for attribute userCode
	 */
	public java.lang.String getUserCode() {
		return this.userCode;
	}

	/**
	 * Sets value for attribute userCode
	 */
	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	/**
	 * Gets value for attribute code
	 */
	public java.lang.String getCode() {
		return this.code;
	}

	/**
	 * Sets value for attribute code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * Gets value for attribute outputDate
	 */
	public java.util.Calendar getOutputDate() {
		return this.outputDate;
	}

	/**
	 * Sets value for attribute outputDate
	 */
	public void setOutputDate(java.util.Calendar outputDate) {
		this.outputDate = outputDate;
	}

	/**
	 * Gets value for attribute expirationDate
	 */
	public java.util.Calendar getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Sets value for attribute expirationDate
	 */
	public void setExpirationDate(java.util.Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets value for attribute active
	 */
	public java.lang.String getActive() {
		return this.active;
	}

	/**
	 * Sets value for attribute active
	 */
	public void setActive(java.lang.String active) {
		this.active = active;
	}

	/**
	 * Gets value for attribute content
	 */
	public java.util.Collection<com.soffid.iam.am.api.ExtranetCardContent> getContent() {
		return this.content;
	}

	/**
	 * Sets value for attribute content
	 */
	public void setContent(java.util.Collection<com.soffid.iam.am.api.ExtranetCardContent> content) {
		this.content = content;
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
		b.append (", userCode: ");
		b.append (this.userCode);
		b.append (", code: ");
		b.append (this.code);
		b.append (", outputDate: ");
		b.append (this.outputDate);
		b.append (", expirationDate: ");
		b.append (this.expirationDate);
		b.append (", active: ");
		b.append (this.active);
		b.append (", content: ");
		b.append (this.content);
		b.append ("]");
		return b.toString();
	}

}
