//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ConsoleProperties",
	 translatedPackage="com.soffid.iam.api")
public class UsuariSEU {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "userName" )
	public java.lang.String codiUsuari;

	@Nullable
	@Attribute(translated = "lastLoginDate" )
	public java.util.Calendar dataDarrerLogin;

	@Nullable
	@Attribute(translated = "version" )
	public java.lang.String versio;

	@Nullable
	@Attribute(translated = "bookmarks" )
	public java.util.Collection<java.lang.String> favoritsSEU;

	@Attribute(translated = "preferences" )
	public java.util.Map preferenciesSEU;

	@Nullable
	public java.lang.String lastIP;

	@Nullable
	@Attribute(translated = "language" )
	public java.lang.String idioma;

}
