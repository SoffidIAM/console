package es.caib.bpm.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.model.ConfiguracioEntity;

@ValueObject(translatedName="ProcessInstance", translatedPackage="com.soffid.iam.bpm.api")
@JsonObject(hibernateClass = ConfiguracioEntity.class)
public class ProcessInstance {
	@Attribute(hidden= true)
	long id = 0;
	@Nullable
	@Attribute(hidden = true)
	transient es.caib.bpm.classloader.UIClassLoader processClassLoader;
	@Nullable
	String description;
	@Nullable
	protected Date start = null;
	@Nullable
	protected Date end = null;
	@Nullable
	@Attribute(hidden = true, synonyms = {"attributes"})
	protected Map<String, Object> variables = null;
	@Nullable
	protected String currentTask = null;
	@Nullable
	@Attribute(hidden=true)
	protected List<Comment> comments = null;

	@Attribute(hidden = true)
	long processDefinition;

	@Attribute(hidden = true)
	boolean dummyProcess;
}
