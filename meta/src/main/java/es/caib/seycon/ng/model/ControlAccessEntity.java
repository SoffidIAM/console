//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_CONACC", translatedName="AccessControlEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ControlAcces.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class ControlAccessEntity {

	@Column (name="CAC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="CAC_GEN_USU", length=2048, translated="genericUser")
	@Nullable
	public java.lang.String usuariGeneric;

	@Column (name="CAC_GEN_MAQ", length=2048, translated="genericHost")
	@Nullable
	public java.lang.String maquinaGeneric;

	@Column (name="CAC_PROGRAM", length=2048)
	public java.lang.String program;

	@Column (name="CAC_ROL_ID", translated="role")
	@Nullable
	public es.caib.seycon.ng.model.RolEntity rol;

	@Column (name="CAC_DIS_ID")
	public es.caib.seycon.ng.model.DispatcherEntity agent;

	@Column (name="CAC_IPMAQ_ORA", length=2048, translated="propagatedIPs")
	@Nullable
	public java.lang.String ipsPropagades;

	@Operation(translated="findByAgentCode")
	@DaoFinder("select cac from es.caib.seycon.ng.model.ControlAccessEntity cac where cac.agent.codi=:codiAgent")
	public java.util.Collection<es.caib.seycon.ng.model.ControlAccessEntity> findByCodiAgent(
		java.lang.String codiAgent) {
	 return null;
	}
}
