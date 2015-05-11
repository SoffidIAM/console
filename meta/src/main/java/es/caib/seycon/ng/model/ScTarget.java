//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TARGET", translatedName="CardEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.ScContar.class,
	es.caib.seycon.ng.comu.TargetaExtranet.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class ScTarget {

	@Column (name="TAR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TAR_CODI", length=10, translated="code")
	public java.lang.String codi;

	@Column (name="TAR_DATEMI", translated="issueDate")
	public java.util.Date dataEmissio;

	@Column (name="TAR_DATCAD", translated="expirationDate")
	public java.util.Date dataCaducitat;

	@Column (name="TAR_ACTIVA", length=1, translated="active")
	public java.lang.String actiu;

	@Column (name="TAR_IDUSU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@ForeignKey (foreignColumn="CTA_IDTAR", translated="content")
	public java.util.Collection<es.caib.seycon.ng.model.ScContar> contingut;

	@Operation(translated="createExtranetCard")
	@DaoOperation
	public es.caib.seycon.ng.model.ScTarget creaTargetaExtranet(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.ScTarget findById(
		java.lang.Long id) {
	 return null;
	}
	@Operation(translated="findByCardCodeAndUserCode")
	@DaoFinder("select targeta \n"
			+ "from com.soffid.iam.model.CardEntity targeta, \n"
			+ "com.soffid.iam.model.UserEntity usuari \n"
			+ "where targeta.code = :cardNumber and targeta.user=usuari and usuari.userName = :userName")
	public es.caib.seycon.ng.model.ScTarget findByCodiTargetaAndCodiUsuari(
		java.lang.String cardNumber, 
		java.lang.String userName) {
	 return null;
	}
}
