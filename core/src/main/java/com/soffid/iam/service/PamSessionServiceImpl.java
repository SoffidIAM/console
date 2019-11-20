package com.soffid.iam.service;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.utils.Security;

public class PamSessionServiceImpl extends PamSessionServiceBase {

	@Override
	protected JumpServerGroup handleCreate(JumpServerGroup jumpServerGroup) throws Exception {
		JumpServerGroupEntity entity = getJumpServerGroupEntityDao().jumpServerGroupToEntity(jumpServerGroup);
		getJumpServerGroupEntityDao().create(entity);
		
		for (String s: jumpServerGroup.getJumpServers())
		{
			if ( s != null && !s.trim().isEmpty())
			{
				JumpServerEntity js = getJumpServerEntityDao().newJumpServerEntity();
				js.setUrl(s);
				js.setJumpServerGroup(entity);
				getJumpServerEntityDao().create(js);
				entity.getJumpServers().add(js);
			}
		}
		return getJumpServerGroupEntityDao().toJumpServerGroup(entity);
	}

	@Override
	protected JumpServerGroup handleUpdate(JumpServerGroup jumpServerGroup) throws Exception {
		JumpServerGroupEntity entity = getJumpServerGroupEntityDao().jumpServerGroupToEntity(jumpServerGroup);
		getJumpServerGroupEntityDao().update(entity);
		
		LinkedList<String> list = new LinkedList<String>( jumpServerGroup.getJumpServers());
		
		for ( JumpServerEntity js:  new LinkedList<JumpServerEntity>(entity.getJumpServers()))
		{
			if ( ! list.contains(js.getUrl()))
			{
				entity.getJumpServers().remove(js);
				getJumpServerEntityDao().remove(js);
			}
			else
			{
				list.remove(js.getUrl());
			}
		}
		
		for (String s: list)
		{
			if ( s != null && !s.trim().isEmpty())
			{
				JumpServerEntity js = getJumpServerEntityDao().newJumpServerEntity();
				js.setUrl(s);
				js.setJumpServerGroup(entity);
				getJumpServerEntityDao().create(js);
				entity.getJumpServers().add(js);
			}
		}
		return getJumpServerGroupEntityDao().toJumpServerGroup(entity);
	}

	@Override
	protected void handleRemove(JumpServerGroup jumpServerGroup) throws Exception {
		JumpServerGroupEntity entity = getJumpServerGroupEntityDao().jumpServerGroupToEntity(jumpServerGroup);
		getJumpServerGroupEntityDao().update(entity);
		
		for ( JumpServerEntity js:  new LinkedList<JumpServerEntity>(entity.getJumpServers()))
		{
			entity.getJumpServers().remove(js);
			getJumpServerEntityDao().remove(js);
		}
		
		getJumpServerGroupEntityDao().remove(entity);
	}

	@Override
	protected URL handleCreateJumpServerSession(Account account) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<JumpServerGroup> handleFindJumpServerGroups() throws Exception {
		if (Security.isUserInRole("jumpServer:query"))
			return getJumpServerGroupEntityDao().toJumpServerGroupList(
				getJumpServerGroupEntityDao().loadAll());
		else
			return new LinkedList<JumpServerGroup>();
	}

}
