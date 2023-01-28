// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Network;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.IPAddress;
import com.soffid.iam.utils.InvalidIPException;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @see es.caib.seycon.ng.model.XarxaEntity
 */
public class NetworkEntityDaoImpl extends com.soffid.iam.model.NetworkEntityDaoBase {

    private void auditarXarxa(String accio, String codiXarxa) {
        String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setNetwork(codiXarxa);
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_XARXA"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void create(com.soffid.iam.model.NetworkEntity xarxa) throws RuntimeException {
        try {
            super.create(xarxa);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_NETWORKS);
            tasque.setSubnet(xarxa.getName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarXarxa("C", xarxa.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("NetworkEntityDaoImpl.0"), xarxa.getName(), message), e);
        }
    }

    public void remove(com.soffid.iam.model.NetworkEntity xarxa) throws RuntimeException {
        try {
            String codiXarxa = xarxa.getName();
            getNetworkDiscoveryAccountEntityDao().remove(xarxa.getAccounts());
            super.remove(xarxa);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_NETWORKS);
            tasque.setSubnet(xarxa.getName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarXarxa("D", codiXarxa); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("NetworkEntityDaoImpl.1"), xarxa.getName(), message), e); //$NON-NLS-1$
        }
    }

    public void update(com.soffid.iam.model.NetworkEntity xarxa) throws RuntimeException {
        try {
            super.update(xarxa);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_NETWORKS);
            tasque.setSubnet(xarxa.getName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarXarxa("U", xarxa.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("NetworkEntityDaoImpl.2"), xarxa.getName(), message), e); //$NON-NLS-1$
        }
    }

    public void toNetwork(com.soffid.iam.model.NetworkEntity sourceEntity, com.soffid.iam.api.Network targetVO) {
        super.toNetwork(sourceEntity, targetVO);
        toXarxaCustom(sourceEntity, targetVO);
    }

    public void toXarxaCustom(com.soffid.iam.model.NetworkEntity sourceEntity, com.soffid.iam.api.Network targetVO) {
        targetVO.setLanAccess(new Boolean(sourceEntity.getNormalized().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setDhcpSupport(sourceEntity.isDchpSupport());
        targetVO.setDiscoveryServer(sourceEntity.getDiscoveryServer() == null ? null: sourceEntity.getDiscoveryServer().getName());
    }

    /**
     * @see es.caib.seycon.ng.model.XarxaEntityDao#toXarxa(es.caib.seycon.ng.model.XarxaEntity)
     */
    public com.soffid.iam.api.Network toNetwork(final com.soffid.iam.model.NetworkEntity entity) {
        Network xarxa = super.toNetwork(entity);
        toXarxaCustom(entity, xarxa);
        return xarxa;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.NetworkEntity loadXarxaEntityFromXarxa(com.soffid.iam.api.Network xarxa) {
        NetworkEntity xarxaEntity = null;
        if (xarxa.getId() != null) {
            xarxaEntity = load(xarxa.getId());
        }
        if (xarxaEntity == null) {
            xarxaEntity = newNetworkEntity();
        }
        return xarxaEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.XarxaEntityDao#xarxaToEntity(es.caib.seycon.ng.comu.Xarxa)
     */
    public com.soffid.iam.model.NetworkEntity networkToEntity(com.soffid.iam.api.Network xarxa) {
        com.soffid.iam.model.NetworkEntity entity = this.loadXarxaEntityFromXarxa(xarxa);
        this.networkToEntity(xarxa, entity, true);
        return entity;
    }

    private void xarxaToEntityCustom(com.soffid.iam.api.Network sourceVO, com.soffid.iam.model.NetworkEntity targetEntity) throws InvalidIPException {

        if (sourceVO.getLanAccess() != null) {
            targetEntity.setNormalized(sourceVO.getLanAccess().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setNormalized("N"); //$NON-NLS-1$
        }
        targetEntity.setDchpSupport(sourceVO.isDhcpSupport());
        targetEntity.setAddress(sourceVO.getIp().trim());
        
        if (sourceVO.getDiscoveryServer() == null || sourceVO.getDiscoveryServer().trim().isEmpty())
        	targetEntity.setDiscoveryServer(null);
        else
        	targetEntity.setDiscoveryServer(getServerEntityDao().findByName(sourceVO.getDiscoveryServer()));
        // Check network mask
        if (sourceVO.getMask() != null)
        {
        	targetEntity.setMask(sourceVO.getMask().trim());
        }
        
        else
        {
        	throw new SeyconException(Messages.getString("NetworkEntityDaoImpl.4")); //$NON-NLS-1$
        }
        
        if (!adrecaCompatibleAmbXarxa(sourceVO.getIp(), sourceVO.getMask())) {
            throw new SeyconException(String.format(Messages.getString("NetworkEntityDaoImpl.3"), sourceVO.getIp(), sourceVO.getMask()));
        }
    }

    private boolean adrecaCorrecta(String adreca) {
    	try {
			InetAddress a = InetAddress.getByName(adreca);
			InetAddress a2 = InetAddress.getByAddress(null, a.getAddress());
			return true;
		} catch (UnknownHostException e) {
			return false;
		}
    }

    private String toBinaryAddress(String[] adreca) {
        String adrecaBinari = ""; //$NON-NLS-1$
        for (int i = 0; i < 4; i++) {
            String currentAdrecaBinari = Integer.toBinaryString(Integer.parseInt(adreca[i]));
            while (currentAdrecaBinari.length() < 8) {
                currentAdrecaBinari = "0" + currentAdrecaBinari; //$NON-NLS-1$
            }
            if (currentAdrecaBinari.length() > 8) {
                currentAdrecaBinari = currentAdrecaBinari.substring(
                        currentAdrecaBinari.length() - 8, currentAdrecaBinari.length());
            }
            adrecaBinari = adrecaBinari + currentAdrecaBinari;
        }
        return adrecaBinari;
    }

    Log log = LogFactory.getLog(getClass());
    
    private boolean adrecaCompatibleAmbXarxa(String adrecaXarxa, String mascaraXarxa) throws InvalidIPException {
		try {
			IPAddress ip = new IPAddress(adrecaXarxa, mascaraXarxa);
			byte [] adrNet = ip.ip;
			byte [] mask = ip.mask;

			if (mask.length != adrNet.length)
				return false;
			
			for ( int i = 0; i < adrNet.length; i++)
			{
				if ( ( ( ~ mask[i] ) &  adrNet[i]) != 0)
				{
					log.info("Error in byte "+i+" Value = "+adrNet[i]+" Mask = "+mask[i]);
					return false;
				}
			}
			return true;
		} catch (NumberFormatException e) {
			log.info ("Error parsing "+adrecaXarxa+"/"+mascaraXarxa, e);
			return false;
		} catch (UnknownHostException e) {
			log.info ("Error parsing "+adrecaXarxa+"/"+mascaraXarxa, e);
			return false;
		}
    }

	private byte[] getMaskBytes(String mascaraXarxa, byte[] adrNet) throws UnknownHostException {
		byte[] mask;
		if ( ! mascaraXarxa.contains("."))
		{
			int bits = Integer.parseInt(mascaraXarxa);
			mask = new byte [adrNet.length];
			for (int i = 0; i < mask.length; i++)
			{
				mask[i] = 0;
				for (byte j = (byte) 128; j >=  1; j = (byte) (j / 2) )
				{
					if (bits > 0) {
						mask [i] = (byte) ( mask [i] | j );
						bits --;
					}
				}
			}
		}
		else
			mask = InetAddress.getByName(mascaraXarxa).getAddress();
		return mask;
	}

    /**
     * @see es.caib.seycon.ng.model.XarxaEntityDao#xarxaToEntity(es.caib.seycon.ng.comu.Xarxa,
     *      es.caib.seycon.ng.model.XarxaEntity)
     */
    public void networkToEntity(com.soffid.iam.api.Network sourceVO, com.soffid.iam.model.NetworkEntity targetEntity, boolean copyIfNull) {
        super.networkToEntity(sourceVO, targetEntity, copyIfNull);
        try {
			xarxaToEntityCustom(sourceVO, targetEntity);
		} catch (InvalidIPException e) {
			throw new RuntimeException(e);
		}
    }

    public String getFirstFreeIP(String ipXarxa, String mascara) {
        try {
            // Prepara el cursor
        	IPAddress ip = new IPAddress(ipXarxa, mascara);
            IPAddress ip2 = new IPAddress(ipXarxa);
            ip2.incrementa();
            // Prueba cada una de las redes
            boolean finalitza = false;
            while (ip.esHostValid(ip2)) {
            	if ( getHostEntityDao().findByIP(ip2.toString()).isEmpty())
                    return ip2.toString();
                ip2.incrementa();
            }
            return ipXarxa;
        } catch (Exception e) {
            throw new SeyconException(e.toString(), e);
        }
    }


    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof NetworkEntity) {
                NetworkEntity entity = (NetworkEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof NetworkEntity) {
                NetworkEntity entity = (NetworkEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof NetworkEntity) {
                NetworkEntity entity = (NetworkEntity) obj;
                this.remove(entity);
            }
        }
    }
}
