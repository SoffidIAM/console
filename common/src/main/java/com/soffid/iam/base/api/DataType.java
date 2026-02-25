//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject DataType
 **/
public class DataType

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute order

	 */
	private java.lang.Long order;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute scope

	 */
	private com.soffid.iam.base.api.MetadataScope scope;

	/**
	 * Attribute objectType
	 * Object type acting as owner of the attribute

	 */
	private java.lang.String objectType;

	/**
	 * Attribute dataObjectType
	 * Object type instances acting as value of the attribute, for custom object type attributes

	 */
	private java.lang.String dataObjectType;

	/**
	 * Attribute type

	 */
	private com.soffid.iam.base.api.TypeEnumeration type;

	/**
	 * Attribute size

	 */
	private java.lang.Integer size;

	/**
	 * Attribute required

	 */
	private boolean required;

	/**
	 * Attribute readOnly

	 */
	private boolean readOnly;

	/**
	 * Attribute multiLine

	 */
	private boolean multiLine;

	/**
	 * Attribute multiValued

	 */
	private boolean multiValued;

	/**
	 * Attribute searchCriteria
	 * Include field in quick searches

	 */
	private java.lang.Boolean searchCriteria;

	/**
	 * Attribute multiValuedRows
	 * Display a maxim of rows values. For more values, a scroll bar will appear

	 */
	private java.lang.Integer multiValuedRows;

	/**
	 * Attribute label
	 * Label for this data type

	 */
	private java.lang.String label;

	/**
	 * Attribute hint
	 * User hint

	 */
	private java.lang.String hint;

	/**
	 * Attribute nlsLabel
	 * Key of the localized label for this data type

	 */
	private java.lang.String nlsLabel;

	/**
	 * Attribute nlsLabels
	 * Label in multiple languages

	 */
	private java.util.Map<java.lang.String,java.lang.String> nlsLabels;

	/**
	 * Attribute description
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute values
	 * List of allowed values

	 */
	private java.util.List<java.lang.String> values = new java.util.LinkedList<String>();

	/**
	 * Attribute adminVisibility
	 * Administrator visibility

	 */
	private com.soffid.iam.base.api.AttributeVisibilityEnum adminVisibility;

	/**
	 * Attribute operatorVisibility
	 * Operator visibility

	 */
	private com.soffid.iam.base.api.AttributeVisibilityEnum operatorVisibility;

	/**
	 * Attribute userVisibility
	 * User visibility

	 */
	private com.soffid.iam.base.api.AttributeVisibilityEnum userVisibility;

	/**
	 * Attribute systemName
	 * System where this attribute applies to. Null applies to identity itself

	 */
	private java.lang.String systemName;

	/**
	 * Attribute unique
	 * Unique value

	 */
	private java.lang.Boolean unique;

	/**
	 * Attribute visibilityExpression
	 * Expression to test if attribute should be displayed or not

	 */
	private java.lang.String visibilityExpression;

	/**
	 * Attribute validationExpression
	 * Expression to test if attribute value is valid or not

	 */
	private java.lang.String validationExpression;

	/**
	 * Attribute filterExpression
	 * SCIM Expression to test if the reference object can be selected 

	 */
	private java.lang.String filterExpression;

	/**
	 * Attribute onLoadTrigger
	 * Trigger to run when the data is displayed

	 */
	private java.lang.String onLoadTrigger;

	/**
	 * Attribute onChangeTrigger
	 * Trigger to run when the data is changed

	 */
	private java.lang.String onChangeTrigger;

	/**
	 * Attribute onFocusTrigger
	 * Trigger to run when the field is focused

	 */
	private java.lang.String onFocusTrigger;

	/**
	 * Attribute validator
	 * Java class to validate field

	 */
	private java.lang.String validator;

	/**
	 * Attribute enumeration
	 * Java class to enumerate values

	 */
	private java.lang.String enumeration;

	/**
	 * Attribute builtin
	 * Built-in attribute

	 */
	private java.lang.Boolean builtin = false;

	/**
	 * Attribute builtinHandler
	 * Bulti-in handler class

	 */
	private java.lang.String builtinHandler;

	/**
	 * Attribute letterCase
	 * Uppercase / lowercase usage

	 */
	private com.soffid.iam.base.api.LetterCaseEnum letterCase = com.soffid.iam.base.api.LetterCaseEnum.MIXEDCASE;

	public DataType()
	{
	}

	public DataType(java.lang.String name, java.lang.Long order, java.lang.Long id, com.soffid.iam.base.api.MetadataScope scope, java.lang.String objectType, java.lang.String dataObjectType, com.soffid.iam.base.api.TypeEnumeration type, java.lang.Integer size, boolean required, boolean readOnly, boolean multiLine, boolean multiValued, java.lang.Boolean searchCriteria, java.lang.Integer multiValuedRows, java.lang.String label, java.lang.String hint, java.lang.String nlsLabel, java.util.Map<java.lang.String,java.lang.String> nlsLabels, java.lang.String description, java.util.List<java.lang.String> values, com.soffid.iam.base.api.AttributeVisibilityEnum adminVisibility, com.soffid.iam.base.api.AttributeVisibilityEnum operatorVisibility, com.soffid.iam.base.api.AttributeVisibilityEnum userVisibility, java.lang.String systemName, java.lang.Boolean unique, java.lang.String visibilityExpression, java.lang.String validationExpression, java.lang.String filterExpression, java.lang.String onLoadTrigger, java.lang.String onChangeTrigger, java.lang.String onFocusTrigger, java.lang.String validator, java.lang.String enumeration, java.lang.Boolean builtin, java.lang.String builtinHandler, com.soffid.iam.base.api.LetterCaseEnum letterCase)
	{
		super();
		this.name = name;
		this.order = order;
		this.id = id;
		this.scope = scope;
		this.objectType = objectType;
		this.dataObjectType = dataObjectType;
		this.type = type;
		this.size = size;
		this.required = required;
		this.readOnly = readOnly;
		this.multiLine = multiLine;
		this.multiValued = multiValued;
		this.searchCriteria = searchCriteria;
		this.multiValuedRows = multiValuedRows;
		this.label = label;
		this.hint = hint;
		this.nlsLabel = nlsLabel;
		this.nlsLabels = nlsLabels;
		this.description = description;
		this.values = values;
		this.adminVisibility = adminVisibility;
		this.operatorVisibility = operatorVisibility;
		this.userVisibility = userVisibility;
		this.systemName = systemName;
		this.unique = unique;
		this.visibilityExpression = visibilityExpression;
		this.validationExpression = validationExpression;
		this.filterExpression = filterExpression;
		this.onLoadTrigger = onLoadTrigger;
		this.onChangeTrigger = onChangeTrigger;
		this.onFocusTrigger = onFocusTrigger;
		this.validator = validator;
		this.enumeration = enumeration;
		this.builtin = builtin;
		this.builtinHandler = builtinHandler;
		this.letterCase = letterCase;
	}

	public DataType(java.lang.String name, com.soffid.iam.base.api.TypeEnumeration type, boolean required, boolean readOnly, boolean multiLine, boolean multiValued)
	{
		super();
		this.name = name;
		this.type = type;
		this.required = required;
		this.readOnly = readOnly;
		this.multiLine = multiLine;
		this.multiValued = multiValued;
	}

	public DataType(DataType otherBean)
	{
		this(otherBean.name, otherBean.order, otherBean.id, otherBean.scope, otherBean.objectType, otherBean.dataObjectType, otherBean.type, otherBean.size, otherBean.required, otherBean.readOnly, otherBean.multiLine, otherBean.multiValued, otherBean.searchCriteria, otherBean.multiValuedRows, otherBean.label, otherBean.hint, otherBean.nlsLabel, otherBean.nlsLabels, otherBean.description, otherBean.values, otherBean.adminVisibility, otherBean.operatorVisibility, otherBean.userVisibility, otherBean.systemName, otherBean.unique, otherBean.visibilityExpression, otherBean.validationExpression, otherBean.filterExpression, otherBean.onLoadTrigger, otherBean.onChangeTrigger, otherBean.onFocusTrigger, otherBean.validator, otherBean.enumeration, otherBean.builtin, otherBean.builtinHandler, otherBean.letterCase);
	}

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
	 * Gets value for attribute name
	 */
	public java.lang.String getCode() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setCode(java.lang.String name) {
		this.name = name;
	}

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
	 * Gets value for attribute objectType
	 */
	public java.lang.String getObjectType() {
		return this.objectType;
	}

	/**
	 * Sets value for attribute objectType
	 */
	public void setObjectType(java.lang.String objectType) {
		this.objectType = objectType;
	}

	/**
	 * Gets value for attribute objectType
	 */
	public java.lang.String getCustomObjectType() {
		return this.objectType;
	}

	/**
	 * Sets value for attribute objectType
	 */
	public void setCustomObjectType(java.lang.String objectType) {
		this.objectType = objectType;
	}

	/**
	 * Gets value for attribute dataObjectType
	 */
	public java.lang.String getDataObjectType() {
		return this.dataObjectType;
	}

	/**
	 * Sets value for attribute dataObjectType
	 */
	public void setDataObjectType(java.lang.String dataObjectType) {
		this.dataObjectType = dataObjectType;
	}

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
	 * Gets value for attribute required
	 */
	public boolean isRequired() {
		return this.required;
	}

	/**
	 * Sets value for attribute required
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * Gets value for attribute readOnly
	 */
	public boolean isReadOnly() {
		return this.readOnly;
	}

	/**
	 * Sets value for attribute readOnly
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Gets value for attribute multiLine
	 */
	public boolean isMultiLine() {
		return this.multiLine;
	}

	/**
	 * Sets value for attribute multiLine
	 */
	public void setMultiLine(boolean multiLine) {
		this.multiLine = multiLine;
	}

	/**
	 * Gets value for attribute multiValued
	 */
	public boolean isMultiValued() {
		return this.multiValued;
	}

	/**
	 * Sets value for attribute multiValued
	 */
	public void setMultiValued(boolean multiValued) {
		this.multiValued = multiValued;
	}

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
	 * Gets value for attribute nlsLabels
	 */
	public java.util.Map<java.lang.String,java.lang.String> getNlsLabels() {
		return this.nlsLabels;
	}

	/**
	 * Sets value for attribute nlsLabels
	 */
	public void setNlsLabels(java.util.Map<java.lang.String,java.lang.String> nlsLabels) {
		this.nlsLabels = nlsLabels;
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
	 * Gets value for attribute values
	 */
	public java.util.List<java.lang.String> getValues() {
		return this.values;
	}

	/**
	 * Sets value for attribute values
	 */
	public void setValues(java.util.List<java.lang.String> values) {
		this.values = values;
	}

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
	 * Gets value for attribute systemName
	 */
	public java.lang.String getSystemName() {
		return this.systemName;
	}

	/**
	 * Sets value for attribute systemName
	 */
	public void setSystemName(java.lang.String systemName) {
		this.systemName = systemName;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[name: ");
		b.append (this.name);
		b.append (", order: ");
		b.append (this.order);
		b.append (", id: ");
		b.append (this.id);
		b.append (", scope: ");
		b.append (this.scope);
		b.append (", objectType: ");
		b.append (this.objectType);
		b.append (", dataObjectType: ");
		b.append (this.dataObjectType);
		b.append (", type: ");
		b.append (this.type);
		b.append (", size: ");
		b.append (this.size);
		b.append (", required: ");
		b.append (this.required);
		b.append (", readOnly: ");
		b.append (this.readOnly);
		b.append (", multiLine: ");
		b.append (this.multiLine);
		b.append (", multiValued: ");
		b.append (this.multiValued);
		b.append (", searchCriteria: ");
		b.append (this.searchCriteria);
		b.append (", multiValuedRows: ");
		b.append (this.multiValuedRows);
		b.append (", label: ");
		b.append (this.label);
		b.append (", hint: ");
		b.append (this.hint);
		b.append (", nlsLabel: ");
		b.append (this.nlsLabel);
		b.append (", nlsLabels: ");
		b.append (this.nlsLabels);
		b.append (", description: ");
		b.append (this.description);
		b.append (", values: ");
		b.append (this.values);
		b.append (", adminVisibility: ");
		b.append (this.adminVisibility);
		b.append (", operatorVisibility: ");
		b.append (this.operatorVisibility);
		b.append (", userVisibility: ");
		b.append (this.userVisibility);
		b.append (", systemName: ");
		b.append (this.systemName);
		b.append (", unique: ");
		b.append (this.unique);
		b.append (", visibilityExpression: ");
		b.append (this.visibilityExpression);
		b.append (", validationExpression: ");
		b.append (this.validationExpression);
		b.append (", filterExpression: ");
		b.append (this.filterExpression);
		b.append (", onLoadTrigger: ");
		b.append (this.onLoadTrigger);
		b.append (", onChangeTrigger: ");
		b.append (this.onChangeTrigger);
		b.append (", onFocusTrigger: ");
		b.append (this.onFocusTrigger);
		b.append (", validator: ");
		b.append (this.validator);
		b.append (", enumeration: ");
		b.append (this.enumeration);
		b.append (", builtin: ");
		b.append (this.builtin);
		b.append (", builtinHandler: ");
		b.append (this.builtinHandler);
		b.append (", letterCase: ");
		b.append (this.letterCase);
		b.append ("]");
		return b.toString();
	}

}
