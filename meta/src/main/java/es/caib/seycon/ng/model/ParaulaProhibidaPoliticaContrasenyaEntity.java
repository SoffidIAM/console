//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_BADCON", translatedName="PolicyForbiddenWordEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.ParaulesProhibidesEntity.class})
public abstract class ParaulaProhibidaPoliticaContrasenyaEntity {

	@Column (name="BDC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="BDC_BDW_ID", translated="forbiddenWord")
	@Nullable
	public es.caib.seycon.ng.model.ParaulesProhibidesEntity paraulaProhibida;

	@Column (name="BDC_PCD_ID", translated="passwordPolicy")
	public es.caib.seycon.ng.model.PoliticaContrasenyaEntity politicaContrasenya;

	@Operation(translated="findByPasswordPolicy")
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity> findByPoliticaContrasenya(
		es.caib.seycon.ng.comu.PoliticaContrasenya passwordPolicy) {
	 return null;
	}
}
