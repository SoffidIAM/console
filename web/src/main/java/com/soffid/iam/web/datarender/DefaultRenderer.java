package com.soffid.iam.web.datarender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONObject;
import org.zkoss.mesg.Messages;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.mesg.MZul;

import com.soffid.iam.api.DataType;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.users.additionalData.InputField2;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox.Type;

public class DefaultRenderer extends DataTypeRenderer {

	@Override
	public JSONObject renderColumn(DataType d) {
		WebDataType dt = new WebDataType(d);
		JSONObject o = new JSONObject();
		String prefix = Boolean.TRUE.equals(d.getBuiltin()) ?  "": "attributes.";
		o.put("value", prefix+dt.getName());
		o.put("name", dt.getLabel());
		if (dt.getType() == TypeEnumeration.DATE_TYPE) {
			o.put("template", "${"+prefix+dt.getName()+"_date}");
			o.put("className", "dateColumn");
		}
		else if (dt.getType() == TypeEnumeration.DATE_TIME_TYPE) {
			o.put("template", "${"+prefix+dt.getName()+"_datetime}");
			o.put("className", "datetimeColumn");
		}
		else if (dt.getType() == TypeEnumeration.BOOLEAN_TYPE) {
			String yes = Messages.get(MZul.YES);
			String no = Messages.get(MZul.NO);
			o.put("template", "${"+prefix+dt.getName()+ "? '" +yes+ "' : '"+no+ "' }");
			o.put("className", "statusColumn");
		}
		else if (dt.getType() == TypeEnumeration.NUMBER_TYPE) {
			o.put("value", "parseInt ("+prefix+dt.getName()+")");
			o.put("className", "statusColumn");
		}
		else if (dt.getType() == TypeEnumeration.BINARY_TYPE) {
			o.put("value", prefix+dt.getName());
		}
		else if (dt.getType() == TypeEnumeration.ATTACHMENT_TYPE) {
			o.put("value", prefix+dt.getName()+".name");
		}
		else if (dt.getType() == TypeEnumeration.PASSWORD_TYPE) {
			o.put("template", "*******");
			o.put("className", "statusColumn");
		}
		else if (dt.getType() == TypeEnumeration.PHOTO_TYPE) {
			o.put("template", "<img class='small-picture' style='display:${"+prefix+dt.getName()+"?'inline-block':'none'}' "
					+ "src='data:${"+prefix+dt.getName()+"_contentType};base64,${"+
					prefix+dt.getName()+ "}'/>");
			o.put("className", "statusColumn");
		}
		else if (dt.getEnumeration() != null && ! dt.getEnumeration().trim().isEmpty()) {
			try {
				String s = "${";
				Class<?> cl = Class.forName(dt.getEnumeration());
				for (Field field: cl.getFields()) {
					if ( (field.getModifiers() & Modifier.STATIC) != 0) {
						if (field.getType() == cl) {
							String name = field.getName();
							Object value = field.get(null);
							String v = value.toString();
							String label = Labels.getLabel(cl.getName()+"."+name);
							if (label == null || label.trim().isEmpty())
								label = name;
							s += prefix+dt.getName()+".value=='"+jsEncode(v)+"'? '"+jsEncode(label)+"':";
						}
					}
				}
				s += "''}";
				o.put("template", s);
			} catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		else if (dt.getValues() != null && !dt.getValues().isEmpty()) {
			String s = "${";
			for (String value: dt.getValues()) {
				int i = value.indexOf(':');
				if (i > 0) 
					s += prefix+dt.getName()+"=='"+jsEncode( value.substring(0, i).trim())+"'? '"+jsEncode(value.substring(i+1).trim())+"':";
			}
			s += prefix+dt.getName()+"}";
			o.put("template", s);
		}
		return o;
	}

	private String jsEncode(String v) {
		return v;
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
