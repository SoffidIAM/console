//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject Server
 **/
public class Server

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
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute pk

	 */
	private byte[] pk;

	/**
	 * Attribute auth

	 */
	private java.lang.String auth;

	/**
	 * Attribute publicKey

	 */
	private java.security.PublicKey publicKey;

	/**
	 * Attribute useMasterDatabase

	 */
	private java.lang.Boolean useMasterDatabase;

	/**
	 * Attribute backupDatabase

	 */
	private java.lang.Long backupDatabase;

	/**
	 * Attribute type

	 */
	private com.soffid.iam.sync.api.ServerType type;

	/**
	 * Attribute url

	 */
	private java.lang.String url;

	/**
	 * Attribute publicUrl

	 */
	private java.lang.String publicUrl;

	/**
	 * Attribute javaOptions

	 */
	private java.lang.String javaOptions;

	public Server()
	{
	}

	public Server(java.lang.Long id, java.lang.String name, byte[] pk, java.lang.String auth, java.security.PublicKey publicKey, java.lang.Boolean useMasterDatabase, java.lang.Long backupDatabase, com.soffid.iam.sync.api.ServerType type, java.lang.String url, java.lang.String publicUrl, java.lang.String javaOptions)
	{
		super();
		this.id = id;
		this.name = name;
		this.pk = pk;
		this.auth = auth;
		this.publicKey = publicKey;
		this.useMasterDatabase = useMasterDatabase;
		this.backupDatabase = backupDatabase;
		this.type = type;
		this.url = url;
		this.publicUrl = publicUrl;
		this.javaOptions = javaOptions;
	}

	public Server(java.lang.String name, com.soffid.iam.sync.api.ServerType type)
	{
		super();
		this.name = name;
		this.type = type;
	}

	public Server(Server otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.pk, otherBean.auth, otherBean.publicKey, otherBean.useMasterDatabase, otherBean.backupDatabase, otherBean.type, otherBean.url, otherBean.publicUrl, otherBean.javaOptions);
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
	 * Gets value for attribute publicKey
	 */
	public java.security.PublicKey getPublicKey() {
		return this.publicKey;
	}

	/**
	 * Sets value for attribute publicKey
	 */
	public void setPublicKey(java.security.PublicKey publicKey) {
		this.publicKey = publicKey;
	}

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
	 * Gets value for attribute backupDatabase
	 */
	public java.lang.Long getBackupDatabase() {
		return this.backupDatabase;
	}

	/**
	 * Sets value for attribute backupDatabase
	 */
	public void setBackupDatabase(java.lang.Long backupDatabase) {
		this.backupDatabase = backupDatabase;
	}

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
	 * Gets value for attribute publicUrl
	 */
	public java.lang.String getPublicUrl() {
		return this.publicUrl;
	}

	/**
	 * Sets value for attribute publicUrl
	 */
	public void setPublicUrl(java.lang.String publicUrl) {
		this.publicUrl = publicUrl;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", name: ");
		b.append (this.name);
		b.append (", pk: ");
		b.append (this.pk);
		b.append (", auth: ");
		b.append (this.auth);
		b.append (", publicKey: ");
		b.append (this.publicKey);
		b.append (", useMasterDatabase: ");
		b.append (this.useMasterDatabase);
		b.append (", backupDatabase: ");
		b.append (this.backupDatabase);
		b.append (", type: ");
		b.append (this.type);
		b.append (", url: ");
		b.append (this.url);
		b.append (", publicUrl: ");
		b.append (this.publicUrl);
		b.append (", javaOptions: ");
		b.append (this.javaOptions);
		b.append ("]");
		return b.toString();
	}

}
