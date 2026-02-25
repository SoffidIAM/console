//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject Stats
 **/
public class Stats

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
	 * Attribute tags

	 */
	private java.util.List<java.lang.String> tags;

	/**
	 * Attribute series

	 */
	private java.util.HashMap<java.lang.String,java.util.List<com.soffid.iam.base.api.StatsSample>> series;

	public Stats()
	{
	}

	public Stats(java.lang.String name, java.util.List<java.lang.String> tags, java.util.HashMap<java.lang.String,java.util.List<com.soffid.iam.base.api.StatsSample>> series)
	{
		super();
		this.name = name;
		this.tags = tags;
		this.series = series;
	}

	public Stats(Stats otherBean)
	{
		this(otherBean.name, otherBean.tags, otherBean.series);
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
	 * Gets value for attribute tags
	 */
	public java.util.List<java.lang.String> getTags() {
		return this.tags;
	}

	/**
	 * Sets value for attribute tags
	 */
	public void setTags(java.util.List<java.lang.String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets value for attribute series
	 */
	public java.util.HashMap<java.lang.String,java.util.List<com.soffid.iam.base.api.StatsSample>> getSeries() {
		return this.series;
	}

	/**
	 * Sets value for attribute series
	 */
	public void setSeries(java.util.HashMap<java.lang.String,java.util.List<com.soffid.iam.base.api.StatsSample>> series) {
		this.series = series;
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
		b.append (", tags: ");
		b.append (this.tags);
		b.append (", series: ");
		b.append (this.series);
		b.append ("]");
		return b.toString();
	}

}
