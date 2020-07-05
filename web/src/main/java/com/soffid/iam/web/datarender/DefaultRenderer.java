package com.soffid.iam.web.datarender;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONObject;
import org.zkoss.zk.ui.Component;

import com.soffid.iam.api.DataType;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.users.additionalData.InputField2;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class DefaultRenderer extends DataTypeRenderer {

	@Override
	public JSONObject renderColumn(DataType d) {
		WebDataType dt = new WebDataType(d);
		JSONObject o = new JSONObject();
		o.put("value", dt.getName());
		o.put("name", dt.getLabel());
		if (dt.getType() == TypeEnumeration.DATE_TYPE)
			o.put("template", "${"+dt.getName()+"_date}");
		if (dt.getType() == TypeEnumeration.DATE_TIME_TYPE)
			o.put("template", "${"+dt.getName()+"_datetime}");
		return o;
	}

	@Override
	public Component renderInputField(DataType dt, Component parent, boolean readonly, 
			Object ownerObject, String ownerContext,
			String bind) throws Exception {
		InputField2 inputField = new InputField2();
		inputField.setParent(parent);
		inputField.setDataType(dt);
		inputField.setReadonly(readonly);
		inputField.setOwnerContext(ownerContext);
		inputField.setOwnerObject(ownerObject);
		inputField.setBind(bind);
		inputField.createField();
		return inputField;
	}

}
