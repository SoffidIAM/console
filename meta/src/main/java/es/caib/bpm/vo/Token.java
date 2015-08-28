package es.caib.bpm.vo;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject(translatedName="Token", translatedPackage="com.soffid.iam.bpm.api")
public class Token {
	@Nullable
	String tokenName;
	long processId ;
	@Nullable
	String nodeName;
	boolean finished;
	boolean locked;
	boolean suspended;
}
