//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserDomain",
	 translatedPackage="com.soffid.iam.api")
public class DominiUsuari {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	@Attribute(translated = "type" )
	public es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration tipus;

	@Nullable
	public java.lang.String bshExpr;

	@Nullable
	public java.lang.String bshExprCreate;

	@Nullable
	public java.lang.String beanGenerator;

}
