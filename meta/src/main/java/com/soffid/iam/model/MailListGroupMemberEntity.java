package com.soffid.iam.model;

import java.util.Collection;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;

import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.LlistaCorreuEntity;
import es.caib.seycon.ng.model.TasqueEntity;

@Entity (table="SC_LCOGRU")
@Depends({TasqueEntity.class})
public class MailListGroupMemberEntity {

	public MailListGroupMemberEntity() {
	}

	@Column(name="LCG_ID" )
	@Identifier
	Long id;
	
	@Description ("Mail list container")
	@Column(name="LCG_LCO_ID", reverseAttribute="groups", cascadeDelete = true)
	LlistaCorreuEntity mailList;
	
	@Description ("Group included in mail list")
	@Column(name="LCR_GRU_ID", reverseAttribute="mailLists", cascadeDelete = true)
	GrupEntity group;
	
	///////////////////////////////////
	@DaoFinder("from com.soffid.iam.model.MailListGroupMemberEntity e where e.mailList.id=:mailListId and e.group.id=:groupId")
	Collection<MailListGroupMemberEntity> findByMailListAndGroup (long mailListId, long groupId) {
		return null;
	}
}
