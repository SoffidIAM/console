//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_POCODO" )
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

	@Column (name="PCD_DESC", length=100)
	@Nullable
	public java.lang.String descripcio;

	@Column (name="PCD_TUS_ID")
	@Nullable
	public es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuariDomini;

	@Column (name="PCD_TIPUS", length=1)
	@Description("A = Automatica\nM = Manual")
	public java.lang.String tipus;

	@Column (name="PCD_RENAGE")
	@Nullable
	public java.lang.Long tempsRenovacio;

	@Column (name="PCD_AGE")
	@Nullable
	public java.lang.Long duradaMaxima;

	@Column (name="PCD_GRACE")
	@Nullable
	public java.lang.Long duradaMaximaCaducada;

	@Column (name="PCD_MINLEN")
	@Nullable
	public java.lang.Long minLongitud;

	@Column (name="PCD_MAXLEN")
	@Nullable
	public java.lang.Long maxLongitud;

	@Column (name="PCD_REGEX", length=50)
	@Nullable
	public java.lang.String expressioRegular;

	@Column (name="PCD_MINUPP")
	@Nullable
	public java.lang.Long minMajuscules;

	@Column (name="PCD_MAXUPP")
	@Nullable
	public java.lang.Long maxMajuscules;

	@Column (name="PCD_MINLOW")
	@Nullable
	public java.lang.Long minMinuscules;

	@Column (name="PCD_MAXLOW")
	@Nullable
	public java.lang.Long maxMinuscules;

	@Column (name="PCD_MINNUM")
	@Nullable
	public java.lang.Long minNumeros;

	@Column (name="PCD_MAXNUM")
	@Nullable
	public java.lang.Long maxNumeros;

	@Column (name="PCD_MINPUN")
	@Nullable
	public java.lang.Long minSignesPuntuacio;

	@Column (name="PCD_MAXPUN")
	@Nullable
	public java.lang.Long maxSignesPuntuacio;

	@Column (name="PCD_MAXHIST")
	@Nullable
	public java.lang.Long maxHistoric;

	@Column (name="PCD_DCN_ID")
	@Nullable
	public es.caib.seycon.ng.model.DominiContrasenyaEntity dominiContrasenya;

	@ForeignKey (foreignColumn="BDC_PCD_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity> paraulaProhibidaContrasenya;

	@Column (name="PCD_PASQRY")
	@Nullable
	public java.lang.Boolean allowPasswordQuery;

	@Column (name="PCD_PASCHG")
	@Nullable
	public java.lang.Boolean allowPasswordChange;

	@DaoFinder("select pol from \nes.caib.seycon.ng.model.PoliticaContrasenyaEntity pol\nleft join pol.dominiContrasenya  con\nwhere (:codiDomini is null or con.codi=:codiDomini) \norder by con.codi")
	public java.util.List<es.caib.seycon.ng.model.PoliticaContrasenyaEntity> findByDominiContrasenya(
		java.lang.String codiDomini) {
	 return null;
	}
	@DaoFinder("select pol from\nes.caib.seycon.ng.model.PoliticaContrasenyaEntity pol \nleft join pol.dominiContrasenya domini\nleft join pol.tipusUsuariDomini tus\nwhere domini.codi=:codiDomini and\n           tus.codi=:tipusUsuari ")
	public es.caib.seycon.ng.model.PoliticaContrasenyaEntity findByDominiContrasenyaTipusUsuari(
		java.lang.String codiDomini, 
		java.lang.String tipusUsuari) {
	 return null;
	}
}
