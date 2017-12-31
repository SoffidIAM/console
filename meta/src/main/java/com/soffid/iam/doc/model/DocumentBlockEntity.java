package com.soffid.iam.doc.model;

import java.util.List;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

@Entity(table="BPM_DOCBLO")
public class DocumentBlockEntity {
	@Nullable @Identifier
	@Column(name="DBL_ID")
	Long id;

	@Column(name="DBL_PATH")
	String path;
	
	@Column(name="DBL_SEQNUM")
	Long sequenceNumber;
	
	@Column(name="DBL_CONTENT", length=16384)
	@Nullable
	byte content[];
	
	@DaoFinder("select db "
			+ "from com.soffid.iam.doc.model.DocumentBlockEntity as db "
			+ "where db.path=:path "
			+ "order by sequenceNumber")
	List<DocumentBlockEntity> findByPath(String path) {return null;}
	
}

@Index (name="BPM_DOCBLO_PATH_NDX", entity=DocumentBlockEntity.class, columns={"DBL_PATH","DBL_SEQNUM"}, unique=true)
class DocumentBlockEntityUniqueIndex {
	
}
