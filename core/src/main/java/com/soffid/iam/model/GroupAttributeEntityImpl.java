//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.caib.seycon.ng.comu.TypeEnumeration;

/**
 * Entity GroupAttributeEntity implementation
 */
public class GroupAttributeEntityImpl extends com.soffid.iam.model.GroupAttributeEntity {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss"); //$NON-NLS-1$
	private static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss"); //$NON-NLS-1$

	@Override
	public void setObjectValue(Object value) {
		AttributeParser ap = new AttributeParser(getMetadata().getName(), getMetadata().getType(), value);
		setValue(ap.getValue());
		setBlobDataValue(ap.getBlobValue());
	}

	@Override
	public Object getObjectValue() {
		return AttributeParser.getObjectValue( getMetadata().getType(), getValue(), getBlobDataValue());
	}


}
