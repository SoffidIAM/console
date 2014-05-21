//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="SC_SERVER_UK1",	unique=true,
	entity=es.caib.seycon.ng.model.ServerEntity.class,
	columns={"SRV_NOM"})
public abstract class ServerIndex {
}

