//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.ReplicaDatabase;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.model.*;

/**
 * DAO ReplicaDatabaseEntity implementation
 */
public class ReplicaDatabaseEntityDaoImpl extends com.soffid.iam.model.ReplicaDatabaseEntityDaoBase
{

	@Override
    public void toReplicaDatabase(com.soffid.iam.model.ReplicaDatabaseEntity source, ReplicaDatabase target) {
		super.toReplicaDatabase(source, target);
		if (Security.isUserInRole(Security.AUTO_SERVER_MANAGE))
		{
    		if (source.getPassword() == null)
    			target.setPassword(null);
    		else
    			target.setPassword(Password.decode(source.getPassword()));
		}
	}

	@Override
    public void replicaDatabaseToEntity(ReplicaDatabase source, com.soffid.iam.model.ReplicaDatabaseEntity target, boolean copyIfNull) {
		if (Security.isUserInRole(Security.AUTO_SERVER_MANAGE))
		{
    		if (source.getPassword() == null && copyIfNull)
    			target.setPassword(null);
    		else if (source.getPassword() != null)
    			target.setPassword(source.getPassword().toString());
		}
		super.replicaDatabaseToEntity(source, target, copyIfNull);
	}
}
