package es.caib.bpm.vo;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="ProcessDefinition", translatedPackage="com.soffid.iam.bpm.api")
public class ProcessDefinition {
	protected int version = -1;
	@Nullable
	protected String tag = null;
	@Nullable
	protected String name;
	@Nullable
	protected long id;
	protected boolean enabled;
	@Nullable
	protected String appliesTo;
	@Nullable
	PredefinedProcessType type;

}
