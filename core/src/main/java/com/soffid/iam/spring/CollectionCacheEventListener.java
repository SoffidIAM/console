package com.soffid.iam.spring;

import org.hibernate.EntityMode;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;

public class CollectionCacheEventListener implements PreDeleteEventListener, PreUpdateEventListener, PreInsertEventListener {

	
	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
		if (JCSCacheProvider.isEnabled())
			removeFromCache(event.getEntity(), event.getPersister(), event.getDeletedState());
		
		return false;
	}

	private Object getAttribute(EntityPersister persister, Object[] state, String att) {
		String[] props = persister.getPropertyNames();
		for (int i = 0; i < props.length; i++)
			if ( props[i].equals(att))
				return state [i];
		return null;
	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		if (JCSCacheProvider.isEnabled())
			removeFromCache(event.getEntity(), event.getPersister(), event.getState());		
		return false;
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		if (JCSCacheProvider.isEnabled())
		{
			removeFromCache(event.getEntity(), event.getPersister(), event.getOldState());
			removeFromCache(event.getEntity(), event.getPersister(), event.getState());
		}
		return false;
	}

	private void removeFromCache(Object entity, EntityPersister entityPersister, Object[] objects) {
		if (entity instanceof UserEntity)
		{
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "primaryGroup");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.primaryGroupUsers",group.getId());
		}
		if (entity instanceof UserGroupEntity)
		{
			UserEntity user = (UserEntity) getAttribute ( entityPersister, objects, "user");
			if (user != null)
				removeFromcache ("com.soffid.iam.model.UserEntityImpl.secondaryGroups", user.getId());
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "group");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.secondaryGroupUsers", group.getId());
		}
		if (entity instanceof UserAccountEntity)
		{
			UserEntity user = (UserEntity) getAttribute ( entityPersister, objects, "user");
			if (user != null)
				removeFromcache ("com.soffid.iam.model.UserEntityImpl.accounts", user.getId());
			AccountEntity account = (AccountEntity) getAttribute ( entityPersister, objects, "account");
			if (account != null)
				removeFromcache ("com.soffid.iam.model.AccountEntityImpl.users", account.getId());
		}
		if (entity instanceof GroupEntity)
		{
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "parent");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.children",group.getId());
		}
		if (entity instanceof RoleGroupEntity)
		{
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "group");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.grantedRoles",group.getId());
			RoleEntity role = (RoleEntity) getAttribute ( entityPersister, objects, "grantedRole");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.containerGroups",role.getId());
		}
		if (entity instanceof RoleAccountEntity)
		{
			AccountEntity account = (AccountEntity) getAttribute ( entityPersister, objects, "ownerAccount");
			if (account != null)
				removeFromcache ("com.soffid.iam.model.AccountEntityImpl.roles",account.getId());
			RoleEntity role = (RoleEntity) getAttribute ( entityPersister, objects, "grantedRole");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.accounts",role.getId());
		}
		if (entity instanceof UserAccountEntity)
		{
			AccountEntity account = (AccountEntity) getAttribute ( entityPersister, objects, "account");
			if (account != null)
				removeFromcache ("com.soffid.iam.model.AccountEntityImpl.users",account.getId());
			UserEntity user = (UserEntity) getAttribute ( entityPersister, objects, "user");
			if (user != null)
				removeFromcache ("com.soffid.iam.model.UserEntityImpl.accounts",user.getId());
		}
		if (entity instanceof RoleDependencyEntity)
		{
			RoleEntity role = (RoleEntity) getAttribute ( entityPersister, objects, "container");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.containedRoles",role.getId());
			role = (RoleEntity) getAttribute ( entityPersister, objects, "contained");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.containerRoles",role.getId());
		}
	}

	private void removeFromcache(String region, Long id) {
		new JCSCache ("hibernate."+region).remove(region+"#"+id);
	}

}
