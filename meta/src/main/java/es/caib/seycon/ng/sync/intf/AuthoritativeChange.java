package es.caib.seycon.ng.sync.intf;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.soffid.iam.api.CustomObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;

@ValueObject(translatedName="AuthoritativeChange", translatedPackage="com.soffid.iam.sync.intf", serialVersion="-3968123356323004507L")
public class AuthoritativeChange {
	@Nullable
	AuthoritativeChangeIdentifier id;
	
	@Nullable
	SoffidObjectType objectType;
	
	@Nullable
	String sourceSystem;
	
	@Nullable
	Usuari user;
	
	@Nullable
	Map<String, Object> attributes;
	
	@Nullable
	Set<String> groups;

	@Nullable
	Collection<UsuariGrup> groups2;
	
	@Nullable
	Grup group;

	@Nullable
	CustomObject object;
}
