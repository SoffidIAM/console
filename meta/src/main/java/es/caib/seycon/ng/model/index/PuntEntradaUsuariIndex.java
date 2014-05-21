//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="PUE_USU_2",	unique=false,
	entity=es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity.class,
	columns={"UPE_IDUSU"})
public abstract class PuntEntradaUsuariIndex {
}

