package com.soffid.iam.spring;

import java.util.Collection;

import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
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

public class CollectionCacheEventListener implements PostDeleteEventListener, PostUpdateEventListener, PostInsertEventListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		if (JCSCacheProvider.isEnabled())
			removeFromCache(event.getEntity(), event.getPersister(), event.getDeletedState(), true);
	}

	private Object getAttribute(EntityPersister persister, Object[] state, String att) {
		String[] props = persister.getPropertyNames();
		for (int i = 0; i < props.length; i++)
			if ( props[i].equals(att))
				return state [i];
		throw new RuntimeException ("Unknown attribute "+att+" in class "+persister.getEntityName());
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		if (JCSCacheProvider.isEnabled())
			removeFromCache(event.getEntity(), event.getPersister(), event.getState(), false);		
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		if (JCSCacheProvider.isEnabled())
		{
			removeFromCache(event.getEntity(), event.getPersister(), event.getOldState(), true);
			removeFromCache(event.getEntity(), event.getPersister(), event.getState(), false);
		}
	}

	private void removeFromCache(Object entity, EntityPersister entityPersister, Object[] objects, boolean deleting) {
		if (entity instanceof UserEntity)
		{
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "primaryGroup");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.primaryGroupUsers", group.getId(), entity, group.getPrimaryGroupUsers(), deleting);
		}
		if (entity instanceof UserGroupEntity)
		{
			UserEntity user = (UserEntity) getAttribute ( entityPersister, objects, "user");
			if (user != null)
				removeFromcache ("com.soffid.iam.model.UserEntityImpl.secondaryGroups", user.getId(), entity, user.getSecondaryGroups(), deleting);
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "group");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.secondaryGroupUsers", group.getId(), entity, group.getSecondaryGroupUsers(), deleting);
		}
		if (entity instanceof UserAccountEntity)
		{
			UserEntity user = (UserEntity) getAttribute ( entityPersister, objects, "user");
			if (user != null)
				removeFromcache ("com.soffid.iam.model.UserEntityImpl.accounts", user.getId(), entity, user.getAccounts(), deleting);
			AccountEntity account = (AccountEntity) getAttribute ( entityPersister, objects, "account");
			if (account != null)
				removeFromcache ("com.soffid.iam.model.AccountEntityImpl.users", account.getId(), entity, account.getUsers(), deleting);
		}
		if (entity instanceof GroupEntity)
		{
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "parent");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.children",group.getId(), entity, group.getChildren(), deleting);
		}
		if (entity instanceof RoleGroupEntity)
		{
			GroupEntity group = (GroupEntity) getAttribute ( entityPersister, objects, "group");
			if (group != null)
				removeFromcache ("com.soffid.iam.model.GroupEntityImpl.grantedRoles",group.getId(), entity, group.getGrantedRoles(), deleting);
			RoleEntity role = (RoleEntity) getAttribute ( entityPersister, objects, "grantedRole");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.containerGroups",role.getId(), entity, role.getContainerGroups(), deleting);
		}
		if (entity instanceof RoleAccountEntity)
		{
			AccountEntity account = (AccountEntity) getAttribute ( entityPersister, objects, "account");
			if (account != null)
				removeFromcache ("com.soffid.iam.model.AccountEntityImpl.roles",account.getId(), entity, account.getRoles(), deleting);
			RoleEntity role = (RoleEntity) getAttribute ( entityPersister, objects, "role");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.accounts",role.getId(), entity, role.getAccounts(), deleting);
		}
		if (entity instanceof UserAccountEntity)
		{
			AccountEntity account = (AccountEntity) getAttribute ( entityPersister, objects, "account");
			if (account != null)
				removeFromcache ("com.soffid.iam.model.AccountEntityImpl.users",account.getId(), entity, account.getUsers(), deleting);
			UserEntity user = (UserEntity) getAttribute ( entityPersister, objects, "user");
			if (user != null)
				removeFromcache ("com.soffid.iam.model.UserEntityImpl.accounts",user.getId(), entity, user.getAccounts(), deleting);
		}
		if (entity instanceof RoleDependencyEntity)
		{
			RoleEntity role = (RoleEntity) getAttribute ( entityPersister, objects, "container");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.containedRoles",role.getId(), entity, role.getContainedRoles(), deleting);
			role = (RoleEntity) getAttribute ( entityPersister, objects, "contained");
			if (role != null)
				removeFromcache ("com.soffid.iam.model.RoleEntityImpl.containerRoles",role.getId(), entity, role.getContainerRoles(), deleting);
		}
	}

	private void removeFromcache(String region, Long id, Object entity, Collection collection, boolean deleting) {
		new JCSCache ("hibernate."+region).remove(region+"#"+id);
		if (Hibernate.isInitialized(collection))
		{
			if (deleting)
				collection.remove(entity);
			else
				collection.add(entity);
		}		
			
	}

}
