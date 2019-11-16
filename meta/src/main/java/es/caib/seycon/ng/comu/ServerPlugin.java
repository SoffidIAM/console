//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;

import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="ServerPlugin",
	 translatedPackage="com.soffid.iam.api")
public class ServerPlugin {

	public java.lang.Long id;

	public java.lang.String version;

	public java.lang.String name;

	public boolean enabled;

	@Nullable
	public java.lang.String author;

	@Nullable
	public Date deployed;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.ServerPluginModule> modules;

}
