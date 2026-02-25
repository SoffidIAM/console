//
// (C) 2020 Soffid
//
//

package com.soffid.iam.web;
/**
 * ValueObject SearchDictionary
 **/
public class SearchDictionary

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute attributes

	 */
	private java.util.List<com.soffid.iam.web.SearchAttributeDefinition> attributes;

	/**
	 * Attribute timestamp

	 */
	private long timestamp;

	public SearchDictionary()
	{
	}

	public SearchDictionary(java.util.List<com.soffid.iam.web.SearchAttributeDefinition> attributes, long timestamp)
	{
		super();
		this.attributes = attributes;
		this.timestamp = timestamp;
	}

	public SearchDictionary(SearchDictionary otherBean)
	{
		this(otherBean.attributes, otherBean.timestamp);
	}

	/**
	 * Gets value for attribute attributes
	 */
	public java.util.List<com.soffid.iam.web.SearchAttributeDefinition> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.List<com.soffid.iam.web.SearchAttributeDefinition> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets value for attribute timestamp
	 */
	public long getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Sets value for attribute timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[attributes: ");
		b.append (this.attributes);
		b.append (", timestamp: ");
		b.append (this.timestamp);
		b.append ("]");
		return b.toString();
	}

}
