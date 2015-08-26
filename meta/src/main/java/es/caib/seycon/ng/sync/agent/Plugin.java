package es.caib.seycon.ng.sync.agent;

import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="Plugin", translatedPackage="com.soffid.iam.sync.agent")
public class Plugin {
	String name;
	String version;
	byte []content;

}
