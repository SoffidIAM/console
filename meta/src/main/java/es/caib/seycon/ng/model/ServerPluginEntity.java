//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SERPLU" , translatedName="ServerPluginEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.ServerPlugin.class,
	es.caib.seycon.ng.model.AgentDescriptorEntity.class,
	es.caib.seycon.ng.model.ServerPluginModuleEntity.class})
public abstract class ServerPluginEntity {

	@Column (name="SPL_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SPL_VERSION", length=30)
	public java.lang.String version;

	@Column (name="SPL_CONTEN", length=40000000)
	@Nullable
	public byte[] content;

	@Column (name="SPL_NAME", length=100)
	public java.lang.String name;

	@Column (name="SPL_ENABLE")
	public boolean enabled;

	@ForeignKey (foreignColumn="ADE_IDSPL")
	public java.util.Collection<es.caib.seycon.ng.model.AgentDescriptorEntity> agents;

	@ForeignKey (foreignColumn="SPM_SPL_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ServerPluginModuleEntity> modules;

	@DaoFinder
	public es.caib.seycon.ng.model.ServerPluginEntity findByName(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findAgentsBasicDataByServerPluginID")
	@DaoFinder("select agent.id ,agent.description, agent.className, agent.enableAccessControl , agent.authoritativeSource,\nagent.enableAttributeMapping\nfrom es.caib.seycon.ng.model.AgentDescriptorEntity agent \nleft join agent.plugin as p \nwhere p.id=:id")
	public java.util.List<es.caib.seycon.ng.model.ServerPluginEntity> findDadesBasiquesAgentsByServerPluginId(
		java.lang.Long id) {
	 return null;
	}
	@Operation(translated="findAllBasicData")
	@DaoFinder("select id, version, name, enabled from es.caib.seycon.ng.model.ServerPluginEntity as serverPluginEntity")
	public java.util.List<es.caib.seycon.ng.model.ServerPluginEntity> findAllOnlyDadesBasiques() {
	 return null;
	}
}
