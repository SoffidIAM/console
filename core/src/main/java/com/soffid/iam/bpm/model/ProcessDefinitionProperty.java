package com.soffid.iam.bpm.model;

/**
 * Representa las propiedas (TAGS) de una proceso
 * 
 *
 */
public class ProcessDefinitionProperty
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
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String value) 
	{
		this.name = value;
	}

	public String getValue() 
	{
		return value;
	}
	
	public void setValue(String value) 
	{
		this.value = value;
	}
	
	private Long id= null;
	private Long processDefinitionId= null;
	private String name= null;
	private String value= null;
}
