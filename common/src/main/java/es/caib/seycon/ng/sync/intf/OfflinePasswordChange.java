package es.caib.seycon.ng.sync.intf;

import java.util.Date;

import es.caib.seycon.ng.comu.Password;

public class OfflinePasswordChange extends OfflineChange
{
	Long userId;
	Long domainId;
	Long accountId;
	Password password;
	Date creation;
	Date expiration;
	boolean mustChange;
	
	public boolean isMustChange()
	{
		return mustChange;
	}
	public void setMustChange(boolean mustChange)
	{
		this.mustChange = mustChange;
	}
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public Long getDomainId()
	{
		return domainId;
	}
	public void setDomainId(Long domainId)
	{
		this.domainId = domainId;
	}
	public Long getAccountId()
	{
		return accountId;
	}
	public void setAccountId(Long accountId)
	{
		this.accountId = accountId;
	}
	public Password getPassword()
	{
		return password;
	}
	public void setPassword(Password password)
	{
		this.password = password;
	}
	public Date getCreation()
	{
		return creation;
	}
	public void setCreation(Date creation)
	{
		this.creation = creation;
	}
	public Date getExpiration()
	{
		return expiration;
	}
	public void setExpiration(Date expiration)
	{
		this.expiration = expiration;
	}
	
	
}
