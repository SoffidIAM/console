//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Reminder",
	 translatedPackage="com.soffid.iam.api")
public abstract class Notificacio {

	@Attribute(translated = "applicationCode" )
	public java.lang.String codiAplicacio;

	@Attribute(translated = "roleName" )
	public java.lang.String nomRol;

	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Attribute(translated = "userFullName" )
	public java.lang.String nomCompletUsuari;

	@Nullable
	@Attribute(translated = "information" )
	public java.lang.String informacio;

	@Nullable
	@Attribute(translated = "assignmentDate" )
	public java.lang.String dataAssignacio;

}
