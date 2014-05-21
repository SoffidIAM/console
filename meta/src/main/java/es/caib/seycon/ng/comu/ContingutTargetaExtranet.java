//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ExtranetCardContent",
	 translatedPackage="com.soffid.iam.api")
public abstract class ContingutTargetaExtranet {

	@Attribute(translated = "rowColumn" )
	public java.lang.String filcol;

	@Attribute(translated = "value" )
	public java.lang.String valor;

	@Attribute(translated = "lastUsedDate" )
	public java.util.Calendar dadaUs;

	@Attribute(translated = "row" )
	public java.lang.String fila;

	@Attribute(translated = "column" )
	public java.lang.String columna;

}
