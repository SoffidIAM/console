//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
		translatedName="SessionCacheService",
		translatedPackage="com.soffid.iam.service")
public abstract class SessionCacheService {

	@Operation(translated="getObject")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Object getObject(
		java.lang.String tag)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="putObject")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void putObject(
		java.lang.String tag, 
		java.lang.Object value)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
