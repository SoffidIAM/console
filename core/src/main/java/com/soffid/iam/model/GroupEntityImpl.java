// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

/**
 * @see es.caib.seycon.ng.model.GrupEntity
 */
public class GroupEntityImpl extends com.soffid.iam.model.GroupEntity {
	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = -6294585483589486558L;

	/**
	 * @see es.caib.seycon.ng.model.GrupEntity#toString()
	 */
	public java.lang.String toString() {
		// @todo implement public java.lang.String toString()
		return String.format(Messages.getString("GroupEntityImpl.toString"), getId(), getCode(), getDescription(), getOrganizatinalUnitType());
	}
}