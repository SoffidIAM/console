//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.bpm.vo;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="BPMUser",
	 translatedPackage="com.soffid.iam.api")
public abstract class BPMUser {

	public java.lang.String userName;

	public java.lang.String givenName;

	public java.lang.String surName;

	public java.lang.String group;

}
