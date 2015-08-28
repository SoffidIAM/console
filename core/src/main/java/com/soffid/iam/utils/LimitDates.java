package com.soffid.iam.utils;
import java.util.Date;

public class LimitDates {
	private Date minimum = null;
	private Date maximum = null;
	
	public LimitDates(Date maximum, Date minimum){
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public Date getMinimum() {
		return minimum;
	}	

	public Date getMaximum() {
		return maximum;
	}

	
	
}
