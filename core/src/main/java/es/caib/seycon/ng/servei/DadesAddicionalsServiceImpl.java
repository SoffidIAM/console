// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.DadaUsuariEntity;
import es.caib.seycon.ng.model.RolEntityDao;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.TipusDadaEntityDao;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.servei.DadesAddicionalsService
 */
public class DadesAddicionalsServiceImpl extends
		es.caib.seycon.ng.servei.DadesAddicionalsServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#getTipusDades()
	 */
	protected java.util.Collection<TipusDada> handleGetTipusDades()
			throws java.lang.Exception {
		List<TipusDadaEntity> col = this.getTipusDadaEntityDao().loadAll();
		return getTipusDadaEntityDao().toTipusDadaList(col);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#create(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected es.caib.seycon.ng.comu.TipusDada handleCreate(
			es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws java.lang.Exception {
		Long order = tipusDada.getOrdre();
		String code = new String();
		boolean found = false;
		List<TipusDadaEntity> tipusDadaEntityList = getTipusDadaEntityDao().loadAll();
		for(TipusDadaEntity tipusDadaEntity: tipusDadaEntityList){
			Long orderDins = tipusDadaEntity.getOrdre();
			if(orderDins.compareTo(order) == 0){
				found = true;
				code = tipusDadaEntity.getCodi();
				break;
			}
		}
		if(found)
			throw new SeyconException(String.format(Messages.getString("DadesAddicionalsServiceImpl.IntegrityViolationOrder"),  //$NON-NLS-1$
							new Object[]{tipusDada.getOrdre(), tipusDada.getCodi(), code}));
		
		Collection tipusDadaMateixCodi = getTipusDadaEntityDao().findTipusDadesByCodi(tipusDada.getCodi());
		if(tipusDadaMateixCodi != null && !tipusDadaMateixCodi.isEmpty())
			throw new SeyconException(String.format(Messages.getString("DadesAddicionalsServiceImpl.IntegrityViolationCode"),  //$NON-NLS-1$
							new Object[]{tipusDada.getCodi()}));
		TipusDadaEntity tipusDadaEntity = getTipusDadaEntityDao()
				.tipusDadaToEntity(tipusDada);
		if (tipusDadaEntity != null) {
			getTipusDadaEntityDao().create(tipusDadaEntity);
			tipusDada.setId(tipusDadaEntity.getId());
			return getTipusDadaEntityDao().toTipusDada(tipusDadaEntity);
		}
		return null;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#delete(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws java.lang.Exception {
		TipusDadaEntity tipusDadaEntity = getTipusDadaEntityDao().load(tipusDada.getId());
		getTipusDadaEntityDao().remove(tipusDadaEntity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#update(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected es.caib.seycon.ng.comu.TipusDada handleUpdate(
			es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws java.lang.Exception {
		TipusDadaEntity tipusDadaEntity = getTipusDadaEntityDao()
				.tipusDadaToEntity(tipusDada);
		getTipusDadaEntityDao().update(tipusDadaEntity);
		return getTipusDadaEntityDao().toTipusDada(tipusDadaEntity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#findTipusDadesByCodi(java.lang.String)
	 */
	protected java.util.Collection<TipusDada> handleFindTipusDadesByCodi(
		java.lang.String codi) throws java.lang.Exception
	{
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		if (codi != null
				&& (codi.trim().compareTo("") == 0 || codi.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			codi = null;
		}
		
		List<TipusDadaEntity> dades = getTipusDadaEntityDao().findTipusDadesByCodi(codi);
		
		Collections.sort(dades, new Comparator<TipusDadaEntity>(){
			public int compare(TipusDadaEntity o1, TipusDadaEntity o2) {
				return o1.getOrdre().compareTo(o2.getOrdre());
			}	
		});
		
		if (dades != null)
		{
			// Check maximum number of results
			if (dades.size() > limitResults)
			{
				return getTipusDadaEntityDao().toTipusDadaList(dades)
					.subList(0, limitResults);
			}
			
			return getTipusDadaEntityDao().toTipusDadaList(dades);
		}
		
		return new Vector();
	}

	protected TipusDada handleFindTipusDadaByCodi(java.lang.String codi)
			throws java.lang.Exception {
		TipusDadaEntity tipusDadaEntity = getTipusDadaEntityDao()
				.findTipusDadaByCodi(codi);
		if (tipusDadaEntity != null) {
			TipusDada tipusDada = getTipusDadaEntityDao().toTipusDada(
					tipusDadaEntity);
			return tipusDada;
		}
		return null;
	}

	public DadaUsuari handleCreate(DadaUsuari dadaUsuari) throws InternalErrorException {
		DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
				.dadaUsuariToEntity(dadaUsuari);

		UsuariEntity usuari = dadaUsuariEntity.getUsuari();
		usuari.setDataDarreraModificacio(GregorianCalendar.getInstance()
				.getTime());
		usuari.setUsuariDarreraModificacio(Security.getCurrentAccount());
		getUsuariEntityDao().update(usuari);

		getDadaUsuariEntityDao().create(dadaUsuariEntity);
		getRuleEvaluatorService().applyRules(usuari);
		return getDadaUsuariEntityDao().toDadaUsuari(dadaUsuariEntity);
	}

	public void handleDelete(DadaUsuari dadaUsuari) throws InternalErrorException {
		DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
				.dadaUsuariToEntity(dadaUsuari);

		UsuariEntity usuari = dadaUsuariEntity.getUsuari();
		usuari.setDataDarreraModificacio(GregorianCalendar.getInstance()
				.getTime());
		usuari.setUsuariDarreraModificacio(Security.getCurrentAccount());
		getUsuariEntityDao().update(usuari);

		getDadaUsuariEntityDao().remove(dadaUsuariEntity);
		getRuleEvaluatorService().applyRules(usuari);
	}

	public DadaUsuari handleUpdate(DadaUsuari dadaUsuari) throws InternalErrorException {
		if (dadaUsuari.getId() != null) {
			if (dadaUsuari.getValorDada() == null
					|| "".equals(dadaUsuari.getValorDada().trim())) { //$NON-NLS-1$
				delete(dadaUsuari); // esborrar dada
				dadaUsuari.setId(null);
				return dadaUsuari;
			} else {
				// Aquí comprovem si pot actualitzar totes les dades addicionals
				// o només el telèfon
				DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
						.dadaUsuariToEntity(dadaUsuari);
				
				if (AutoritzacionsUsuari.canUpdateUserMetadata(dadaUsuariEntity.getUsuari()) 
						|| (AutoritzacionsUsuari.canUpdateCustomUser(dadaUsuariEntity.getUsuari()) && 
								dadaUsuariEntity.getTipusDada().getCodi().equals("PHONE"))) { //$NON-NLS-1$

					UsuariEntity usuari = dadaUsuariEntity.getUsuari();
					usuari.setDataDarreraModificacio(GregorianCalendar
							.getInstance().getTime());
					usuari.setUsuariDarreraModificacio(Security.getCurrentAccount());
					getUsuariEntityDao().update(usuari);

					getDadaUsuariEntityDao().update(dadaUsuariEntity);
					getRuleEvaluatorService().applyRules(usuari);
					return getDadaUsuariEntityDao().toDadaUsuari(
							dadaUsuariEntity);
				} else
					throw new SeyconAccessLocalException(
							"DadesAddicionalsService", "update (DadaUsuari)", //$NON-NLS-1$ //$NON-NLS-2$
							"user:metadata:update", //$NON-NLS-1$
							"Probably not authorized to update users in that group"); //$NON-NLS-1$

			}
		} else {
			return create(dadaUsuari);
		}
	}
}