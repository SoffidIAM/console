package com.soffid.iam.model;

import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.JumpServerGroupEntityDaoBase;

public class JumpServerGroupEntityDaoImpl extends JumpServerGroupEntityDaoBase {

	@Override
	public void toJumpServerGroup(JumpServerGroupEntity source, JumpServerGroup target) {
		super.toJumpServerGroup(source, target);
		
		for ( JumpServerEntity js: source.getJumpServers())
		{
			target.getJumpServers().add(js.getUrl());
		}
	}

	@Override
	public void remove(JumpServerGroupEntity entity) {
		getSession().createQuery("update com.soffid.iam.model.AccountEntityImpl "
				+ "set jumpServerGroup = null "
				+ "where jumpServerGroup.id = :id")
			.setParameter("id", entity.getId())
			.executeUpdate();
		super.remove(entity);
	}

}
