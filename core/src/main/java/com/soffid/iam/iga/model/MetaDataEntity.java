//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity MetaDataEntity
 */

public abstract class MetaDataEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name
	 */
	private java.lang.String name;
	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
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
	 * Attribute data
	 */
	private java.util.Collection<com.soffid.iam.base.model.UserDataEntity> data =  new java.util.HashSet<com.soffid.iam.base.model.UserDataEntity>();
	/**
	 * Gets value for attribute data
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> getData() {
		return this.data;
	}
	/**
	 * Sets value for attribute data
	 */
	public void setData(java.util.Collection<com.soffid.iam.base.model.UserDataEntity> data) {
		this.data = data;
	}
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
	 * Attribute scope
	 */
	private com.soffid.iam.base.api.MetadataScope scope;
	/**
	 * Gets value for attribute scope
	 */
	public com.soffid.iam.base.api.MetadataScope getScope() {
		return this.scope;
	}
	/**
	 * Sets value for attribute scope
	 */
	public void setScope(com.soffid.iam.base.api.MetadataScope scope) {
		this.scope = scope;
	}
	/**
	 * Attribute type
	 */
	private com.soffid.iam.base.api.TypeEnumeration type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.base.api.TypeEnumeration getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.base.api.TypeEnumeration type) {
		this.type = type;
	}
	/**
	 * Attribute size
	 */
	private java.lang.Integer size;
	/**
	 * Gets value for attribute size
	 */
	public java.lang.Integer getSize() {
		return this.size;
	}
	/**
	 * Sets value for attribute size
	 */
	public void setSize(java.lang.Integer size) {
		this.size = size;
	}
	/**
	 * Attribute required
	 */
	private java.lang.Boolean required;
	/**
	 * Gets value for attribute required
	 */
	public java.lang.Boolean getRequired() {
		return this.required;
	}
	/**
	 * Sets value for attribute required
	 */
	public void setRequired(java.lang.Boolean required) {
		this.required = required;
	}
	/**
	 * Attribute readOnly
	 */
	private java.lang.Boolean readOnly;
	/**
	 * Gets value for attribute readOnly
	 */
	public java.lang.Boolean getReadOnly() {
		return this.readOnly;
	}
	/**
	 * Sets value for attribute readOnly
	 */
	public void setReadOnly(java.lang.Boolean readOnly) {
		this.readOnly = readOnly;
	}
	/**
	 * Attribute multiValued
	 */
	private java.lang.Boolean multiValued;
	/**
	 * Gets value for attribute multiValued
	 */
	public java.lang.Boolean getMultiValued() {
		return this.multiValued;
	}
	/**
	 * Sets value for attribute multiValued
	 */
	public void setMultiValued(java.lang.Boolean multiValued) {
		this.multiValued = multiValued;
	}
	/**
	 * Attribute multiLine
	 */
	private java.lang.Boolean multiLine;
	/**
	 * Gets value for attribute multiLine
	 */
	public java.lang.Boolean getMultiLine() {
		return this.multiLine;
	}
	/**
	 * Sets value for attribute multiLine
	 */
	public void setMultiLine(java.lang.Boolean multiLine) {
		this.multiLine = multiLine;
	}
	/**
	 * Attribute searchCriteria
	 * Include field in quick searches
	 */
	private java.lang.Boolean searchCriteria;
	/**
	 * Gets value for attribute searchCriteria
	 */
	public java.lang.Boolean getSearchCriteria() {
		return this.searchCriteria;
	}
	/**
	 * Sets value for attribute searchCriteria
	 */
	public void setSearchCriteria(java.lang.Boolean searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	/**
	 * Attribute multiValuedRows
	 * Display a maxim of rows values. For more values, a scroll bar will appear
	 */
	private java.lang.Integer multiValuedRows;
	/**
	 * Gets value for attribute multiValuedRows
	 */
	public java.lang.Integer getMultiValuedRows() {
		return this.multiValuedRows;
	}
	/**
	 * Sets value for attribute multiValuedRows
	 */
	public void setMultiValuedRows(java.lang.Integer multiValuedRows) {
		this.multiValuedRows = multiValuedRows;
	}
	/**
	 * Attribute values
	 * blank separated list of url-encoded values
	 */
	private java.lang.String values;
	/**
	 * Gets value for attribute values
	 */
	public java.lang.String getValues() {
		return this.values;
	}
	/**
	 * Sets value for attribute values
	 */
	public void setValues(java.lang.String values) {
		this.values = values;
	}
	/**
	 * Attribute label
	 * Label to display
	 */
	private java.lang.String label;
	/**
	 * Gets value for attribute label
	 */
	public java.lang.String getLabel() {
		return this.label;
	}
	/**
	 * Sets value for attribute label
	 */
	public void setLabel(java.lang.String label) {
		this.label = label;
	}
	/**
	 * Attribute hint
	 * User hint
	 */
	private java.lang.String hint;
	/**
	 * Gets value for attribute hint
	 */
	public java.lang.String getHint() {
		return this.hint;
	}
	/**
	 * Sets value for attribute hint
	 */
	public void setHint(java.lang.String hint) {
		this.hint = hint;
	}
	/**
	 * Attribute nlsLabel
	 * NLS Label to display
	 */
	private java.lang.String nlsLabel;
	/**
	 * Gets value for attribute nlsLabel
	 */
	public java.lang.String getNlsLabel() {
		return this.nlsLabel;
	}
	/**
	 * Sets value for attribute nlsLabel
	 */
	public void setNlsLabel(java.lang.String nlsLabel) {
		this.nlsLabel = nlsLabel;
	}
	/**
	 * Attribute builtinHandler
	 * Bulti-in handler class
	 */
	private java.lang.String builtinHandler;
	/**
	 * Gets value for attribute builtinHandler
	 */
	public java.lang.String getBuiltinHandler() {
		return this.builtinHandler;
	}
	/**
	 * Sets value for attribute builtinHandler
	 */
	public void setBuiltinHandler(java.lang.String builtinHandler) {
		this.builtinHandler = builtinHandler;
	}
	/**
	 * Attribute adminVisibility
	 * Administrator visibility
	 */
	private com.soffid.iam.base.api.AttributeVisibilityEnum adminVisibility;
	/**
	 * Gets value for attribute adminVisibility
	 */
	public com.soffid.iam.base.api.AttributeVisibilityEnum getAdminVisibility() {
		return this.adminVisibility;
	}
	/**
	 * Sets value for attribute adminVisibility
	 */
	public void setAdminVisibility(com.soffid.iam.base.api.AttributeVisibilityEnum adminVisibility) {
		this.adminVisibility = adminVisibility;
	}
	/**
	 * Attribute operatorVisibility
	 * Administrator visibility
	 */
	private com.soffid.iam.base.api.AttributeVisibilityEnum operatorVisibility;
	/**
	 * Gets value for attribute operatorVisibility
	 */
	public com.soffid.iam.base.api.AttributeVisibilityEnum getOperatorVisibility() {
		return this.operatorVisibility;
	}
	/**
	 * Sets value for attribute operatorVisibility
	 */
	public void setOperatorVisibility(com.soffid.iam.base.api.AttributeVisibilityEnum operatorVisibility) {
		this.operatorVisibility = operatorVisibility;
	}
	/**
	 * Attribute userVisibility
	 * User visibility
	 */
	private com.soffid.iam.base.api.AttributeVisibilityEnum userVisibility;
	/**
	 * Gets value for attribute userVisibility
	 */
	public com.soffid.iam.base.api.AttributeVisibilityEnum getUserVisibility() {
		return this.userVisibility;
	}
	/**
	 * Sets value for attribute userVisibility
	 */
	public void setUserVisibility(com.soffid.iam.base.api.AttributeVisibilityEnum userVisibility) {
		this.userVisibility = userVisibility;
	}
	/**
	 * Attribute unique
	 * Unique value
	 */
	private java.lang.Boolean unique;
	/**
	 * Gets value for attribute unique
	 */
	public java.lang.Boolean getUnique() {
		return this.unique;
	}
	/**
	 * Sets value for attribute unique
	 */
	public void setUnique(java.lang.Boolean unique) {
		this.unique = unique;
	}
	/**
	 * Attribute description
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
	 * Attribute objectType
	 * Object type acting as owner of the attribute
	 */
	private com.soffid.iam.iga.model.CustomObjectTypeEntity objectType;
	/**
	 * Gets value for attribute objectType
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity getObjectType() {
		return this.objectType;
	}
	/**
	 * Sets value for attribute objectType
	 */
	public void setObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity objectType) {
		this.objectType = objectType;
	}
	/**
	 * Attribute dataObjectType
	 * Object type instances acting as value of the attribute, for custom object type attributes
	 */
	private com.soffid.iam.iga.model.CustomObjectTypeEntity dataObjectType;
	/**
	 * Gets value for attribute dataObjectType
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity getDataObjectType() {
		return this.dataObjectType;
	}
	/**
	 * Sets value for attribute dataObjectType
	 */
	public void setDataObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity dataObjectType) {
		this.dataObjectType = dataObjectType;
	}
	/**
	 * Attribute visibilityExpression
	 */
	private java.lang.String visibilityExpression;
	/**
	 * Gets value for attribute visibilityExpression
	 */
	public java.lang.String getVisibilityExpression() {
		return this.visibilityExpression;
	}
	/**
	 * Sets value for attribute visibilityExpression
	 */
	public void setVisibilityExpression(java.lang.String visibilityExpression) {
		this.visibilityExpression = visibilityExpression;
	}
	/**
	 * Attribute validationExpression
	 */
	private java.lang.String validationExpression;
	/**
	 * Gets value for attribute validationExpression
	 */
	public java.lang.String getValidationExpression() {
		return this.validationExpression;
	}
	/**
	 * Sets value for attribute validationExpression
	 */
	public void setValidationExpression(java.lang.String validationExpression) {
		this.validationExpression = validationExpression;
	}
	/**
	 * Attribute onLoadTrigger
	 */
	private java.lang.String onLoadTrigger;
	/**
	 * Gets value for attribute onLoadTrigger
	 */
	public java.lang.String getOnLoadTrigger() {
		return this.onLoadTrigger;
	}
	/**
	 * Sets value for attribute onLoadTrigger
	 */
	public void setOnLoadTrigger(java.lang.String onLoadTrigger) {
		this.onLoadTrigger = onLoadTrigger;
	}
	/**
	 * Attribute onChangeTrigger
	 */
	private java.lang.String onChangeTrigger;
	/**
	 * Gets value for attribute onChangeTrigger
	 */
	public java.lang.String getOnChangeTrigger() {
		return this.onChangeTrigger;
	}
	/**
	 * Sets value for attribute onChangeTrigger
	 */
	public void setOnChangeTrigger(java.lang.String onChangeTrigger) {
		this.onChangeTrigger = onChangeTrigger;
	}
	/**
	 * Attribute onFocusTrigger
	 */
	private java.lang.String onFocusTrigger;
	/**
	 * Gets value for attribute onFocusTrigger
	 */
	public java.lang.String getOnFocusTrigger() {
		return this.onFocusTrigger;
	}
	/**
	 * Sets value for attribute onFocusTrigger
	 */
	public void setOnFocusTrigger(java.lang.String onFocusTrigger) {
		this.onFocusTrigger = onFocusTrigger;
	}
	/**
	 * Attribute validator
	 * Java class to validate field
	 */
	private java.lang.String validator;
	/**
	 * Gets value for attribute validator
	 */
	public java.lang.String getValidator() {
		return this.validator;
	}
	/**
	 * Sets value for attribute validator
	 */
	public void setValidator(java.lang.String validator) {
		this.validator = validator;
	}
	/**
	 * Attribute enumeration
	 * Java class to enumerate values
	 */
	private java.lang.String enumeration;
	/**
	 * Gets value for attribute enumeration
	 */
	public java.lang.String getEnumeration() {
		return this.enumeration;
	}
	/**
	 * Sets value for attribute enumeration
	 */
	public void setEnumeration(java.lang.String enumeration) {
		this.enumeration = enumeration;
	}
	/**
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Attribute filterExpression
	 * SCIM Expression to test if the reference object can be selected 
	 */
	private java.lang.String filterExpression;
	/**
	 * Gets value for attribute filterExpression
	 */
	public java.lang.String getFilterExpression() {
		return this.filterExpression;
	}
	/**
	 * Sets value for attribute filterExpression
	 */
	public void setFilterExpression(java.lang.String filterExpression) {
		this.filterExpression = filterExpression;
	}
	/**
	 * Attribute builtin
	 * Built-in attribute
	 */
	private java.lang.Boolean builtin = false;
	/**
	 * Gets value for attribute builtin
	 */
	public java.lang.Boolean getBuiltin() {
		return this.builtin;
	}
	/**
	 * Sets value for attribute builtin
	 */
	public void setBuiltin(java.lang.Boolean builtin) {
		this.builtin = builtin;
	}
	/**
	 * Attribute letterCase
	 * Uppercase / lowercase usage
	 */
	private com.soffid.iam.base.api.LetterCaseEnum letterCase = com.soffid.iam.base.api.LetterCaseEnum.MIXEDCASE;
	/**
	 * Gets value for attribute letterCase
	 */
	public com.soffid.iam.base.api.LetterCaseEnum getLetterCase() {
		return this.letterCase;
	}
	/**
	 * Sets value for attribute letterCase
	 */
	public void setLetterCase(com.soffid.iam.base.api.LetterCaseEnum letterCase) {
		this.letterCase = letterCase;
	}
	/**
	 * Attribute translations

	 */
	private java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> translations =  new java.util.HashSet<com.soffid.iam.iga.model.TranslatedLabelEntity>();
	/**
	 * Gets value for attribute translations
	 */
	public java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> getTranslations() {
		return this.translations;
	}
	/**
	 * Sets value for attribute translations
	 */
	public void setTranslations(java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> translations) {
		this.translations = translations;
	}
	/**
	 * Returns <code>true</code> if the argument is an MetaDataEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof MetaDataEntity))
		{
			return false;
		}
		final MetaDataEntity that = (MetaDataEntity)object;
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
