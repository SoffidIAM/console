package es.caib.seycon.ng.web.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;

public class ValorTipusDominiUsuari {
	
	
	public static List<ValorTipusDominiUsuari> valorsTipusDominiUsuari  = new ArrayList<ValorTipusDominiUsuari>();
	
	static {
		valorsTipusDominiUsuari.add(new ValorTipusDominiUsuari(TipusDominiUsuariEnumeration.PRINCIPAL, 
				Messages.getString("ValorTipusDominiUsuari.3"))); //$NON-NLS-1$
		valorsTipusDominiUsuari.add(new ValorTipusDominiUsuari(TipusDominiUsuariEnumeration.OPERADOR, 
				Messages.getString("ValorTipusDominiUsuari.1"))); //$NON-NLS-1$
		valorsTipusDominiUsuari.add(new ValorTipusDominiUsuari(TipusDominiUsuariEnumeration.SHELL, 
				Messages.getString("ValorTipusDominiUsuari.5"))); //$NON-NLS-1$
		valorsTipusDominiUsuari.add(new ValorTipusDominiUsuari(TipusDominiUsuariEnumeration.SPRINGCLASS, 
				Messages.getString("ValorTipusDominiUsuari.7"))); //$NON-NLS-1$
		
	}

	public String desc;
	public TipusDominiUsuariEnumeration value;

	public ValorTipusDominiUsuari(TipusDominiUsuariEnumeration value, String desc) {
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
