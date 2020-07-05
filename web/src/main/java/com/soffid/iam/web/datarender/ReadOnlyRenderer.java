package com.soffid.iam.web.datarender;

import org.json.JSONObject;
import org.zkoss.zk.ui.Component;

import com.soffid.iam.api.DataType;
import com.soffid.iam.web.users.additionalData.InputField2;

public class ReadOnlyRenderer extends DefaultRenderer {

	@Override
	public Component renderInputField(DataType dt, Component parent, boolean readonly, 
			Object ownerObject, String ownerContext,
			String bind) throws Exception {
		return super.renderInputField(dt, parent, true, ownerObject, ownerContext, bind);
	}

}
