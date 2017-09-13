package com.soffid.iam.doc.model;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

@Entity(table="BPM_DOCUMENT")
public class DocumentEntity {
	@Nullable @Identifier
	@Column(name="DOC_ID")
	Long id;

	@Column(name="DOC_MIME_TYPE")
	String mimeType;
	
	@Column(name="DOC_EXTERNAL_NAME", length=256)
	String externalName;
	
	@Column(name="DOC_HASH", length=128)
	@Nullable
	String hash;
	
	@Column(name="DOC_FS_PATH", length=128)
	@Nullable
	String fsPath;
	
	@Column(name="DOC_TEN_ID")
	@Nullable
	TenantEntity tenant;
	
	
	
	public String getApplication () {return null;}
	public int getYear() { return 0;}
	

}

@Index (name="BPM_DOCUMENT_NAME_NDX", entity=DocumentEntity.class, columns={"DOC_FS_PATH"}, unique=true)
class DocumentEntityUniqueIndex {
	
}
