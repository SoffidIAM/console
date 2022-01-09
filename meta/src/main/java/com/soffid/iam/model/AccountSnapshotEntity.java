package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;

@Entity(table = "SC_ACCSNA")
public class AccountSnapshotEntity {
	@Column(name = "ASN_ID")
	@Identifier Long id;
	
	@Column(name="ASN_DATA", length = 128000)
	byte[] data;
}
