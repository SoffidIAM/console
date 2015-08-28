package es.caib.bpm.vo;

import java.io.Serializable;
import java.util.Comparator;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;


@ValueObject(translatedName="FilteredGroupVO", translatedPackage="com.soffid.iam.bpm.api")
public class FilteredGroupVO implements Serializable{
	@Nullable
	String name;
	//ignored no existe en BBDD, sinó que se utiliza para el binding con el checkbox de zkib
	@Nullable
	Boolean ignored; 
	@Nullable
	String userName;
	@Nullable
    Integer type;
	@Nullable
    String pooledActorsStyleName;
	
	public static class FilteredGroupVoNameComparator implements Comparator<FilteredGroupVO>{

		public int compare(FilteredGroupVO o1, FilteredGroupVO o2) {
			return o1.name.toLowerCase().compareTo(o2.name.toLowerCase());
		}
		
	}
}

