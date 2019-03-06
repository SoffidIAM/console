//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.mda.annotation.Service;

@Service (translatedName="ApplicationShutdownService",
	translatedPackage="com.soffid.iam.service")
public abstract class ApplicationShutdownService {

	public void syncServerShutdown()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	public void consoleShutdown()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
