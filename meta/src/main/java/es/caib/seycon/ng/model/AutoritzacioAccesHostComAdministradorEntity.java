//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ADMMAQ", translatedName="HostAdminEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class})
public abstract class AutoritzacioAccesHostComAdministradorEntity {

	@Column (name="ADM_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="ADM_DATCAD", translated="expirationDate")
	public java.util.Date dataCaducitatAutoritzacioAcces;

	@Column (name="ADM_USUID", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="ADM_MAQID")
	public es.caib.seycon.ng.model.MaquinaEntity host;

	@Column (name="ADM_IDWF", translated="processWFID")
	public java.lang.Long idProcesWorkflow;

	@Column (name="ADM_DATINI", translated="requestDate")
	@Nullable
	public java.util.Date dataPeticio;

	@Operation(translated="findByHostNameAndRequestDate")
	@DaoFinder("select autoriza "
			+ "from com.soffid.iam.model.HostAdminEntity autoriza "
			+ "where "
			+ "  autoriza.host.name = :nomHost and "
			+ "  (:requestDate = :nullDate or autoriza.requestDate >= :requestDate) and "
			+ "  (:expirationDate = :nullDate or autoriza.expirationDate >= :expirationDate) "
			+ "order by autoriza.requestDate")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity> findByNomHostIDataPeticio(
		java.lang.String nomHost, 
		@Nullable java.util.Date requestDate, 
		@Nullable java.util.Date expirationDate, 
		java.util.Date nullDate) {
	 return null;
	}
}
