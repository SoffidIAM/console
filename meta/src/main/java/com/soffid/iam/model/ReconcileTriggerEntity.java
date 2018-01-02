package com.soffid.iam.model;

import com.soffid.iam.api.ReconcileTrigger;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.comu.SoffidObjectTrigger;
import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.seycon.ng.model.DispatcherEntity;

@Entity(table="SC_RECPAR")
@Depends({ReconcileTrigger.class})
public class ReconcileTriggerEntity {
	@Column (name="RPA_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="RPA_TRIGGER")
	public SoffidObjectTrigger trigger;

	@Column (name="RPA_SCRIPT", length=20000)
	public java.lang.String script;

	@Column (name="RPA_TYPE")
	@Nullable
	public SoffidObjectType objectType;

	@Column (name="RPA_DIS_ID", reverseAttribute="reconcileTriggers", composition=true)
	public DispatcherEntity system;
}
