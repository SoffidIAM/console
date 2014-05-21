//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="MailList",
	 translatedPackage="com.soffid.iam.api")
public abstract class LlistaCorreu {

	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "domainCode" )
	public java.lang.String codiDomini;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "usersList" )
	public java.lang.String llistaUsuaris;

	@Nullable
	@Attribute(translated = "lists" )
	public java.lang.String llistaLlistes;

	@Nullable
	@Attribute(translated = "externalList" )
	public java.lang.String llistaExterns;

	@Nullable
	@Attribute(translated = "listsBelong" )
	public java.lang.String llistaLlistesOnPertany;

}
