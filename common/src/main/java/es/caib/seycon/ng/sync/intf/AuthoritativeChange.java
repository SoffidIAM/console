package es.caib.seycon.ng.sync.intf;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import es.caib.seycon.ng.comu.Usuari;

public class AuthoritativeChange implements Serializable
{
	AuthoritativeChangeIdentifier id;
	
	String sourceSystem;
	
	public String getSourceSystem()
	{
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem)
	{
		this.sourceSystem = sourceSystem;
	}

	Usuari user;
	
	Map<String, Object> attributes;
	
	Set<String> groups;

	public Set<String> getGroups()
	{
		return groups;
	}

	public void setGroups(Set<String> groups)
	{
		this.groups = groups;
	}

	public AuthoritativeChangeIdentifier getId()
	{
		return id;
	}

	public void setId(AuthoritativeChangeIdentifier id)
	{
		this.id = id;
	}

	public Usuari getUser()
	{
		return user;
	}

	public void setUser(Usuari user)
	{
		this.user = user;
	}

	public Map<String, Object> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes)
	{
		this.attributes = attributes;
	}

	public AuthoritativeChange()
	{
	}

}
