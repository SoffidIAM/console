//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_POCODO", translatedName="PasswordPolicyEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.PoliticaContrasenya.class,
	es.caib.seycon.ng.model.ParaulesProhibidesEntity.class,
	es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity.class})
public abstract class PoliticaContrasenyaEntity {

	@Column (name="PCD_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="PCD_DESC", length=100, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Column (name="PCD_TUS_ID", translated="userType")
	@Nullable
	public es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuariDomini;

	@Column (name="PCD_TIPUS", length=1, translated="type")
	@Description("A = Automatica\nM = Manual")
	public java.lang.String tipus;

	@Column (name="PCD_RENAGE", translated="renewalTime")
	@Nullable
	public java.lang.Long tempsRenovacio;

	@Column (name="PCD_AGE", translated="availableTime")
	@Nullable
	public java.lang.Long duradaMaxima;

	@Column (name="PCD_GRACE", translated="gracePeriodTime")
	@Nullable
	public java.lang.Long duradaMaximaCaducada;

	@Column (name="PCD_MINLEN", translated="minLength")
	@Nullable
	public java.lang.Long minLongitud;

	@Column (name="PCD_MAXLEN", translated="maxLength")
	@Nullable
	public java.lang.Long maxLongitud;

	@Column (name="PCD_REGEX", length=50, translated="regularExpression")
	@Nullable
	public java.lang.String expressioRegular;

	@Column (name="PCD_MINUPP", translated="minUpperCase")
	@Nullable
	public java.lang.Long minMajuscules;

	@Column (name="PCD_MAXUPP", translated="maxUpperCase")
	@Nullable
	public java.lang.Long maxMajuscules;

	@Column (name="PCD_MINLOW", translated="minLowerCase")
	@Nullable
	public java.lang.Long minMinuscules;

	@Column (name="PCD_MAXLOW", translated="maxLowerCase")
	@Nullable
	public java.lang.Long maxMinuscules;

	@Column (name="PCD_MINNUM", translated="minNumbers")
	@Nullable
	public java.lang.Long minNumeros;

	@Column (name="PCD_MAXNUM", translated="maxNumbers")
	@Nullable
	public java.lang.Long maxNumeros;

	@Column (name="PCD_MINPUN", translated="minSymbols")
	@Nullable
	public java.lang.Long minSignesPuntuacio;

	@Column (name="PCD_MAXPUN", translated="maxSymbols")
	@Nullable
	public java.lang.Long maxSignesPuntuacio;

	@Column (name="PCD_MAXHIST", translated="rememberedPasswords")
	@Nullable
	public java.lang.Long maxHistoric;

	@Column (name="PCD_DCN_ID", translated="passwordDomain")
	@Nullable
	public es.caib.seycon.ng.model.DominiContrasenyaEntity dominiContrasenya;

	@ForeignKey (foreignColumn="BDC_PCD_ID", translated="forbiddenWords")
	public java.util.Collection<es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity> paraulaProhibidaContrasenya;

	@Column (name="PCD_PASQRY")
	@Nullable
	public java.lang.Boolean allowPasswordQuery;

	@Column (name="PCD_PASCHG")
	@Nullable
	public java.lang.Boolean allowPasswordChange;

	@Description ("Enable complex password just like MS AD")
	@Column (name="PCD_COMPLEX", defaultValue="false")
	public Boolean complexPasswords;

	@Operation(translated="findByPasswordDomain")
	@DaoFinder("select pol from \n"
			+ "com.soffid.iam.model.PasswordPolicyEntity pol\n"
			+ "left join pol.passwordDomain con\n"
			+ "where (:passwordDomain is null or con.name=:passwordDomain) \n"
			+ "order by con.name")

	public java.util.List<es.caib.seycon.ng.model.PoliticaContrasenyaEntity> findByDominiContrasenya(
		java.lang.String passwordDomain) {
	 return null;
	}
	@Operation(translated="findByPasswordDomainAndUserType")
	@DaoFinder("select pol from \n"
			+ "com.soffid.iam.model.PasswordPolicyEntity pol\n"
			+ "left join pol.passwordDomain domini\n"
			+ "left join pol.userType tus\n"
			+ "where domini.name=:passwordDomain and\n"
			+ "           tus.name=:userType ")
	public es.caib.seycon.ng.model.PoliticaContrasenyaEntity findByDominiContrasenyaTipusUsuari(
		java.lang.String passwordDomain, 
		java.lang.String userType) {
		return null;
	}
}
