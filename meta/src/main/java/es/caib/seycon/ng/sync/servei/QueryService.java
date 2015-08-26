//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
	 serverOnly=true,
	 translatedName="QueryService",
	 translatedPackage="com.soffid.iam.sync.service")
public abstract class QueryService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void query(
		java.lang.String path, 
		java.lang.String contentType, 
		java.io.Writer writer)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List queryHql(
		java.lang.String path)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
