//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@Criteria 
public class XarxaSearchCriteria {

	@CriteriaColumn(comparator="LIKE_COMPARATOR", parameter="name")
	public java.lang.String codi;

	@CriteriaColumn(comparator="LIKE_COMPARATOR", parameter="address")
	public java.lang.String adreca;

	@CriteriaColumn(comparator="LIKE_COMPARATOR",parameter="description")
	public java.lang.String descripcio;

	@CriteriaColumn(comparator="LIKE_COMPARATOR",parameter="mask")
	public java.lang.String mascara;

	@CriteriaColumn(comparator="LIKE_COMPARATOR",parameter="normalized")
	public java.lang.String normalitzada;

	public java.lang.String dhcp;

}
