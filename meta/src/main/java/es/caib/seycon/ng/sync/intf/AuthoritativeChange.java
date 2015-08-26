package es.caib.seycon.ng.sync.intf;

import java.util.Map;
import java.util.Set;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Usuari;

@ValueObject(translatedName="AuthoritativeChange", translatedPackage="com.soffid.iam.sync.intf")
public class AuthoritativeChange {
	@Nullable
	AuthoritativeChangeIdentifier id;
	
	@Nullable
	String sourceSystem;
	
	@Nullable
	Usuari user;
	
	@Nullable
	Map<String, Object> attributes;
	
	@Nullable
	Set<String> groups;

}
