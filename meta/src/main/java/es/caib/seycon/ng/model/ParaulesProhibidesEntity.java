//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_BADWORD" )
@Depends ({es.caib.seycon.ng.comu.ParaulaProhibida.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity.class})
public abstract class ParaulesProhibidesEntity {

	@Column (name="BDW_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="BDW_WORD", length=200)
	public java.lang.String paraulaProhibida;

	@ForeignKey (foreignColumn="BDC_BDW_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity> paraulaProhibidaContrasenya;

}
