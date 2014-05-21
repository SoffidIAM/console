// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Calendar;

import es.caib.seycon.ng.comu.ContingutTargetaExtranet;

/**
 * @see es.caib.seycon.ng.model.ScContar
 */
public class ScContarDaoImpl extends es.caib.seycon.ng.model.ScContarDaoBase {
	/**
	 * @see es.caib.seycon.ng.model.ScContarDao#toContingutTargetaExtranet(es.caib.seycon.ng.model.ScContar,
	 *      es.caib.seycon.ng.comu.ContingutTargetaExtranet)
	 */
	public void toContingutTargetaExtranet(
			es.caib.seycon.ng.model.ScContar source,
			es.caib.seycon.ng.comu.ContingutTargetaExtranet target) {
		// @todo verify behavior of toContingutTargetaExtranet
		super.toContingutTargetaExtranet(source, target);
		// WARNING! No conversion for target.dadaUs (can't convert
		// source.getDadaUs():java.util.Date to java.util.Date
		toContingutTargetaExtranetCustom(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.ScContarDao#toContingutTargetaExtranet(es.caib.seycon.ng.model.ScContar)
	 */
	public es.caib.seycon.ng.comu.ContingutTargetaExtranet toContingutTargetaExtranet(
			final es.caib.seycon.ng.model.ScContar entity) {
		// @todo verify behavior of toContingutTargetaExtranet
		return super.toContingutTargetaExtranet(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.ScContar loadScContarFromContingutTargetaExtranet(
			es.caib.seycon.ng.comu.ContingutTargetaExtranet ContingutTargetaExtranet) {
		// @todo implement loadScContarFromContingutTargetaExtranet
		throw new java.lang.UnsupportedOperationException(
				"es.caib.seycon.ng.model.loadScContarFromContingutTargetaExtranet(es.caib.seycon.ng.comu.ContingutTargetaExtranet) not yet implemented."); //$NON-NLS-1$

		/*
		 * A typical implementation looks like this:
		 * es.caib.seycon.ng.model.ScContar scContar =
		 * this.load(ContingutTargetaExtranet.getId()); if (scContar == null) {
		 * scContar = es.caib.seycon.ng.model.ScContar.Factory.newInstance(); }
		 * return scContar;
		 */
	}

	/**
	 * @see es.caib.seycon.ng.model.ScContarDao#ContingutTargetaExtranetToEntity(es.caib.seycon.ng.comu.ContingutTargetaExtranet)
	 */
	public es.caib.seycon.ng.model.ScContar contingutTargetaExtranetToEntity(
			es.caib.seycon.ng.comu.ContingutTargetaExtranet ContingutTargetaExtranet) {
		// @todo verify behavior of ContingutTargetaExtranetToEntity
		es.caib.seycon.ng.model.ScContar entity = this
				.loadScContarFromContingutTargetaExtranet(ContingutTargetaExtranet);
		this.ContingutTargetaExtranetToEntity(ContingutTargetaExtranet, entity,
				true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ScContarDao#ContingutTargetaExtranetToEntity(es.caib.seycon.ng.comu.ContingutTargetaExtranet,
	 *      es.caib.seycon.ng.model.ScContar)
	 */
	public void ContingutTargetaExtranetToEntity(
			es.caib.seycon.ng.comu.ContingutTargetaExtranet source,
			es.caib.seycon.ng.model.ScContar target, boolean copyIfNull) {
		// @todo verify behavior of ContingutTargetaExtranetToEntity
		super.contingutTargetaExtranetToEntity(source, target, copyIfNull);
		// No conversion for target.dadaUs (can't convert
		// source.getDadaUs():java.util.Date to java.util.Date
		ContingutTargetaExtranetToEntityCustom(source, target);
		
		
	}

	private void ContingutTargetaExtranetToEntityCustom(
			es.caib.seycon.ng.comu.ContingutTargetaExtranet source,
			es.caib.seycon.ng.model.ScContar target) {
	}
	
	private void toContingutTargetaExtranetCustom(
			es.caib.seycon.ng.model.ScContar source,
			es.caib.seycon.ng.comu.ContingutTargetaExtranet target) {
		
		if (source.getDadaUs()!=null) {
			Calendar dadaUs = Calendar.getInstance();
			dadaUs.setTime(source.getDadaUs());
			target.setDadaUs(dadaUs);
		}
		// Separamos fila y columna
		try {
			target.setFila(""+source.getFilcol().charAt(0)); //$NON-NLS-1$
			target.setColumna(""+source.getFilcol().charAt(1)); //$NON-NLS-1$
		} catch (Exception ex) {

		}

	}
}