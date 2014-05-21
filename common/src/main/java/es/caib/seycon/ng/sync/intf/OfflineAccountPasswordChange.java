package es.caib.seycon.ng.sync.intf;

import java.util.Date;

import es.caib.seycon.ng.comu.Password;

public class OfflineAccountPasswordChange extends OfflineChange
{
	Long accountId;
	Date date;
	Password password;
	String status;
	boolean mustChange;
	
	public Long getAccountId()
	{
		return accountId;
	}
	public void setAccountId(Long accountId)
	{
		this.accountId = accountId;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Password getPassword()
	{
		return password;
	}
	public void setPassword(Password password)
	{
		this.password = password;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public boolean isMustChange()
	{
		return mustChange;
	}
	public void setMustChange(boolean mustChange)
	{
		this.mustChange = mustChange;
	}
	
}
