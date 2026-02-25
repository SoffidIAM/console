//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject SoDRuleMatrix
 **/
public class SoDRuleMatrix

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
	 * Attribute ruleId

	 */
	private java.lang.Long ruleId;

	/**
	 * Attribute risk

	 */
	private com.soffid.iam.rc.api.SoDRisk risk;

	/**
	 * Attribute row

	 */
	private java.lang.Long row;

	/**
	 * Attribute column

	 */
	private java.lang.Long column;

	public SoDRuleMatrix()
	{
	}

	public SoDRuleMatrix(java.lang.Long id, java.lang.Long ruleId, com.soffid.iam.rc.api.SoDRisk risk, java.lang.Long row, java.lang.Long column)
	{
		super();
		this.id = id;
		this.ruleId = ruleId;
		this.risk = risk;
		this.row = row;
		this.column = column;
	}

	public SoDRuleMatrix(java.lang.Long ruleId, com.soffid.iam.rc.api.SoDRisk risk, java.lang.Long row, java.lang.Long column)
	{
		super();
		this.ruleId = ruleId;
		this.risk = risk;
		this.row = row;
		this.column = column;
	}

	public SoDRuleMatrix(SoDRuleMatrix otherBean)
	{
		this(otherBean.id, otherBean.ruleId, otherBean.risk, otherBean.row, otherBean.column);
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
	 * Gets value for attribute ruleId
	 */
	public java.lang.Long getRuleId() {
		return this.ruleId;
	}

	/**
	 * Sets value for attribute ruleId
	 */
	public void setRuleId(java.lang.Long ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * Gets value for attribute risk
	 */
	public com.soffid.iam.rc.api.SoDRisk getRisk() {
		return this.risk;
	}

	/**
	 * Sets value for attribute risk
	 */
	public void setRisk(com.soffid.iam.rc.api.SoDRisk risk) {
		this.risk = risk;
	}

	/**
	 * Gets value for attribute row
	 */
	public java.lang.Long getRow() {
		return this.row;
	}

	/**
	 * Sets value for attribute row
	 */
	public void setRow(java.lang.Long row) {
		this.row = row;
	}

	/**
	 * Gets value for attribute column
	 */
	public java.lang.Long getColumn() {
		return this.column;
	}

	/**
	 * Sets value for attribute column
	 */
	public void setColumn(java.lang.Long column) {
		this.column = column;
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
		b.append (", ruleId: ");
		b.append (this.ruleId);
		b.append (", risk: ");
		b.append (this.risk);
		b.append (", row: ");
		b.append (this.row);
		b.append (", column: ");
		b.append (this.column);
		b.append ("]");
		return b.toString();
	}

}
