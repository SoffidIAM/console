//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_BADCON" )
@Depends ({es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.ParaulesProhibidesEntity.class})
public abstract class ParaulaProhibidaPoliticaContrasenyaEntity {

	@Column (name="BDC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="BDC_BDW_ID")
	@Nullable
	public es.caib.seycon.ng.model.ParaulesProhibidesEntity paraulaProhibida;

	@Column (name="BDC_PCD_ID")
	public es.caib.seycon.ng.model.PoliticaContrasenyaEntity politicaContrasenya;

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity> findByPoliticaContrasenya(
		es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenya) {
	 return null;
	}
}
