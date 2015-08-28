package es.caib.bpm.vo;

import java.util.Date;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="Comment", translatedPackage="com.soffid.iam.bpm.api")
public class Comment {
	@Nullable
    String message;
	@Nullable
    String actor;
	@Nullable
    Date time;
}
