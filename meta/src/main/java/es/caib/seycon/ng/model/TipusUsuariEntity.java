//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPUSU", translatedName="UserTypeEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.TipusUsuari.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class})
public abstract class TipusUsuariEntity {

	@Column (name="TUS_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TUS_CODI", length=1, translated="name")
	public java.lang.String codi;

	@Column (name="TUS_DESC", length=50, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@ForeignKey (foreignColumn="PCD_TUS_ID", translated="policies")
	public java.util.Collection<es.caib.seycon.ng.model.PoliticaContrasenyaEntity> politiques;

	@ForeignKey (foreignColumn="ACC_TUS_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountEntity> accounts;

	@Operation(translated="findByName")
	@DaoFinder
	public es.caib.seycon.ng.model.TipusUsuariEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
}
