//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject ForbiddenWord
 **/
public class ForbiddenWord

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
	 * Attribute forbiddenWord

	 */
	private java.lang.String forbiddenWord;

	public ForbiddenWord()
	{
	}

	public ForbiddenWord(java.lang.Long id, java.lang.String forbiddenWord)
	{
		super();
		this.id = id;
		this.forbiddenWord = forbiddenWord;
	}

	public ForbiddenWord(ForbiddenWord otherBean)
	{
		this(otherBean.id, otherBean.forbiddenWord);
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
	 * Gets value for attribute forbiddenWord
	 */
	public java.lang.String getForbiddenWord() {
		return this.forbiddenWord;
	}

	/**
	 * Sets value for attribute forbiddenWord
	 */
	public void setForbiddenWord(java.lang.String forbiddenWord) {
		this.forbiddenWord = forbiddenWord;
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
		b.append (", forbiddenWord: ");
		b.append (this.forbiddenWord);
		b.append ("]");
		return b.toString();
	}

}
