//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.iam.api.ApplicationType;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.model.AplicacioEntity;

@JsonObject(hibernateClass = AplicacioEntity.class)
@ValueObject(translatedName = "Application", translatedPackage = "com.soffid.iam.api")
public abstract class Aplicacio {

	@Description ("Business process or application")
	@Nullable
	@Attribute(defaultValue = "com.soffid.iam.api.ApplicationType.APPLICATION")
	public ApplicationType type;
	
	@Attribute(translated = "name", hidden = true )
	public java.lang.String codi;

	@Attribute(translated = "parent", type = "APPLICATION")
	@Nullable
	public java.lang.String parent;

	public java.lang.String relativeName;

	@Attribute(translated = "description" )
	public java.lang.String nom;

	@Description("where source files are located")
	@Nullable
	@Attribute(translated = "source" )
	public java.lang.String directoriFonts;

	@Description("User code of the responsible for the application.")
	@Nullable
	@Attribute(translated = "owner" )
	public java.lang.String codiPersonaContacte;

	@Description("Where the executables are located")
	@Nullable
	@Attribute(translated = "executable" )
	public java.lang.String directoriExecutable;

	@Nullable
	@Attribute(translated = "database" )
	public java.lang.String bd;

	@Nullable
	@Attribute(hidden = true)
	public java.lang.Long id;

	@Description("full name of application responsible")
	@Nullable
	@Attribute(translated = "ownerName", type = "USER" )
	public java.lang.String nomComplertPersonaContacte;

	@Nullable
	@Attribute(translated = "bpmEnabled", synonyms = {"bpmEnforced"} )
	public java.lang.Boolean gestionableWF;

	@Nullable
	@Attribute(translated = "notificationEmails" )
	public java.lang.String correusNotificacions;

	@Description ("Approval process needed for workflow managed roles belonging to this application. Null value means no approval process is needed")
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.application.RoleApprovalProcessFieldHandler")
	public String approvalProcess;

	@Description ("Process needed for any change applied to this application roles. Null value means no approval process is needed")
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.application.RoleDefinitionProcessFieldHandler")
	public String roleDefinitionProcess;
	
	@Description ("Only one single role can be assigned to each user")
	@Nullable
	public boolean singleRole;
	
	@Description ("Application custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()", hidden = true)
	@Nullable
	public Map<String,Object> attributes; 
}
