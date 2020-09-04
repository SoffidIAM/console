//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.List;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.ImpressoraEntity;

@JsonObject(hibernateClass = ImpressoraEntity.class )
@ValueObject ( translatedName="Printer",
	 translatedPackage="com.soffid.iam.api")
public class Impressora {

	@Nullable
	public java.lang.String model;

	@Attribute(translated = "name", synonyms = {"code"} )
	public java.lang.String codi;

	@Attribute( )
	public java.lang.String description;

	@Nullable
	@Attribute(translated = "hostName" )
	public java.lang.String nomMaquina;

	@Nullable
	public java.lang.Boolean local;

	@Nullable
	public java.lang.Long id;

	@Nullable
	List<String> users;

	@Nullable
	List<String> groups;
}
