//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.DominiCorreuEntity;

@ValueObject ( translatedName="MailDomain",
	 translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass = DominiCorreuEntity.class)
public class DominiCorreu {

	@Attribute(translated = "name", synonyms = {"code"}, searchCriteria = true )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "description", searchCriteria = true )
	public java.lang.String descripcio;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(defaultValue = "false", translated = "obsolete" )
	public java.lang.Boolean obsolet;

}
