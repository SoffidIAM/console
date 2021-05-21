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
 * Entity RoleAttributeEntity implementation
 */
public class MailListAttributeEntityImpl extends com.soffid.iam.model.MailListAttributeEntity {
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
