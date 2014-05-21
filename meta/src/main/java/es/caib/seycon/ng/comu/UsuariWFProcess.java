//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="BpmUserProcess",
	 translatedPackage="com.soffid.iam.api")
public abstract class UsuariWFProcess {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "processId" )
	public java.lang.Long idProces;

	@Nullable
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Attribute(translated = "terminated" )
	public java.lang.Boolean finalitzat;

	@Nullable
	@Attribute(translated = "userNationalId" )
	public java.lang.String nifUsuari;

}
