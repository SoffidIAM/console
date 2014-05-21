//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ApplicationAccessTree",
	 translatedPackage="com.soffid.iam.api")
public abstract class ArbrePuntEntrada {

	public java.lang.Long id;

	public java.lang.String ordre;

	@Attribute(translated = "parentId" )
	public java.lang.Long idPare;

	@Attribute(translated = "parentName" )
	public java.lang.String nomPare;

	@Attribute(translated = "childId" )
	public java.lang.Long idFill;

	@Attribute(translated = "childName" )
	public java.lang.String nomFill;

}
