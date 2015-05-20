//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="UserData",
	 translatedPackage="com.soffid.iam.api")
public abstract class DadaUsuari {

	@Attribute(translated = "attribute" )
	public java.lang.String codiDada;

	@Nullable
	@Attribute(translated = "value" )
	public java.lang.String valorDada;

	@Description ("User name, when the data applies to a user. Null when applies to an account")
	@Attribute(translated = "user" )
	@Nullable
	public java.lang.String codiUsuari;

	@Description ("Account name, when the data applies to an account. Null when applies to a user")
	@Nullable
	String accountName;
	
	@Description ("Account system, when the data applies to an account. Null when applies to a user")
	@Nullable
	String systemName;
	
	@Nullable
	public java.lang.Long id;

	@Nullable
	public byte[] blobDataValue;

	@Nullable
	public java.util.Calendar valorDadaDate;

	@Nullable
	public String dataLabel;
	
	@Nullable
	public AttributeVisibilityEnum visibility;
}
