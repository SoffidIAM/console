//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject StatsSample
 **/
public class StatsSample

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute min

	 */
	private long min;

	/**
	 * Attribute max

	 */
	private long max;

	/**
	 * Attribute sum

	 */
	private long sum;

	/**
	 * Attribute average

	 */
	private long average;

	/**
	 * Attribute instances

	 */
	private long instances;

	public StatsSample()
	{
	}

	public StatsSample(long min, long max, long sum, long average, long instances)
	{
		super();
		this.min = min;
		this.max = max;
		this.sum = sum;
		this.average = average;
		this.instances = instances;
	}

	public StatsSample(StatsSample otherBean)
	{
		this(otherBean.min, otherBean.max, otherBean.sum, otherBean.average, otherBean.instances);
	}

	/**
	 * Gets value for attribute min
	 */
	public long getMin() {
		return this.min;
	}

	/**
	 * Sets value for attribute min
	 */
	public void setMin(long min) {
		this.min = min;
	}

	/**
	 * Gets value for attribute max
	 */
	public long getMax() {
		return this.max;
	}

	/**
	 * Sets value for attribute max
	 */
	public void setMax(long max) {
		this.max = max;
	}

	/**
	 * Gets value for attribute sum
	 */
	public long getSum() {
		return this.sum;
	}

	/**
	 * Sets value for attribute sum
	 */
	public void setSum(long sum) {
		this.sum = sum;
	}

	/**
	 * Gets value for attribute average
	 */
	public long getAverage() {
		return this.average;
	}

	/**
	 * Sets value for attribute average
	 */
	public void setAverage(long average) {
		this.average = average;
	}

	/**
	 * Gets value for attribute instances
	 */
	public long getInstances() {
		return this.instances;
	}

	/**
	 * Sets value for attribute instances
	 */
	public void setInstances(long instances) {
		this.instances = instances;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[min: ");
		b.append (this.min);
		b.append (", max: ");
		b.append (this.max);
		b.append (", sum: ");
		b.append (this.sum);
		b.append (", average: ");
		b.append (this.average);
		b.append (", instances: ");
		b.append (this.instances);
		b.append ("]");
		return b.toString();
	}

}
