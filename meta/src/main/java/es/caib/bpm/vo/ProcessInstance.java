package es.caib.bpm.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="ProcessInstance", translatedPackage="com.soffid.iam.bpm.api")
public class ProcessInstance {
	long id = 0;
	@Nullable
	transient es.caib.bpm.classloader.UIClassLoader processClassLoader;
	@Nullable
	String description;
	@Nullable
	protected Date start = null;
	@Nullable
	protected Date end = null;
	@Nullable
	protected Map<String, Object> variables = null;
	@Nullable
	protected String currentTask = null;
	@Nullable
	protected List<Comment> comments = null;

}
