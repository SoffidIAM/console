//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="HostAlias",
	 translatedPackage="com.soffid.iam.api")
public abstract class AliasMaquina {

	@Nullable
	public java.lang.Long id;

	public java.lang.String alias;

	@Nullable
	@Attribute(translated = "hostId" )
	public java.lang.Long idMaquina;

	@Attribute(translated = "hostName" )
	public java.lang.String nomMaquina;

}
