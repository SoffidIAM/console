//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject (translatedName="ObjectMapping", translatedPackage="com.soffid.iam.api") 
public class ObjectMapping {

	@Nullable
	public java.lang.Long id;

	public java.lang.String systemObject;

	public SoffidObjectType soffidObject;

	@Nullable
	public java.lang.String condition;

	public java.lang.Long dispatcherId;

}
