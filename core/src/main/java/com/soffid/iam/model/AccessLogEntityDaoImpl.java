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

import com.soffid.iam.model.AccessLogEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.utils.ExceptionTranslator;

import es.caib.seycon.ng.exception.SeyconException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @see es.caib.seycon.ng.model.RegistreAccesEntity
 */
public class AccessLogEntityDaoImpl extends
        com.soffid.iam.model.AccessLogEntityDaoBase {

    public void create(com.soffid.iam.model.AccessLogEntity registreAcces) throws RuntimeException {
        try {
            super.create(registreAcces);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AccessLogEntityDaoImpl.errorCreating"), registreAcces.getSessionId(), message));
        }
    }

    public void remove(com.soffid.iam.model.AccessLogEntity registreAcces) throws RuntimeException {
        try {
            super.remove(registreAcces);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AccessLogEntityDaoImpl.errorDeleting"), registreAcces.getSessionId(), message));
        }
    }

    public void toAccessLog(com.soffid.iam.model.AccessLogEntity source, com.soffid.iam.api.AccessLog target) {
        super.toAccessLog(source, target);

        Calendar calendar = GregorianCalendar.getInstance();
        if (source.getStartDate() != null) {
            calendar.setTime(source.getStartDate());
            target.setStartDate(calendar);
        }

        if (source.getEndDate() != null) {
            calendar = GregorianCalendar.getInstance();
            calendar.setTime(source.getEndDate());
            target.setEndDate(calendar);
        }

        UserEntity usuari = source.getUser();
        if (usuari != null) {
            target.setUserCode(usuari.getUserName());
            // Afegim nom i llinatges de l'usuari
            String nom = usuari.getFirstName();
            nom = nom != null ? nom : ""; //$NON-NLS-1$
            String primerCognom = usuari.getLastName();
            primerCognom = primerCognom != null ? primerCognom + " " : ""; //$NON-NLS-1$ //$NON-NLS-2$
            String segonCognom = usuari.getMiddleName();
            segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
            target.setUserFullName(nom + " " + primerCognom + segonCognom); //$NON-NLS-1$
        }

        HostEntity servidor = source.getServer();
        if (servidor != null) {
            target.setServerName(servidor.getName());
        } else if (source.getHostName() != null )
        {
        	target.setServerName(source.getHostName());
        } else if (source.getHostAddress() != null)
        {
        	target.setServerName(source.getHostAddress());
        }

        HostEntity client = source.getClient();
        if (client != null) {
            target.setClientName(client.getName());
        } else if (source.getClientHostName() != null )
        {
        	target.setClientName(source.getClientHostName());
        } else if (source.getClientAddress() != null)
        {
        	target.setClientName(source.getClientAddress());
        }

        if (source.getAccessType() == null || "L".equals(source.getAccessType())) //$NON-NLS-1$
            target.setAccessType("logon"); //$NON-NLS-1$
        else if ("D".equals(source.getAccessType())) //$NON-NLS-1$
            target.setAccessType("logon denied"); //$NON-NLS-1$

        // Afegim informació del protocol d'accés
        if (source.getProtocol() != null) {
            target.setAccessProtocol(source.getProtocol().getName());
        }
    }

    /**
     * @see es.caib.seycon.ng.model.RegistreAccesEntityDao#toRegistreAcces(es.caib.seycon.ng.model.RegistreAccesEntity)
     */
    public com.soffid.iam.api.AccessLog toAccessLog(final com.soffid.iam.model.AccessLogEntity entity) {
        // @todo verify behavior of toRegistreAcces
        return super.toAccessLog(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.AccessLogEntity loadRegistreAccesEntityFromRegistreAcces(com.soffid.iam.api.AccessLog registreAcces) {
        throw new SeyconException(
                Messages.getString("AccessLogEntityDaoImpl.cannotUpdate")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.model.RegistreAccesEntityDao#registreAccesToEntity(es.caib.seycon.ng.comu.RegistreAcces)
     */
    public com.soffid.iam.model.AccessLogEntity accessLogToEntity(com.soffid.iam.api.AccessLog registreAcces) {
        // @todo verify behavior of registreAccesToEntity
        com.soffid.iam.model.AccessLogEntity entity = this.loadRegistreAccesEntityFromRegistreAcces(registreAcces);
        this.accessLogToEntity(registreAcces, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.RegistreAccesEntityDao#registreAccesToEntity(es.caib.seycon.ng.comu.RegistreAcces,
     *      es.caib.seycon.ng.model.RegistreAccesEntity)
     */
    public void accessLogToEntity(com.soffid.iam.api.AccessLog source, com.soffid.iam.model.AccessLogEntity target, boolean copyIfNull) {
        // @todo verify behavior of registreAccesToEntity
        super.accessLogToEntity(source, target, copyIfNull);
        // No conversion for target.dataInici (can't convert
        // source.getDataInici():java.util.Date to java.util.Date
        // No conversion for target.dataFi (can't convert
        // source.getDataFi():java.util.Date to java.util.Date
    }

    public List<AccessLogEntity> find(final java.lang.String queryString, final Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this,
                    queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    // Versió optimitzada per la consulta dels darrers n registres
    // d'accés de l'usuari des de la finestra d'usuaris
    // Alejandro Usero - 12/09/2011
    public List<AccessLogEntity> findDarrersRegistresByCodiUsuari(String codiUsuari, String numRegistres, String codiProtocolAcces) {

        int numRegs = -1;

        if (codiUsuari == null
                || (codiUsuari != null && "".equals(codiUsuari.trim())) //$NON-NLS-1$
                || codiProtocolAcces == null
                || (codiProtocolAcces != null && "".equals(codiProtocolAcces //$NON-NLS-1$
                        .trim()))) {
            throw new SeyconException(
                    Messages.getString("AccessLogEntityDaoImpl.missingData")); //$NON-NLS-1$
        }

        if (numRegistres != null && !"%".equals(numRegistres.trim())) { //$NON-NLS-1$
            try {
                numRegs = Integer.parseInt(numRegistres);
            } catch (Exception ex) {
                throw new SeyconException(
                        Messages.getString("AccessLogEntityDaoImpl.wrongFormat")); //$NON-NLS-1$
            }
        }
        if (numRegs >= 201) {
            throw new SeyconException(
                    Messages.getString("AccessLogEntityDaoImpl.tooManyRecords")); //$NON-NLS-1$
        }

        try {
            String queryString = "select registreAcces "
            		+ "from com.soffid.iam.model.AccessLogEntity registreAcces " //$NON-NLS-1$
                    + "left join registreAcces.user " //$NON-NLS-1$
                    + "left join registreAcces.protocol where  " //$NON-NLS-1$
                    + "(registreAcces.user is not null and registreAcces.user.name = :codiUsuari) and " //$NON-NLS-1$
                    + "(registreAcces.protocol is not null and registreAcces.protocol.name= :codiProtocolAcces) " //$NON-NLS-1$
                    + "order by registreAcces.dataInici desc "; //$NON-NLS-1$

            org.hibernate.Query queryObject = super.getSession(false)
                    .createQuery(queryString);
            queryObject.setParameter("codiUsuari", codiUsuari); //$NON-NLS-1$
            queryObject.setParameter("codiProtocolAcces", codiProtocolAcces); //$NON-NLS-1$
            // Probem el maxresults per restringir els resultats de la cerca:
            queryObject.setMaxResults(numRegs);
            java.util.List results = queryObject.list();
            return results;

        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    // Versió optimitzada per la consulta dels darrers n registres
    // d'accés a una màquina des de la finestra de màquines
    // Alejandro Usero - 12/09/2011
    public List<AccessLogEntity> findDarrersRegistresAccesMaquinaProtocol(java.lang.String nomServidor, String numRegistres, java.lang.String protocolAcces) {
        int numRegs = -1;
        if (nomServidor == null
                || (nomServidor != null && "".equals(nomServidor.trim())) //$NON-NLS-1$
                || protocolAcces == null
                || (protocolAcces != null && "".equals(protocolAcces.trim()))) { //$NON-NLS-1$
            throw new SeyconException(
                    Messages.getString("AccessLogEntityDaoImpl.missingServerAndProtocol")); //$NON-NLS-1$

        }
        if (numRegistres != null && !"%".equals(numRegistres.trim())) { //$NON-NLS-1$
            try {
                numRegs = Integer.parseInt(numRegistres);
            } catch (Exception ex) {
                throw new SeyconException(
                        Messages.getString("AccessLogEntityDaoImpl.badNumberFormat")); //$NON-NLS-1$
            }
        }
        if (numRegs >= 201) {
            throw new SeyconException(
                    Messages.getString("AccessLogEntityDaoImpl.tooManyRecordsFound")); //$NON-NLS-1$
        }

        try {
            String queryString = "select registreAcces "
            		+ "from com.soffid.iam.model.AccessLogEntity registreAcces " //$NON-NLS-1$
                    + "left join registreAcces.server server where " //$NON-NLS-1$
                    + "(registreAcces.server is not null and  server.nom like :nomServidor) and " //$NON-NLS-1$
                    + "(registreAcces.protocol is not null and registreAcces.protocol.name = :protocolAcces) " //$NON-NLS-1$
                    + "order by registreAcces.startDate desc"; //$NON-NLS-1$

            org.hibernate.Query queryObject = super.getSession(false)
                    .createQuery(queryString);
            queryObject.setParameter("nomServidor", nomServidor); //$NON-NLS-1$
            queryObject.setParameter("protocolAcces", protocolAcces); //$NON-NLS-1$
            // Probem el maxresults per restringir els resultats de la cerca:
            queryObject.setMaxResults(numRegs);
            java.util.List results = queryObject.list();
            return results;

        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    // Versió optimitzada per la consulta dels darrers n registres
    // Alejandro Usero - 12/09/2011
    public List<AccessLogEntity> findAccessLogByCriteria2(Date nullDate, Date dataIni, Date dataFi, String nomClient, String nomServidor, String codiUsuari) {

        int MAX_REGS = 201; // Sempre els resultats han de ser inferiors a 201

        // Analizamos los parámetros de entrada para optimizar la consulta

        try {
            boolean senseDataIni = dataIni == null
                    || (dataIni != null && dataIni.equals(nullDate));
            boolean senseDataFi = dataFi == null
                    || (dataFi != null && dataFi.equals(nullDate));
            boolean senseClient = (nomClient == null);
            boolean senseServidor = (nomServidor == null);
            boolean senseUsuari = (codiUsuari == null);
            if (senseDataIni && !senseDataFi) {
                throw new SeyconException(
                        Messages.getString("AccessLogEntityDaoImpl.endDateRequired")); //$NON-NLS-1$
            }
            if (senseDataIni && senseDataFi && senseClient && senseServidor
                    && senseUsuari) {
                throw new SeyconException(
                        Messages.getString("AccessLogEntityDaoImpl.missingParameter")); //$NON-NLS-1$
            }

            String queryString = "select registreAcces "
            		+ "from com.soffid.iam.model.AccessLogEntity registreAcces " //$NON-NLS-1$
                    + (!senseClient ? "left join registreAcces.client client " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseServidor ? "left join registreAcces.server server " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseUsuari ? "left join registreAcces.user user " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseDataIni || !senseDataFi || !senseClient
                            || !senseServidor || !senseUsuari ? "where 1=1 " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseClient ? "and (client.name like :nomClient) " : "") //$NON-NLS-1$ //$NON-NLS-2$
                    + (!senseServidor ? "and (server.name like :nomServidor) " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseUsuari ? "and (:codiUsuari is null or user.userName like :codiUsuari) " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseDataIni ? "and (registreAcces.startDate >= :dataIni ) " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseDataFi ? "and (registreAcces.endDate <= :dataFi ) " //$NON-NLS-1$
                            : "") + "order by registreAcces.endDate"; //$NON-NLS-1$ //$NON-NLS-2$

            org.hibernate.Query queryObject = super.getSession(false)
                    .createQuery(queryString);
            if (!senseClient)
                queryObject.setParameter("nomClient", nomClient); //$NON-NLS-1$
            if (!senseServidor)
                queryObject.setParameter("nomServidor", nomServidor); //$NON-NLS-1$
            if (!senseUsuari)
                queryObject.setParameter("codiUsuari", codiUsuari); //$NON-NLS-1$
            if (!senseDataIni)
                queryObject.setParameter("dataIni", dataIni); //$NON-NLS-1$
            // if (!senseDataIni || !senseDataFi)
            // queryObject.setParameter("nullDate", nullDate);
            if (!senseDataFi)
                queryObject.setParameter("dataFi", dataFi); //$NON-NLS-1$

            // Probem el maxresults per restringir els resultats de la cerca:
            queryObject.setMaxResults(MAX_REGS);
            java.util.List results = queryObject.list();
            return results;

        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }

    }

}
