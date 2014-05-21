//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="dispatcherGroup",
	 translatedPackage="com.soffid.iam.api")
public abstract class GrupDispatcher {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "dispatcherCode" )
	public java.lang.String codiDispatcher;

	@Attribute(translated = "groupCode" )
	public java.lang.String codiGrup;

}
