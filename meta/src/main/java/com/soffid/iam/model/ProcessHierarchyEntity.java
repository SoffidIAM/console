//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "BPM_PROHIE")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class })
public abstract class ProcessHierarchyEntity {
	@Column(name = "PRH_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "PRH_PARPRO", length = 1024)
	public java.lang.Long parentProcess;

	@Column(name = "PRH_CHIPRO", length = 1024)
	public java.lang.Long childProcess;

	public java.util.List<ProcessHierarchyEntity> findByParent(
			Long parentProcess) {
		return null;
	}

	public java.util.List<ProcessHierarchyEntity> findByChildren(
			Long childProcess) {
		return null;
	}
}

@Index(entity=ProcessHierarchyEntity.class,name="BPM_PROHIE_PAR", columns="PRH_PARPRO")
class ParentProcessIndex {}

@Index(entity=ProcessHierarchyEntity.class,name="BPM_PROHIE_CHI", columns="PRH_CHIPRO")
class ChildProcessIndex {}
