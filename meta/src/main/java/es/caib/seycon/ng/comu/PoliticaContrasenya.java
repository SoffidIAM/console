//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="PasswordPolicy",
	 translatedPackage="com.soffid.iam.api")
public class PoliticaContrasenya {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "type" )
	public java.lang.String tipus;

	@Nullable
	@Attribute(translated = "renewalTime" )
	public java.lang.Long tempsRenovacio;

	@Nullable
	@Attribute(translated = "maximumPeriod" )
	public java.lang.Long duradaMaxima;

	@Nullable
	@Attribute(translated = "maximumPeriodExpired" )
	public java.lang.Long duradaMaximaCaducada;

	@Nullable
	@Attribute(translated = "minimumLength" )
	public java.lang.Long minLongitud;

	@Nullable
	@Attribute(translated = "maximumLength" )
	public java.lang.Long maxLongitud;

	@Nullable
	@Attribute(translated = "regularExpression" )
	public java.lang.String expressioRegular;

	@Nullable
	@Attribute(translated = "minimumUppercase" )
	public java.lang.Long minMajuscules;

	@Nullable
	@Attribute(translated = "maximumUppercase" )
	public java.lang.Long maxMajuscules;

	@Nullable
	@Attribute(translated = "minimumLowercase" )
	public java.lang.Long minMinuscules;

	@Nullable
	@Attribute(translated = "maximumLowercase" )
	public java.lang.Long maxMinuscules;

	@Nullable
	@Attribute(translated = "minimumNumbers" )
	public java.lang.Long minNumeros;

	@Nullable
	@Attribute(translated = "maximumNumbers" )
	public java.lang.Long maxNumeros;

	@Nullable
	@Attribute(translated = "minimumSymbols" )
	public java.lang.Long minSignesPuntuacio;

	@Nullable
	@Attribute(translated = "maximumSymbols" )
	public java.lang.Long maxSignesPuntuacio;

	@Nullable
	@Attribute(translated = "maximumHistorical" )
	public java.lang.Long maxHistoric;

	@Nullable
	@Attribute(translated = "userType" )
	public java.lang.String tipusUsuari;

	@Nullable
	@Attribute(translated = "userTypeDescription" )
	public java.lang.String decripcioTipusUsuari;

	@Nullable
	@Attribute(translated = "usersDomainCode" )
	public java.lang.String codiDominiUsuaris;

	@Nullable
	@Attribute(translated = "passwordDomainCode" )
	public java.lang.String codiDominiContrasenya;


	@Description("Enables users to query password value")
	public boolean allowPasswordQuery;

	@Description("Enables users to change password value")
	public boolean allowPasswordChange;

	@Description ("Enable complex password just like MS AD")
	public boolean complexPasswords;
}
