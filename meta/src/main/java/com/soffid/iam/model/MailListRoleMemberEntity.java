package com.soffid.iam.model;

import java.util.Collection;

import com.soffid.iam.api.MailListRoleMember;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.LlistaCorreuEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.ValorDominiAplicacioEntity;

@Entity(table="SC_LCOROL")
@Depends({MailListRoleMember.class,
	TasqueEntity.class})
public class MailListRoleMemberEntity {

	public MailListRoleMemberEntity() {
	}

	@Column(name="LCR_ID" )
	@Identifier
	Long id;
	
	@Description ("Mail list container")
	@Column(name="LCR_LCO_ID", reverseAttribute="roles", cascadeDelete = true)
	LlistaCorreuEntity mailList;
	
	@Description ("Role included in mail list")
	@Column(name="LCR_ROL_ID", reverseAttribute="mailLists", cascadeDelete = true)
	RolEntity role;
	
	@Description ("Optional application scope for role grant")
	@Column(name="LCR_APL_ID", reverseAttribute="roleScopeMailLists", cascadeDelete = true)
	@Nullable
	AplicacioEntity informationSystemScope;
	
	@Description ("Optional group scope for role grant")
	@Column(name="LCR_GRU_ID", reverseAttribute="roleScopeMailLists", cascadeDelete=true)
	@Nullable GrupEntity groupScope;
	
	@Description("Optional application value scope for role grant")
	@Column(name="LCR_VDO_ID", reverseAttribute="roleScopeMailLists", cascadeDelete=true)
	@Nullable ValorDominiAplicacioEntity domainValueScope;

	///////////////////////////////////
	@DaoFinder("from com.soffid.iam.model.MailListRoleMemberEntity e "
			+ "where e.mailList.id=:mailListId and e.role.id=:roleId "
			+ "and e.role.system.tenant.id=:tenantId")
	Collection<MailListGroupMemberEntity> findByMailListAndGroup (long mailListId, long roleId) {
		return null;
	}

}
