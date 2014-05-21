//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Index (name="SC_USUSEU_USUID",	unique=true,
	entity=es.caib.seycon.ng.model.UsuariSEUEntity.class,
	columns={"USE_USUID"})
public abstract class UsuariSEUIndex {
}

