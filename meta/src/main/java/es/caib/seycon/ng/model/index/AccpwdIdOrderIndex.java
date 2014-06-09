//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.AccountPasswordEntity;

@Index (name="SC_ACCPWD_UK1",	unique=true,
	entity=es.caib.seycon.ng.model.AccountPasswordEntity.class,
	columns={"APW_ORDER", "APW_ACC_ID"})
public abstract class AccpwdIdOrderIndex {
}

