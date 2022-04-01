//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

/**
 * Entity GroupAttributeEntity implementation
 */
public class HostAttributeEntityImpl extends com.soffid.iam.model.HostAttributeEntity {

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
