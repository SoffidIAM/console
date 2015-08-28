package es.caib.bpm.vo;

import java.util.Date;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="TaskDefinition", translatedPackage="com.soffid.iam.bpm.api")
public class TaskDefinition {
	  public static final int PRIORITY_HIGHEST = 1;
	  public static final int PRIORITY_HIGH = 2;
	  public static final int PRIORITY_NORMAL = 3;
	  public static final int PRIORITY_LOW = 4;
	  public static final int PRIORITY_LOWEST = 5;

	  protected long id;
		@Nullable
	  protected String name;
		@Nullable
	  protected String description = null;
		@Nullable
	  protected boolean blocking = false;
		@Nullable
	  protected boolean signalling = true;
}
