//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_OBJMAP", translatedName="ObjectMappingEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ObjectMapping.class,
	es.caib.seycon.ng.model.ObjectMappingPropertyEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.AttributeMappingEntity.class})
public abstract class ObjectMappingEntity {

	@Column (name="OBM_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="OBM_SYSOBJ", length=50)
	public java.lang.String systemObject;

	@Column (name="OBM_SOFOBJ", length=50)
	public es.caib.seycon.ng.comu.SoffidObjectType soffidObject;
	
	@Nullable
	@Column (name="OBM_CUSOBJ", composition=true, reverseAttribute="mappings")
	public CustomObjectTypeEntity soffidCustomObject;
	
	@Column (name="OBM_SOEXOB", length=50)
	@Nullable
	public java.lang.String soffidExtensibleObject;


	@Column (name="OBM_CONDIT", length=2000)
	@Nullable
	public java.lang.String condition;

	@ForeignKey (foreignColumn="OMP_OBM_ID")
	public java.util.Collection<es.caib.seycon.ng.model.ObjectMappingPropertyEntity> properties;

	@Column (name="OBM_DIS_ID", translated="system")
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

//	@Column (name="OBM_AUTHOR")
//	public boolean authoritative;

	@ForeignKey (foreignColumn="ATM_OBM_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AttributeMappingEntity> attributeMappings;

}
