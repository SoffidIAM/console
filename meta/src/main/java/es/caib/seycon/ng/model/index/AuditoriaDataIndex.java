//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="SC_AUDITO_BORRAR",	unique=false,
	entity=es.caib.seycon.ng.model.AuditoriaEntity.class,
	columns={"AUD_DATA"})
public abstract class AuditoriaDataIndex {
}

