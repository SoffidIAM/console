// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.MaquinaEntity
 */
public class MaquinaEntityDaoImpl extends
        es.caib.seycon.ng.model.MaquinaEntityDaoBase {

    public void toMaquina(es.caib.seycon.ng.model.MaquinaEntity sourceEntity,
            es.caib.seycon.ng.comu.Maquina targetVO) {
        super.toMaquina(sourceEntity, targetVO);
        toMaquinaCustom(sourceEntity, targetVO);
    }

    private void toMaquinaCustom(
            es.caib.seycon.ng.model.MaquinaEntity sourceEntity,
            es.caib.seycon.ng.comu.Maquina targetVO) {
        XarxaEntity xarxa = sourceEntity.getXarxa();
        if (xarxa != null) {
            targetVO.setCodiXarxa(sourceEntity.getXarxa().getCodi());
        } else {
            targetVO.setCodiXarxa(null);
        }
        targetVO.setOfimatica(new Boolean(sourceEntity.getOfimatica()
                .compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setCorreu(new Boolean(
                sourceEntity.getCorreu().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setServidorImpressores(new Boolean(sourceEntity
                .getServidorImpressores().compareTo("S") == 0)); //$NON-NLS-1$

        String alies = ""; //$NON-NLS-1$
        Collection c_alies = sourceEntity.getAliasMaquina();
        for (Iterator it = c_alies.iterator(); it.hasNext();) {
            AliasMaquinaEntity al = (AliasMaquinaEntity) it.next();
            alies += al.getAlias() + " "; //$NON-NLS-1$
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
    public es.caib.seycon.ng.comu.Maquina toMaquina(
            final es.caib.seycon.ng.model.MaquinaEntity entity) {
        Maquina maquina = super.toMaquina(entity);
        toMaquinaCustom(entity, maquina);
        return maquina;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.MaquinaEntity loadMaquinaEntityFromMaquina(
            es.caib.seycon.ng.comu.Maquina maquina) {
        es.caib.seycon.ng.model.MaquinaEntity maquinaEntity = null;
        if (maquina.getId() != null) {
            maquinaEntity = load(maquina.getId());
        }
        if (maquinaEntity == null) {
            maquinaEntity = newMaquinaEntity();
        }
        return maquinaEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.MaquinaEntityDao#maquinaToEntity(es.caib.seycon.ng.comu.Maquina)
     */
    public es.caib.seycon.ng.model.MaquinaEntity maquinaToEntity(
            es.caib.seycon.ng.comu.Maquina maquina) {
        es.caib.seycon.ng.model.MaquinaEntity entity = this
                .loadMaquinaEntityFromMaquina(maquina);
        this.maquinaToEntity(maquina, entity, true);
        return entity;
    }

    private void maquinaToEntityCustom(es.caib.seycon.ng.comu.Maquina sourceVO,
            es.caib.seycon.ng.model.MaquinaEntity targetEntity) {
        if (sourceVO.getOfimatica() != null) {
            targetEntity
                    .setOfimatica(sourceVO.getOfimatica().booleanValue() ? "S" //$NON-NLS-1$
                            : "N"); //$NON-NLS-1$
        } else {
            targetEntity.setOfimatica("N"); //$NON-NLS-1$
        }

        if (sourceVO.getCorreu() != null) {
            targetEntity.setCorreu(sourceVO.getCorreu().booleanValue() ? "S" //$NON-NLS-1$
                    : "N"); //$NON-NLS-1$
        } else {
            targetEntity.setCorreu("N"); //$NON-NLS-1$
        }

        if (sourceVO.getServidorImpressores() != null) {
            targetEntity.setServidorImpressores(sourceVO
                    .getServidorImpressores().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setServidorImpressores("N"); //$NON-NLS-1$
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
            XarxaEntity xarxaEntity = getXarxaEntityDao().findByCodi(codiXarxa);
            if (xarxaEntity != null) {
                /* comprova que la ip és compatible amb la xarxa */
                Xarxa xarxa = this.getXarxaEntityDao().toXarxa(xarxaEntity);
                if (maquinaCompatibleAmbXarxa(sourceVO.getAdreca(),
                        xarxa.getAdreca(), xarxa.getMascara())) {
                    targetEntity.setXarxa(xarxaEntity);
                } else {
                    throw new SeyconException(String.format(Messages.getString("MaquinaEntityDaoImpl.0"), //$NON-NLS-1$
                            xarxa.getMascara(),
                            xarxa.getAdreca(),
                            sourceVO.getAdreca()));
                }
            } else {
				throw new SeyconException(String.format(Messages.getString("MaquinaEntityDaoImpl.1"), codiXarxa)); //$NON-NLS-1$
            }
        } else {
            targetEntity.setXarxa(null);
        }
        
		if (sourceVO.getSistemaOperatiu() != null)
		{
			OsTypeEntity os = getOsTypeEntityDao().findOSTypeByName(sourceVO.getSistemaOperatiu());
			if (os == null)
				throw new SeyconException (String.format(Messages.getString("MaquinaEntityDaoImpl.InvalidOS"), //$NON-NLS-1$
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
    public void maquinaToEntity(es.caib.seycon.ng.comu.Maquina sourceVO,
            es.caib.seycon.ng.model.MaquinaEntity targetEntity,
            boolean copyIfNull) {
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
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void remove(es.caib.seycon.ng.model.MaquinaEntity maquinaEntity)
            throws RuntimeException {
        try {
            String nomMaquina = maquinaEntity.getNom();
            super.remove(maquinaEntity);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_HOST);
            tasque.setMaquin(maquinaEntity.getNom());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();
            auditarMaquina("D", nomMaquina); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("MaquinaEntityDaoImpl.2"),  //$NON-NLS-1$
					maquinaEntity.getNom(), 
					message));
		}
    }

    public void create(es.caib.seycon.ng.model.MaquinaEntity maquina)
            throws RuntimeException {
        try {
            super.create(maquina);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_HOST);
            tasque.setMaquin(maquina.getNom());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();
            auditarMaquina("C", maquina.getNom()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("MaquinaEntityDaoImpl.3"),  //$NON-NLS-1$
					maquina.getNom(), 
					message));
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
    public void update(es.caib.seycon.ng.model.MaquinaEntity maquina)
            throws RuntimeException {
        try {
            MaquinaEntity oldMaquina = load(maquina.getId());
            super.update(maquina);
            if (areDifferent(oldMaquina.getAdreca(), maquina.getAdreca()) ||
           		areDifferent(oldMaquina.getCorreu(), maquina.getCorreu()) ||
           		areDifferent(oldMaquina.getDescripcio(), maquina.getDescripcio()) ||
                areDifferent(oldMaquina.getOfimatica(), maquina.getOfimatica()) ||
                areDifferent(oldMaquina.getMac(), maquina.getMac()) ||
                areDifferent(oldMaquina.getDhcp(), maquina.getDhcp()) ||
                areDifferent(oldMaquina.getSerialNumber(), maquina.getSerialNumber()) ||
                areDifferent(oldMaquina.getServidorImpressores(), maquina.getServidorImpressores()) ||
                areDifferent(oldMaquina.getOperatingSystem(), maquina.getOperatingSystem()) ||
                areDifferent(oldMaquina.getDeleted(), maquina.getDeleted()) ||
                areDifferent(oldMaquina.getDynamicIP(), maquina.getDynamicIP()) ||
                areDifferent(oldMaquina.getNom(), maquina.getNom())) 
            {
                TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_HOST);
            	tasque.setMaquin(oldMaquina.getNom());
            	getTasqueEntityDao().create(tasque);
                auditarMaquina("U", maquina.getNom()); //$NON-NLS-1$
            }
            if (! oldMaquina.getNom().equals(maquina.getNom())) 
            {
                TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_HOST);
                tasque.setMaquin(maquina.getNom());
                getTasqueEntityDao().create(tasque);
            }
            getSession().flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("MaquinaEntityDaoImpl.4"),  //$NON-NLS-1$
					maquina.getNom(), 
					message));
        }
    }

    public String[] getTasques(String nomMaquina) {
        LinkedList<String> lista = new LinkedList<String>();
    	for (TasqueEntity t: getTasqueEntityDao().query("select tasca from es.caib.seycon.ng.model.TasqueEntity as tasca where tasca.maquin=:maquina", new Parameter[]{ //$NON-NLS-1$
            			new Parameter("maquina", nomMaquina) //$NON-NLS-1$
            				
            }))
            {
    		String transaccion = t.getTransa(); //$NON-NLS-1$
    		Timestamp datatime = t.getData();// Obtenemos //$NON-NLS-1$
            								// fecha y hora
    		Date data = new Date();
            data.setTime(datatime.getTime()); // Para formatearla
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
            String dataString = dateFormat.format(data);
            String missatge = t.getMissat(); //$NON-NLS-1$
            String rol = t.getRole() + "@" //$NON-NLS-1$ //$NON-NLS-2$
                            + t.getBd(); //$NON-NLS-1$
            // Obtenemos información de los agentes pendientes
            StringBuffer agentsPendents = new StringBuffer();
            for (TaskLogEntity tl: t.getLogs())
            {
            	if ("N".equals(tl.getComplet())) //$NON-NLS-1$
            	{
                	if (agentsPendents.length() > 0)
                		agentsPendents.append (", "); //$NON-NLS-1$
                	agentsPendents.append(tl.getDispatcher().getCodi());
            	}
            }
            lista.add(transaccion + " # " + dataString + " # " + missatge //$NON-NLS-1$ //$NON-NLS-2$
                            + " # " + rol + " # " + agentsPendents); //$NON-NLS-1$ //$NON-NLS-2$
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
    public List<MaquinaEntity> find(final java.lang.String queryString,
            final es.caib.seycon.ng.model.Parameter[] parameters) {
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
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof MaquinaEntity) {
                    MaquinaEntity entity = (MaquinaEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof MaquinaEntity) {
                    MaquinaEntity entity = (MaquinaEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof MaquinaEntity) {
                    MaquinaEntity entity = (MaquinaEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

}
