package com.soffid.iam.reconcile.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity (table="SC_RECACO" )
public class ReconcileAccountAttributesEntity {
	@Column (name="RAA_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="RAA_RAC_ID", composition=true, reverseAttribute="attributes")
	public ReconcileAccountEntity account;

	@Column (name="RAA_ATTRIB")
	public java.lang.String attribute;

	@Column (name="RAA_VALUE")
	public java.lang.String value;

	@Column (name="RAA_DATE")
	public java.util.Date dateValue;

}
