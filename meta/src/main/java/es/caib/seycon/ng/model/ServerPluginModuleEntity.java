//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SEPLMO" )
@Depends ({es.caib.seycon.ng.comu.ServerPluginModule.class,
	es.caib.seycon.ng.model.ServerPluginEntity.class,
	es.caib.seycon.ng.model.AgentDescriptorEntity.class})
public abstract class ServerPluginModuleEntity {

	@Column (name="SPM_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SPM_NAME", length=255)
	public java.lang.String name;

	@Column (name="SPM_DATA", length=40000000)
	public byte[] contents;

	@Column (name="SPM_TYPE", length=255)
	public es.caib.seycon.ng.comu.ServerPluginModuleType type;

	@Column (name="SPM_SPL_ID")
	public es.caib.seycon.ng.model.ServerPluginEntity plugin;

	@ForeignKey (foreignColumn="ADE_SPM_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AgentDescriptorEntity> agents;

	@Column (name="SPM_CLASS", length=255)
	@Nullable
	public java.lang.String initClass;

	@Column (name="SPM_RESNAM", length=255)
	@Nullable
	public java.lang.String resourceName;

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.ServerPluginModuleEntity> findByType(
		es.caib.seycon.ng.comu.ServerPluginModuleType type) {
	 return null;
	}
}
