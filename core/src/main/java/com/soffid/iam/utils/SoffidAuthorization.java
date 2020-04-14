package com.soffid.iam.utils;

import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SoffidAuthorization {
	String name;
	String description;
	String type; //opcional [SENSE_DOMINI per defecte]
	String scope; //opcional
	String ambit; //opcional
	HashSet<String> inherits = new HashSet();
	String inherited; //opcional [com al xml]
	
	private static Log log = LogFactory.getLog(SoffidAuthorization.class);
	
	
	/**
	 * Construix una AutoritzacioSEU a partir de un node XML
	 * @param nodeAnalitzar
	 */
	public SoffidAuthorization(Element nodeAnalitzar) {
		
		String codiAutoritzacio = getAtributXML(nodeAnalitzar, "codi"); //$NON-NLS-1$
		
		if (codiAutoritzacio != null) {
			//té codi: sinó no analitzem més
			
			// cerquem els altres atributs
			String descripcio = getAtributXML(nodeAnalitzar, "descripcio"); //$NON-NLS-1$
			String tipusDomini = getAtributXML(nodeAnalitzar, "tipusDomini"); //$NON-NLS-1$
			String scope = getAtributXML(nodeAnalitzar, "scope"); //$NON-NLS-1$
			String autosHereta = getAtributXML(nodeAnalitzar, "hereta"); //$NON-NLS-1$
			String ambit = getAtributXML(nodeAnalitzar, "ambit"); //$NON-NLS-1$
			
			this.name = codiAutoritzacio;
			this.description = descripcio;
			this.type = tipusDomini;
			this.scope = scope;
			this.ambit = ambit;
			this.inherited = autosHereta;
			if (autosHereta!=null && !"".equals(autosHereta)) { //$NON-NLS-1$
				String [] autos_hereta = autosHereta.split(","); //$NON-NLS-1$
				if (autos_hereta !=null) {
					for (int i=0; i < autos_hereta.length; i++) {
						String auto = autos_hereta[i];
						if (auto!=null) inherits.add(auto.trim());
					}
				}
			}
		}
		
	}
	
		
	/**
	 * Construix "manualment" una autorització SEU
	 * @param codi
	 * @param descripcio
	 * @param tipusDomini
	 * @param scope
	 * @param autosHereta
	 */
	public SoffidAuthorization(String codi, String descripcio, String tipusDomini, String scope, String autosHereta) {
		this.name = codi;
		this.description = descripcio;
		this.type = tipusDomini;
		this.scope = scope;
		if (autosHereta!=null && !"".equals(autosHereta)) { //$NON-NLS-1$
			String [] autos_hereta = autosHereta.split(","); //$NON-NLS-1$
			if (autos_hereta !=null) {
				for (int i=0; i < autos_hereta.length; i++) {
					String auto = autos_hereta[i];
					if (auto!=null) inherits.add(auto.trim());
				}
			}
		}
	}
	
	private static String getAtributXML (Element elementPare, String nomAtribut) {
		// Cerquem els elements fill amb el nom "nomAtribut"
		NodeList elementsAtribut = elementPare.getElementsByTagName(nomAtribut);
		// Pot ésser opcional
		Element lstNmElmnt = (Element) elementsAtribut.item(0);
		try {
		if (lstNmElmnt != null) { // existeix l'atribut
			NodeList lstNm = lstNmElmnt.getChildNodes();
			//return ((org.w3c.dom.Node) lstNm.item(0)).getTextContent(); // sense espais, saltos, etc.
			String valorAtributXML = ((Node) lstNm.item(0)).getNodeValue();
			if (valorAtributXML!=null) return valorAtributXML.replaceAll("\\n", "").replaceAll("\\t","").replaceAll("\\r","").trim(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			return valorAtributXML;
		} 
		} catch (Throwable th) {
			// log.error (String.format(Messages.getString("AutoritzacioSEU.ObtainAttributeAutoritationsFileError"), nomAtribut,th));   //$NON-NLS-1$
		}
		//System.out.println(nomAtribut+": NUL");
		return null; //no s'ha trobat
	}	

	public String getCodi() {
		return name;
	}

	public void setCodi(String codi) {
		this.name = codi;
	}

	public String getDescripcio() {
		return description;
	}

	public void setDescripcio(String descripcio) {
		this.description = descripcio;
	}

	public String getTipusDomini() {
		return type;
	}

	public void setTipusDomini(String tipusDomini) {
		this.type = tipusDomini;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public HashSet<String> getAutoritzacionsHereta() {
		return inherits;
	}

	public void setAutoritzacionsHereta(HashSet<String> hereta) {
		this.inherits = hereta;
	}

	public String getAmbit() {
		return ambit;
	}


	public void setAmbit(String ambit) {
		this.ambit = ambit;
	}


	public String getHereta() {
		return inherited;
	}


	public void setHereta(String hereta) {
		this.inherited = hereta;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public HashSet<String> getInherits() {
		return inherits;
	}


	public void setInherits(HashSet<String> inherits) {
		this.inherits = inherits;
	}


	public String getInherited() {
		return inherited;
	}


	public void setInherited(String inherited) {
		this.inherited = inherited;
	}
	
	
}