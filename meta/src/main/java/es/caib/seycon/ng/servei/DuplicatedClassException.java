package es.caib.seycon.ng.servei;

import com.soffid.mda.annotation.ApplicationException;
import com.soffid.mda.annotation.TranslatedClass;

@ApplicationException 
@TranslatedClass (name="DuplicatedClassException",
	pkg="com.soffid.iam.exception")
public class DuplicatedClassException extends Exception {

}
