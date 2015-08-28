package com.soffid.iam.bpm.model;

/**
 * Representa los roles y/o usuarios que cumplen un rol para la definicion del proceso.
 * 
 * Los roles son:
 * 	Iniciador
 * 	Supervisor
 * 	Oservador
 * 	Ejecutor
 * 
 * @author Pablo Hern�n Gim�nez.
 *
 */
public class ProcessDefinitionUserRole 
{
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public Long getProcessDefinitionId() 
	{
		return processDefinitionId;
	}
	
	public void setProcessDefinitionId(Long processDefinitionId) 
	{
		this.processDefinitionId = processDefinitionId;
	}
	
	public String getAppRole() 
	{
		return appRole;
	}
	
	public void setAppRole(String appRole) 
	{
		this.appRole = appRole;
	}
	
	public Boolean getIsUser() 
	{
		return isUser;
	}
	
	public void setIsUser(Boolean isUser) 
	{
		this.isUser = isUser;
	}
	
	public String getUserRole() 
	{
		return userRole;
	}
	
	public void setUserRole(String userRole) 
	{
		this.userRole = userRole;
	}
	
	private Long id= null;
	private Long processDefinitionId= null;
	private String appRole= null;
	private String userRole= null;
	private Boolean isUser= null;
}
