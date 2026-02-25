//
// (C) 2020 Soffid
//
//

package com.soffid.iam.impl.api;
/**
 * ValueObject UpgradeStatus
 **/
public class UpgradeStatus

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute upgradeNeeded

	 */
	private boolean upgradeNeeded;

	/**
	 * Attribute upgradeInProgress

	 */
	private boolean upgradeInProgress;

	/**
	 * Attribute error

	 */
	private boolean error;

	/**
	 * Attribute upgradeProcessStatus

	 */
	private java.lang.String upgradeProcessStatus;

	public UpgradeStatus()
	{
	}

	public UpgradeStatus(boolean upgradeNeeded, boolean upgradeInProgress, boolean error, java.lang.String upgradeProcessStatus)
	{
		super();
		this.upgradeNeeded = upgradeNeeded;
		this.upgradeInProgress = upgradeInProgress;
		this.error = error;
		this.upgradeProcessStatus = upgradeProcessStatus;
	}

	public UpgradeStatus(UpgradeStatus otherBean)
	{
		this(otherBean.upgradeNeeded, otherBean.upgradeInProgress, otherBean.error, otherBean.upgradeProcessStatus);
	}

	/**
	 * Gets value for attribute upgradeNeeded
	 */
	public boolean isUpgradeNeeded() {
		return this.upgradeNeeded;
	}

	/**
	 * Sets value for attribute upgradeNeeded
	 */
	public void setUpgradeNeeded(boolean upgradeNeeded) {
		this.upgradeNeeded = upgradeNeeded;
	}

	/**
	 * Gets value for attribute upgradeInProgress
	 */
	public boolean isUpgradeInProgress() {
		return this.upgradeInProgress;
	}

	/**
	 * Sets value for attribute upgradeInProgress
	 */
	public void setUpgradeInProgress(boolean upgradeInProgress) {
		this.upgradeInProgress = upgradeInProgress;
	}

	/**
	 * Gets value for attribute error
	 */
	public boolean isError() {
		return this.error;
	}

	/**
	 * Sets value for attribute error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Gets value for attribute upgradeProcessStatus
	 */
	public java.lang.String getUpgradeProcessStatus() {
		return this.upgradeProcessStatus;
	}

	/**
	 * Sets value for attribute upgradeProcessStatus
	 */
	public void setUpgradeProcessStatus(java.lang.String upgradeProcessStatus) {
		this.upgradeProcessStatus = upgradeProcessStatus;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[upgradeNeeded: ");
		b.append (this.upgradeNeeded);
		b.append (", upgradeInProgress: ");
		b.append (this.upgradeInProgress);
		b.append (", error: ");
		b.append (this.error);
		b.append (", upgradeProcessStatus: ");
		b.append (this.upgradeProcessStatus);
		b.append ("]");
		return b.toString();
	}

}
