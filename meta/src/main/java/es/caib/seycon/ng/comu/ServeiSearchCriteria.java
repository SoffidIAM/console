//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@Criteria 
public abstract class ServeiSearchCriteria {

	@CriteriaColumn(parameter="name", comparator="LIKE_COMPARATOR")
	public java.lang.String codi;

	@CriteriaColumn(parameter="description", comparator="LIKE_COMPARATOR")
	public java.lang.String descripcio;

}
