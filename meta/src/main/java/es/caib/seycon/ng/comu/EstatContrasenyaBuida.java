//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="VoidPasswordStatus",
	 translatedPackage="com.soffid.iam.api")
public class EstatContrasenyaBuida extends es.caib.seycon.ng.comu.EstatContrasenya {

	@Attribute(translated = "userWithoutPasswordDomain" )
	public java.lang.Boolean usuariSenseContrasenyaDomini;

}
