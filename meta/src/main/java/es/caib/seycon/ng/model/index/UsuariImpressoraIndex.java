//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="UIM_UK_IDIMP_IDUSU",	unique=false,
	entity=es.caib.seycon.ng.model.UsuariImpressoraEntity.class,
	columns={"UIM_IDUSU", "UIM_IDIMP"})
public abstract class UsuariImpressoraIndex {
}

