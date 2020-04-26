package com.soffid.iam.web.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.web.utils.Messages;

public class UserDomainType {
	
	
	public static List<UserDomainType> values  = new ArrayList<UserDomainType>();
	
	static {
		values.add(new UserDomainType(TipusDominiUsuariEnumeration.PRINCIPAL, 
				Messages.getString("ValorTipusDominiUsuari.3"))); //$NON-NLS-1$
		values.add(new UserDomainType(TipusDominiUsuariEnumeration.OPERADOR, 
				Messages.getString("ValorTipusDominiUsuari.1"))); //$NON-NLS-1$
		values.add(new UserDomainType(TipusDominiUsuariEnumeration.SHELL, 
				Messages.getString("ValorTipusDominiUsuari.5"))); //$NON-NLS-1$
		values.add(new UserDomainType(TipusDominiUsuariEnumeration.SPRINGCLASS, 
				Messages.getString("ValorTipusDominiUsuari.7"))); //$NON-NLS-1$
		
	}

	public String desc;
	public TipusDominiUsuariEnumeration value;

	public UserDomainType(TipusDominiUsuariEnumeration value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public TipusDominiUsuariEnumeration getValue() {
		return value;
	}

}
