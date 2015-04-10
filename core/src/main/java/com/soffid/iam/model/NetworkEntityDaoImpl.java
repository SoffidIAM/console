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
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.TaskEntity;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.IPAddress;
import es.caib.seycon.ng.utils.InvalidIPException;
import es.caib.seycon.ng.utils.Security;

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

/**
 * @see es.caib.seycon.ng.model.XarxaEntity
 */
public class NetworkEntityDaoImpl extends com.soffid.iam.model.NetworkEntityDaoBase {

    private void auditarXarxa(String accio, String codiXarxa) {
        String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setXarxa(codiXarxa);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_XARXA"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
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
            throw new SeyconException(String.format(Messages.getString("NetworkEntityDaoImpl.0"), xarxa.getName(), message));
        }
    }

    public void remove(com.soffid.iam.model.NetworkEntity xarxa) throws RuntimeException {
        try {
            String codiXarxa = xarxa.getName();
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
            throw new SeyconException(String.format(Messages.getString("NetworkEntityDaoImpl.1"), xarxa.getName(), message)); //$NON-NLS-1$
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
            throw new SeyconException(String.format(Messages.getString("NetworkEntityDaoImpl.2"), xarxa.getName(), message)); //$NON-NLS-1$
        }
    }

    public void toXarxa(com.soffid.iam.model.NetworkEntity sourceEntity, es.caib.seycon.ng.comu.Xarxa targetVO) {
        super.toXarxa(sourceEntity, targetVO);
        toXarxaCustom(sourceEntity, targetVO);
    }

    public void toXarxaCustom(com.soffid.iam.model.NetworkEntity sourceEntity, es.caib.seycon.ng.comu.Xarxa targetVO) {
        targetVO.setNormalitzada(new Boolean(sourceEntity.getNormalized().compareTo("S") == 0)); //$NON-NLS-1$
        String adrecaXarxa = targetVO.getAdreca();
        StringTokenizer tokenizer = new StringTokenizer(adrecaXarxa, ".", false); //$NON-NLS-1$
        List partsAdrecaXarxaList = new LinkedList();
        while (tokenizer.hasMoreTokens()) {
            partsAdrecaXarxaList.add((String) tokenizer.nextToken());
        }
        String partsAdrecaXarxa[] = (String[]) partsAdrecaXarxaList.toArray(new String[0]);

        String adrecaBenFormada = ""; //$NON-NLS-1$
        for (int i = 0; i < partsAdrecaXarxa.length; i++) {
            adrecaBenFormada = adrecaBenFormada + partsAdrecaXarxa[i];
            if (i < 3) {
                adrecaBenFormada = adrecaBenFormada + "."; //$NON-NLS-1$
            }
        }
        // posar zeros
        for (int i = partsAdrecaXarxa.length; i < 4; i++) {
            adrecaBenFormada = adrecaBenFormada + "0"; //$NON-NLS-1$
            if (i < 3) {
                adrecaBenFormada = adrecaBenFormada + "."; //$NON-NLS-1$
            }
        }
        targetVO.setAdreca(adrecaBenFormada);
        targetVO.setDhcpSupport(sourceEntity.isDchpSupport());
    }

    /**
     * @see es.caib.seycon.ng.model.XarxaEntityDao#toXarxa(es.caib.seycon.ng.model.XarxaEntity)
     */
    public es.caib.seycon.ng.comu.Xarxa toXarxa(final com.soffid.iam.model.NetworkEntity entity) {
        Xarxa xarxa = super.toXarxa(entity);
        toXarxaCustom(entity, xarxa);
        return xarxa;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.NetworkEntity loadXarxaEntityFromXarxa(es.caib.seycon.ng.comu.Xarxa xarxa) {
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
    public com.soffid.iam.model.NetworkEntity xarxaToEntity(es.caib.seycon.ng.comu.Xarxa xarxa) {
        com.soffid.iam.model.NetworkEntity entity = this.loadXarxaEntityFromXarxa(xarxa);
        this.xarxaToEntity(xarxa, entity, true);
        return entity;
    }

    private boolean maquinaCompatibleAmbXarxa(String adrecaMaquina, String adrecaXarxa,
            String mascaraXarxa) {
        if (adrecaXarxa.compareTo("0.0.0.0") == 0 && mascaraXarxa.compareTo("0.0.0.0") == 0) { //$NON-NLS-1$ //$NON-NLS-2$
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

    private void xarxaToEntityCustom(es.caib.seycon.ng.comu.Xarxa sourceVO, com.soffid.iam.model.NetworkEntity targetEntity) {

        if (sourceVO.getNormalitzada() != null) {
            targetEntity.setNormalized(sourceVO.getNormalitzada().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setNormalized("N"); //$NON-NLS-1$
        }
        targetEntity.setDchpSupport(sourceVO.isDhcpSupport());
        targetEntity.setAddress(sourceVO.getAdreca().trim());
        
        // Check network mask
        if (sourceVO.getMascara() != null)
        {
        	targetEntity.setMask(sourceVO.getMascara().trim());
        }
        
        else
        {
        	throw new SeyconException(Messages.getString("NetworkEntityDaoImpl.4")); //$NON-NLS-1$
        }
        
        if (!adrecaCompatibleAmbXarxa(sourceVO.getAdreca(), sourceVO.getMascara())) {
            throw new SeyconException(String.format(
                    Messages.getString("NetworkEntityDaoImpl.3"), //$NON-NLS-1$
                    sourceVO.getAdreca(), sourceVO.getMascara()));
        }
    }

    private boolean adrecaCorrecta(String adreca) {
        String[] adreces = adreca.split("\\."); //$NON-NLS-1$
        if (adreces.length == 4) {
            boolean correcte = true;
            for (int i = 0; adreces.length < 4 && correcte; i++) {
                try {
                    correcte = Integer.parseInt(adreces[i]) <= 255;
                } catch (Exception e) {
                    return false;
                }
            }
            return correcte;
        }
        return false;
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

    private boolean adrecaCompatibleAmbXarxa(String adrecaXarxa, String mascaraXarxa) {
        if (!adrecaCorrecta(mascaraXarxa)) {
            return false;
        }
        String[] mascaresXarxa = mascaraXarxa.split("\\."); //$NON-NLS-1$
        if (!adrecaCorrecta(adrecaXarxa)) {
            return false;
        }
        String[] adrecaXarxes = adrecaXarxa.split("\\."); //$NON-NLS-1$
        try {
            String mascaraBinari = toBinaryAddress(mascaresXarxa);
            String adrecaBinari = toBinaryAddress(adrecaXarxes);
            boolean compatible = true;
            for (int i = 0; i < mascaraBinari.length() && compatible; i++) {
                if (mascaraBinari.charAt(i) == '0') {
                    compatible = adrecaBinari.charAt(i) == '0';
                }
            }
            return compatible;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @see es.caib.seycon.ng.model.XarxaEntityDao#xarxaToEntity(es.caib.seycon.ng.comu.Xarxa,
     *      es.caib.seycon.ng.model.XarxaEntity)
     */
    public void xarxaToEntity(es.caib.seycon.ng.comu.Xarxa sourceVO, com.soffid.iam.model.NetworkEntity targetEntity, boolean copyIfNull) {
        super.xarxaToEntity(sourceVO, targetEntity, copyIfNull);
        xarxaToEntityCustom(sourceVO, targetEntity);
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
            	HostEntity maquina = getHostEntityDao().findByIP(ip2.toString());
                if (maquina == null)
                    return ip2.toString();
                ip2.incrementa();
            }
            return ipXarxa;
        } catch (Exception e) {
            throw new SeyconException(e.toString());
        }
    }

    /**
     * Calcula el n�mero de direcciones IP libres u ocupadas en la subred
     * 
     * @param ni
     *            datos de la surbed
     * @param buides
     *            true si debe calcular las IPs libres, false si debe calcular
     *            las ocupadas
     * @return n�mero de IPs libres u ocupades
     * @throws InternalErrorException
     *             error de configuraci�n del servidor o en los datos de la
     *             subred.
     * @throws UnknownNetworkException
     *             subred desconocida
     */
    private Long getIPsBuidesOcupades(String ipXarxa, String netmask, boolean buides) {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        Connection conn = null;
        long contador = 0;
        // Prepara el cursor
        // Prepara las IPs de la red y del host
        try 
        {
	        IPAddress ip = new IPAddress(ipXarxa, netmask);
	        IPAddress ip2 = new IPAddress(ipXarxa);
	        ip2.incrementa();
	        // Prueba cada una de las redes
	        while (ip.esHostValid(ip2)) {
	        	if ( getHostEntityDao().findByIP(ip2.toString()) == null)
	        	{
	                if (buides)
	                    contador++;
	        	}
	        	else
	        	{
	                if (!buides)
	                    contador++;
	        	}
	            ip2.incrementa();
	        }
	        return new Long(contador);
		} catch (InvalidIPException e) {
			throw new RuntimeException("Invalid IP", e);
		}
    }

    /**
     * Calcula el n�mero de direcciones IP ocupadas en la subred
     * 
     * @param ni
     *            datos de la surbed
     * @return n�mero de IPs ocupadas
     * @throws InternalErrorException
     *             error de configuraci�n del servidor o en los datos de la
     *             subred.
     * @throws UnknownNetworkException
     *             subred desconocida
     */
    public Long getVoidIPs(String ipXarxa, String netmask) {
        return getIPsBuidesOcupades(ipXarxa, netmask, true);
    }

    /**
     * Calcula el n�mero de direcciones IP ocupadas en la subred
     * 
     * @param ni
     *            datos de la surbed
     * @return n�mero de IPs ocupadas
     * @throws InternalErrorException
     *             error de configuraci�n del servidor o en los datos de la
     *             subred.
     * @throws UnknownNetworkException
     *             subred desconocida
     */
    public Long getUsedIPs(String ipXarxa, String netmask) {

        return getIPsBuidesOcupades(ipXarxa, netmask, false);
    }

    public java.util.List find(final java.lang.String queryString,
            final Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this, queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
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
