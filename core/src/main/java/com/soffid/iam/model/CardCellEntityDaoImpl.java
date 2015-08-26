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

import es.caib.seycon.ng.comu.ContingutTargetaExtranet;
import es.caib.seycon.ng.model.*;

import java.util.Calendar;

/**
 * @see es.caib.seycon.ng.model.ScContar
 */
public class CardCellEntityDaoImpl extends com.soffid.iam.model.CardCellEntityDaoBase {
	/**
	 * @see es.caib.seycon.ng.model.ScContarDao#toContingutTargetaExtranet(es.caib.seycon.ng.model.ScContar,
	 *      es.caib.seycon.ng.comu.ContingutTargetaExtranet)
	 */
	public void toExtranetCardContent(com.soffid.iam.model.CardCellEntity source, com.soffid.iam.api.ExtranetCardContent target) {
		// @todo verify behavior of toContingutTargetaExtranet
		super.toExtranetCardContent(source, target);
		// WARNING! No conversion for target.dadaUs (can't convert
		// source.getDadaUs():java.util.Date to java.util.Date
		toContingutTargetaExtranetCustom(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.ScContarDao#toContingutTargetaExtranet(es.caib.seycon.ng.model.ScContar)
	 */
	public com.soffid.iam.api.ExtranetCardContent toExtranetCardContent(final com.soffid.iam.model.CardCellEntity entity) {
		// @todo verify behavior of toContingutTargetaExtranet
		return super.toExtranetCardContent(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.CardCellEntity loadScContarFromContingutTargetaExtranet(com.soffid.iam.api.ExtranetCardContent ContingutTargetaExtranet) {
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
	public com.soffid.iam.model.CardCellEntity extranetCardContentToEntity(com.soffid.iam.api.ExtranetCardContent ContingutTargetaExtranet) {
		// @todo verify behavior of ContingutTargetaExtranetToEntity
		com.soffid.iam.model.CardCellEntity entity = this.loadScContarFromContingutTargetaExtranet(ContingutTargetaExtranet);
		this.ContingutTargetaExtranetToEntity(ContingutTargetaExtranet, entity,
				true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ScContarDao#ContingutTargetaExtranetToEntity(es.caib.seycon.ng.comu.ContingutTargetaExtranet,
	 *      es.caib.seycon.ng.model.ScContar)
	 */
	public void ContingutTargetaExtranetToEntity(com.soffid.iam.api.ExtranetCardContent source, com.soffid.iam.model.CardCellEntity target, boolean copyIfNull) {
		// @todo verify behavior of ContingutTargetaExtranetToEntity
		super.extranetCardContentToEntity(source, target, copyIfNull);
		// No conversion for target.dadaUs (can't convert
		// source.getDadaUs():java.util.Date to java.util.Date
		ContingutTargetaExtranetToEntityCustom(source, target);
		
		
	}

	private void ContingutTargetaExtranetToEntityCustom(com.soffid.iam.api.ExtranetCardContent source, com.soffid.iam.model.CardCellEntity target) {
	}
	
	private void toContingutTargetaExtranetCustom(com.soffid.iam.model.CardCellEntity source, com.soffid.iam.api.ExtranetCardContent target) {
		
		if (source.getExpirationDate() != null) {
			Calendar dadaUs = Calendar.getInstance();
			dadaUs.setTime(source.getExpirationDate());
			target.setLastUsedDate(dadaUs);
		}
		// Separamos fila y columna
		try {
			target.setRow("" + source.getCell().charAt(0)); //$NON-NLS-1$
			target.setColumn("" + source.getCell().charAt(1)); //$NON-NLS-1$
		} catch (Exception ex) {

		}

	}

}