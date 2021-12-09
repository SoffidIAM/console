// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;

/**
 * @see es.caib.seycon.ng.model.RegistreAccesEntity
 */
public class RegistreAccesEntityDaoImpl extends
        es.caib.seycon.ng.model.RegistreAccesEntityDaoBase {

    public void create(es.caib.seycon.ng.model.RegistreAccesEntity registreAcces)
            throws RuntimeException {
        try {
            super.create(registreAcces);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RegistreAccesEntityDaoImpl.errorCreating"), //$NON-NLS-1$
					registreAcces.getIdSessio(), 
					message));
        }
    }

    public void remove(es.caib.seycon.ng.model.RegistreAccesEntity registreAcces)
            throws RuntimeException {
        try {
            super.remove(registreAcces);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RegistreAccesEntityDaoImpl.errorDeleting"), //$NON-NLS-1$
					registreAcces.getIdSessio(), 
					message));
        }
    }

    public void toRegistreAcces(
            es.caib.seycon.ng.model.RegistreAccesEntity source,
            es.caib.seycon.ng.comu.RegistreAcces target) {
        super.toRegistreAcces(source, target);

        Calendar calendar = GregorianCalendar.getInstance();
        if (source.getDataInici() != null) {
            calendar.setTime(source.getDataInici());
            target.setDataInici(calendar);
        }

        if (source.getDataFi() != null) {
            calendar = GregorianCalendar.getInstance();
            calendar.setTime(source.getDataFi());
            target.setDataFi(calendar);
        }

        UsuariEntity usuari = source.getUsuari();
        if (usuari != null) {
            target.setCodiUsuari(usuari.getCodi());
            // Afegim nom i llinatges de l'usuari
            String nom = usuari.getNom();
            nom = nom != null ? nom : ""; //$NON-NLS-1$
            String primerCognom = usuari.getPrimerLlinatge();
            primerCognom = primerCognom != null ? primerCognom + " " : ""; //$NON-NLS-1$ //$NON-NLS-2$
            String segonCognom = usuari.getSegonLlinatge();
            segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
            target.setNomCompletUsuari(nom + " " + primerCognom + segonCognom); //$NON-NLS-1$
        }

        MaquinaEntity servidor = source.getServidor();
        if (servidor != null) {
            target.setNomServidor(servidor.getNom());
        } else if (source.getHostName() != null )
        {
        	target.setNomServidor(source.getHostName());
        } else if (source.getHostAddress() != null)
        {
        	target.setNomServidor(source.getHostAddress());
        }

        MaquinaEntity client = source.getClient();
        if (client != null) {
            target.setNomClinet(client.getNom());
        } else if (source.getClientHostName() != null )
        {
        	target.setNomClinet(source.getClientHostName());
        } else if (source.getClientAddress() != null)
        {
        	target.setNomClinet(source.getClientAddress());
        }

        if (source.getTipusAcces() == null
                || "L".equals(source.getTipusAcces())) //$NON-NLS-1$
            target.setTipusAcces("logon"); //$NON-NLS-1$
        else if ("D".equals(source.getTipusAcces())) //$NON-NLS-1$
            target.setTipusAcces("logon denied"); //$NON-NLS-1$

        // Afegim informació del protocol d'accés
        if (source.getProtocol() != null) {
            target.setProtocolAcces(source.getProtocol().getCodi());
        }
    }

    /**
     * @see es.caib.seycon.ng.model.RegistreAccesEntityDao#toRegistreAcces(es.caib.seycon.ng.model.RegistreAccesEntity)
     */
    public es.caib.seycon.ng.comu.RegistreAcces toRegistreAcces(
            final es.caib.seycon.ng.model.RegistreAccesEntity entity) {
        // @todo verify behavior of toRegistreAcces
        return super.toRegistreAcces(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.RegistreAccesEntity loadRegistreAccesEntityFromRegistreAcces(
            es.caib.seycon.ng.comu.RegistreAcces registreAcces) {
        throw new SeyconException(
                Messages.getString("RegistreAccesEntityDaoImpl.cannotUpdate")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.model.RegistreAccesEntityDao#registreAccesToEntity(es.caib.seycon.ng.comu.RegistreAcces)
     */
    public es.caib.seycon.ng.model.RegistreAccesEntity registreAccesToEntity(
            es.caib.seycon.ng.comu.RegistreAcces registreAcces) {
        // @todo verify behavior of registreAccesToEntity
        es.caib.seycon.ng.model.RegistreAccesEntity entity = this
                .loadRegistreAccesEntityFromRegistreAcces(registreAcces);
        this.registreAccesToEntity(registreAcces, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.RegistreAccesEntityDao#registreAccesToEntity(es.caib.seycon.ng.comu.RegistreAcces,
     *      es.caib.seycon.ng.model.RegistreAccesEntity)
     */
    public void registreAccesToEntity(
            es.caib.seycon.ng.comu.RegistreAcces source,
            es.caib.seycon.ng.model.RegistreAccesEntity target,
            boolean copyIfNull) {
        // @todo verify behavior of registreAccesToEntity
        super.registreAccesToEntity(source, target, copyIfNull);
        // No conversion for target.dataInici (can't convert
        // source.getDataInici():java.util.Date to java.util.Date
        // No conversion for target.dataFi (can't convert
        // source.getDataFi():java.util.Date to java.util.Date
    }

    public List<RegistreAccesEntity> find(
            final java.lang.String queryString,
            final es.caib.seycon.ng.model.Parameter[] parameters) {
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
    public List<RegistreAccesEntity> findDarrersRegistresByCodiUsuari(String codiUsuari,
            String numRegistres, String codiProtocolAcces) {

        int numRegs = -1;

        if (codiUsuari == null
                || (codiUsuari != null && "".equals(codiUsuari.trim())) //$NON-NLS-1$
                || codiProtocolAcces == null
                || (codiProtocolAcces != null && "".equals(codiProtocolAcces //$NON-NLS-1$
                        .trim()))) {
            throw new SeyconException(
                    Messages.getString("RegistreAccesEntityDaoImpl.missingData")); //$NON-NLS-1$
        }

        if (numRegistres != null && !"%".equals(numRegistres.trim())) { //$NON-NLS-1$
            try {
                numRegs = Integer.parseInt(numRegistres);
            } catch (Exception ex) {
                throw new SeyconException(
                        Messages.getString("RegistreAccesEntityDaoImpl.wrongFormat")); //$NON-NLS-1$
            }
        }
        if (numRegs >= 201) {
            throw new SeyconException(
                    Messages.getString("RegistreAccesEntityDaoImpl.tooManyRecords")); //$NON-NLS-1$
        }

        try {
            String queryString = "select registreAcces from es.caib.seycon.ng.model.RegistreAccesEntity registreAcces " //$NON-NLS-1$
                    + "left join registreAcces.usuari " //$NON-NLS-1$
                    + "left join registreAcces.protocol where  " //$NON-NLS-1$
                    + "(registreAcces.usuari is not null and registreAcces.usuari.codi = :codiUsuari) and " //$NON-NLS-1$
                    + "(registreAcces.protocol is not null and registreAcces.protocol.codi= :codiProtocolAcces) " //$NON-NLS-1$
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
    public List<RegistreAccesEntity> findDarrersRegistresAccesMaquinaProtocol(
            java.lang.String nomServidor, String numRegistres,
            java.lang.String protocolAcces) {
        int numRegs = -1;
        if (nomServidor == null
                || (nomServidor != null && "".equals(nomServidor.trim())) //$NON-NLS-1$
                || protocolAcces == null
                || (protocolAcces != null && "".equals(protocolAcces.trim()))) { //$NON-NLS-1$
            throw new SeyconException(
                    Messages.getString("RegistreAccesEntityDaoImpl.missingServerAndProtocol")); //$NON-NLS-1$

        }
        if (numRegistres != null && !"%".equals(numRegistres.trim())) { //$NON-NLS-1$
            try {
                numRegs = Integer.parseInt(numRegistres);
            } catch (Exception ex) {
                throw new SeyconException(
                        Messages.getString("RegistreAccesEntityDaoImpl.badNumberFormat")); //$NON-NLS-1$
            }
        }
        if (numRegs >= 201) {
            throw new SeyconException(
                    Messages.getString("RegistreAccesEntityDaoImpl.tooManyRecordsFound")); //$NON-NLS-1$
        }

        try {
            String queryString = "select registreAcces from es.caib.seycon.ng.model.RegistreAccesEntity registreAcces " //$NON-NLS-1$
                    + "left join registreAcces.servidor servidor where " //$NON-NLS-1$
                    + "(registreAcces.servidor is not null and  servidor.nom like :nomServidor) and " //$NON-NLS-1$
                    + "(registreAcces.protocol is not null and registreAcces.protocol.codi = :protocolAcces) " //$NON-NLS-1$
                    + "order by registreAcces.dataInici desc"; //$NON-NLS-1$

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
    public List<RegistreAccesEntity> findRegistreByFiltreNou(Date nullDate, Date dataIni,
            Date dataFi, String nomClient, String nomServidor, String codiUsuari) {

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
                        Messages.getString("RegistreAccesEntityDaoImpl.endDateRequired")); //$NON-NLS-1$
            }
            if (senseDataIni && senseDataFi && senseClient && senseServidor
                    && senseUsuari) {
                throw new SeyconException(
                        Messages.getString("RegistreAccesEntityDaoImpl.missingParameter")); //$NON-NLS-1$
            }

            String queryString = "select registreAcces from es.caib.seycon.ng.model.RegistreAccesEntity registreAcces " //$NON-NLS-1$
                    + (!senseClient ? "left join registreAcces.client client " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseServidor ? "left join registreAcces.servidor servidor " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseUsuari ? "left join registreAcces.usuari usuari " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseDataIni || !senseDataFi || !senseClient
                            || !senseServidor || !senseUsuari ? "where 1=1 " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseClient ? "and (client.nom like :nomClient) " : "") //$NON-NLS-1$ //$NON-NLS-2$
                    + (!senseServidor ? "and (servidor.nom like :nomServidor) " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseUsuari ? "and (:codiUsuari is null or usuari.codi like :codiUsuari) " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseDataIni ? "and (registreAcces.dataInici >= :dataIni ) " //$NON-NLS-1$
                            : "") //$NON-NLS-1$
                    + (!senseDataFi ? "and (registreAcces.dataFi <= :dataFi ) " //$NON-NLS-1$
                            : "") + "order by registreAcces.dataInici"; //$NON-NLS-1$ //$NON-NLS-2$

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