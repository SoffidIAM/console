//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="SC_ACCOUN_NAME",	unique=true,
	entity=es.caib.seycon.ng.model.AccountEntity.class,
	columns={"ACC_NAME", "ACC_DIS_ID"})
public abstract class AccountIndex {
}

