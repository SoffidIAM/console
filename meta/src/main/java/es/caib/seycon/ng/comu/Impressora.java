//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Printer",
	 translatedPackage="com.soffid.iam.api")
public class Impressora {

	@Nullable
	public java.lang.String model;

	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "hostName" )
	public java.lang.String nomMaquina;

	@Nullable
	public java.lang.Boolean local;

	@Nullable
	public java.lang.Long id;

}
