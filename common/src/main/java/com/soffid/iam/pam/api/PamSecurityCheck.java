//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject PamSecurityCheck
 **/
public class PamSecurityCheck

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute allowed

	 */
	private boolean allowed;

	/**
	 * Attribute obligations

	 */
	private java.util.List<com.soffid.iam.rc.api.RequestedObligation> obligations;

	public PamSecurityCheck()
	{
	}

	public PamSecurityCheck(boolean allowed, java.util.List<com.soffid.iam.rc.api.RequestedObligation> obligations)
	{
		super();
		this.allowed = allowed;
		this.obligations = obligations;
	}

	public PamSecurityCheck(boolean allowed)
	{
		super();
		this.allowed = allowed;
	}

	public PamSecurityCheck(PamSecurityCheck otherBean)
	{
		this(otherBean.allowed, otherBean.obligations);
	}

	/**
	 * Gets value for attribute allowed
	 */
	public boolean isAllowed() {
		return this.allowed;
	}

	/**
	 * Sets value for attribute allowed
	 */
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}

	/**
	 * Gets value for attribute obligations
	 */
	public java.util.List<com.soffid.iam.rc.api.RequestedObligation> getObligations() {
		return this.obligations;
	}

	/**
	 * Sets value for attribute obligations
	 */
	public void setObligations(java.util.List<com.soffid.iam.rc.api.RequestedObligation> obligations) {
		this.obligations = obligations;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[allowed: ");
		b.append (this.allowed);
		b.append (", obligations: ");
		b.append (this.obligations);
		b.append ("]");
		return b.toString();
	}

}
