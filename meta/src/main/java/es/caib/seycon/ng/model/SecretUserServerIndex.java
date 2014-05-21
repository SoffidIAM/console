//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Index (name="SC_SEC_USUSERUK",	unique=true,
	entity=es.caib.seycon.ng.model.SecretEntity.class,
	columns={"SEC_IDUSU", "SEC_IDSRV"})
public abstract class SecretUserServerIndex {
}

