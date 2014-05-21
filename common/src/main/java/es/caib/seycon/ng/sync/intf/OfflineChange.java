package es.caib.seycon.ng.sync.intf;

import java.util.Date;

public class OfflineChange
{
	Long id;
	Date date;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	
}
