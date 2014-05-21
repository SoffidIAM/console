package es.caib.seycon.ng.utils;

import java.util.Date;

public class DateQuery{
	
	private Date date = null;
	private boolean secondsPrecision = false;
	private String query = null;
	
	public DateQuery (String query, Date date, boolean secondsPrecision){
		this.date = date;
		this.secondsPrecision = secondsPrecision;
		this.query = query;
	}
	
	public Date getDate(){
		return date;
	}
	
	public boolean isSecondsPrecision(){
		return secondsPrecision;
	}
	public String getQuery(){
		return query;
	}
}