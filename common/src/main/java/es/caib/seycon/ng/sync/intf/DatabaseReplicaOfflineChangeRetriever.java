package es.caib.seycon.ng.sync.intf;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface DatabaseReplicaOfflineChangeRetriever
{
	public abstract List<OfflineChange> getOfflineChanges(Long firstId)
			throws InternalErrorException, SQLException;

	void removeOfflineChange(Long id) throws InternalErrorException, SQLException;
	
}