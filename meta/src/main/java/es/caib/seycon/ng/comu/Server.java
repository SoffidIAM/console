//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Server",
	 translatedPackage="com.soffid.iam.api")
public class Server {

	@Nullable
	public java.lang.Long id;

	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Nullable
	public byte[] pk;

	@Nullable
	public java.lang.String auth;

	@Nullable
	public java.security.PublicKey publicKey;

	@Nullable
	public java.lang.Boolean useMasterDatabase;

	@Nullable
	public java.lang.Long backupDatabase;

	public es.caib.seycon.ng.comu.ServerType type;

	@Nullable
	public java.lang.String url;

	@Nullable
	public java.lang.String publicUrl;

	@Nullable
	public java.lang.String javaOptions;
}
