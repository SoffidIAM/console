//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity PasswordPolicyEntity
 */

public abstract class PasswordPolicyEntity {

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
	 * Attribute description
	 */
	private java.lang.String description;
	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}
	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	/**
	 * Attribute userType
	 */
	private com.soffid.iam.base.model.UserTypeEntity userType;
	/**
	 * Gets value for attribute userType
	 */
	public com.soffid.iam.base.model.UserTypeEntity getUserType() {
		return this.userType;
	}
	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(com.soffid.iam.base.model.UserTypeEntity userType) {
		this.userType = userType;
	}
	/**
	 * Attribute type
	 * A = Automatica
	 * M = Manual
	 */
	private java.lang.String type;
	/**
	 * Gets value for attribute type
	 */
	public java.lang.String getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}
	/**
	 * Attribute renewalTime
	 */
	private java.lang.Long renewalTime;
	/**
	 * Gets value for attribute renewalTime
	 */
	public java.lang.Long getRenewalTime() {
		return this.renewalTime;
	}
	/**
	 * Sets value for attribute renewalTime
	 */
	public void setRenewalTime(java.lang.Long renewalTime) {
		this.renewalTime = renewalTime;
	}
	/**
	 * Attribute maximumPeriod
	 */
	private java.lang.Long maximumPeriod;
	/**
	 * Gets value for attribute maximumPeriod
	 */
	public java.lang.Long getMaximumPeriod() {
		return this.maximumPeriod;
	}
	/**
	 * Sets value for attribute maximumPeriod
	 */
	public void setMaximumPeriod(java.lang.Long maximumPeriod) {
		this.maximumPeriod = maximumPeriod;
	}
	/**
	 * Attribute maximumPeriodExpired
	 */
	private java.lang.Long maximumPeriodExpired;
	/**
	 * Gets value for attribute maximumPeriodExpired
	 */
	public java.lang.Long getMaximumPeriodExpired() {
		return this.maximumPeriodExpired;
	}
	/**
	 * Sets value for attribute maximumPeriodExpired
	 */
	public void setMaximumPeriodExpired(java.lang.Long maximumPeriodExpired) {
		this.maximumPeriodExpired = maximumPeriodExpired;
	}
	/**
	 * Attribute minimumPeriod
	 */
	private java.lang.Long minimumPeriod;
	/**
	 * Gets value for attribute minimumPeriod
	 */
	public java.lang.Long getMinimumPeriod() {
		return this.minimumPeriod;
	}
	/**
	 * Sets value for attribute minimumPeriod
	 */
	public void setMinimumPeriod(java.lang.Long minimumPeriod) {
		this.minimumPeriod = minimumPeriod;
	}
	/**
	 * Attribute minimumLength
	 */
	private java.lang.Long minimumLength;
	/**
	 * Gets value for attribute minimumLength
	 */
	public java.lang.Long getMinimumLength() {
		return this.minimumLength;
	}
	/**
	 * Sets value for attribute minimumLength
	 */
	public void setMinimumLength(java.lang.Long minimumLength) {
		this.minimumLength = minimumLength;
	}
	/**
	 * Attribute maximumLength
	 */
	private java.lang.Long maximumLength;
	/**
	 * Gets value for attribute maximumLength
	 */
	public java.lang.Long getMaximumLength() {
		return this.maximumLength;
	}
	/**
	 * Sets value for attribute maximumLength
	 */
	public void setMaximumLength(java.lang.Long maximumLength) {
		this.maximumLength = maximumLength;
	}
	/**
	 * Attribute regularExpression
	 */
	private java.lang.String regularExpression;
	/**
	 * Gets value for attribute regularExpression
	 */
	public java.lang.String getRegularExpression() {
		return this.regularExpression;
	}
	/**
	 * Sets value for attribute regularExpression
	 */
	public void setRegularExpression(java.lang.String regularExpression) {
		this.regularExpression = regularExpression;
	}
	/**
	 * Attribute minimumUppercase
	 */
	private java.lang.Long minimumUppercase;
	/**
	 * Gets value for attribute minimumUppercase
	 */
	public java.lang.Long getMinimumUppercase() {
		return this.minimumUppercase;
	}
	/**
	 * Sets value for attribute minimumUppercase
	 */
	public void setMinimumUppercase(java.lang.Long minimumUppercase) {
		this.minimumUppercase = minimumUppercase;
	}
	/**
	 * Attribute maximumUppercase
	 */
	private java.lang.Long maximumUppercase;
	/**
	 * Gets value for attribute maximumUppercase
	 */
	public java.lang.Long getMaximumUppercase() {
		return this.maximumUppercase;
	}
	/**
	 * Sets value for attribute maximumUppercase
	 */
	public void setMaximumUppercase(java.lang.Long maximumUppercase) {
		this.maximumUppercase = maximumUppercase;
	}
	/**
	 * Attribute minimumLowercase
	 */
	private java.lang.Long minimumLowercase;
	/**
	 * Gets value for attribute minimumLowercase
	 */
	public java.lang.Long getMinimumLowercase() {
		return this.minimumLowercase;
	}
	/**
	 * Sets value for attribute minimumLowercase
	 */
	public void setMinimumLowercase(java.lang.Long minimumLowercase) {
		this.minimumLowercase = minimumLowercase;
	}
	/**
	 * Attribute maximumLowercase
	 */
	private java.lang.Long maximumLowercase;
	/**
	 * Gets value for attribute maximumLowercase
	 */
	public java.lang.Long getMaximumLowercase() {
		return this.maximumLowercase;
	}
	/**
	 * Sets value for attribute maximumLowercase
	 */
	public void setMaximumLowercase(java.lang.Long maximumLowercase) {
		this.maximumLowercase = maximumLowercase;
	}
	/**
	 * Attribute minimumNumbers
	 */
	private java.lang.Long minimumNumbers;
	/**
	 * Gets value for attribute minimumNumbers
	 */
	public java.lang.Long getMinimumNumbers() {
		return this.minimumNumbers;
	}
	/**
	 * Sets value for attribute minimumNumbers
	 */
	public void setMinimumNumbers(java.lang.Long minimumNumbers) {
		this.minimumNumbers = minimumNumbers;
	}
	/**
	 * Attribute maximumNumbers
	 */
	private java.lang.Long maximumNumbers;
	/**
	 * Gets value for attribute maximumNumbers
	 */
	public java.lang.Long getMaximumNumbers() {
		return this.maximumNumbers;
	}
	/**
	 * Sets value for attribute maximumNumbers
	 */
	public void setMaximumNumbers(java.lang.Long maximumNumbers) {
		this.maximumNumbers = maximumNumbers;
	}
	/**
	 * Attribute minimumSymbols
	 */
	private java.lang.Long minimumSymbols;
	/**
	 * Gets value for attribute minimumSymbols
	 */
	public java.lang.Long getMinimumSymbols() {
		return this.minimumSymbols;
	}
	/**
	 * Sets value for attribute minimumSymbols
	 */
	public void setMinimumSymbols(java.lang.Long minimumSymbols) {
		this.minimumSymbols = minimumSymbols;
	}
	/**
	 * Attribute maximumSymbols
	 */
	private java.lang.Long maximumSymbols;
	/**
	 * Gets value for attribute maximumSymbols
	 */
	public java.lang.Long getMaximumSymbols() {
		return this.maximumSymbols;
	}
	/**
	 * Sets value for attribute maximumSymbols
	 */
	public void setMaximumSymbols(java.lang.Long maximumSymbols) {
		this.maximumSymbols = maximumSymbols;
	}
	/**
	 * Attribute maximumHistorical
	 */
	private java.lang.Long maximumHistorical;
	/**
	 * Gets value for attribute maximumHistorical
	 */
	public java.lang.Long getMaximumHistorical() {
		return this.maximumHistorical;
	}
	/**
	 * Sets value for attribute maximumHistorical
	 */
	public void setMaximumHistorical(java.lang.Long maximumHistorical) {
		this.maximumHistorical = maximumHistorical;
	}
	/**
	 * Attribute passwordDomain
	 */
	private com.soffid.iam.am.model.PasswordDomainEntity passwordDomain;
	/**
	 * Gets value for attribute passwordDomain
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity getPasswordDomain() {
		return this.passwordDomain;
	}
	/**
	 * Sets value for attribute passwordDomain
	 */
	public void setPasswordDomain(com.soffid.iam.am.model.PasswordDomainEntity passwordDomain) {
		this.passwordDomain = passwordDomain;
	}
	/**
	 * Attribute forbiddenWords
	 */
	private java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> forbiddenWords =  new java.util.HashSet<com.soffid.iam.am.model.PolicyForbiddenWordEntity>();
	/**
	 * Gets value for attribute forbiddenWords
	 */
	public java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> getForbiddenWords() {
		return this.forbiddenWords;
	}
	/**
	 * Sets value for attribute forbiddenWords
	 */
	public void setForbiddenWords(java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> forbiddenWords) {
		this.forbiddenWords = forbiddenWords;
	}
	/**
	 * Attribute allowPasswordQuery
	 */
	private java.lang.Boolean allowPasswordQuery;
	/**
	 * Gets value for attribute allowPasswordQuery
	 */
	public java.lang.Boolean getAllowPasswordQuery() {
		return this.allowPasswordQuery;
	}
	/**
	 * Sets value for attribute allowPasswordQuery
	 */
	public void setAllowPasswordQuery(java.lang.Boolean allowPasswordQuery) {
		this.allowPasswordQuery = allowPasswordQuery;
	}
	/**
	 * Attribute allowPasswordChange
	 */
	private java.lang.Boolean allowPasswordChange;
	/**
	 * Gets value for attribute allowPasswordChange
	 */
	public java.lang.Boolean getAllowPasswordChange() {
		return this.allowPasswordChange;
	}
	/**
	 * Sets value for attribute allowPasswordChange
	 */
	public void setAllowPasswordChange(java.lang.Boolean allowPasswordChange) {
		this.allowPasswordChange = allowPasswordChange;
	}
	/**
	 * Attribute storeUserPasswords
	 */
	private java.lang.Boolean storeUserPasswords;
	/**
	 * Gets value for attribute storeUserPasswords
	 */
	public java.lang.Boolean getStoreUserPasswords() {
		return this.storeUserPasswords;
	}
	/**
	 * Sets value for attribute storeUserPasswords
	 */
	public void setStoreUserPasswords(java.lang.Boolean storeUserPasswords) {
		this.storeUserPasswords = storeUserPasswords;
	}
	/**
	 * Attribute complexPasswords
	 * Enable complex password just like MS AD
	 */
	private java.lang.Boolean complexPasswords = false;
	/**
	 * Gets value for attribute complexPasswords
	 */
	public java.lang.Boolean getComplexPasswords() {
		return this.complexPasswords;
	}
	/**
	 * Sets value for attribute complexPasswords
	 */
	public void setComplexPasswords(java.lang.Boolean complexPasswords) {
		this.complexPasswords = complexPasswords;
	}
	/**
	 * Attribute maxFailures
	 * Maximum number of failures before locking
	 */
	private java.lang.Integer maxFailures;
	/**
	 * Gets value for attribute maxFailures
	 */
	public java.lang.Integer getMaxFailures() {
		return this.maxFailures;
	}
	/**
	 * Sets value for attribute maxFailures
	 */
	public void setMaxFailures(java.lang.Integer maxFailures) {
		this.maxFailures = maxFailures;
	}
	/**
	 * Attribute unlockAfterSeconds
	 * Automatically unlock after X seconds. Null locks permanently
	 */
	private java.lang.Integer unlockAfterSeconds;
	/**
	 * Gets value for attribute unlockAfterSeconds
	 */
	public java.lang.Integer getUnlockAfterSeconds() {
		return this.unlockAfterSeconds;
	}
	/**
	 * Sets value for attribute unlockAfterSeconds
	 */
	public void setUnlockAfterSeconds(java.lang.Integer unlockAfterSeconds) {
		this.unlockAfterSeconds = unlockAfterSeconds;
	}
	/**
	 * Attribute validationScript
	 * Script to check password is valid
	 */
	private java.lang.String validationScript;
	/**
	 * Gets value for attribute validationScript
	 */
	public java.lang.String getValidationScript() {
		return this.validationScript;
	}
	/**
	 * Sets value for attribute validationScript
	 */
	public void setValidationScript(java.lang.String validationScript) {
		this.validationScript = validationScript;
	}
	/**
	 * Attribute validationScriptDescription
	 * Description of script to check password is valid
	 */
	private java.lang.String validationScriptDescription;
	/**
	 * Gets value for attribute validationScriptDescription
	 */
	public java.lang.String getValidationScriptDescription() {
		return this.validationScriptDescription;
	}
	/**
	 * Sets value for attribute validationScriptDescription
	 */
	public void setValidationScriptDescription(java.lang.String validationScriptDescription) {
		this.validationScriptDescription = validationScriptDescription;
	}
	/**
	 * Attribute checkPasswordBreached
	 * Allows Soffid to check it the password has been breached if if has the network intelligence license active
	 */
	private java.lang.Boolean checkPasswordBreached = false;
	/**
	 * Gets value for attribute checkPasswordBreached
	 */
	public java.lang.Boolean getCheckPasswordBreached() {
		return this.checkPasswordBreached;
	}
	/**
	 * Sets value for attribute checkPasswordBreached
	 */
	public void setCheckPasswordBreached(java.lang.Boolean checkPasswordBreached) {
		this.checkPasswordBreached = checkPasswordBreached;
	}
	/**
	 * Attribute createdOn

	 */
	private java.util.Date createdOn;
	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}
	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;
	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}
	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}
	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}
	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	/**
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}
	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
	}
	/**
	 * Returns <code>true</code> if the argument is an PasswordPolicyEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PasswordPolicyEntity))
		{
			return false;
		}
		final PasswordPolicyEntity that = (PasswordPolicyEntity)object;
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
