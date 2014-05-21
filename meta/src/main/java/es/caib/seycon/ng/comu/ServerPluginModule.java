//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ServerPluginModule",
	 translatedPackage="com.soffid.iam.api")
public abstract class ServerPluginModule {

	public java.lang.String name;

	public es.caib.seycon.ng.comu.ServerPluginModuleType type;

	@Nullable
	public java.lang.String initClass;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.AgentDescriptor> agents;

	@Nullable
	public java.lang.String resourceName;

}
