package com.soffid.iam.model;

import java.util.Collection;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.UsuariEntity;

@Entity(table = "SC_USEPRE")
public class UserPreferenceEntity {
	@Identifier @Nullable
	Long id;
	
	@Column(name = "USP_USU_ID", reverseAttribute = "preferences")
	UsuariEntity user;
	
	@Description("Preference name")
	@Column(name = "USP_NAME", length = 250)
	String name;
	
	@Description("Preference value")
	@Nullable
	@Column(name = "USP_VALUE", length = 500)
	String value;
	
	@DaoFinder("select up from com.soffid.iam.model.UserPreferenceEntity as up "
			+ "where up.user.userName = :userName and up.user.tenant.id=:tenantId")
	Collection<UserPreferenceEntity> findByUserName (String userName) { return null;}

	@DaoFinder("select up from com.soffid.iam.model.UserPreferenceEntity as up "
			+ "where up.user.userName = :userName and up.name=:name and up.user.tenant.id=:tenantId")
	Collection<UserPreferenceEntity> findByNameAndUserName (String name, String userName) { return null;}
}

@Index(name = "SC_USEPRE_UK", columns = {"USP_USU_ID", "USP_NAME"}, entity = UserPreferenceEntity.class, unique = true)
class UserPreferenceUk {}

