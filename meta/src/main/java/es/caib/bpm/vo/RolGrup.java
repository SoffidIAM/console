package es.caib.bpm.vo;

import java.io.Serializable;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="RoleGroup", translatedPackage="com.soffid.iam.bpm.api")
public class RolGrup implements Serializable{
	@Nullable
	String rol;
	@Nullable
	String grup;
	
}
