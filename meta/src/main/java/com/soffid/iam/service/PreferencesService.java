package com.soffid.iam.service;

import java.util.Map;

import com.soffid.iam.model.UserPreferenceEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.UsuariService;
import roles.Tothom;
import roles.user_update;

@Service
@Description("Manages user preferences")
@Depends({
	UserPreferenceEntity.class,
	UsuariEntity.class,
	UsuariService.class
})
public class PreferencesService {
	@Operation(grantees = { user_update.class })
	@Description("Retrieves all the preferences for the selected user")
	public Map<String,String> findUserPreferences(String user) { return null; }

	@Operation(grantees = { user_update.class })
	@Description("Sets all user preferences for the selected user")
	public Map<String,String> setUserPreferences(String user, @Nullable Map<String,String> preferences) { return null; }

	@Operation(grantees = { user_update.class })
	public void setUserPreference(String user, String preference, String value) {  }

	@Operation(grantees = {Tothom.class})
	@Description("Retrieves current user preference")
	public String findMyPreference(String name) {return null;}
	
	@Operation(grantees = {Tothom.class})
	@Description("Changes current user preference")
	public void updateMyPreference(String name, @Nullable String value) {}
	
}
