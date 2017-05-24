//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Map;

import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Application",
	 translatedPackage="com.soffid.iam.api")
public abstract class Aplicacio {

	@Attribute(translated = "name" )
	public java.lang.String codi;

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
	public java.lang.Long id;

	@Description("full name of application responsible")
	@Nullable
	@Attribute(translated = "ownerName" )
	public java.lang.String nomComplertPersonaContacte;

	@Nullable
	@Attribute(translated = "bpmEnforced" )
	public java.lang.Boolean gestionableWF;

	@Nullable
	@Attribute(translated = "notificationEmails" )
	public java.lang.String correusNotificacions;

	@Description ("Approval process needed for workflow managed roles belonging to this application. Null value means no approval process is needed")
	@Nullable
	public String approvalProcess;

	@Description ("Process needed for any change applied to this application roles. Null value means no approval process is needed")
	@Nullable
	public String roleDefinitionProcess;
	
	@Description ("Application custom attributes")
	@Nullable
	public Map<String,Object> attributes; 
}
