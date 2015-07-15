// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import java.util.ArrayList;

import es.caib.seycon.ng.comu.RegistreAcces;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownHostException;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.MaquinaEntityDao;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.RegistreAccesEntity;
import es.caib.seycon.ng.model.ServeiEntity;
import es.caib.seycon.ng.model.ServeiEntityDao;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.DateUtils;
import es.caib.seycon.ng.utils.LimitDates;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.servei.RegistreAccesService
 */
public class RegistreAccesServiceImpl extends
		es.caib.seycon.ng.servei.RegistreAccesServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.RegistreAccesService#findRegistresAccesByFiltre(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Deprecated
	protected java.util.Collection<RegistreAcces> handleFindRegistresAccesByFiltre(
			java.lang.String data, java.lang.String nomServidor,
			java.lang.String nomClient, java.lang.String codiUsuari)
			throws java.lang.Exception {

		if (data==null && nomServidor==null && nomClient==null && codiUsuari==null) {
			return new Vector(); //No retornem res
		}
		
		LimitDates limitDates = null;
		if (data != null && data.trim().compareTo("") != 0 //$NON-NLS-1$
				&& data.trim().compareTo("%") != 0) { //$NON-NLS-1$
			data = data.trim();
			limitDates = DateUtils.getLimitDatesFromQuery(data);
		}

		if (nomServidor != null
				&& (nomServidor.trim().compareTo("") == 0 || nomServidor.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			nomServidor = null;
		}
		if (nomClient != null
				&& (nomClient.trim().compareTo("") == 0 || nomClient.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			nomClient = null;
		}
		if (codiUsuari != null
				&& (codiUsuari.trim().compareTo("") == 0 || codiUsuari.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			codiUsuari = null;
		}
		
		/*/ Restringimos los parámetros para evitar consulta muy compleja
		if (nomServidor == null && nomClient == null && codiUsuari == null) {
			throw new SeyconException(
					"Cal especificar almenys un d'aquests paràmetres de cerca: usuari, client o servidor");
		}*/

		Collection registresAcces = new Vector();
		if (limitDates != null) {
			registresAcces = this.getRegistreAccesEntityDao()
					.findRegistreByFiltre(DateUtils.nullDate,
							limitDates.getMaximum(), limitDates.getMinimum(),
							nomClient, nomServidor, codiUsuari);
		} else {
			//NOTA: se restringe el número de resultados a <=201
			registresAcces = getRegistreAccesEntityDao().findRegistreByFiltre(
					nomClient, nomServidor, codiUsuari);
		}
		if (registresAcces != null) {
			if (registresAcces.size() >= 201) {
				throw new SeyconException(
						Messages.getString("RegistreAccesServiceImpl.8")); //$NON-NLS-1$
			}
			return getRegistreAccesEntityDao().toRegistreAccesList(
					registresAcces);
		}
		return new Vector();
	}
	
	@Deprecated
	protected Collection<RegistreAcces> handleFindRegistresAccesByFiltre(String dataIni,
			String dataFi, String nomServidor, String nomClient,
			String codiUsuari) throws Exception {
		
		if (dataIni==null && dataFi==null && nomServidor==null && nomClient==null && codiUsuari==null) {
			return new LinkedList<RegistreAcces>(); //No retornem res
		}
		
		Date d_dataIni = null;
		Date d_dataFi = null;
		if (dataIni != null && dataIni.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataIni.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataIni = dataIni.trim();
			d_dataIni  = DateUtils.stringToDate(dataIni,false);			
		}
		if (dataFi !=null && dataFi.trim().compareTo("") !=0 //$NON-NLS-1$
				&& dataFi.trim().compareTo("%") !=0) { //$NON-NLS-1$
			dataFi = dataFi.trim();
			d_dataFi  = DateUtils.stringToDate(dataFi,true);
		}
		
		if (nomServidor != null
				&& (nomServidor.trim().compareTo("") == 0 || nomServidor.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			nomServidor = null;
		}
		if (nomClient != null
				&& (nomClient.trim().compareTo("") == 0 || nomClient.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			nomClient = null;
		}
		if (codiUsuari != null
				&& (codiUsuari.trim().compareTo("") == 0 || codiUsuari.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			codiUsuari = null;
		}
		
		/*
		if (nomServidor == null && nomClient == null && codiUsuari == null) {
			throw new SeyconException(
					"Cal especificar almenys un d'aquests paràmetres de cerca: usuari, client o servidor");
		}
		*/		

		Collection<RegistreAccesEntity> registresAcces = new LinkedList<RegistreAccesEntity>();
		if (d_dataIni == null && d_dataFi == null) { // Sin fechas
			registresAcces = getRegistreAccesEntityDao().findRegistreByFiltre(
					nomClient, nomServidor, codiUsuari);
		} else { // Con fechas (cualquiera de las dos)
			Date fechaIni = d_dataIni != null ? d_dataIni : DateUtils.nullDate;
			Date fechaFin = d_dataFi != null ? d_dataFi : DateUtils.nullDate;
			//Nota: se restringen los resultados a <=201 filas
			registresAcces = this.getRegistreAccesEntityDao()
					.findRegistreByFiltre2Datas(DateUtils.nullDate, fechaIni,
							fechaFin, nomClient, nomServidor, codiUsuari);
		}

		if (registresAcces != null) { // Restringit en la cerca a <=201 (com a molt serà 201)
			if (registresAcces.size() >= 201) {
				throw new SeyconException(
						Messages.getString("RegistreAccesServiceImpl.19")); //$NON-NLS-1$
			}
			return getRegistreAccesEntityDao().toRegistreAccesList(
					registresAcces);
		}
		return new LinkedList<RegistreAcces> ();

	}

	@Deprecated
	protected Collection<RegistreAcces> handleFindRegistresAccesByMaquina(String dataIni,
			String nomServidor, String numRegistres, String protocolAcces) throws Exception {
		Collection<RegistreAccesEntity> registresAcces=null;
		Date d_dataIni = null;
		if (dataIni != null && dataIni.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataIni.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataIni = dataIni.trim();
			d_dataIni  = DateUtils.stringToDate(dataIni,false);			
		} 
		if (numRegistres!=null && !"".equals(numRegistres.trim())) { //$NON-NLS-1$
			try {
				Integer.parseInt(numRegistres);
			} catch (Exception ex ) {
				throw new SeyconException (Messages.getString("RegistreAccesServiceImpl.23")); //$NON-NLS-1$
			}
		}
		if (nomServidor!=null && !"".equals(nomServidor.trim()) && !("%").equals(nomServidor.trim()) ) { //$NON-NLS-1$ //$NON-NLS-2$
			Date fechaIni = d_dataIni != null ? d_dataIni : DateUtils.nullDate;
			if (numRegistres != null && ! numRegistres.isEmpty())
			{
				CriteriaSearchConfiguration config  = new CriteriaSearchConfiguration();
				config.setMaximumResultSize(Integer.decode(numRegistres));
				registresAcces = getRegistreAccesEntityDao().findRegistreByMaquina(config, DateUtils.nullDate, fechaIni, nomServidor, protocolAcces);
			} else {
				registresAcces = getRegistreAccesEntityDao().findRegistreByMaquina(DateUtils.nullDate, fechaIni, nomServidor, protocolAcces);
			}
			return getRegistreAccesEntityDao().toRegistreAccesList(registresAcces); //Convertim a VO
		}		
		return new LinkedList<RegistreAcces>();
	}
	
	@Deprecated
	protected Collection<RegistreAcces>  handleFindRegistresAccesByMaquinaAccesSSO(String dataIni,
			String nomServidor, String numRegistres) throws Exception {
		Collection<RegistreAccesEntity> registresAcces=null;
		Date d_dataIni = null;
		if (dataIni != null && dataIni.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataIni.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataIni = dataIni.trim();
			d_dataIni  = DateUtils.stringToDate(dataIni,false);			
		} 
		int numRegs = -1;
		if (numRegistres!=null && !"%".equals(numRegistres.trim())) { //$NON-NLS-1$
			try {
				numRegs = Integer.parseInt(numRegistres);
			} catch (Exception ex ) {
				throw new SeyconException (Messages.getString("RegistreAccesServiceImpl.29")); //$NON-NLS-1$
			}
		}
		if (d_dataIni == null && numRegs == -1)
			throw new SeyconException(//TODO la fecha siempre será nula(está deshabilitada en el zul): Cambiar comentario si se habilita d nuevo
					Messages.getString("RegistreAccesServiceImpl.30"));  //$NON-NLS-1$
		if (nomServidor!=null && !"".equals(nomServidor.trim()) && !("%").equals(nomServidor.trim()) ) { //$NON-NLS-1$ //$NON-NLS-2$
			Date fechaIni = d_dataIni != null ? d_dataIni : DateUtils.nullDate;

			registresAcces = getRegistreAccesEntityDao().findRegistreByMaquinaDataIniDesc(DateUtils.nullDate, fechaIni, nomServidor, "sso"); //$NON-NLS-1$
			Collection res = null;
			if (numRegs>0) {
				res = new Vector(numRegs); int pos = 0;
				for (Iterator it = registresAcces.iterator(); it.hasNext() && pos++ < numRegs; ) 
				{
					res.add(it.next());
				}
			} else res = registresAcces;
			return getRegistreAccesEntityDao().toRegistreAccesList(res); //Convertim a VO
		}		
		return new LinkedList<RegistreAcces>();
	}

	@Deprecated
	protected Collection<RegistreAcces> handleFindRegistreByDataIniAndCodiUsuari(
			String dataIni, String codiUsuari, String numRegistres)
			throws Exception {
		Collection<RegistreAccesEntity> registresAcces=null;
		Date d_dataIni = null;
		if (dataIni != null && dataIni.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataIni.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataIni = dataIni.trim();
			d_dataIni  = DateUtils.stringToDate(dataIni,false);			
		} 
		int numRegs = -1;
		if (numRegistres!=null && !"%".equals(numRegistres.trim())) { //$NON-NLS-1$
			try {
				numRegs = Integer.parseInt(numRegistres);
			} catch (Exception ex ) {
				throw new SeyconException (Messages.getString("RegistreAccesServiceImpl.37")); //$NON-NLS-1$
			}
		}
		
		Date fechaIni = d_dataIni != null ? d_dataIni : DateUtils.nullDate;
		
		if (d_dataIni!=null) {
			
			registresAcces = getRegistreAccesEntityDao().findRegistreByDataIniAndCodiUsuari(DateUtils.nullDate, fechaIni, codiUsuari);
			if (registresAcces != null) { 
				if (registresAcces.size() >= 201) {
					throw new SeyconException(
						Messages.getString("RegistreAccesServiceImpl.38")); //$NON-NLS-1$
				}
				return getRegistreAccesEntityDao().toRegistreAccesList(
					registresAcces);
			}
		} else if (numRegs!=-1 && numRegs < 201) {//com a molt 200 registres
			// Los obtenemos ordenados por fecha inicio sesión descendentemente
			// Obtenemos sólo los registros de acceso de tipo SSO
			registresAcces = getRegistreAccesEntityDao().findRegistreByDataIniDescAndCodiUsuari(DateUtils.nullDate, fechaIni, codiUsuari,"sso"); //$NON-NLS-1$
			Collection res = null;
			if (numRegs>0) {
				res = new Vector(numRegs); int pos = 0;
				for (Iterator it = registresAcces.iterator(); it.hasNext() && pos++ < numRegs; ) 
				{
					res.add(it.next());
				}
			} else res = registresAcces;
			return getRegistreAccesEntityDao().toRegistreAccesList(res); //Convertim a VO
		} else 
			throw new SeyconException (Messages.getString("RegistreAccesServiceImpl.40")); //$NON-NLS-1$
		
		return new LinkedList<RegistreAcces>();	
	}
	
	// Emprat des de la finestra d'usuaris: darrers registres d'accés (sso) de l'usuari
	// Versió optimitzada... [12/09/2011] Alejandro Usero Ruiz
	protected Collection<RegistreAcces>  handleFindDarrersRegistresByCodiUsuari(
			String codiUsuari, String numRegistres, String codiProtocolAcces)
			throws Exception {
		
		UsuariEntity userEntity = getUsuariEntityDao().findByCodi(codiUsuari);
		Usuari user = getUsuariEntityDao().toUsuari(userEntity);
		
		// Mirem les autoritzacions del peticionari sobre l'usuari on es fa la petició
		if (getAutoritzacioService().hasPermission(
				Security.AUTO_ACCESSREGISTER_QUERY, new Object[] {userEntity})) {
			CriteriaSearchConfiguration config  = new CriteriaSearchConfiguration();
			if (numRegistres != null && ! numRegistres.isEmpty())
			{
				config.setMaximumResultSize(Integer.decode(numRegistres));
			}
			Collection<RegistreAccesEntity> registresAcces = getRegistreAccesEntityDao()
					.findDarrersRegistresByCodiUsuari(config, codiUsuari,
							codiProtocolAcces);
			if (registresAcces != null) {
				return getRegistreAccesEntityDao().toRegistreAccesList(registresAcces);
			}
			return new LinkedList<RegistreAcces>();
		} 
		throw new SeyconAccessLocalException("registreAccesService", //$NON-NLS-1$
				"findDarrersRegistresByCodiUsuari", //$NON-NLS-1$
				"user:accessRegister:query", //$NON-NLS-1$
				"Probably not authorized to query users in the group where the user belongs"); //$NON-NLS-1$
	}
	
	
	// Emprat des de la finestra de màquines: darrers registres d'accés (sso) a la màquina
	// Versió optimitzada... [12/09/2011] Alejandro Usero Ruiz
	protected Collection<RegistreAcces> handleFindDarrersRegistresAccesMaquinaSSO(
			String nomServidor, String numRegistres) throws Exception {

		CriteriaSearchConfiguration config  = new CriteriaSearchConfiguration();
		if (numRegistres != null && ! numRegistres.isEmpty())
		{
			config.setMaximumResultSize(Integer.decode(numRegistres));
		}
		Collection<RegistreAccesEntity> registresAcces = getRegistreAccesEntityDao()
				.findDarrersRegistresAccesMaquinaProtocol(config, nomServidor, "sso"); //$NON-NLS-1$
		
		if (registresAcces != null) {
			
			return getRegistreAccesEntityDao().toRegistreAccesList(
					registresAcces);
		}
		return new ArrayList();

	}


	// Emprat des de la finestra de registres d'accés: 
	// Versió optimitzada... [12/09/2011] Alejandro Usero Ruiz
	protected Collection<RegistreAcces> handleFindRegistresAccesByFiltreNou(
		String dataIni, String dataFi, String nomServidor, String nomClient,
		String codiUsuari) throws Exception
	{
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		Date d_dataIni = null;
		Date d_dataFi = null;
		if (dataIni != null && dataIni.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataIni.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataIni = dataIni.trim();
			d_dataIni  = DateUtils.stringToDate(dataIni,false);			
		}
		if (dataFi !=null && dataFi.trim().compareTo("") !=0 //$NON-NLS-1$
				&& dataFi.trim().compareTo("%") !=0) { //$NON-NLS-1$
			dataFi = dataFi.trim();
			d_dataFi  = DateUtils.stringToDate(dataFi,true);
		}

		d_dataIni = d_dataIni != null ? d_dataIni : DateUtils.nullDate;
		d_dataFi = d_dataFi != null ? d_dataFi : DateUtils.nullDate;
		
		if (nomServidor != null
				&& (nomServidor.trim().compareTo("") == 0 || nomServidor.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			nomServidor = null;
		}
		if (nomClient != null
				&& (nomClient.trim().compareTo("") == 0 || nomClient.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			nomClient = null;
		}
		if (codiUsuari != null
				&& (codiUsuari.trim().compareTo("") == 0 || codiUsuari.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			codiUsuari = null;
		}
		

		// Query optimitzada segons els paràmetres d'entrada
		Collection<RegistreAccesEntity>  registresAcces = getRegistreAccesEntityDao()
				.findRegistreByFiltreNou(DateUtils.nullDate, d_dataIni, d_dataFi, nomClient,
						nomServidor, codiUsuari);
		if (registresAcces != null)
		{
			// Check maximum number of results
			if (registresAcces.size() > limitResults)
			{
				return getRegistreAccesEntityDao()
					.toRegistreAccesList(registresAcces).subList(0, limitResults);
			}

			return getRegistreAccesEntityDao()
				.toRegistreAccesList(registresAcces);
		}
		
		return new LinkedList<RegistreAcces>();
	}

    @Override
    protected RegistreAcces handleCreate(RegistreAcces registre) throws Exception {
        RegistreAccesEntity entity =
        		getRegistreAccesEntityDao().newRegistreAccesEntity();
        MaquinaEntityDao maquinaDao = getMaquinaEntityDao();
        ServeiEntityDao serveiDao = getServeiEntityDao();
        if (registre.getNomClinet() != null && registre.getNomClinet().length() > 0) {
            MaquinaEntity maquina = maquinaDao.findByNom(registre.getNomClinet());
            if (maquina == null)
                maquina = maquinaDao.findByAdreca(registre.getNomClinet());
            if (maquina == null) {
                if (registre.getInformacio() == null)
                    registre.setInformacio(Messages.getString("RegistreAccesServiceImpl.57") + registre.getNomClinet()); //$NON-NLS-1$
                else
                    registre.setInformacio(registre.getInformacio()+Messages.getString("RegistreAccesServiceImpl.58")+registre.getNomClinet()); //$NON-NLS-1$
            } else {
                entity.setClient(maquina);
            }
        }
        MaquinaEntity maquina = maquinaDao.findByNom(registre.getNomServidor());
        entity.setServidor(maquina);
        
        ServeiEntity servei = serveiDao.findByCodi(registre.getProtocolAcces());
        if (servei == null) {
            servei =  getServeiEntityDao().newServeiEntity();
            servei.setCodi(registre.getProtocolAcces());
            serveiDao.create(servei);
        }
        entity.setCodeAge(registre.getCodeAge());
        entity.setDataInici(registre.getDataInici().getTime());
        entity.setDataFi(registre.getDataFi().getTime());
        entity.setInformacio(registre.getInformacio());
        entity.setIdSessio(registre.getIdSessio());
        entity.setProtocol(servei);
        entity.setTipusAcces(registre.getTipusAcces());
        entity.setUsuari(getUsuariEntityDao().findByCodi(registre.getCodiUsuari()));
        getRegistreAccesEntityDao().create(entity);
        return getRegistreAccesEntityDao().toRegistreAcces(entity);
    }
	

}
