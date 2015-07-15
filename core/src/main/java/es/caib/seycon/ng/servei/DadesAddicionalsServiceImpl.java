// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
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
		List<MetaDataEntity> col = this.getMetaDataEntityDao().loadAll();
		return getMetaDataEntityDao().toTipusDadaList(col);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#create(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected es.caib.seycon.ng.comu.TipusDada handleCreate(
			es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws java.lang.Exception {
		if (tipusDada.getAdminVisibility() == null)
			tipusDada.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
		if (tipusDada.getAdminVisibility() == null)
			tipusDada.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
		if (tipusDada.getAdminVisibility() == null)
			tipusDada.setUserVisibility(AttributeVisibilityEnum.READONLY);

		if (tipusDada.getSystemName() != null && tipusDada.getSystemName().trim().length() > 0)
		{
			Long order = tipusDada.getOrdre();
			String code = new String();
			boolean found = false;
			List<AccountMetadataEntity> tipusDadaEntityList = getAccountMetadataEntityDao().findBySystem(tipusDada.getSystemName());
			if (order == 0)
			{
				long next = 10;
				for(AccountMetadataEntity tipusDadaEntity: tipusDadaEntityList){
					if (tipusDadaEntity.getOrder().longValue() >= next)
						next = tipusDadaEntity.getOrder().longValue() + 1;
				}
			}
			else
			{
				for(AccountMetadataEntity tipusDadaEntity: tipusDadaEntityList){
					Long orderDins = tipusDadaEntity.getOrder();
					if(orderDins.compareTo(order) == 0){
						found = true;
						code = tipusDadaEntity.getName();
						break;
					}
				}
			}
			if(found)
				throw new SeyconException(String.format(Messages.getString("DadesAddicionalsServiceImpl.IntegrityViolationOrder"),  //$NON-NLS-1$
								new Object[]{tipusDada.getOrdre(), tipusDada.getCodi(), code}));
			
			AccountMetadataEntity tipusDadaMateixCodi = getAccountMetadataEntityDao().findByName(tipusDada.getSystemName(), tipusDada.getCodi());
			if(tipusDadaMateixCodi != null)
				throw new SeyconException(String.format(Messages.getString("DadesAddicionalsServiceImpl.IntegrityViolationCode"),  //$NON-NLS-1$
								new Object[]{tipusDada.getCodi()}));
			AccountMetadataEntity tipusDadaEntity = getAccountMetadataEntityDao().tipusDadaToEntity(tipusDada);
			if (tipusDadaEntity != null) {
				getAccountMetadataEntityDao().create(tipusDadaEntity);
				tipusDada.setId(tipusDadaEntity.getId());
				return getAccountMetadataEntityDao().toTipusDada(tipusDadaEntity);
			}
		}
		else
		{
			// Create user data
			Long order = tipusDada.getOrdre();
			String code = new String();
			boolean found = false;

			List<MetaDataEntity> tipusDadaEntityList = getMetaDataEntityDao().loadAll();
			for (MetaDataEntity tipusDadaEntity : tipusDadaEntityList) {
	            Long orderDins = tipusDadaEntity.getOrder();
	            if (orderDins.compareTo(order) == 0) {
	                found = true;
	                code = tipusDadaEntity.getName();
	                break;
	            }
	        }
			if(found)
				throw new SeyconException(String.format(Messages.getString("DadesAddicionalsServiceImpl.IntegrityViolationOrder"),  //$NON-NLS-1$
								new Object[]{tipusDada.getOrdre(), tipusDada.getCodi(), code}));
			
			Collection tipusDadaMateixCodi = getMetaDataEntityDao().findDataTypesByName(tipusDada.getCodi());
			if(tipusDadaMateixCodi != null && !tipusDadaMateixCodi.isEmpty())
				throw new SeyconException(String.format(Messages.getString("DadesAddicionalsServiceImpl.IntegrityViolationCode"),  //$NON-NLS-1$
								new Object[]{tipusDada.getCodi()}));
			MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().tipusDadaToEntity(tipusDada);
			if (tipusDadaEntity != null) {
				getMetaDataEntityDao().create(tipusDadaEntity);
				tipusDada.setId(tipusDadaEntity.getId());
				return getMetaDataEntityDao().toTipusDada(tipusDadaEntity);
			}
		}
		return null;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#delete(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws java.lang.Exception {
		if (tipusDada.getSystemName() == null || tipusDada.getSystemName().length() == 0)
		{
			MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().load(tipusDada.getId());
			getMetaDataEntityDao().remove(tipusDadaEntity);
		} else {
			AccountMetadataEntity tipusDadaEntity = getAccountMetadataEntityDao().load(tipusDada.getId());
			getAccountMetadataEntityDao().remove(tipusDadaEntity);
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DadesAddicionalsService#update(es.caib.seycon.ng.comu.TipusDada)
	 */
	protected es.caib.seycon.ng.comu.TipusDada handleUpdate(
			es.caib.seycon.ng.comu.TipusDada tipusDada)
			throws java.lang.Exception {
		if (tipusDada.getSystemName() == null || tipusDada.getSystemName().trim().length() == 0)
		{
			MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().tipusDadaToEntity(tipusDada);
		
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setUserVisibility(AttributeVisibilityEnum.READONLY);
	
			getMetaDataEntityDao().update(tipusDadaEntity);
			return getMetaDataEntityDao().toTipusDada(tipusDadaEntity);
		} else {
			AccountMetadataEntity tipusDadaEntity = getAccountMetadataEntityDao()
					.tipusDadaToEntity(tipusDada);
			
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
			if (tipusDadaEntity.getAdminVisibility() == null)
				tipusDadaEntity.setUserVisibility(AttributeVisibilityEnum.READONLY);
	
			getAccountMetadataEntityDao().update(tipusDadaEntity);
			return getAccountMetadataEntityDao().toTipusDada(tipusDadaEntity);
		}
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
		
		List<MetaDataEntity> dades = getMetaDataEntityDao().findDataTypesByName(codi);
		
		Collections.sort(dades, new Comparator<MetaDataEntity>(){
            
            
            public int compare(MetaDataEntity o1, MetaDataEntity o2) {
                return o1.getOrder().compareTo(o2.getOrder());
            }
        });
		
		if (dades != null)
		{
			// Check maximum number of results
			if (dades.size() > limitResults)
			{
				return getMetaDataEntityDao().toTipusDadaList(dades).subList(0, limitResults);
			}
			
			return getMetaDataEntityDao().toTipusDadaList(dades);
		}
		
		return new Vector();
	}

	protected TipusDada handleFindTipusDadaByCodi(java.lang.String codi)
			throws java.lang.Exception {
		MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().findDataTypeByName(codi);
		if (tipusDadaEntity != null) {
			TipusDada tipusDada = getMetaDataEntityDao().toTipusDada(tipusDadaEntity);
			return tipusDada;
		}
		return null;
	}

	public DadaUsuari handleCreate(DadaUsuari dadaUsuari) throws InternalErrorException {
		
		UserDataEntity dadaUsuariEntity = getUserDataEntityDao().dadaUsuariToEntity(dadaUsuari);

		AttributeVisibilityEnum visibility = dadaUsuariEntity.getAttributeVisibility();

		if (!visibility.equals(AttributeVisibilityEnum.EDITABLE))
			throw new SecurityException (String.format("Not allowed to modify the attributes %s", dadaUsuari.getCodiDada()));
		
		UserEntity usuari = dadaUsuariEntity.getUser();
		usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
		usuari.setLastUserModification(Security.getCurrentAccount());
		getUserEntityDao().update(usuari);

		getUserDataEntityDao().create(dadaUsuariEntity);
		
		auditChange(dadaUsuari);

		getRuleEvaluatorService().applyRules(usuari);
		return getUserDataEntityDao().toDadaUsuari(dadaUsuariEntity);
	}

	public void handleDelete(DadaUsuari dadaUsuari) throws InternalErrorException {
		UserDataEntity dadaUsuariEntity = getUserDataEntityDao().dadaUsuariToEntity(dadaUsuari);

		AttributeVisibilityEnum visibility = dadaUsuariEntity.getAttributeVisibility(); 

		if (!visibility.equals(AttributeVisibilityEnum.EDITABLE))
			throw new SecurityException (String.format("Not allowed to modify the attributes %s", dadaUsuari.getCodiDada()));

		UserEntity usuari = dadaUsuariEntity.getUser();
		usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
		usuari.setLastUserModification(Security.getCurrentAccount());
		getUserEntityDao().update(usuari);

		getUserDataEntityDao().remove(dadaUsuariEntity);

		auditChange(dadaUsuari);

		getRuleEvaluatorService().applyRules(usuari);
	}

	public DadaUsuari handleUpdate(DadaUsuari dadaUsuari) throws InternalErrorException {
		if (dadaUsuari.getId() != null) {
			auditChange(dadaUsuari);
			if (dadaUsuari.getValorDada() == null
					|| "".equals(dadaUsuari.getValorDada().trim())) { //$NON-NLS-1$
				delete(dadaUsuari); // esborrar dada
				dadaUsuari.setId(null);
				return dadaUsuari;
			} else {
				// Aquí comprovem si pot actualitzar totes les dades addicionals
				// o només el telèfon
				UserDataEntity dadaUsuariEntity = getUserDataEntityDao().dadaUsuariToEntity(dadaUsuari);
				
				AttributeVisibilityEnum visibility = dadaUsuariEntity.getAttributeVisibility();

				if (!visibility.equals(AttributeVisibilityEnum.EDITABLE))
					throw new SecurityException (String.format("Not allowed to modify the attributes %s", dadaUsuari.getCodiDada()));
				
				UserEntity usuari = dadaUsuariEntity.getUser();
				usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
				usuari.setLastUserModification(Security.getCurrentAccount());
				getUserEntityDao().update(usuari);

				getUserDataEntityDao().update(dadaUsuariEntity);
				getRuleEvaluatorService().applyRules(usuari);
				return getUserDataEntityDao().toDadaUsuari(dadaUsuariEntity);
			}
		} else {
			return create(dadaUsuari);
		}
	}

	private void auditChange(DadaUsuari dadaUsuari)
			throws InternalErrorException {
		Auditoria audit = new Auditoria();
		audit.setObjecte("SC_DADUSU");
		audit.setAccio("U");
		audit.setAutor( Security.getCurrentAccount() );
		audit.setCalendar(Calendar.getInstance());
		audit.setParametreConfiguracio(dadaUsuari.getCodiDada());
		audit.setUsuari(dadaUsuari.getCodiUsuari());
		getAuditoriaService().create(audit);
	}

	@Override
	protected TipusDada handleFindSystemDataType(String system, String name)
			throws Exception {
		AccountMetadataEntity am = getAccountMetadataEntityDao().findByName(system, name);
		if (am == null)
			return null;
		else
			return getAccountMetadataEntityDao().toTipusDada(am);
	}

	@Override
	protected List<TipusDada> handleFindSystemDataTypes(String system)
			throws Exception {
		SystemEntity de = getSystemEntityDao().findByName(system);
		if (de == null)
			return null;
		return getAccountMetadataEntityDao().toTipusDadaList(de.getMetaData());
	}
}
