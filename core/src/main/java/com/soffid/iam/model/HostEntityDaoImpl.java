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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Network;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.model.MaquinaEntity
 */
public class HostEntityDaoImpl extends
        com.soffid.iam.model.HostEntityDaoBase {

    public void toHost(com.soffid.iam.model.HostEntity sourceEntity, com.soffid.iam.api.Host targetVO) {
        super.toHost(sourceEntity, targetVO);
        toMaquinaCustom(sourceEntity, targetVO);
    }

    private void toMaquinaCustom(com.soffid.iam.model.HostEntity sourceEntity, com.soffid.iam.api.Host targetVO) {
        NetworkEntity xarxa = sourceEntity.getNetwork();
        if (xarxa != null) {
            targetVO.setNetworkCode(sourceEntity.getNetwork().getName());
        } else {
            targetVO.setNetworkCode(null);
        }
        targetVO.setOffice(new Boolean(sourceEntity.getFolders().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setMail(new Boolean(sourceEntity.getMail().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setPrintersServer(new Boolean(sourceEntity.getPrintersServer().compareTo("S") == 0)); //$NON-NLS-1$

        List<String> alias = new LinkedList<>();
        for (HostAliasEntity ha: sourceEntity.getHostAlias()) {
        	alias.add(ha.getAlias());
        }
        Collections.sort(alias);
        targetVO.setHostAlias(alias);
        if (sourceEntity.getLastSeen() == null)
            targetVO.setLastSeen(null);
        else
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceEntity.getLastSeen());
            targetVO.setLastSeen(cal);
        }
        targetVO.setDynamicIp(sourceEntity.getDynamicIP());
        
        targetVO.setOs(sourceEntity.getOperatingSystem() == null ? null : sourceEntity.getOperatingSystem().getName());
		targetVO.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = targetVO.getAttributes();
		for (HostAttributeEntity att : sourceEntity.getAttributes()) {
			if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
			{
				LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getMetadata().getName());
				if (r == null)
				{
					r = new LinkedList<Object>();
					attributes.put(att.getMetadata().getName(), r);
				}
				if (att.getObjectValue() != null)
					r.add(att.getObjectValue());
			}
			else
			{
				attributes.put(att.getMetadata().getName(),att.getObjectValue());
			}
		}
		for (Object o: attributes.values())
		{
			if (o != null && o instanceof List) Collections.sort((List) o);
		}
    }
    

    /**
     * @see es.caib.seycon.ng.model.MaquinaEntityDao#toMaquina(es.caib.seycon.ng.model.MaquinaEntity)
     */
    public com.soffid.iam.api.Host toHost(final com.soffid.iam.model.HostEntity entity) {
        Host maquina = super.toHost(entity);
        toMaquinaCustom(entity, maquina);
        return maquina;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.HostEntity loadMaquinaEntityFromMaquina(com.soffid.iam.api.Host maquina) {
        com.soffid.iam.model.HostEntity maquinaEntity = null;
        if (maquina.getId() != null) {
            maquinaEntity = load(maquina.getId());
        }
        if (maquinaEntity == null) {
            maquinaEntity = newHostEntity();
        }
        return maquinaEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.MaquinaEntityDao#maquinaToEntity(es.caib.seycon.ng.comu.Maquina)
     */
    public com.soffid.iam.model.HostEntity hostToEntity(com.soffid.iam.api.Host maquina) {
        com.soffid.iam.model.HostEntity entity = this.loadMaquinaEntityFromMaquina(maquina);
        this.hostToEntity(maquina, entity, true);
        return entity;
    }

    private void maquinaToEntityCustom(com.soffid.iam.api.Host sourceVO, com.soffid.iam.model.HostEntity targetEntity) {
        if (sourceVO.getOffice() != null) {
            targetEntity.setFolders(sourceVO.getOffice().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setFolders("N"); //$NON-NLS-1$
        }

        if (sourceVO.getMail() != null) {
            targetEntity.setMail(sourceVO.getMail().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setMail("N"); //$NON-NLS-1$
        }

        if (sourceVO.getPrintersServer() != null) {
            targetEntity.setPrintersServer(sourceVO.getPrintersServer().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setPrintersServer("N"); //$NON-NLS-1$
        }

        if (sourceVO.getLastSeen() == null)
            targetEntity.setLastSeen( null );
        else
            targetEntity.setLastSeen(sourceVO.getLastSeen().getTime());
        if (sourceVO.getDynamicIp() == null)
            targetEntity.setDynamicIP(new Boolean (false));
        else
        	targetEntity.setDynamicIP(sourceVO.getDynamicIp());

        String codiXarxa = sourceVO.getNetworkCode();
        if (codiXarxa != null && codiXarxa.trim().compareTo("") != 0) { //$NON-NLS-1$
            NetworkEntity xarxaEntity = getNetworkEntityDao().findByName(codiXarxa);
            if (xarxaEntity != null) {
                /* comprova que la ip és compatible amb la xarxa */
                Network xarxa = this.getNetworkEntityDao().toNetwork(xarxaEntity);
                if (maquinaCompatibleAmbXarxa(sourceVO.getIp(), xarxa.getIp(), xarxa.getMask())) {
                    targetEntity.setNetwork(xarxaEntity);
                } else {
                    throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.0"), xarxa.getMask(), xarxa.getIp(), sourceVO.getIp())); //$NON-NLS-1$
                }
            } else {
				throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.1"), codiXarxa)); //$NON-NLS-1$
            }
        } else {
            targetEntity.setNetwork(null);
        }
        
		if (sourceVO.getOs() != null)
		{
			com.soffid.iam.model.OsTypeEntity os = getOsTypeEntityDao().findOSTypeByName(sourceVO.getOs());
			if (os == null)
				throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.InvalidOS"), sourceVO.getOs())); //$NON-NLS-1$
			targetEntity.setOperatingSystem(os);
		}
    }

    private boolean adrecaCorrecta(String adreca) {
        if (adreca != null && adreca.trim().compareTo("") != 0) { //$NON-NLS-1$
            String[] adreces = adreca.split("\\."); //$NON-NLS-1$
            if (adreces.length == 4) {
                boolean correcte = true;
                for (int i = 0; i < 4 && correcte; i++) {
                    try {
                        correcte = Integer.parseInt(adreces[i]) <= 255;
                    } catch (Exception e) {
                        return false;
                    }
                }
                return correcte;
            }
        }
        return false;
    }

    private boolean maquinaCompatibleAmbXarxa(String adrecaMaquina,
            String adrecaXarxa, String mascaraXarxa) {
        if (adrecaMaquina == null || adrecaMaquina.trim().compareTo("") == 0) { //$NON-NLS-1$
            return true;
        }
        if (adrecaXarxa.compareTo("0.0.0.0") == 0 //$NON-NLS-1$
                && mascaraXarxa.compareTo("0.0.0.0") == 0) { //$NON-NLS-1$
            return true;
        }

        if (!adrecaCorrecta(mascaraXarxa)) {
            return false;
        }
        String[] mascaresXarxa = mascaraXarxa.split("\\."); //$NON-NLS-1$
        if (!adrecaCorrecta(adrecaXarxa)) {
            return false;
        }
        String[] adrecaXarxes = adrecaXarxa.split("\\."); //$NON-NLS-1$
        if (!adrecaCorrecta(adrecaMaquina)) {
            return false;
        }
        // Comparamos que la red de la máquina NO SEA IGUAL a la de la Xarxa
        if (adrecaMaquina.equals(adrecaXarxa))
            return false;

        String[] adrecaMaquines = adrecaMaquina.split("\\."); //$NON-NLS-1$
        boolean compatible = true;
        for (int i = 0; i < 4 && compatible; i++) {
            int currentMascaraXarxa = Integer.parseInt(mascaresXarxa[i]);
            int currentAdrecaMaquina = Integer.parseInt(adrecaMaquines[i]);
            int currentAdrecaXarxa = Integer.parseInt(adrecaXarxes[i]);
            compatible = (currentMascaraXarxa & currentAdrecaXarxa) == (currentMascaraXarxa & currentAdrecaMaquina);
        }
        return compatible;
    }

    /**
     * @see es.caib.seycon.ng.model.MaquinaEntityDao#maquinaToEntity(es.caib.seycon.ng.comu.Maquina,
     *      es.caib.seycon.ng.model.MaquinaEntity)
     */
    public void hostToEntity(com.soffid.iam.api.Host sourceVO, com.soffid.iam.model.HostEntity targetEntity, boolean copyIfNull) {
        super.hostToEntity(sourceVO, targetEntity, copyIfNull);
        maquinaToEntityCustom(sourceVO, targetEntity);
    }

    private void auditarMaquina(String accio, String nomMaquina) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setHost(nomMaquina);
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_MAQUIN"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void remove(com.soffid.iam.model.HostEntity maquinaEntity) throws RuntimeException {
        try {
            String nomMaquina = maquinaEntity.getName();
            getHostEntryPointEntityDao().remove(maquinaEntity.getEntryPoints());
            getHostAliasEntityDao().remove(maquinaEntity.getHostAlias());
            getHostAdminEntityDao().remove(maquinaEntity.getAdministratorAuthorizationAccess());
            getHostPortEntityDao().remove(maquinaEntity.getPorts());
            getHostServiceEntityDao().remove(maquinaEntity.getServices());
            getHostSystemEntityDao().remove(maquinaEntity.getSystems());
            getHostAttributeEntityDao().remove(new LinkedList<>( maquinaEntity.getAttributes() ));
            
            getSession().createQuery("update com.soffid.iam.model.IssueHostEntity "
            		+ "set host = null "
            		+ "where host.id = :id")
            	.setLong("id", maquinaEntity.getId())
            	.executeUpdate();
            
            super.remove(maquinaEntity);
            auditarMaquina("D", nomMaquina); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.2"), maquinaEntity.getName(), message), e); //$NON-NLS-1$
		}
    }

    public void create(com.soffid.iam.model.HostEntity maquina) throws RuntimeException {
        try {
        	maquina.setCreated(new Date());
            super.create(maquina);
            getSession(false).flush();
            auditarMaquina("C", maquina.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.3"), maquina.getName(), message), e); //$NON-NLS-1$
        }
    }

    private boolean areDifferent (Object s1, Object s2) {
    	if (s1 == null && s2 == null)
    		return false;
    	else if (s1 == null || s2 == null)
    		return true;
    	else
    		return !s1.equals(s2);
    }
    public void update(com.soffid.iam.model.HostEntity maquina) throws RuntimeException {
        try {
            super.update(maquina);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.4"), maquina.getName(), message), e); //$NON-NLS-1$
        }
    }

    public String[] getTasks(String nomMaquina) {
        LinkedList<String> lista = new LinkedList<String>();
    	for (TaskEntity t : getTaskEntityDao().query(
    			"select tasca from com.soffid.iam.model.TaskEntity as tasca " //$NON-NLS-1$
    			+ "where tasca.host=:maquina and tasca.tenant.id = :tenantId",  //$NON-NLS-1$
    			new Parameter[]{
    					new Parameter("maquina", nomMaquina), //$NON-NLS-1$
    					new Parameter("tenantId", Security.getCurrentTenantId()) //$NON-NLS-1$
    			})) {
            String transaccion = t.getTransaction();
            Timestamp datatime = t.getDate();
            Date data = new Date();
            data.setTime(datatime.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
            String dataString = dateFormat.format(data);
            String missatge = t.getMessage();
            String rol = t.getRole() + "@" + t.getDb(); //$NON-NLS-1$
            StringBuffer agentsPendents = new StringBuffer();
            for (com.soffid.iam.model.TaskLogEntity tl : t.getLogs()) {
                if ("N".equals(tl.getCompleted())) { //$NON-NLS-1$
                    if (agentsPendents.length() > 0) agentsPendents.append(", "); //$NON-NLS-1$
                    agentsPendents.append(tl.getSystem().getName());
                }
            }
            lista.add(transaccion + " # " + dataString + " # " + missatge + " # " + rol + " # " + agentsPendents); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
        return (String[]) lista.toArray(new String[lista.size()]);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof HostEntity) {
                HostEntity entity = (HostEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof HostEntity) {
                HostEntity entity = (HostEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof HostEntity) {
                HostEntity entity = (HostEntity) obj;
                this.remove(entity);
            }
        }
    }

}
