// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.HostAliasEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.TaskEntity;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @see es.caib.seycon.ng.model.MaquinaEntity
 */
public class HostEntityDaoImpl extends
        com.soffid.iam.model.HostEntityDaoBase {

    public void toMaquina(com.soffid.iam.model.HostEntity sourceEntity, es.caib.seycon.ng.comu.Maquina targetVO) {
        super.toMaquina(sourceEntity, targetVO);
        toMaquinaCustom(sourceEntity, targetVO);
    }

    private void toMaquinaCustom(com.soffid.iam.model.HostEntity sourceEntity, es.caib.seycon.ng.comu.Maquina targetVO) {
        NetworkEntity xarxa = sourceEntity.getNetwork();
        if (xarxa != null) {
            targetVO.setCodiXarxa(sourceEntity.getNetwork().getName());
        } else {
            targetVO.setCodiXarxa(null);
        }
        targetVO.setOfimatica(new Boolean(sourceEntity.getFolders().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setCorreu(new Boolean(sourceEntity.getMail().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setServidorImpressores(new Boolean(sourceEntity.getPrintersServer().compareTo("S") == 0)); //$NON-NLS-1$

        String alies = ""; //$NON-NLS-1$
        Collection c_alies = sourceEntity.getHostAlias();
        for (Iterator it = c_alies.iterator(); it.hasNext(); ) {
            HostAliasEntity al = (HostAliasEntity) it.next();
            alies += al.getAlias() + " ";
        }
        targetVO.setAliasMaquina(alies.trim()); // quitamos espacios "extra"
        if (sourceEntity.getLastSeen() == null)
            targetVO.setLastSeen(null);
        else
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceEntity.getLastSeen());
            targetVO.setLastSeen(cal);
        }
        targetVO.setDynamicIp(sourceEntity.getDynamicIP());
        
        targetVO.setSistemaOperatiu(sourceEntity.getOperatingSystem() == null ?
			null : sourceEntity.getOperatingSystem().getName());
    }
    

    /**
     * @see es.caib.seycon.ng.model.MaquinaEntityDao#toMaquina(es.caib.seycon.ng.model.MaquinaEntity)
     */
    public es.caib.seycon.ng.comu.Maquina toMaquina(final com.soffid.iam.model.HostEntity entity) {
        Maquina maquina = super.toMaquina(entity);
        toMaquinaCustom(entity, maquina);
        return maquina;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.HostEntity loadMaquinaEntityFromMaquina(es.caib.seycon.ng.comu.Maquina maquina) {
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
    public com.soffid.iam.model.HostEntity maquinaToEntity(es.caib.seycon.ng.comu.Maquina maquina) {
        com.soffid.iam.model.HostEntity entity = this.loadMaquinaEntityFromMaquina(maquina);
        this.maquinaToEntity(maquina, entity, true);
        return entity;
    }

    private void maquinaToEntityCustom(es.caib.seycon.ng.comu.Maquina sourceVO, com.soffid.iam.model.HostEntity targetEntity) {
        if (sourceVO.getOfimatica() != null) {
            targetEntity.setFolders(sourceVO.getOfimatica().booleanValue() ? "S" : "N"); //$NON-NLS-1$
        } else {
            targetEntity.setFolders("N"); //$NON-NLS-1$
        }

        if (sourceVO.getCorreu() != null) {
            targetEntity.setMail(sourceVO.getCorreu().booleanValue() ? "S" : "N"); //$NON-NLS-1$
        } else {
            targetEntity.setMail("N"); //$NON-NLS-1$
        }

        if (sourceVO.getServidorImpressores() != null) {
            targetEntity.setPrintersServer(sourceVO.getServidorImpressores().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
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

        String codiXarxa = sourceVO.getCodiXarxa();
        if (codiXarxa != null && codiXarxa.trim().compareTo("") != 0) { //$NON-NLS-1$
            NetworkEntity xarxaEntity = getNetworkEntityDao().findByName(codiXarxa);
            if (xarxaEntity != null) {
                /* comprova que la ip és compatible amb la xarxa */
                Xarxa xarxa = this.getNetworkEntityDao().toXarxa(xarxaEntity);
                if (maquinaCompatibleAmbXarxa(sourceVO.getAdreca(),
                        xarxa.getAdreca(), xarxa.getMascara())) {
                    targetEntity.setNetwork(xarxaEntity);
                } else {
                    throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.0"), //$NON-NLS-1$
                            xarxa.getMascara(),
                            xarxa.getAdreca(),
                            sourceVO.getAdreca()));
                }
            } else {
				throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.1"), codiXarxa)); //$NON-NLS-1$
            }
        } else {
            targetEntity.setNetwork(null);
        }
        
		if (sourceVO.getSistemaOperatiu() != null)
		{
			com.soffid.iam.model.OsTypeEntity os = getOsTypeEntityDao().findOSTypeByName(sourceVO.getSistemaOperatiu());
			if (os == null)
				throw new SeyconException (String.format(Messages.getString("HostEntityDaoImpl.InvalidOS"), //$NON-NLS-1$
								sourceVO.getSistemaOperatiu()));
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
    public void maquinaToEntity(es.caib.seycon.ng.comu.Maquina sourceVO, com.soffid.iam.model.HostEntity targetEntity, boolean copyIfNull) {
        super.maquinaToEntity(sourceVO, targetEntity, copyIfNull);
        maquinaToEntityCustom(sourceVO, targetEntity);
    }

    private void auditarMaquina(String accio, String nomMaquina) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setMaquina(nomMaquina);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_MAQUIN"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void remove(com.soffid.iam.model.HostEntity maquinaEntity) throws RuntimeException {
        try {
            String nomMaquina = maquinaEntity.getName();
            super.remove(maquinaEntity);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_HOST);
            tasque.setHost(maquinaEntity.getName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarMaquina("D", nomMaquina); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.2"), maquinaEntity.getName(), message));
		}
    }

    public void create(com.soffid.iam.model.HostEntity maquina) throws RuntimeException {
        try {
            super.create(maquina);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_HOST);
            tasque.setHost(maquina.getName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarMaquina("C", maquina.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.3"), maquina.getName(), message));
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
            HostEntity oldMaquina = load(maquina.getId());
            super.update(maquina);
            if (areDifferent(oldMaquina.getHostIP(), maquina.getHostIP()) || areDifferent(oldMaquina.getMail(), maquina.getMail()) || areDifferent(oldMaquina.getDescription(), maquina.getDescription()) || areDifferent(oldMaquina.getFolders(), maquina.getFolders()) || areDifferent(oldMaquina.getMac(), maquina.getMac()) || areDifferent(oldMaquina.getDhcp(), maquina.getDhcp()) || areDifferent(oldMaquina.getSerialNumber(), maquina.getSerialNumber()) || areDifferent(oldMaquina.getPrintersServer(), maquina.getPrintersServer()) || areDifferent(oldMaquina.getOperatingSystem(), maquina.getOperatingSystem()) || areDifferent(oldMaquina.getDeleted(), maquina.getDeleted()) || areDifferent(oldMaquina.getDynamicIP(), maquina.getDynamicIP()) || areDifferent(oldMaquina.getName(), maquina.getName())) 
            {
                TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_HOST);
            	tasque.setHost(oldMaquina.getName());
            	getTaskEntityDao().create(tasque);
                auditarMaquina("U", maquina.getName()); //$NON-NLS-1$
            }
            if (!oldMaquina.getName().equals(maquina.getName())) 
            {
                TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_HOST);
                tasque.setHost(maquina.getName());
                getTaskEntityDao().create(tasque);
            }
            getSession().flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostEntityDaoImpl.4"), maquina.getName(), message));
        }
    }

    public String[] getTasks(String nomMaquina) {
        LinkedList<String> lista = new LinkedList<String>();
    	for (TaskEntity t : getTaskEntityDao().query(
    			"select tasca from com.soffid.iam.model.TaskEntity as tasca "
    			+ "where tasca.host=:maquina", 
    			new Parameter[]{
    					new Parameter("maquina", nomMaquina
    			)})) {
            String transaccion = t.getTransaction();
            Timestamp datatime = t.getDate();
            Date data = new Date();
            data.setTime(datatime.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
            String dataString = dateFormat.format(data);
            String missatge = t.getMessage();
            String rol = t.getRole() + "@" + t.getDb();
            StringBuffer agentsPendents = new StringBuffer();
            for (com.soffid.iam.model.TaskLogEntity tl : t.getLogs()) {
                if ("N".equals(tl.getCompleted())) {
                    if (agentsPendents.length() > 0) agentsPendents.append(", ");
                    agentsPendents.append(tl.getSystem().getName());
                }
            }
            lista.add(transaccion + " # " + dataString + " # " + missatge + " # " + rol + " # " + agentsPendents);
        }
        return (String[]) lista.toArray(new String[lista.size()]);
    }

    /*
     * The finder !! in the sun and in the rain, it's quite the same, it's never
     * gonna change i'll be looking for Jane (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.model.MaquinaEntityDaoBase#find(int,
     * java.lang.String, es.caib.seycon.ng.model.Parameter[])
     */
    public List<HostEntity> find(final java.lang.String queryString, final Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this,
                    queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
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
