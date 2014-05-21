package es.caib.bpm.presentation;

public class TaskDistributionSearch 
{
	public String getNodeName() 
	{
		return nodeName;
	}
	
	public void setNodeName(String nodeName) 
	{
		this.nodeName = nodeName;
	}
	
	public Integer getValue() 
	{
		return value;
	}
	
	public void setValue(Integer value) 
	{
		this.value = value;
	}
	
	private String nodeName= null;
	private Integer value= null;
}
