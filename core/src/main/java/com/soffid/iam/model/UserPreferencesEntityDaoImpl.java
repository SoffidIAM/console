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

import com.soffid.iam.model.UserEntity;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @see es.caib.seycon.ng.model.UsuariSEUEntity
 */
public class UserPreferencesEntityDaoImpl extends
		com.soffid.iam.model.UserPreferencesEntityDaoBase {
	/**
	 * @see es.caib.seycon.ng.model.UsuariSEUEntityDao#toUsuariSEU(es.caib.seycon.ng.model.UsuariSEUEntity,
	 *      es.caib.seycon.ng.comu.UsuariSEU)
	 */
	public void toUsuariSEU(com.soffid.iam.model.UserPreferencesEntity source, es.caib.seycon.ng.comu.UsuariSEU target) {
		// @todo verify behavior of toUsuariSEU
		super.toUsuariSEU(source, target);

		toUsuariSEUCustom(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.UsuariSEUEntityDao#toUsuariSEU(es.caib.seycon.ng.model.UsuariSEUEntity)
	 */
	public es.caib.seycon.ng.comu.UsuariSEU toUsuariSEU(final com.soffid.iam.model.UserPreferencesEntity entity) {
		// @todo verify behavior of toUsuariSEU
		return super.toUsuariSEU(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.UserPreferencesEntity loadUsuariSEUEntityFromUsuariSEU(es.caib.seycon.ng.comu.UsuariSEU usuariSEU) {
		com.soffid.iam.model.UserPreferencesEntity usuariSEUEntity = null;
		if (usuariSEU.getId() != null) {
			usuariSEUEntity = this.load(usuariSEU.getId());
		}
		if (usuariSEUEntity == null) {
			usuariSEUEntity = newUserPreferencesEntity();
		}
		return usuariSEUEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.UsuariSEUEntityDao#usuariSEUToEntity(es.caib.seycon.ng.comu.UsuariSEU)
	 */
	public com.soffid.iam.model.UserPreferencesEntity usuariSEUToEntity(es.caib.seycon.ng.comu.UsuariSEU usuariSEU) {
		// @todo verify behavior of usuariSEUToEntity
		com.soffid.iam.model.UserPreferencesEntity entity = this.loadUsuariSEUEntityFromUsuariSEU(usuariSEU);
		this.usuariSEUToEntity(usuariSEU, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.UsuariSEUEntityDao#usuariSEUToEntity(es.caib.seycon.ng.comu.UsuariSEU,
	 *      es.caib.seycon.ng.model.UsuariSEUEntity)
	 */
	public void usuariSEUToEntity(es.caib.seycon.ng.comu.UsuariSEU source, com.soffid.iam.model.UserPreferencesEntity target, boolean copyIfNull) {
		// @todo verify behavior of usuariSEUToEntity
		super.usuariSEUToEntity(source, target, copyIfNull);
		// No conversion for target.dataDarrerLogin (can't convert
		// source.getDataDarrerLogin():java.util.Date to java.util.Date

		usuariSEUToEntityCustom(source, target);
	}

	public void toUsuariSEUCustom(com.soffid.iam.model.UserPreferencesEntity source, es.caib.seycon.ng.comu.UsuariSEU target) {

		target.setCodiUsuari(source.getUser().getUserName());

		// Mostrem la data de darrer login al seu
		if (source.getLastLoginData() != null) {
			Calendar darrerAccesSEU = Calendar.getInstance();
			darrerAccesSEU.setTime(source.getLastLoginData());
			target.setDataDarrerLogin(darrerAccesSEU);
		}

		// Obtenim les preferències de l'usuari
		Map preferencies = new HashMap();
		if (source.getPreferences() != null) {
			String[] favs = source.getPreferences().split("#-#"); // SEPARADOR //$NON-NLS-1$
			for (int i = 0; i < favs.length; i++) {
				if (favs[i] != null) {
					// Ara separem key, value (key=value)
					String[] preff = favs[i].split("="); //$NON-NLS-1$
					// Format: clau=valor
					if (preff.length == 2) {
						String key = preff[0];
						String val = preff[1];
						if (key != null && val != null) {
							key = key.trim();
							val = val.trim();
							preferencies.put(key, val);
						}
					}
				}
			}
		}
		// Guardem les preferències de l'usuari
		target.setPreferenciesSEU(preferencies);
		// Establim certes values de preferències (no funciona el EL d'accés a
		// Map)
		target.setIdioma((String) preferencies.get("lang")); //$NON-NLS-1$
		target.setLastIP((String) preferencies.get("lastIP")); //$NON-NLS-1$
	}

	private void usuariSEUToEntityCustom(es.caib.seycon.ng.comu.UsuariSEU source, com.soffid.iam.model.UserPreferencesEntity target) {

		UserEntity usuari = getUserEntityDao().findByUserName(source.getCodiUsuari());
		
		target.setUser(usuari);

		// Guardamos fecha de última sesión del SEU:
		if (source.getDataDarrerLogin() != null) {
			target.setLastLoginData(source.getDataDarrerLogin().getTime());
		}

		// Guardem les preferencies-opcions de l'usuari
		Map prefs = source.getPreferenciesSEU();
		String preferencies = ""; //$NON-NLS-1$
		if (prefs != null) {
			for (Iterator it = prefs.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) prefs.get(key);
				// Guardem la preferencia key1=value1#-#...#-#keyn=valuen
				if (key != null && value != null)
					preferencies += key.trim() + "=" + value.trim() + "#-#"; // separador //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		target.setPreferences(preferencies);
	}

}