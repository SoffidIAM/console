//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUPRO", translatedName="UserProcessEntity", translatedPackage="com.soffid.iam.model"  )
@Depends ({es.caib.seycon.ng.comu.UsuariWFProcess.class})
public abstract class UsuariWFProcessEntity {

	@Column (name="UPR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="UPR_USUCOD", length=150, translated="userName")
	@Nullable
	public java.lang.String codiUsuari;

	@Column (name="UPR_IDPROC", translated="processId")
	public java.lang.Long idProces;

	@Column (name="UPR_END", translated="finished")
	public java.lang.Boolean finalitzat;

	@Column (name="UPR_USUNIF", length=50, translated="nationalId")
	@Nullable
	public java.lang.String nifUsuari;

	@Operation(translated="findByUserName")
	@DaoFinder
	public java.util.Collection<es.caib.seycon.ng.model.UsuariWFProcessEntity> findByCodiUsuari(
		java.lang.String userName) {
	 return null;
	}
	@Operation(translated="findByProcessId")
	@DaoFinder
	public java.util.Collection<es.caib.seycon.ng.model.UsuariWFProcessEntity> findByIdProces(
		java.lang.Long processId) {
	 return null;
	}
	@Operation(translated="findByUserNationalId")
	@DaoFinder
	public java.util.Collection<es.caib.seycon.ng.model.UsuariWFProcessEntity> findByNifUsuari(
		java.lang.String nationalId) {
	 return null;
	}
}
