//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.ReplicaDatabase;
import es.caib.seycon.ng.utils.Security;

/**
 * DAO ReplicaDatabaseEntity implementation
 */
public class ReplicaDatabaseEntityDaoImpl extends ReplicaDatabaseEntityDaoBase
{

	@Override
	public void toReplicaDatabase (ReplicaDatabaseEntity source, ReplicaDatabase target)
	{
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
	public void replicaDatabaseToEntity (ReplicaDatabase source,
					ReplicaDatabaseEntity target, boolean copyIfNull)
	{
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
