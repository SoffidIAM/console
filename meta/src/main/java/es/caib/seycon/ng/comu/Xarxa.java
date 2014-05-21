//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Network",
	 translatedPackage="com.soffid.iam.api")
public abstract class Xarxa {

	@Attribute(translated = "code" )
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

}
