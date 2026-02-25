//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject ProcessInstance
 **/
public class ProcessInstance

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private long id;

	/**
	 * Attribute processClassLoader

	 */
	private transient com.soffid.iam.bpm.api.UIClassLoader processClassLoader;

	/**
	 * Attribute processNumber

	 */
	private java.lang.Long processNumber;

	/**
	 * Attribute tenantId

	 */
	private java.lang.Long tenantId;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute start

	 */
	private java.util.Date start;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	/**
	 * Attribute variables

	 */
	private java.util.Map<java.lang.String,java.lang.Object> variables;

	/**
	 * Attribute currentTask

	 */
	private java.lang.String currentTask;

	/**
	 * Attribute comments

	 */
	private java.util.List<com.soffid.iam.bpm.api.Comment> comments;

	/**
	 * Attribute processDefinition

	 */
	private long processDefinition;

	/**
	 * Attribute dummyProcess

	 */
	private boolean dummyProcess;

	/**
	 * Attribute initiator

	 */
	private java.lang.String initiator;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	public ProcessInstance()
	{
	}

	public ProcessInstance(long id, com.soffid.iam.bpm.api.UIClassLoader processClassLoader, java.lang.Long processNumber, java.lang.Long tenantId, java.lang.String description, java.util.Date start, java.util.Date end, java.util.Map<java.lang.String,java.lang.Object> variables, java.lang.String currentTask, java.util.List<com.soffid.iam.bpm.api.Comment> comments, long processDefinition, boolean dummyProcess, java.lang.String initiator, java.util.Date deletedOn, java.lang.String deletedBy, java.util.Date createdOn)
	{
		super();
		this.id = id;
		this.processClassLoader = processClassLoader;
		this.processNumber = processNumber;
		this.tenantId = tenantId;
		this.description = description;
		this.start = start;
		this.end = end;
		this.variables = variables;
		this.currentTask = currentTask;
		this.comments = comments;
		this.processDefinition = processDefinition;
		this.dummyProcess = dummyProcess;
		this.initiator = initiator;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.createdOn = createdOn;
	}

	public ProcessInstance(long id, long processDefinition, boolean dummyProcess)
	{
		super();
		this.id = id;
		this.processDefinition = processDefinition;
		this.dummyProcess = dummyProcess;
	}

	public ProcessInstance(ProcessInstance otherBean)
	{
		this(otherBean.id, otherBean.processClassLoader, otherBean.processNumber, otherBean.tenantId, otherBean.description, otherBean.start, otherBean.end, otherBean.variables, otherBean.currentTask, otherBean.comments, otherBean.processDefinition, otherBean.dummyProcess, otherBean.initiator, otherBean.deletedOn, otherBean.deletedBy, otherBean.createdOn);
	}

	/**
	 * Gets value for attribute id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets value for attribute processClassLoader
	 */
	public com.soffid.iam.bpm.api.UIClassLoader getProcessClassLoader() {
		return this.processClassLoader;
	}

	/**
	 * Sets value for attribute processClassLoader
	 */
	public void setProcessClassLoader(com.soffid.iam.bpm.api.UIClassLoader processClassLoader) {
		this.processClassLoader = processClassLoader;
	}

	/**
	 * Gets value for attribute processNumber
	 */
	public java.lang.Long getProcessNumber() {
		return this.processNumber;
	}

	/**
	 * Sets value for attribute processNumber
	 */
	public void setProcessNumber(java.lang.Long processNumber) {
		this.processNumber = processNumber;
	}

	/**
	 * Gets value for attribute tenantId
	 */
	public java.lang.Long getTenantId() {
		return this.tenantId;
	}

	/**
	 * Sets value for attribute tenantId
	 */
	public void setTenantId(java.lang.Long tenantId) {
		this.tenantId = tenantId;
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
	 * Gets value for attribute start
	 */
	public java.util.Date getStart() {
		return this.start;
	}

	/**
	 * Sets value for attribute start
	 */
	public void setStart(java.util.Date start) {
		this.start = start;
	}

	/**
	 * Gets value for attribute end
	 */
	public java.util.Date getEnd() {
		return this.end;
	}

	/**
	 * Sets value for attribute end
	 */
	public void setEnd(java.util.Date end) {
		this.end = end;
	}

	/**
	 * Gets value for attribute variables
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getVariables() {
		return this.variables;
	}

	/**
	 * Sets value for attribute variables
	 */
	public void setVariables(java.util.Map<java.lang.String,java.lang.Object> variables) {
		this.variables = variables;
	}

	/**
	 * Gets value for attribute variables
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getAttributes() {
		return this.variables;
	}

	/**
	 * Sets value for attribute variables
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.Object> variables) {
		this.variables = variables;
	}

	/**
	 * Gets value for attribute currentTask
	 */
	public java.lang.String getCurrentTask() {
		return this.currentTask;
	}

	/**
	 * Sets value for attribute currentTask
	 */
	public void setCurrentTask(java.lang.String currentTask) {
		this.currentTask = currentTask;
	}

	/**
	 * Gets value for attribute comments
	 */
	public java.util.List<com.soffid.iam.bpm.api.Comment> getComments() {
		return this.comments;
	}

	/**
	 * Sets value for attribute comments
	 */
	public void setComments(java.util.List<com.soffid.iam.bpm.api.Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Gets value for attribute processDefinition
	 */
	public long getProcessDefinition() {
		return this.processDefinition;
	}

	/**
	 * Sets value for attribute processDefinition
	 */
	public void setProcessDefinition(long processDefinition) {
		this.processDefinition = processDefinition;
	}

	/**
	 * Gets value for attribute dummyProcess
	 */
	public boolean isDummyProcess() {
		return this.dummyProcess;
	}

	/**
	 * Sets value for attribute dummyProcess
	 */
	public void setDummyProcess(boolean dummyProcess) {
		this.dummyProcess = dummyProcess;
	}

	/**
	 * Gets value for attribute initiator
	 */
	public java.lang.String getInitiator() {
		return this.initiator;
	}

	/**
	 * Sets value for attribute initiator
	 */
	public void setInitiator(java.lang.String initiator) {
		this.initiator = initiator;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", processClassLoader: ");
		b.append (this.processClassLoader);
		b.append (", processNumber: ");
		b.append (this.processNumber);
		b.append (", tenantId: ");
		b.append (this.tenantId);
		b.append (", description: ");
		b.append (this.description);
		b.append (", start: ");
		b.append (this.start);
		b.append (", end: ");
		b.append (this.end);
		b.append (", variables: ");
		b.append (this.variables);
		b.append (", currentTask: ");
		b.append (this.currentTask);
		b.append (", comments: ");
		b.append (this.comments);
		b.append (", processDefinition: ");
		b.append (this.processDefinition);
		b.append (", dummyProcess: ");
		b.append (this.dummyProcess);
		b.append (", initiator: ");
		b.append (this.initiator);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append ("]");
		return b.toString();
	}

}
