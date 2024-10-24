//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserType",
	 translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass = es.caib.seycon.ng.model.TipusUsuariEntity.class)
public abstract class TipusUsuari {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "name", synonyms = {"code"} )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Description ("True if this user or account should not be synchronized")
	@Attribute (defaultValue="false")
	public boolean unmanaged;
	
	@Description ("True if this user or account should be synchronized")
	@Attribute (defaultValue="true")
	public boolean managed;
}
