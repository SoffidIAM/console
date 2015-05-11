//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_CONTAR", translatedName="CardCellEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ContingutTargetaExtranet.class,
	es.caib.seycon.ng.model.ScTarget.class})
public abstract class ScContar {

	@Column (name="CTA_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="CTA_FILCOL", length=10, translated="cell")
	public java.lang.String filcol;

	@Column (name="CTA_VALOR", length=10, translated="value")
	public java.lang.String valor;

	@Column (name="CTA_DADAUS", translated="expirationDate")
	@Nullable
	public java.util.Date dadaUs;

	@Column (name="CTA_IDTAR", translated="card")
	public es.caib.seycon.ng.model.ScTarget targeta;

}
