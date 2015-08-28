package es.caib.bpm.vo;

import java.util.Date;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="Job", translatedPackage="com.soffid.iam.bpm.api")
public class Job {
	@Nullable
	String name;
	@Nullable
	long id;
	@Nullable
	Date dueDate;
	@Nullable
	boolean locked;
	@Nullable
	String errorMessage;
	@Nullable
	boolean error;
	@Nullable
	int failures;
	@Nullable
	boolean paused;
	@Nullable
	long processId;
	
	

}
