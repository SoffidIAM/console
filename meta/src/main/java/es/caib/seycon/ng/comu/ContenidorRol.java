//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;

import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ContainerRole",
	 translatedPackage="com.soffid.iam.api")
public class ContenidorRol {

	public java.lang.Long id;

	@Attribute(translated = "containerInfo" )
	public java.lang.String infoContenidor;

	@Attribute(translated = "type" )
	public java.lang.String tipus;

	@Description("Last certification date")
	@Nullable
	public Date certificationDate;

	public java.lang.String metaInfo;

}
