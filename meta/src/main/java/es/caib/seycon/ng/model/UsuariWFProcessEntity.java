//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUPRO" )
@Depends ({es.caib.seycon.ng.comu.UsuariWFProcess.class})
public abstract class UsuariWFProcessEntity {

	@Column (name="UPR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="UPR_USUCOD", length=150)
	@Nullable
	public java.lang.String codiUsuari;

	@Column (name="UPR_IDPROC")
	public java.lang.Long idProces;

	@Column (name="UPR_END")
	public java.lang.Boolean finalitzat;

	@Column (name="UPR_USUNIF", length=50)
	@Nullable
	public java.lang.String nifUsuari;

	@DaoFinder
	public java.util.Collection<es.caib.seycon.ng.model.UsuariWFProcessEntity> findByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.util.Collection<es.caib.seycon.ng.model.UsuariWFProcessEntity> findByIdProces(
		java.lang.Long idProces) {
	 return null;
	}
	@DaoFinder
	public java.util.Collection<es.caib.seycon.ng.model.UsuariWFProcessEntity> findByNifUsuari(
		java.lang.String nifUsuari) {
	 return null;
	}
}
