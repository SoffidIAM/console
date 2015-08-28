//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.bpm.vo;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="BPMUser",
	 translatedPackage="com.soffid.iam.bpm.api")
public class BPMUser {

	@Nullable
	public java.lang.String userName;

	@Nullable
	public java.lang.String givenName;

	@Nullable
	public java.lang.String surName;

	@Nullable
	public java.lang.String group;

}
