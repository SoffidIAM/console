//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserData",
	 translatedPackage="com.soffid.iam.api")
public abstract class DadaUsuari {

	@Attribute(translated = "attribute" )
	public java.lang.String codiDada;

	@Nullable
	@Attribute(translated = "value" )
	public java.lang.String valorDada;

	@Attribute(translated = "user" )
	public java.lang.String codiUsuari;

	@Nullable
	public java.lang.Long id;

	@Nullable
	public byte[] blobDataValue;

	@Nullable
	public java.util.Calendar valorDadaDate;

	@Nullable
	public String dataLabel;
}
