//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserTypeDispatcher",
	 translatedPackage="com.soffid.iam.api")
public abstract class TipusUsuariDispatcher {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "type" )
	public java.lang.String tipus;

	@Attribute(translated = "dispatcherCode" )
	public java.lang.String codiDispatcher;

}
