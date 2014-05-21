//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="OsType",
	 translatedPackage="com.soffid.iam.api")
public abstract class OsType {

	@Nullable
	public java.lang.Long id;

	public java.lang.String name;

	@Nullable
	public java.lang.String description;

}
