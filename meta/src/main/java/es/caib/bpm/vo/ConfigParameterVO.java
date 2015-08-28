package es.caib.bpm.vo;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="ConfigParameterVO", translatedPackage="com.soffid.iam.bpm.api")
public class ConfigParameterVO {
	@Nullable
	Long id;
	@Nullable
	String app;
	@Nullable
	String key;
	@Nullable
	String value;

}
