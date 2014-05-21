//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="MAQ_UK_SERIAL",	unique=true,
	entity=es.caib.seycon.ng.model.MaquinaEntity.class,
	columns={"MAQ_SERIAL"})
public abstract class MaquinaSerialIndex {
}

