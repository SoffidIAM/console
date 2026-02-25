//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AsyncProcessTracker
 **/
public class AsyncProcessTracker

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
	 * Attribute progress

	 */
	private float progress;

	/**
	 * Attribute report

	 */
	private java.lang.String report;

	/**
	 * Attribute finished

	 */
	private boolean finished;

	/**
	 * Attribute cancelled

	 */
	private boolean cancelled;

	/**
	 * Attribute errorMessage

	 */
	private java.lang.String errorMessage;

	/**
	 * Attribute start

	 */
	private java.util.Date start;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	/**
	 * Attribute current

	 */
	private java.lang.String current;

	public AsyncProcessTracker()
	{
	}

	public AsyncProcessTracker(java.lang.Long id, float progress, java.lang.String report, boolean finished, boolean cancelled, java.lang.String errorMessage, java.util.Date start, java.util.Date end, java.lang.String current)
	{
		super();
		this.id = id;
		this.progress = progress;
		this.report = report;
		this.finished = finished;
		this.cancelled = cancelled;
		this.errorMessage = errorMessage;
		this.start = start;
		this.end = end;
		this.current = current;
	}

	public AsyncProcessTracker(java.lang.Long id, float progress, boolean finished, boolean cancelled)
	{
		super();
		this.id = id;
		this.progress = progress;
		this.finished = finished;
		this.cancelled = cancelled;
	}

	public AsyncProcessTracker(AsyncProcessTracker otherBean)
	{
		this(otherBean.id, otherBean.progress, otherBean.report, otherBean.finished, otherBean.cancelled, otherBean.errorMessage, otherBean.start, otherBean.end, otherBean.current);
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
	 * Gets value for attribute progress
	 */
	public float getProgress() {
		return this.progress;
	}

	/**
	 * Sets value for attribute progress
	 */
	public void setProgress(float progress) {
		this.progress = progress;
	}

	/**
	 * Gets value for attribute report
	 */
	public java.lang.String getReport() {
		return this.report;
	}

	/**
	 * Sets value for attribute report
	 */
	public void setReport(java.lang.String report) {
		this.report = report;
	}

	/**
	 * Gets value for attribute finished
	 */
	public boolean isFinished() {
		return this.finished;
	}

	/**
	 * Sets value for attribute finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * Gets value for attribute cancelled
	 */
	public boolean isCancelled() {
		return this.cancelled;
	}

	/**
	 * Sets value for attribute cancelled
	 */
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * Gets value for attribute errorMessage
	 */
	public java.lang.String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Sets value for attribute errorMessage
	 */
	public void setErrorMessage(java.lang.String errorMessage) {
		this.errorMessage = errorMessage;
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
	 * Gets value for attribute current
	 */
	public java.lang.String getCurrent() {
		return this.current;
	}

	/**
	 * Sets value for attribute current
	 */
	public void setCurrent(java.lang.String current) {
		this.current = current;
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
		b.append (", progress: ");
		b.append (this.progress);
		b.append (", report: ");
		b.append (this.report);
		b.append (", finished: ");
		b.append (this.finished);
		b.append (", cancelled: ");
		b.append (this.cancelled);
		b.append (", errorMessage: ");
		b.append (this.errorMessage);
		b.append (", start: ");
		b.append (this.start);
		b.append (", end: ");
		b.append (this.end);
		b.append (", current: ");
		b.append (this.current);
		b.append ("]");
		return b.toString();
	}

}
