//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.List;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.XarxaEntity;

@JsonObject (hibernateClass=XarxaEntity.class)
@ValueObject ( translatedName="Network",
	 translatedPackage="com.soffid.iam.api")
public class Xarxa {

	@Attribute(translated = "name", synonyms = {"code"} )
	public java.lang.String codi;

	@Attribute(translated = "ip" )
	public java.lang.String adreca;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "mask" )
	public java.lang.String mascara;

	@Nullable
	@Attribute(translated = "lanAccess" )
	public java.lang.Boolean normalitzada;

	@Nullable
	public java.lang.String dhcp;

	@Nullable
	public java.lang.Long id;

	public boolean dhcpSupport;

	@Nullable
	public java.lang.Boolean loginRestriction;

	@Nullable
	Boolean discovery;

	@Nullable
	String discoveryServer;

	@Nullable @Attribute(defaultValue = "new java.util.LinkedList()")
	List<String> discoveryRanges;
}
