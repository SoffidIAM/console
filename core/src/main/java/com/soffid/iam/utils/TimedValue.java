package com.soffid.iam.utils;

public class TimedValue<E> {
	long timeout;
	E data;
	
	public TimedValue (E data, long timeout) {
		this.timeout = timeout;
		this.data = data;
	}
	
	public boolean isExpired() {
		return timeout > 0 && System.currentTimeMillis() > timeout;
	}
	
	public E getValue() {
		return data;		
	}
}
