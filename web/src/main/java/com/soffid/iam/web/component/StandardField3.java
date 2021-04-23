package com.soffid.iam.web.component;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.WebDataType;

import es.caib.seycon.ng.exception.InternalErrorException;

public class StandardField3 extends InputField3 {
	private static final long serialVersionUID = 1L;
	String attribute;
	private DataType dataType;
	
	public String getAttribute() {
		return attribute;
	}
	
	public void setAttribute(String attribute) throws InternalErrorException, NamingException, CreateException {
		this.attribute = attribute;
		int lastDot = attribute.lastIndexOf('.');
		if (lastDot < 0) throw new UiException("Bad attribute name");
		String objectType = attribute.substring(0, lastDot);
		String attName = attribute.substring(lastDot+1);
		
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			Collection<DataType> list = EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(objectType, attName);
			if (list == null || list.isEmpty())
				throw new UiException("Cannot find attribute definition "+attName+" in object "+objectType);
			dataType = list.iterator().next();
			dataType = new WebDataType(dataType);
			setDataType( dataType );
		} finally {
			Security.nestedLogoff();
		}
	}

	@Override
	public void setReadonly(boolean readonly) {
		super.setReadonly(readonly);
		if (dataType != null)
			dataType.setReadOnly(readonly);
	}

	@Override
	public void setRequired(boolean required) {
		super.setRequired(required);
		if (dataType != null)
			dataType.setRequired(required);
	}
}