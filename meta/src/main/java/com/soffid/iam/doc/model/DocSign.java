package com.soffid.iam.doc.model;

import java.util.Date;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table="BPM_SIGN")
public class DocSign {
	@Nullable @Identifier
	@Column(name="SIG_ID")
	Long id;
	
	@Column(name="SIG_SIGN_TYPE")
	String signType;
	
	@Column(name="SIG_FS_PATH", length=512)
	String fsPath;
	
	@Column(name="SIG_TIMESTAMP")
	Date timestamp;
	
	@Column(name="SIG_DOC_ID", reverseAttribute="signs")
	DocumentEntity bpmDocument;

}
