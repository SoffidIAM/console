//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.SoffidObjectTrigger;

@Entity (table="SC_OBMATR", translatedName="ObjectMappingTriggerEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ObjectMappingTrigger.class,
	es.caib.seycon.ng.model.ObjectMappingEntity.class})

public abstract class ObjectMappingTriggerEntity {

	@Column (name="OMT_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="OMT_TRIGGER")
	public SoffidObjectTrigger trigger;

	@Column (name="OMT_SCRIPT", length=20000)
	public java.lang.String script;

	@Column (name="OMT_OBM_ID", reverseAttribute="triggers", composition=true)
	public es.caib.seycon.ng.model.ObjectMappingEntity object;

}
