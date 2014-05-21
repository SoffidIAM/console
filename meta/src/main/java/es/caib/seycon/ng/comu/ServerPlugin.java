//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ServerPlugin",
	 translatedPackage="com.soffid.iam.api")
public abstract class ServerPlugin {

	public java.lang.Long id;

	public java.lang.String version;

	public java.lang.String name;

	public boolean enabled;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.ServerPluginModule> modules;

}
