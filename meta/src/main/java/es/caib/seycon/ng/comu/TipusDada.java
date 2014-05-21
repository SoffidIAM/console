//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="DataType",
	 translatedPackage="com.soffid.iam.api")
public abstract class TipusDada {

	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Attribute(translated = "order" )
	public java.lang.Long ordre;

	@Nullable
	public java.lang.Long id;

	@Nullable
	public es.caib.seycon.ng.comu.TypeEnumeration type;

	@Nullable
	public java.lang.Integer size;

}
