//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject DeployToken
 **/
public class DeployToken

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private int id;

	public DeployToken()
	{
	}

	public DeployToken(int id)
	{
		super();
		this.id = id;
	}

	public DeployToken(DeployToken otherBean)
	{
		this(otherBean.id);
	}

	/**
	 * Gets value for attribute id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(int id) {
		this.id = id;
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
		b.append ("]");
		return b.toString();
	}

}
