//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DEOBMA", translatedName="DefaultObjectMappingEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.AgentDescriptorEntity.class,
	es.caib.seycon.ng.model.DefaultObjectMappingPropertyEntity.class,
	es.caib.seycon.ng.model.DefaultAttributeMappingEntity.class})
public abstract class DefaultObjectMappingEntity {

	@Column (name="DOM_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="DOM_SYSOBJ", length=50)
	public java.lang.String systemObject;

	@Column (name="DAM_ADE_ID")
	public es.caib.seycon.ng.model.AgentDescriptorEntity agentDescriptor;

	@Column (name="DOM_SOFOBJ", length=50)
	public es.caib.seycon.ng.comu.SoffidObjectType soffidObject;

	@Column (name="DOM_CONDIT", length=2000)
	@Nullable
	public java.lang.String condition;

	@ForeignKey (foreignColumn="DOP_DOM_ID")
	public java.util.Collection<es.caib.seycon.ng.model.DefaultObjectMappingPropertyEntity> properties;

	@ForeignKey (foreignColumn="DAM_DOM_ID")
	public java.util.Collection<es.caib.seycon.ng.model.DefaultAttributeMappingEntity> defaultAttributeMappings;

}
