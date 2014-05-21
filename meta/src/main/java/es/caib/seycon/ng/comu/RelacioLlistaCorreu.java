//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="MailListRelated",
	 translatedPackage="com.soffid.iam.api")
public abstract class RelacioLlistaCorreu {

	@Attribute(translated = "mailListNameBelong" )
	public java.lang.String nomLlistaCorreuPertany;

	@Attribute(translated = "mailListNameIncluded" )
	public java.lang.String nomLlistaCorreuConte;

	@Nullable
	@Attribute(translated = "mailDomainBelongCode" )
	public java.lang.String codiDominiCorreuPertany;

	@Nullable
	@Attribute(translated = "mailDomainAccountCode" )
	public java.lang.String codiDominiCorreuConte;

	@Nullable
	public java.lang.Long id;

}
