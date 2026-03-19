//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject PasswordPolicy
 **/
public class PasswordPolicy

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
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute type

	 */
	private java.lang.String type;

	/**
	 * Attribute renewalTime

	 */
	private java.lang.Long renewalTime;

	/**
	 * Attribute maximumPeriod

	 */
	private java.lang.Long maximumPeriod;

	/**
	 * Attribute maximumPeriodExpired

	 */
	private java.lang.Long maximumPeriodExpired;

	/**
	 * Attribute minimumPeriod

	 */
	private java.lang.Long minimumPeriod;

	/**
	 * Attribute minimumLength

	 */
	private java.lang.Long minimumLength;

	/**
	 * Attribute maximumLength

	 */
	private java.lang.Long maximumLength;

	/**
	 * Attribute regularExpression

	 */
	private java.lang.String regularExpression;

	/**
	 * Attribute minimumUppercase
	 * Minimum number of uppercase letters

	 */
	private java.lang.Long minimumUppercase;

	/**
	 * Attribute maximumUppercase
	 * Maximum number of uppercase letters

	 */
	private java.lang.Long maximumUppercase;

	/**
	 * Attribute minimumLowercase
	 * Minimum number of lowercase letters

	 */
	private java.lang.Long minimumLowercase;

	/**
	 * Attribute maximumLowercase
	 * Maximum number of lowercase letters

	 */
	private java.lang.Long maximumLowercase;

	/**
	 * Attribute minimumNumbers
	 * Minimum number of numbers

	 */
	private java.lang.Long minimumNumbers;

	/**
	 * Attribute maximumNumbers
	 * Minimum number of numbers

	 */
	private java.lang.Long maximumNumbers;

	/**
	 * Attribute minimumSymbols
	 * Minimum number of non alphanumeric symbols

	 */
	private java.lang.Long minimumSymbols;

	/**
	 * Attribute maximumSymbols
	 * Maximum number of non alphanumeric symbols

	 */
	private java.lang.Long maximumSymbols;

	/**
	 * Attribute maximumHistorical
	 * The password should not be the same as any of the previous ones. This field contains the number of old passwords to check

	 */
	private java.lang.Long maximumHistorical;

	/**
	 * Attribute userType
	 * User type. The most comman values are I for internals and E for externals.

	 */
	private java.lang.String userType;

	/**
	 * Attribute userTypeDescription

	 */
	private java.lang.String userTypeDescription;

	/**
	 * Attribute usersDomainCode
	 * Users domain. Default value is DEFAULT

	 */
	private java.lang.String usersDomainCode;

	/**
	 * Attribute passwordDomainCode
	 * Password domain. Default value is DEFAULT

	 */
	private java.lang.String passwordDomainCode;

	/**
	 * Attribute maxFailures

	 */
	private java.lang.Integer maxFailures;

	/**
	 * Attribute unlockAfterSeconds

	 */
	private java.lang.Integer unlockAfterSeconds;

	/**
	 * Attribute allowPasswordQuery
	 * Enables users to query password value

	 */
	private boolean allowPasswordQuery;

	/**
	 * Attribute allowPasswordChange
	 * Enables users to change password value

	 */
	private boolean allowPasswordChange;

	/**
	 * Attribute complexPasswords
	 * Enable complex password just like MS AD

	 */
	private boolean complexPasswords;

	/**
	 * Attribute validationScript
	 * Script to check password is valid

	 */
	private java.lang.String validationScript;

	/**
	 * Attribute validationScriptDescription
	 * Description of script to check password is valid

	 */
	private java.lang.String validationScriptDescription;

	/**
	 * Attribute checkPasswordBreached
	 * Allows Soffid to check it the password has been breached. Prevens user to use compromised credentials.

	 */
	private boolean checkPasswordBreached;

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

	/**
	 * Attribute updatedOn


	 */
	private java.util.Date updatedOn;

	/**
	 * Attribute updatedBy


	 */
	private java.lang.String updatedBy;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	public PasswordPolicy()
	{
	}

	public PasswordPolicy(java.lang.Long id, java.lang.String description, java.lang.String type, java.lang.Long renewalTime, java.lang.Long maximumPeriod, java.lang.Long maximumPeriodExpired, java.lang.Long minimumPeriod, java.lang.Long minimumLength, java.lang.Long maximumLength, java.lang.String regularExpression, java.lang.Long minimumUppercase, java.lang.Long maximumUppercase, java.lang.Long minimumLowercase, java.lang.Long maximumLowercase, java.lang.Long minimumNumbers, java.lang.Long maximumNumbers, java.lang.Long minimumSymbols, java.lang.Long maximumSymbols, java.lang.Long maximumHistorical, java.lang.String userType, java.lang.String userTypeDescription, java.lang.String usersDomainCode, java.lang.String passwordDomainCode, java.lang.Integer maxFailures, java.lang.Integer unlockAfterSeconds, boolean allowPasswordQuery, boolean allowPasswordChange, boolean complexPasswords, java.lang.String validationScript, java.lang.String validationScriptDescription, boolean checkPasswordBreached, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.description = description;
		this.type = type;
		this.renewalTime = renewalTime;
		this.maximumPeriod = maximumPeriod;
		this.maximumPeriodExpired = maximumPeriodExpired;
		this.minimumPeriod = minimumPeriod;
		this.minimumLength = minimumLength;
		this.maximumLength = maximumLength;
		this.regularExpression = regularExpression;
		this.minimumUppercase = minimumUppercase;
		this.maximumUppercase = maximumUppercase;
		this.minimumLowercase = minimumLowercase;
		this.maximumLowercase = maximumLowercase;
		this.minimumNumbers = minimumNumbers;
		this.maximumNumbers = maximumNumbers;
		this.minimumSymbols = minimumSymbols;
		this.maximumSymbols = maximumSymbols;
		this.maximumHistorical = maximumHistorical;
		this.userType = userType;
		this.userTypeDescription = userTypeDescription;
		this.usersDomainCode = usersDomainCode;
		this.passwordDomainCode = passwordDomainCode;
		this.maxFailures = maxFailures;
		this.unlockAfterSeconds = unlockAfterSeconds;
		this.allowPasswordQuery = allowPasswordQuery;
		this.allowPasswordChange = allowPasswordChange;
		this.complexPasswords = complexPasswords;
		this.validationScript = validationScript;
		this.validationScriptDescription = validationScriptDescription;
		this.checkPasswordBreached = checkPasswordBreached;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public PasswordPolicy(boolean allowPasswordQuery, boolean allowPasswordChange, boolean complexPasswords, boolean checkPasswordBreached)
	{
		super();
		this.allowPasswordQuery = allowPasswordQuery;
		this.allowPasswordChange = allowPasswordChange;
		this.complexPasswords = complexPasswords;
		this.checkPasswordBreached = checkPasswordBreached;
	}

	public PasswordPolicy(PasswordPolicy otherBean)
	{
		this(otherBean.id, otherBean.description, otherBean.type, otherBean.renewalTime, otherBean.maximumPeriod, otherBean.maximumPeriodExpired, otherBean.minimumPeriod, otherBean.minimumLength, otherBean.maximumLength, otherBean.regularExpression, otherBean.minimumUppercase, otherBean.maximumUppercase, otherBean.minimumLowercase, otherBean.maximumLowercase, otherBean.minimumNumbers, otherBean.maximumNumbers, otherBean.minimumSymbols, otherBean.maximumSymbols, otherBean.maximumHistorical, otherBean.userType, otherBean.userTypeDescription, otherBean.usersDomainCode, otherBean.passwordDomainCode, otherBean.maxFailures, otherBean.unlockAfterSeconds, otherBean.allowPasswordQuery, otherBean.allowPasswordChange, otherBean.complexPasswords, otherBean.validationScript, otherBean.validationScriptDescription, otherBean.checkPasswordBreached, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute maximumPeriod
	 */
	public java.lang.Long getAvailableTime() {
		return this.maximumPeriod;
	}

	/**
	 * Sets value for attribute maximumPeriod
	 */
	public void setAvailableTime(java.lang.Long maximumPeriod) {
		this.maximumPeriod = maximumPeriod;
	}

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
	 * Gets value for attribute userType
	 */
	public java.lang.String getUserType() {
		return this.userType;
	}

	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	/**
	 * Gets value for attribute userTypeDescription
	 */
	public java.lang.String getUserTypeDescription() {
		return this.userTypeDescription;
	}

	/**
	 * Sets value for attribute userTypeDescription
	 */
	public void setUserTypeDescription(java.lang.String userTypeDescription) {
		this.userTypeDescription = userTypeDescription;
	}

	/**
	 * Gets value for attribute usersDomainCode
	 */
	public java.lang.String getUsersDomainCode() {
		return this.usersDomainCode;
	}

	/**
	 * Sets value for attribute usersDomainCode
	 */
	public void setUsersDomainCode(java.lang.String usersDomainCode) {
		this.usersDomainCode = usersDomainCode;
	}

	/**
	 * Gets value for attribute passwordDomainCode
	 */
	public java.lang.String getPasswordDomainCode() {
		return this.passwordDomainCode;
	}

	/**
	 * Sets value for attribute passwordDomainCode
	 */
	public void setPasswordDomainCode(java.lang.String passwordDomainCode) {
		this.passwordDomainCode = passwordDomainCode;
	}

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
	 * Gets value for attribute allowPasswordQuery
	 */
	public boolean isAllowPasswordQuery() {
		return this.allowPasswordQuery;
	}

	/**
	 * Sets value for attribute allowPasswordQuery
	 */
	public void setAllowPasswordQuery(boolean allowPasswordQuery) {
		this.allowPasswordQuery = allowPasswordQuery;
	}

	/**
	 * Gets value for attribute allowPasswordChange
	 */
	public boolean isAllowPasswordChange() {
		return this.allowPasswordChange;
	}

	/**
	 * Sets value for attribute allowPasswordChange
	 */
	public void setAllowPasswordChange(boolean allowPasswordChange) {
		this.allowPasswordChange = allowPasswordChange;
	}

	/**
	 * Gets value for attribute complexPasswords
	 */
	public boolean isComplexPasswords() {
		return this.complexPasswords;
	}

	/**
	 * Sets value for attribute complexPasswords
	 */
	public void setComplexPasswords(boolean complexPasswords) {
		this.complexPasswords = complexPasswords;
	}

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
	 * Gets value for attribute checkPasswordBreached
	 */
	public boolean isCheckPasswordBreached() {
		return this.checkPasswordBreached;
	}

	/**
	 * Sets value for attribute checkPasswordBreached
	 */
	public void setCheckPasswordBreached(boolean checkPasswordBreached) {
		this.checkPasswordBreached = checkPasswordBreached;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", description: ");
		b.append (this.description);
		b.append (", type: ");
		b.append (this.type);
		b.append (", renewalTime: ");
		b.append (this.renewalTime);
		b.append (", maximumPeriod: ");
		b.append (this.maximumPeriod);
		b.append (", maximumPeriodExpired: ");
		b.append (this.maximumPeriodExpired);
		b.append (", minimumPeriod: ");
		b.append (this.minimumPeriod);
		b.append (", minimumLength: ");
		b.append (this.minimumLength);
		b.append (", maximumLength: ");
		b.append (this.maximumLength);
		b.append (", regularExpression: ");
		b.append (this.regularExpression);
		b.append (", minimumUppercase: ");
		b.append (this.minimumUppercase);
		b.append (", maximumUppercase: ");
		b.append (this.maximumUppercase);
		b.append (", minimumLowercase: ");
		b.append (this.minimumLowercase);
		b.append (", maximumLowercase: ");
		b.append (this.maximumLowercase);
		b.append (", minimumNumbers: ");
		b.append (this.minimumNumbers);
		b.append (", maximumNumbers: ");
		b.append (this.maximumNumbers);
		b.append (", minimumSymbols: ");
		b.append (this.minimumSymbols);
		b.append (", maximumSymbols: ");
		b.append (this.maximumSymbols);
		b.append (", maximumHistorical: ");
		b.append (this.maximumHistorical);
		b.append (", userType: ");
		b.append (this.userType);
		b.append (", userTypeDescription: ");
		b.append (this.userTypeDescription);
		b.append (", usersDomainCode: ");
		b.append (this.usersDomainCode);
		b.append (", passwordDomainCode: ");
		b.append (this.passwordDomainCode);
		b.append (", maxFailures: ");
		b.append (this.maxFailures);
		b.append (", unlockAfterSeconds: ");
		b.append (this.unlockAfterSeconds);
		b.append (", allowPasswordQuery: ");
		b.append (this.allowPasswordQuery);
		b.append (", allowPasswordChange: ");
		b.append (this.allowPasswordChange);
		b.append (", complexPasswords: ");
		b.append (this.complexPasswords);
		b.append (", validationScript: ");
		b.append (this.validationScript);
		b.append (", validationScriptDescription: ");
		b.append (this.validationScriptDescription);
		b.append (", checkPasswordBreached: ");
		b.append (this.checkPasswordBreached);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", updatedOn: ");
		b.append (this.updatedOn);
		b.append (", updatedBy: ");
		b.append (this.updatedBy);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append ("]");
		return b.toString();
	}

}
