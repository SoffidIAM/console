//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject ServerRegistrationToken
 **/
public class ServerRegistrationToken

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute step

	 */
	private int step;

	public ServerRegistrationToken()
	{
	}

	public ServerRegistrationToken(int step)
	{
		super();
		this.step = step;
	}

	public ServerRegistrationToken(ServerRegistrationToken otherBean)
	{
		this(otherBean.step);
	}

	/**
	 * Gets value for attribute step
	 */
	public int getStep() {
		return this.step;
	}

	/**
	 * Sets value for attribute step
	 */
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[step: ");
		b.append (this.step);
		b.append ("]");
		return b.toString();
	}

}
