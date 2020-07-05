package com.soffid.iam.web;

import java.util.List;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.LetterCaseEnum;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.comu.TypeEnumeration;


public class WebDataType extends DataType {

	public WebDataType() {
		super();
	}

	public WebDataType(DataType otherBean) {
		super(otherBean);
	}

	@Override
	public String getLabel() {
		String p = super.getLabel();
		if ( p == null || p.trim().isEmpty()) {
			if (getNlsLabel() != null)
				return Labels.getLabel(getNlsLabel());
		}
		return p;
	}
	
	@Override
	public void setLabel(String label) {
		if (getNlsLabel() != null) {
			String p = Labels.getLabel(getNlsLabel());
			if (label != null && label.equals(p))
				label = null;
		}
		super.setLabel(label);
	}
}
