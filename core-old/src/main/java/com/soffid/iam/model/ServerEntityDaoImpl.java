// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.hibernate.HibernateException;

import com.soffid.iam.model.ScheduledTaskEntity;

/**
 * @see es.caib.seycon.ng.model.ServerEntity
 */
public class ServerEntityDaoImpl
    extends com.soffid.iam.model.ServerEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ServerEntityDao#toServer(es.caib.seycon.ng.model.ServerEntity, es.caib.seycon.ng.comu.Server)
     */
    public void toServer(com.soffid.iam.model.ServerEntity source, es.caib.seycon.ng.comu.Server target) {
        // @todo verify behavior of toServer
        super.toServer(source, target);
        KeyFactory factory;
        if (source.getPk() == null)
        	target.setPublicKey(null);
        else
        {
            try {
                factory = KeyFactory.getInstance("RSA"); //$NON-NLS-1$
                try {
                    target.setPublicKey(factory.generatePublic(new X509EncodedKeySpec(source.getPk())));
                } catch (InvalidKeySpecException e) {
                    target.setPublicKey(null);
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        if (source.getBackupDatabase() != null)
        	target.setBackupDatabase(source.getBackupDatabase().getId());
        else
        	target.setBackupDatabase(null);
    }


    /**
     * @see es.caib.seycon.ng.model.ServerEntityDao#toServer(es.caib.seycon.ng.model.ServerEntity)
     */
    public es.caib.seycon.ng.comu.Server toServer(final com.soffid.iam.model.ServerEntity entity) {
        // @todo verify behavior of toServer
        return super.toServer(entity);
    }


    

    /**
     * @see es.caib.seycon.ng.model.ServerEntityDao#serverToEntity(es.caib.seycon.ng.comu.Server, es.caib.seycon.ng.model.ServerEntity)
     */
    public void serverToEntity(es.caib.seycon.ng.comu.Server source, com.soffid.iam.model.ServerEntity target, boolean copyIfNull) {
        // @todo verify behavior of serverToEntity
        super.serverToEntity(source, target, copyIfNull);
        if (source.getBackupDatabase() == null && copyIfNull)
        	target.setBackupDatabase(null);
        else if (source.getBackupDatabase() != null)
        {
        	com.soffid.iam.model.ReplicaDatabaseEntity rde = getReplicaDatabaseEntityDao().load(source.getBackupDatabase());
        	if (rde != null)
        		target.setBackupDatabase(rde);
        	else
        		throw new HibernateException(String.format(Messages.getString("ServerEntityDaoImpl.DBnotFound"), source.getBackupDatabase())); //$NON-NLS-1$
        }
    }

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.model.ServerEntityDaoBase#remove(es.caib.seycon.ng.model.ServerEntity)
	 */
	@Override
    public void remove(com.soffid.iam.model.ServerEntity entity) {
		getSecretEntityDao().remove(entity.getSecrets());
		for (ScheduledTaskEntity te: entity.getScheduledTasks())
		{
			te.setServer(null);
			getScheduledTaskEntityDao().update(te);
		}
		super.remove(entity);
	}

}