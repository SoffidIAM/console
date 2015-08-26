//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ReplicaDatabase",
	 translatedPackage="com.soffid.iam.api")
public class ReplicaDatabase {

	@Nullable
	public java.lang.Long id;

	public java.lang.String name;

	@Nullable
	public java.lang.String userName;

	@Nullable
	public es.caib.seycon.ng.comu.Password password;

	public java.lang.String url;

	@Nullable
	public java.lang.Long idSeed;

}
