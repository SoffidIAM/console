package com.soffid.iam.doc.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity (table="BPM_FILE_SYSTEM")
public class FileSystem {
	@Nullable @Identifier
	@Column(name="FIL_ID")
	Long id;
	
	@Column(name="FIL_APPLICATION", length=64)
	String application;
	
	@Column(name="FIL_YEAR")
	Integer year;
	
	@Column(name="FIL_NEXT_DOC")
	Long nextDocNumber;
	
	@DaoFinder
	Long nextNumberFor (String application, int year) {return null;}
}
