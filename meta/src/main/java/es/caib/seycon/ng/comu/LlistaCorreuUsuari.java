//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserMailList",
	 translatedPackage="com.soffid.iam.api")
public class LlistaCorreuUsuari {

	@Attribute(translated = "mailListName" )
	public java.lang.String nomLlistaCorreu;

	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Nullable
	@Attribute(translated = "fullName" )
	public java.lang.String nomComplert;

	@Nullable
	@Attribute(translated = "domainCode" )
	public java.lang.String codiDomini;

	@Nullable
	public java.lang.Long id;

}
