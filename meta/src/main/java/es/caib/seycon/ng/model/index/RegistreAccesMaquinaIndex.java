//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="RAC_MAQ_ORI_P_I",	unique=false,
	entity=es.caib.seycon.ng.model.RegistreAccesEntity.class,
	columns={"RAC_DATINI", "RAC_IDMAOR"})
public abstract class RegistreAccesMaquinaIndex {
}

