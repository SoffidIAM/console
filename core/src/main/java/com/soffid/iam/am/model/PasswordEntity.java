//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity PasswordEntity
 */

public abstract class PasswordEntity {

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
	 * Attribute password
	 */
	private java.lang.String password;
	/**
	 * Gets value for attribute password
	 */
	public java.lang.String getPassword() {
		return this.password;
	}
	/**
	 * Sets value for attribute password
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	/**
	 * Attribute password2
	 */
	private java.lang.String password2;
	/**
	 * Gets value for attribute password2
	 */
	public java.lang.String getPassword2() {
		return this.password2;
	}
	/**
	 * Sets value for attribute password2
	 */
	public void setPassword2(java.lang.String password2) {
		this.password2 = password2;
	}
	/**
	 * Attribute active
	 */
	private java.lang.String active;
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
	 * Attribute order
	 */
	private java.lang.Long order;
	/**
	 * Gets value for attribute order
	 */
	public java.lang.Long getOrder() {
		return this.order;
	}
	/**
	 * Sets value for attribute order
	 */
	public void setOrder(java.lang.Long order) {
		this.order = order;
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
	 * Attribute expirationDate
	 */
	private java.util.Date expirationDate;
	/**
	 * Gets value for attribute expirationDate
	 */
	public java.util.Date getExpirationDate() {
		return this.expirationDate;
	}
	/**
	 * Sets value for attribute expirationDate
	 */
	public void setExpirationDate(java.util.Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * Attribute domain
	 */
	private com.soffid.iam.am.model.PasswordDomainEntity domain;
	/**
	 * Gets value for attribute domain
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity getDomain() {
		return this.domain;
	}
	/**
	 * Sets value for attribute domain
	 */
	public void setDomain(com.soffid.iam.am.model.PasswordDomainEntity domain) {
		this.domain = domain;
	}
	/**
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
	}
	/**
	 * Attribute fails
	 */
	private java.lang.Integer fails;
	/**
	 * Gets value for attribute fails
	 */
	public java.lang.Integer getFails() {
		return this.fails;
	}
	/**
	 * Sets value for attribute fails
	 */
	public void setFails(java.lang.Integer fails) {
		this.fails = fails;
	}
	/**
	 * Attribute unlockDate
	 */
	private java.util.Date unlockDate;
	/**
	 * Gets value for attribute unlockDate
	 */
	public java.util.Date getUnlockDate() {
		return this.unlockDate;
	}
	/**
	 * Sets value for attribute unlockDate
	 */
	public void setUnlockDate(java.util.Date unlockDate) {
		this.unlockDate = unlockDate;
	}
	/**
	 * Returns <code>true</code> if the argument is an PasswordEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PasswordEntity))
		{
			return false;
		}
		final PasswordEntity that = (PasswordEntity)object;
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
