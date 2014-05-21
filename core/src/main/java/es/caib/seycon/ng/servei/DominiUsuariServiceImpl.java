// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import es.caib.seycon.ng.comu.DominiContrasenya;
import es.caib.seycon.ng.comu.DominiUsuari;
import es.caib.seycon.ng.comu.ParaulaProhibida;
import es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya;
import es.caib.seycon.ng.comu.PoliticaContrasenya;
import es.caib.seycon.ng.comu.TipusUsuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.DominiContrasenyaEntity;
import es.caib.seycon.ng.model.DominiUsuariEntity;
import es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity;
import es.caib.seycon.ng.model.ParaulesProhibidesEntity;
import es.caib.seycon.ng.model.PoliticaContrasenyaEntity;
import es.caib.seycon.ng.model.TipusUsuariEntity;
import es.caib.seycon.ng.servei.account.AccountNameGenerator;

/**
 * @see es.caib.seycon.ng.servei.DominiUsuariService
 */
public class DominiUsuariServiceImpl extends es.caib.seycon.ng.servei.DominiUsuariServiceBase  implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#create(es.caib.seycon.ng.comu.DominiUsuari)
	 */
	protected es.caib.seycon.ng.comu.DominiUsuari handleCreate(es.caib.seycon.ng.comu.DominiUsuari dominiUsuari)
			throws java.lang.Exception {

		if (dominiUsuari.getCodi() == null || "".equals(dominiUsuari.getCodi())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.0")); //$NON-NLS-1$
		}

		if (dominiUsuari.getTipus() == null || "".equals(dominiUsuari.getTipus())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.1")); //$NON-NLS-1$
		}

		DominiUsuariEntity entity = getDominiUsuariEntityDao().dominiUsuariToEntity(dominiUsuari);
		getDominiUsuariEntityDao().create(entity);
		return getDominiUsuariEntityDao().toDominiUsuari(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#update(es.caib.seycon.ng.comu.DominiUsuari)
	 */
	protected es.caib.seycon.ng.comu.DominiUsuari handleUpdate(es.caib.seycon.ng.comu.DominiUsuari dominiUsuari)
			throws java.lang.Exception {

		if (dominiUsuari.getCodi() == null || "".equals(dominiUsuari.getCodi())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.2")); //$NON-NLS-1$
		}

		if (dominiUsuari.getTipus() == null || "".equals(dominiUsuari.getTipus())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.3")); //$NON-NLS-1$
		}

		DominiUsuariEntity entity = getDominiUsuariEntityDao().dominiUsuariToEntity(dominiUsuari);
		getDominiUsuariEntityDao().update(entity);
		return getDominiUsuariEntityDao().toDominiUsuari(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#delete(es.caib.seycon.ng.comu.DominiUsuari)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.DominiUsuari dominiUsuari) throws java.lang.Exception {
		DominiUsuariEntity entity = getDominiUsuariEntityDao().dominiUsuariToEntity(dominiUsuari);
		getDominiUsuariEntityDao().remove(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#create(es.caib.seycon.ng.comu.TipusUsuari)
	 */
	protected es.caib.seycon.ng.comu.TipusUsuari handleCreate(es.caib.seycon.ng.comu.TipusUsuari tipusUsuari)
			throws java.lang.Exception {
		TipusUsuariEntity entity = getTipusUsuariEntityDao().tipusUsuariToEntity(tipusUsuari);
		getTipusUsuariEntityDao().create(entity);
		return getTipusUsuariEntityDao().toTipusUsuari(entity);

	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#update(es.caib.seycon.ng.comu.TipusUsuari)
	 */
	protected es.caib.seycon.ng.comu.TipusUsuari handleUpdate(es.caib.seycon.ng.comu.TipusUsuari tipusUsuari)
			throws java.lang.Exception {
		TipusUsuariEntity entity = getTipusUsuariEntityDao().tipusUsuariToEntity(tipusUsuari);
		getTipusUsuariEntityDao().update(entity);
		return getTipusUsuariEntityDao().toTipusUsuari(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#delete(es.caib.seycon.ng.comu.TipusUsuari)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.TipusUsuari tipusUsuari) throws java.lang.Exception {
		TipusUsuariEntity entity = getTipusUsuariEntityDao().tipusUsuariToEntity(tipusUsuari);
		if(entity.getAccounts() != null && !entity.getAccounts().isEmpty())
			throw new InternalErrorException(Messages.getString("DominiUsuariServiceImpl.UserTypeWithAccounts")); //$NON-NLS-1$
		if(entity.getPolitiques() != null && !entity.getPolitiques().isEmpty())
			throw new InternalErrorException(Messages.getString("DominiUsuariServiceImpl.UserTypeWithPolicy")); //$NON-NLS-1$
		getTipusUsuariEntityDao().remove(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#findAllDominiUsuari()
	 */
	protected java.util.Collection<DominiUsuari> handleFindAllDominiUsuari() throws java.lang.Exception {
		Collection<DominiUsuariEntity> dominisUsuari = getDominiUsuariEntityDao().loadAll();
		return getDominiUsuariEntityDao().toDominiUsuariList(dominisUsuari);
	}
	
	/**
	 * @see es.caib.seycon.ng.servei.DominiUsuariService#findAllTipusUsuari()
	 */
	protected java.util.Collection<TipusUsuari> handleFindAllTipusUsuari() throws java.lang.Exception {
		Collection tipusUsuari = getTipusUsuariEntityDao().loadAll();
		return getTipusUsuariEntityDao().toTipusUsuariList(tipusUsuari);
	}

	@Override
	protected Collection<PoliticaContrasenya> handleFindAllPolitiquesContrasenyaDomini(String codiDomini) throws Exception {
		Collection politiques = getPoliticaContrasenyaEntityDao().findByDominiContrasenya(codiDomini);
		return getPoliticaContrasenyaEntityDao().toPoliticaContrasenyaList(politiques);
	}

	@Override
	protected Collection<ParaulaProhibida> handleFindAllParaulesProhibides() throws Exception {
		Collection forbidden = getParaulesProhibidesEntityDao().loadAll();
		return getParaulesProhibidesEntityDao().toParaulaProhibidaList(forbidden);
	}

	@Override
	protected ParaulaProhibida handleCreate(ParaulaProhibida paraulaProhibida) throws Exception {
		ParaulesProhibidesEntity entity = getParaulesProhibidesEntityDao().paraulaProhibidaToEntity(paraulaProhibida);
		getParaulesProhibidesEntityDao().create(entity);
		return getParaulesProhibidesEntityDao().toParaulaProhibida(entity);
	}

	@Override
	protected ParaulaProhibida handleUpdate(ParaulaProhibida paraulaProhibida) throws Exception {
		ParaulesProhibidesEntity entity = getParaulesProhibidesEntityDao().paraulaProhibidaToEntity(paraulaProhibida);
		getParaulesProhibidesEntityDao().update(entity);
		return getParaulesProhibidesEntityDao().toParaulaProhibida(entity);
	}

	@Override
	protected void handleDelete(ParaulaProhibida paraulaProhibida) throws Exception {
		ParaulesProhibidesEntity entity = getParaulesProhibidesEntityDao().paraulaProhibidaToEntity(paraulaProhibida);
		getParaulesProhibidesEntityDao().remove(entity);

	}

	@Override
	protected PoliticaContrasenya handleCreate(PoliticaContrasenya politicaContrasenyaDomini) throws Exception {
		if (politicaContrasenyaDomini.getTipusUsuari() == null || "".equals(politicaContrasenyaDomini.getTipusUsuari())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.4")); //$NON-NLS-1$
		}

		if (politicaContrasenyaDomini.getTipus() == null || "".equals(politicaContrasenyaDomini.getTipus())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.5")); //$NON-NLS-1$
		}

		// Mirem que no existisca una altra amb el mateix tipus d'usuari i
		// domini de contrasenyes
		Collection altres = getPoliticaContrasenyaEntityDao().findByDominiContrasenya(
				politicaContrasenyaDomini.getCodiDominiContrasenya());
		if (altres != null)
			for (Iterator<PoliticaContrasenyaEntity> it = altres.iterator(); it.hasNext();)
			{
				PoliticaContrasenyaEntity pcd = it.next();
				if (pcd.getTipusUsuariDomini() != null 	&&
					pcd.getTipusUsuariDomini().getCodi()
						.equals(politicaContrasenyaDomini.getTipusUsuari()))
				{
					TipusUsuariEntity tipusu = getTipusUsuariEntityDao().findByCodi(politicaContrasenyaDomini.getTipusUsuari());
					throw new Exception(String.format(Messages.getString("DominiUsuariServiceImpl.6"),  //$NON-NLS-1$
							tipusu.getDescripcio(),
							politicaContrasenyaDomini.getCodiDominiContrasenya()));
				}
			}
		
		checkpoliticaContrasenyaDominiValues(politicaContrasenyaDomini);

		PoliticaContrasenyaEntity entity = getPoliticaContrasenyaEntityDao().politicaContrasenyaToEntity(politicaContrasenyaDomini);
		getPoliticaContrasenyaEntityDao().create(entity);
		return getPoliticaContrasenyaEntityDao().toPoliticaContrasenya(entity);

	}

	/** Method that implements the functionality to check the password policy values.
	 * @param pcd Password policy to check.
	 */
	private void checkpoliticaContrasenyaDominiValues (PoliticaContrasenya pcd)
		throws Exception
	{
		checkPasswordLength(pcd);
		checkUppercaseLettersLength(pcd);
		checkLowercaseLettersLength(pcd);
		checkNumberLength(pcd);
		checkSymbolsLength(pcd);
		
		checkGlobalMinValues(pcd);
		checkGlobalMaxValues(pcd);
	}

	/** Method that implements the functionality to check the password policy minimum values.
	 * @param pcd Password policy to check.
	 */
	private void checkGlobalMaxValues (PoliticaContrasenya pcd)
		throws Exception
	{
		long maxValues = 0;
		
		if (pcd.getMaxLongitud() != null)
		{
			if (pcd.getMaxMajuscules() != null)
				maxValues += pcd.getMaxMajuscules();
			
			if (pcd.getMaxMinuscules() != null)
				maxValues += pcd.getMaxMinuscules();
			
			if (pcd.getMaxNumeros() != null)
				maxValues += pcd.getMaxNumeros();
			
			if (pcd.getMaxSignesPuntuacio() != null)
				maxValues += pcd.getMaxSignesPuntuacio();
			
			if (pcd.getMaxLongitud() < maxValues)
			{
				throw new Exception(Messages.getString("DominiUsuariServiceImpl.MaxValuesLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy maximum values.
	 * @param pcd Password policy to check.
	 */
	private void checkGlobalMinValues (PoliticaContrasenya pcd)
		throws Exception
	{
		long minValues = 0;
		
		if (pcd.getMinLongitud() != null)
		{
    		if (pcd.getMinMajuscules() != null)
    			minValues += pcd.getMinMajuscules();
    		
    		if (pcd.getMinMinuscules() != null)
    			minValues += pcd.getMinMinuscules();
    		
    		if (pcd.getMinNumeros() != null)
    			minValues += pcd.getMinNumeros();
    		
    		if (pcd.getMinSignesPuntuacio() != null)
    			minValues += pcd.getMinSignesPuntuacio();
    		
    		if (pcd.getMinLongitud() < minValues)
    		{
    			throw new Exception(Messages.getString("DominiUsuariServiceImpl.MinValuesLengthErrorMessage")); //$NON-NLS-1$
    		}
		}
	}

	/** Method that implements the functionality to check the password policy symbols length.
	 * @param pcd Password policy to check.
	 */
	private void checkSymbolsLength (PoliticaContrasenya pcd)
		throws Exception
	{
		if ((pcd.getMinSignesPuntuacio() != null) &&
			(pcd.getMaxSignesPuntuacio() != null))
		{
			if (pcd.getMinSignesPuntuacio() > pcd.getMaxSignesPuntuacio())
			{
				throw new Exception(Messages.getString("DominiUsuariServiceImpl.MaxSymbolsLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy numbers length.
	 * @param pcd Password policy to check.
	 */
	private void checkNumberLength (PoliticaContrasenya pcd)
		throws Exception
	{
		if ((pcd.getMinNumeros() != null) && (pcd.getMaxNumeros() != null))
		{
			if (pcd.getMinNumeros() > pcd.getMaxNumeros())
			{
				throw new Exception(Messages.getString("DominiUsuariServiceImpl.MaxNumbersLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy lower case length.
	 * @param pcd Password policy to check.
	 */
	private void checkLowercaseLettersLength (PoliticaContrasenya pcd)
		throws Exception
	{
		if ((pcd.getMinMinuscules() != null) &&
			(pcd.getMaxMinuscules() != null))
		{
			if (pcd.getMinMinuscules() > pcd.getMaxMinuscules())
			{
				throw new Exception(Messages.getString("DominiUsuariServiceImpl.MaxLowerLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy upper case length.
	 * @param pcd Password policy to check.
	 */
	private void checkUppercaseLettersLength (PoliticaContrasenya pcd)
		throws Exception
	{
		if ((pcd.getMinMajuscules() != null) &&
			(pcd.getMaxMajuscules() != null))
		{
			if (pcd.getMinMajuscules() > pcd.getMaxMajuscules())
			{
				throw new Exception(Messages.getString("DominiUsuariServiceImpl.MaxUpperLengthErrorMessage")); //$NON-NLS-1$
			}
		}
	}

	/** Method that implements the functionality to check the password policy length.
	 * @param pcd Password policy to check.
	 */
	private void checkPasswordLength (PoliticaContrasenya pcd)
		throws Exception
	{
		// Check password length
		if ((pcd.getMinLongitud() != null) && (pcd.getMaxLongitud() != null))
		{
			if (pcd.getMinLongitud() > pcd.getMaxLongitud())
			{
				throw new Exception(
					Messages.getString(Messages.getString("DominiUsuariServiceImpl.MaxLengthErrorMessage"))); //$NON-NLS-1$
			}
		}
	}

	@Override
	protected PoliticaContrasenya handleUpdate(PoliticaContrasenya politicaContrasenyaDomini) throws Exception {
		if (politicaContrasenyaDomini.getTipusUsuari() == null || "".equals(politicaContrasenyaDomini.getTipusUsuari())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.7")); //$NON-NLS-1$
		}

		if (politicaContrasenyaDomini.getTipus() == null || "".equals(politicaContrasenyaDomini.getTipus())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.8")); //$NON-NLS-1$
		}

		PoliticaContrasenyaEntity entity = getPoliticaContrasenyaEntityDao().politicaContrasenyaToEntity(politicaContrasenyaDomini);

		getPoliticaContrasenyaEntityDao().update(entity);
		return getPoliticaContrasenyaEntityDao().toPoliticaContrasenya(entity);
	}

	@Override
	protected void handleDelete(PoliticaContrasenya politicaContrasenyaDomini) throws Exception {
		PoliticaContrasenyaEntity entity = getPoliticaContrasenyaEntityDao().politicaContrasenyaToEntity(politicaContrasenyaDomini);
		getPoliticaContrasenyaEntityDao().remove(entity);
	}

	@Override
	protected ParaulaProhibidaPoliticaContrasenya handleCreate(ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenyaDomini)
			throws Exception {
		ParaulaProhibidaPoliticaContrasenyaEntity entity = getParaulaProhibidaPoliticaContrasenyaEntityDao()
				.paraulaProhibidaPoliticaContrasenyaToEntity(paraulaProhibidaContrasenyaDomini);
		getParaulaProhibidaPoliticaContrasenyaEntityDao().create(entity);
		return getParaulaProhibidaPoliticaContrasenyaEntityDao().toParaulaProhibidaPoliticaContrasenya(entity);
	}

	@Override
	protected ParaulaProhibidaPoliticaContrasenya handleUpdate(ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenyaDomini)
			throws Exception {
		ParaulaProhibidaPoliticaContrasenyaEntity entity = getParaulaProhibidaPoliticaContrasenyaEntityDao()
				.paraulaProhibidaPoliticaContrasenyaToEntity(paraulaProhibidaContrasenyaDomini);
		getParaulaProhibidaPoliticaContrasenyaEntityDao().update(entity);
		return getParaulaProhibidaPoliticaContrasenyaEntityDao().toParaulaProhibidaPoliticaContrasenya(entity);
	}

	@Override
	protected void handleDelete(ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenyaDomini) throws Exception {
		ParaulaProhibidaPoliticaContrasenyaEntity entity = getParaulaProhibidaPoliticaContrasenyaEntityDao()
				.paraulaProhibidaPoliticaContrasenyaToEntity(paraulaProhibidaContrasenyaDomini);
		getParaulaProhibidaPoliticaContrasenyaEntityDao().remove(entity);
	}

	@Override
	protected DominiContrasenya handleCreate(DominiContrasenya dominiContrasenya) throws Exception {
		if (dominiContrasenya.getCodi() == null || "".equals(dominiContrasenya.getCodi().trim())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.9")); //$NON-NLS-1$
		}
		DominiContrasenyaEntity contrasenyaDominiEntity = getDominiContrasenyaEntityDao().dominiContrasenyaToEntity(dominiContrasenya);
		
		getDominiContrasenyaEntityDao().create(contrasenyaDominiEntity);
		return getDominiContrasenyaEntityDao().toDominiContrasenya(contrasenyaDominiEntity);
	}

	@Override
	protected DominiContrasenya handleUpdate(DominiContrasenya dominiContrasenya) throws Exception {
		if (dominiContrasenya.getCodi() == null || "".equals(dominiContrasenya.getCodi().trim())) { //$NON-NLS-1$
			throw new Exception(Messages.getString("DominiUsuariServiceImpl.11")); //$NON-NLS-1$
		}
		DominiContrasenyaEntity contrasenyaDominiEntity = getDominiContrasenyaEntityDao().dominiContrasenyaToEntity(dominiContrasenya);

		getDominiContrasenyaEntityDao().update(contrasenyaDominiEntity);
		return getDominiContrasenyaEntityDao().toDominiContrasenya(contrasenyaDominiEntity);
	}

	@Override
	protected void handleDelete(DominiContrasenya dominiContrasenya) throws Exception {
		DominiContrasenyaEntity contrasenyaDominiEntity = getDominiContrasenyaEntityDao().dominiContrasenyaToEntity(dominiContrasenya);

		getDominiContrasenyaEntityDao().remove(contrasenyaDominiEntity);
	}

	final static String DU_DU = "DU"; // Domini d'usuaris //$NON-NLS-1$
	final static String DU_DU_S = "Domini d'usuaris"; //$NON-NLS-1$
	final static String DU_DC = "DC"; // Domini de contrasenyes //$NON-NLS-1$
	// Domini de contrasenyes:
	final static String DU_DC_S = "Domini de contrasenyes";  //$NON-NLS-1$
	final static String DU_PC = "PC"; // Politica de contrasenyes de domini //$NON-NLS-1$
	// Politica de contrasenyes de domini:
	final static String DU_PC_S = "Política de contrasenyes";  //$NON-NLS-1$

	@Override
	protected DominiUsuari handleFindDominiUsuariByCodi(String codiDominiUsuari) throws Exception {
		DominiUsuariEntity d = getDominiUsuariEntityDao().findByCodi(codiDominiUsuari);
		if (d == null)
			return null;
		else
			return getDominiUsuariEntityDao().toDominiUsuari(d);
	}

	@Override
	protected Collection<ParaulaProhibidaPoliticaContrasenya> handleFindParaulesProhibidesPoliticaContrasenya(
			PoliticaContrasenya politicaContrasenya) throws Exception {
		PoliticaContrasenyaEntity entity = getPoliticaContrasenyaEntityDao().politicaContrasenyaToEntity(politicaContrasenya);
		// relació entre ParaulesProhibidesEntity i PoliticaContrasenyaEntity
		Collection<ParaulaProhibidaPoliticaContrasenyaEntity> paraulesContrasenya = new LinkedList(entity.getParaulaProhibidaContrasenya());
		// Tornem les relacions ParaulaProhibidaPoliticaContrasenya
		return getParaulaProhibidaPoliticaContrasenyaEntityDao().toParaulaProhibidaPoliticaContrasenyaList(paraulesContrasenya);
	}

	@Override
	protected DominiContrasenya handleFindDominiContrasenyaByCodi(String codi)
			throws Exception
	{
		DominiContrasenyaEntity dce = getDominiContrasenyaEntityDao().findByCodi(codi);
		if (dce == null)
			return null;
		else
			return getDominiContrasenyaEntityDao().toDominiContrasenya(dce);
	}

	@Override
	protected PoliticaContrasenya handleFindPoliticaByTipusAndDominiContrasenyas(
			String tipus, String domini) throws Exception
	{
		PoliticaContrasenyaEntity pce = getPoliticaContrasenyaEntityDao().findByDominiContrasenyaTipusUsuari(domini, tipus);
		if (pce == null)
			return null;
		else
			return getPoliticaContrasenyaEntityDao().toPoliticaContrasenya(pce);
	}

	@Override
	protected Collection<DominiContrasenya> handleFindAllDominiContrasenya()
			throws Exception
	{
		return getDominiContrasenyaEntityDao().toDominiContrasenyaList(
				getDominiContrasenyaEntityDao().loadAll());
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	@Override
	protected Collection<String> handleFindNameGenerators() throws Exception
	{
		LinkedList<String> result =  new LinkedList<String>();
		for (String name: applicationContext.getBeanNamesForType(AccountNameGenerator.class))
		{
			result.add(name);
		}
		return result;
	}
}