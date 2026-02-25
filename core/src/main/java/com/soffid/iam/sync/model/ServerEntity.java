//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity ServerEntity
 */

public abstract class ServerEntity {

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
	 * Attribute pk
	 */
	private byte[] pk;
	/**
	 * Gets value for attribute pk
	 */
	public byte[] getPk() {
		return this.pk;
	}
	/**
	 * Sets value for attribute pk
	 */
	public void setPk(byte[] pk) {
		this.pk = pk;
	}
	/**
	 * Attribute auth
	 */
	private java.lang.String auth;
	/**
	 * Gets value for attribute auth
	 */
	public java.lang.String getAuth() {
		return this.auth;
	}
	/**
	 * Sets value for attribute auth
	 */
	public void setAuth(java.lang.String auth) {
		this.auth = auth;
	}
	/**
	 * Attribute type
	 */
	private com.soffid.iam.sync.api.ServerType type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.sync.api.ServerType getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.sync.api.ServerType type) {
		this.type = type;
	}
	/**
	 * Attribute useMasterDatabase
	 */
	private java.lang.Boolean useMasterDatabase;
	/**
	 * Gets value for attribute useMasterDatabase
	 */
	public java.lang.Boolean getUseMasterDatabase() {
		return this.useMasterDatabase;
	}
	/**
	 * Sets value for attribute useMasterDatabase
	 */
	public void setUseMasterDatabase(java.lang.Boolean useMasterDatabase) {
		this.useMasterDatabase = useMasterDatabase;
	}
	/**
	 * Attribute url
	 */
	private java.lang.String url;
	/**
	 * Gets value for attribute url
	 */
	public java.lang.String getUrl() {
		return this.url;
	}
	/**
	 * Sets value for attribute url
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	/**
	 * Attribute javaOptions
	 */
	private java.lang.String javaOptions;
	/**
	 * Gets value for attribute javaOptions
	 */
	public java.lang.String getJavaOptions() {
		return this.javaOptions;
	}
	/**
	 * Sets value for attribute javaOptions
	 */
	public void setJavaOptions(java.lang.String javaOptions) {
		this.javaOptions = javaOptions;
	}
	/**
	 * Attribute secrets
	 */
	private java.util.Collection<com.soffid.iam.am.model.SecretEntity> secrets =  new java.util.HashSet<com.soffid.iam.am.model.SecretEntity>();
	/**
	 * Gets value for attribute secrets
	 */
	public java.util.Collection<com.soffid.iam.am.model.SecretEntity> getSecrets() {
		return this.secrets;
	}
	/**
	 * Sets value for attribute secrets
	 */
	public void setSecrets(java.util.Collection<com.soffid.iam.am.model.SecretEntity> secrets) {
		this.secrets = secrets;
	}
	/**
	 * Attribute scheduledTasks
	 */
	private java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> scheduledTasks =  new java.util.HashSet<com.soffid.iam.sync.model.ScheduledTaskEntity>();
	/**
	 * Gets value for attribute scheduledTasks
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> getScheduledTasks() {
		return this.scheduledTasks;
	}
	/**
	 * Sets value for attribute scheduledTasks
	 */
	public void setScheduledTasks(java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> scheduledTasks) {
		this.scheduledTasks = scheduledTasks;
	}
	/**
	 * Attribute networks

	 */
	private java.util.Collection<com.soffid.iam.am.model.NetworkEntity> networks =  new java.util.HashSet<com.soffid.iam.am.model.NetworkEntity>();
	/**
	 * Gets value for attribute networks
	 */
	public java.util.Collection<com.soffid.iam.am.model.NetworkEntity> getNetworks() {
		return this.networks;
	}
	/**
	 * Sets value for attribute networks
	 */
	public void setNetworks(java.util.Collection<com.soffid.iam.am.model.NetworkEntity> networks) {
		this.networks = networks;
	}
	/**
	 * Attribute tenants

	 */
	private java.util.Collection<com.soffid.iam.base.model.TenantServerEntity> tenants =  new java.util.HashSet<com.soffid.iam.base.model.TenantServerEntity>();
	/**
	 * Gets value for attribute tenants
	 */
	public java.util.Collection<com.soffid.iam.base.model.TenantServerEntity> getTenants() {
		return this.tenants;
	}
	/**
	 * Sets value for attribute tenants
	 */
	public void setTenants(java.util.Collection<com.soffid.iam.base.model.TenantServerEntity> tenants) {
		this.tenants = tenants;
	}
	/**
	 * Attribute certificates

	 */
	private java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> certificates =  new java.util.HashSet<com.soffid.iam.sync.model.ServerCertificateEntity>();
	/**
	 * Gets value for attribute certificates
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> getCertificates() {
		return this.certificates;
	}
	/**
	 * Sets value for attribute certificates
	 */
	public void setCertificates(java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> certificates) {
		this.certificates = certificates;
	}
	/**
	 * Attribute instances

	 */
	private java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> instances =  new java.util.HashSet<com.soffid.iam.sync.model.ServerInstanceEntity>();
	/**
	 * Gets value for attribute instances
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> getInstances() {
		return this.instances;
	}
	/**
	 * Sets value for attribute instances
	 */
	public void setInstances(java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> instances) {
		this.instances = instances;
	}
	/**
	 * Returns <code>true</code> if the argument is an ServerEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ServerEntity))
		{
			return false;
		}
		final ServerEntity that = (ServerEntity)object;
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
