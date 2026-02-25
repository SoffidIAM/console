//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject SamlRequest
 **/
public class SamlRequest

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute url

	 */
	private java.lang.String url;

	/**
	 * Attribute method

	 */
	private java.lang.String method;

	/**
	 * Attribute parameters

	 */
	private java.util.Map<java.lang.String,java.lang.String> parameters;

	public SamlRequest()
	{
	}

	public SamlRequest(java.lang.String url, java.lang.String method, java.util.Map<java.lang.String,java.lang.String> parameters)
	{
		super();
		this.url = url;
		this.method = method;
		this.parameters = parameters;
	}

	public SamlRequest(SamlRequest otherBean)
	{
		this(otherBean.url, otherBean.method, otherBean.parameters);
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
	 * Gets value for attribute method
	 */
	public java.lang.String getMethod() {
		return this.method;
	}

	/**
	 * Sets value for attribute method
	 */
	public void setMethod(java.lang.String method) {
		this.method = method;
	}

	/**
	 * Gets value for attribute parameters
	 */
	public java.util.Map<java.lang.String,java.lang.String> getParameters() {
		return this.parameters;
	}

	/**
	 * Sets value for attribute parameters
	 */
	public void setParameters(java.util.Map<java.lang.String,java.lang.String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[url: ");
		b.append (this.url);
		b.append (", method: ");
		b.append (this.method);
		b.append (", parameters: ");
		b.append (this.parameters);
		b.append ("]");
		return b.toString();
	}

}
