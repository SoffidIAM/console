//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="MAQ_UK_IP",	unique=true,
	entity=es.caib.seycon.ng.model.MaquinaEntity.class,
	columns={"MAQ_ADRIP"})
public abstract class MaquinaIndex {
}

