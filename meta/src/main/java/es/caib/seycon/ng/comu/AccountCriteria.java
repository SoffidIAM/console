//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@Criteria 
public abstract class AccountCriteria {

	@Nullable
	public java.lang.String name;

	@Nullable
	public java.lang.String description;

	@Nullable
	public es.caib.seycon.ng.comu.AccountType type;

	@Nullable
	@CriteriaColumn(parameter="acl.group.codi")
	public java.lang.String grantedGroups;

	@Nullable
	@CriteriaColumn(parameter="acl.user.codi")
	public java.lang.String grantedUsers;

	@Nullable
	@CriteriaColumn(parameter="acl.rol.codi")
	public java.lang.String grantedRoles;

	@Nullable
	@CriteriaColumn(parameter="dispatcher.codi")
	public java.lang.String dispatcher;

	@Nullable
	@CriteriaColumn (comparator="NOT_EQUAL_COMPARATOR", parameter="type")
	public es.caib.seycon.ng.comu.AccountType excludeType;

}
