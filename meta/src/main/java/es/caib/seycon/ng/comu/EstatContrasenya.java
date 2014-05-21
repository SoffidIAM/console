//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="PasswordStatus",
	 translatedPackage="com.soffid.iam.api")
public abstract class EstatContrasenya extends java.lang.Object {

	@Nullable
	@Attribute(translated = "user" )
	public java.lang.String usuari;

	@Nullable
	@Attribute(translated = "PasswordDomain" )
	public java.lang.String dominiContrasenyes;

	@Nullable
	public java.lang.String dispatcher;

	@Nullable
	public java.lang.String accountName;

	@Attribute(translated = "date" )
	public java.util.Calendar data;

	@Attribute(translated = "expirationDate" )
	public java.util.Calendar caducitat;

	@Nullable
	@Attribute(translated = "expired" )
	public java.lang.Boolean caducada;

	@Attribute(translated = "passwordPolicyType" )
	public java.lang.String tipusPoliticaContrasenya;

}
