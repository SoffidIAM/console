//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject (translatedName="SoDRule", translatedPackage="com.soffid.iam.api")
public class SoDRule {

	@Nullable
	public java.lang.Long id;

	public java.lang.String name;

	@Nullable
	public Integer number;

	public es.caib.seycon.ng.comu.SoDRisk risk;

	public java.lang.Long applicationId;

}
