//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@Enumeration 
public class SoffidObjectTrigger {

	public java.lang.String PRE_INSERT="preInsert";

	public java.lang.String PRE_UPDATE="preUpdate";

	public java.lang.String PRE_DELETE="preDelete";

	public java.lang.String POST_INSERT="postInsert";

	public java.lang.String POST_UPDATE="postUpdate";

	public java.lang.String POST_DELETE="postDelete";

	public java.lang.String PRE_SET_PASSWORD="preSetPassword";

	public java.lang.String POST_SET_PASSWORD="postSetPassword";
}
