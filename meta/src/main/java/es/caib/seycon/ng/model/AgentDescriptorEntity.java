//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_AGEDES", translatedName="AgentDescriptorEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.AgentDescriptor.class,
	es.caib.seycon.ng.model.ServerPluginEntity.class,
	es.caib.seycon.ng.model.ServerPluginModuleEntity.class,
	es.caib.seycon.ng.model.DefaultObjectMappingEntity.class})
public abstract class AgentDescriptorEntity {

	@Column (name="ADE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="ADE_DESCRI", length=512)
	public java.lang.String description;

	@Column (name="ADE_CLASS", length=512)
	public java.lang.String className;

	@Column (name="ADE_UI", length=65000)
	public byte[] userInterface;

	@Column (name="ADE_IDSPL")
	@Nullable
	public es.caib.seycon.ng.model.ServerPluginEntity plugin;

	@Column (name="ADE_CAC")
	public boolean enableAccessControl;

	@Column (name="ADE_SPM_ID")
	@Nullable
	public es.caib.seycon.ng.model.ServerPluginModuleEntity module;

	@Column (name="ADE_AUTHOR")
	public boolean authoritativeSource;

	@Column (name="ADE_ATTMAP",
		defaultValue="false")
	public boolean enableAttributeMapping;

	@ForeignKey (foreignColumn="DAM_ADE_ID")
	public java.util.Collection<es.caib.seycon.ng.model.DefaultObjectMappingEntity> defaultObjectMappings;

	@DaoFinder
	public es.caib.seycon.ng.model.AgentDescriptorEntity findByClass(
		java.lang.String className) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.AgentDescriptorEntity> findByDescription(
		java.lang.String description) {
	 return null;
	}
	@Operation(translated="findAllOnlyBasicData")
	@DaoFinder("select agentDescriptorEntity\n"
			+ "from com.soffid.iam.model.AgentDescriptorEntity as agentDescriptorEntity\n"
			+ "order by agentDescriptorEntity.description")
	public java.util.List<es.caib.seycon.ng.model.AgentDescriptorEntity> findAllOnlyDadesBasiques() {
	 return null;
	}
}
