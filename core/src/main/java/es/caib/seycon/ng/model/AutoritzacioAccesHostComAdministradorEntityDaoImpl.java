// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity
 */
public class AutoritzacioAccesHostComAdministradorEntityDaoImpl
		extends
		es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDaoBase {
	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#toAutoritzacioAccesHostComAdministrador(es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity,
	 *      es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador)
	 */
	public void toAutoritzacioAccesHostComAdministrador(
			es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity source,
			es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador target) {
		// @todo verify behavior of toAutoritzacioAccesHostComAdministrador
		super.toAutoritzacioAccesHostComAdministrador(source, target);
		// WARNING! No conversion for target.dataCaducitatAutoritzacioAcces
		// (can't convert
		// source.getDataCaducitatAutoritzacioAcces():java.util.Date to
		// java.util.Date
		toAutoritzacioAccesHostComAdministradorCustom(source, target);

	}

	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#toAutoritzacioAccesHostComAdministrador(es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity)
	 */
	public es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador toAutoritzacioAccesHostComAdministrador(
			final es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity entity) {
		// @todo verify behavior of toAutoritzacioAccesHostComAdministrador
		return super.toAutoritzacioAccesHostComAdministrador(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity loadAutoritzacioAccesHostComAdministradorEntityFromAutoritzacioAccesHostComAdministrador(
			es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador autoritzacioAccesHostComAdministrador) {

		es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity autoritzacioAccesHostComAdministradorEntity = null;
		
		if (autoritzacioAccesHostComAdministrador.getId()!=null) {
			autoritzacioAccesHostComAdministradorEntity = this.load(autoritzacioAccesHostComAdministrador.getId());
		}
		if (autoritzacioAccesHostComAdministradorEntity == null) {
			autoritzacioAccesHostComAdministradorEntity = newAutoritzacioAccesHostComAdministradorEntity();
		}
		return autoritzacioAccesHostComAdministradorEntity;
		 
	}

	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#autoritzacioAccesHostComAdministradorToEntity(es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador)
	 */
	public es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity autoritzacioAccesHostComAdministradorToEntity(
			es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador autoritzacioAccesHostComAdministrador) {
		es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity entity = this
				.loadAutoritzacioAccesHostComAdministradorEntityFromAutoritzacioAccesHostComAdministrador(autoritzacioAccesHostComAdministrador);
		this.autoritzacioAccesHostComAdministradorToEntity(
				autoritzacioAccesHostComAdministrador, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntityDao#autoritzacioAccesHostComAdministradorToEntity(es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador,
	 *      es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity)
	 */
	public void autoritzacioAccesHostComAdministradorToEntity(
			es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador source,
			es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity target,
			boolean copyIfNull) {
		super.autoritzacioAccesHostComAdministradorToEntity(source, target,
				copyIfNull);
		autoritzacioAccesHostComAdministradorToEntityCustom(source, target);
	}

	public void toAutoritzacioAccesHostComAdministradorCustom(
			es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity source,
			es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador target) {
		
		UsuariEntity usu = source.getUsuari();
		MaquinaEntity maq = source.getHost();
		target.setCodiUsuari(usu.getCodi());
		if (usu.getNomCurt()!=null && usu.getDominiCorreu()!=null) 
			target.setCorreuUsuari(usu.getNomCurt()+"@"+usu.getDominiCorreu().getCodi()); //$NON-NLS-1$
		
		Calendar data = GregorianCalendar.getInstance();
		data.setTime(source.getDataCaducitatAutoritzacioAcces());
		target.setDataCaducitatAutoritzacioAcces(data);
		target.setDescripcioHost(maq.getDescripcio());
		target.setIpHost(maq.getAdreca());
		target.setNomHost(maq.getNom());
		target.setNomUsuari(usu.getNom()+" "+usu.getPrimerLlinatge()+" "+usu.getSegonLlinatge()); //$NON-NLS-1$ //$NON-NLS-2$
		target.setXarxaHost(maq.getXarxa().getCodi());
		Calendar dataPeticio = GregorianCalendar.getInstance();
		if (source.getDataPeticio()!=null) dataPeticio.setTime(source.getDataPeticio());
		target.setDataPeticio(dataPeticio);

	}

	public void autoritzacioAccesHostComAdministradorToEntityCustom(
			es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador source,
			es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity target) {
		
		// Hem de cercar: usuari i màquina
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(source.getCodiUsuari());
		MaquinaEntity host = getMaquinaEntityDao().findByNom(source.getNomHost());
		
		if (source.getDataPeticio() !=null) {
			target.setDataPeticio(source.getDataPeticio().getTime());
		}
		target.setDataCaducitatAutoritzacioAcces(source.getDataCaducitatAutoritzacioAcces().getTime());
		target.setUsuari(usuari);
		target.setHost(host);
		target.setIdProcesWorkflow(source.getIdProcesWorkflow());
		
	}

}