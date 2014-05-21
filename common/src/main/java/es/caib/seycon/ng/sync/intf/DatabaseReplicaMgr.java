package es.caib.seycon.ng.sync.intf;


import java.util.Vector;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface DatabaseReplicaMgr extends DatabaseReplicaOfflineChangeRetriever
{
	public abstract void update(String tableName, String idColumn,
			Long idValue, Vector<String> cols, Vector<Object> values)
			throws InternalErrorException;
	
	void remove (String tableName, String idColumn, Long value) throws InternalErrorException;

}
