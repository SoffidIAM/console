package es.caib.bpm.vo;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="TaskInstance", translatedPackage="com.soffid.iam.bpm.api")
public class TaskInstance {
	long id = 0;
	@Nullable
	transient es.caib.bpm.classloader.UIClassLoader processClassLoader;
	@Nullable
	protected String processName = null;
	@Nullable
	protected long processId = -1L;
	
	long processDefinition;

	boolean dummyTask;

	@Nullable
	protected String name = null;
	@Nullable
	protected String description = null;
	@Nullable
	protected String actorId = null;
	@Nullable
	protected Date create = null;
	@Nullable
	protected Date start = null;
	@Nullable
	protected Date end = null;
	@Nullable
	protected Date dueDate = null;
	@Nullable
	protected int priority = 0;
	protected boolean cancelled = false;
	protected boolean open = true;
	protected boolean signalling = true;
	protected boolean blocking = false;
	@Nullable
	protected String swimlane = null;
	@Nullable
	protected Set pooledActors = null;
	@Nullable
	protected Map variables = null;
	@Nullable
	protected String[] transitions = null;
}
