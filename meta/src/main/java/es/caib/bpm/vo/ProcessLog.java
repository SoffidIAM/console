package es.caib.bpm.vo;

import java.util.Date;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="ProcessLog", translatedPackage="com.soffid.iam.bpm.api")
public class ProcessLog {
	long processId;
	@Nullable
	Date date;
	@Nullable
	String action;
	@Nullable
	String user;
}
