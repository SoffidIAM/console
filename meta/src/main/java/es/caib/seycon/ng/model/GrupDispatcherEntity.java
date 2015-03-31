//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_GRUDIS",
	translatedName="SystemGroupEntity",
	translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.GrupDispatcher.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class})
public abstract class GrupDispatcherEntity {

	@Column (name="GRD_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="GRD_IDGRUP", translated="group")
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column (name="GRD_IDDIS", translated="system")
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

	@Operation(translated="findByAgentCode")
	@DaoFinder("select gd from es.caib.seycon.ng.model.GrupDispatcherEntity gd where gd.dispatcher.codi=:codiAgent")
	public java.util.Collection<es.caib.seycon.ng.model.GrupDispatcherEntity> findByCodiAgent(
		java.lang.String codiAgent) {
	 return null;
	}
}
