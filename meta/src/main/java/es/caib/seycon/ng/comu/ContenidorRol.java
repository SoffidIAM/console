//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ContainerRole",
	 translatedPackage="com.soffid.iam.api")
public abstract class ContenidorRol {

	public java.lang.Long id;

	@Attribute(translated = "containerInfo" )
	public java.lang.String infoContenidor;

	@Attribute(translated = "type" )
	public java.lang.String tipus;

	public java.lang.String metaInfo;

}
