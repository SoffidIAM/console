package com.soffid.iam.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserPreferenceEntity;
import com.soffid.iam.utils.Security;

public class PreferencesServiceImpl extends PreferencesServiceBase {

	@Override
	protected String handleFindMyPreference(String name) throws Exception {
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		if (principal == null)
			return null;
		String userName = principal.getUserName();
		if (userName == null)
			return null;
		
		Collection<UserPreferenceEntity> entity = getUserPreferenceEntityDao().findByNameAndUserName(name, userName);
		if (entity.isEmpty())
			return null;
		else
			return entity.iterator().next().getValue();
	}

	@Override
	protected Map<String, String> handleFindUserPreferences(String user) throws Exception {
		Map<String,String> r = new HashMap<String, String>();
		for (UserPreferenceEntity entity: getUserPreferenceEntityDao().findByUserName(user)) {
			r.put(entity.getName(), entity.getValue());
		}
		return r;
	}

	@Override
	protected Map<String, String> handleSetUserPreferences(String user, Map<String, String> preferences)
			throws Exception {
		UserEntity userEntity = getUserEntityDao().findByUserName(user);
		if (userEntity == null)
			return null;
		
		Map<String,String> r = new HashMap<String, String>(preferences);
		for (UserPreferenceEntity entity: getUserPreferenceEntityDao().findByUserName(user)) {
			if ( ! r.containsKey(entity.getName()) || r.get(entity.getName()) == null ||
					r.get(entity.getName()).trim().isEmpty()) {
				r.remove(entity.getName());
				getUserPreferenceEntityDao().remove(entity);
			} else {
				entity.setValue(r.get(entity.getName()));
				r.remove(entity.getName());
				getUserPreferenceEntityDao().update(entity);
			}
		}
		
		for (Entry<String, String> pair: r.entrySet()) {
			UserPreferenceEntity entity = getUserPreferenceEntityDao().newUserPreferenceEntity();
			entity.setName(pair.getKey());
			entity.setValue(pair.getValue());
			entity.setUser(userEntity);
			getUserPreferenceEntityDao().create(entity);
		}
		return preferences;
	}

	@Override
	protected void handleSetUserPreference(String user, String preference, String value) throws Exception {
		UserEntity userEntity = getUserEntityDao().findByUserName(user);
		if (userEntity == null)
			return ;
		
		if (value.length() > 500)
			value = null;
		
		Collection<UserPreferenceEntity> entities = getUserPreferenceEntityDao().findByNameAndUserName(preference, user);
		for (UserPreferenceEntity entity: entities) {
			if ( value == null || value.trim().isEmpty()) {
				getUserPreferenceEntityDao().remove(entity);
			} else {
				entity.setValue(value);
				getUserPreferenceEntityDao().update(entity);
			}
		}
		
		if (entities.isEmpty()) {
			UserPreferenceEntity entity = getUserPreferenceEntityDao().newUserPreferenceEntity();
			entity.setName(preference);
			entity.setValue(value);
			entity.setUser(userEntity);
			getUserPreferenceEntityDao().create(entity);
		}
	}

	@Override
	protected void handleUpdateMyPreference(String name, String value) throws Exception {
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		if (principal == null)
			return;
		String userName = principal.getUserName();
		if (userName == null)
			return;
		
		handleSetUserPreference(userName, name, value);
	}

}
