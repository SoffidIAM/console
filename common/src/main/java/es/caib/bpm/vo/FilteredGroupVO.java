package es.caib.bpm.vo;

import java.io.Serializable;
import java.util.Comparator;


public class FilteredGroupVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	//ignored no existe en BBDD, sinó que se utiliza para el binding con el checkbox de zkib
	Boolean ignored; 
	String userName;
    Integer type;
    String pooledActorsStyleName;
	
	public String getName() 				{			return name; 				}
	public void setName(String name) 		{			this.name = name;			}
	public Boolean getIgnored() 			{			return ignored;				}
	public void setIgnored(Boolean ignored) {			this.ignored = ignored;		}
	public String getUserName() 			{			return userName;			}
	public void setUserName(String userName){			this.userName = userName;	}
	public Integer getType() 				{			return type;				}
	public void setType(Integer type) 		{			this.type = type;			}
	public String getPooledActorsStyleName(){			return pooledActorsStyleName;}
	public void setPooledActorsStyleName(String pooledActorsStyleName) {this.pooledActorsStyleName = pooledActorsStyleName;}

	
	public static class FilteredGroupVoNameComparator implements Comparator<FilteredGroupVO>{

		public int compare(FilteredGroupVO o1, FilteredGroupVO o2) {
			return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
		}
		
	}
}

