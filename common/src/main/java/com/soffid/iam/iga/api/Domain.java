//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject Domain
 **/
public class Domain

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 * Unique identifier

	 */
	private java.lang.Long id;

	/**
	 * Attribute name
	 * Domain name

	 */
	private java.lang.String name;

	/**
	 * Attribute informationSystem
	 * Information system name

	 */
	private java.lang.String informationSystem;

	/**
	 * Attribute description
	 * Domain description

	 */
	private java.lang.String description;

	public Domain()
	{
	}

	public Domain(java.lang.Long id, java.lang.String name, java.lang.String informationSystem, java.lang.String description)
	{
		super();
		this.id = id;
		this.name = name;
		this.informationSystem = informationSystem;
		this.description = description;
	}

	public Domain(java.lang.String name)
	{
		super();
		this.name = name;
	}

	public Domain(Domain otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.informationSystem, otherBean.description);
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
	 * Gets value for attribute informationSystem
	 */
	public java.lang.String getInformationSystem() {
		return this.informationSystem;
	}

	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(java.lang.String informationSystem) {
		this.informationSystem = informationSystem;
	}

	/**
	 * Gets value for attribute informationSystem
	 */
	public java.lang.String getExternalCode() {
		return this.informationSystem;
	}

	/**
	 * Sets value for attribute informationSystem
	 */
	public void setExternalCode(java.lang.String informationSystem) {
		this.informationSystem = informationSystem;
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
		b.append (", informationSystem: ");
		b.append (this.informationSystem);
		b.append (", description: ");
		b.append (this.description);
		b.append ("]");
		return b.toString();
	}

}
