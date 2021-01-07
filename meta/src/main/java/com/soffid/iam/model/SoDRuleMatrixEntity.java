package com.soffid.iam.model;

import com.soffid.iam.api.SoDRuleMatrix;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.SoDRoleEntity;
import es.caib.seycon.ng.model.SoDRuleEntity;

@Entity(table = "SC_SODMAT")
@Depends({SoDRuleMatrix.class})
public class SoDRuleMatrixEntity {
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="SMA_SOD_ID", reverseAttribute = "matrixCells")
	public SoDRuleEntity rule;

	@Column (name="SOD_RISK")
	public es.caib.seycon.ng.comu.SoDRisk risk;

	@Column (name="SOD_SOR_ROW", reverseAttribute = "columns")
	SoDRoleEntity row;

	@Column (name="SOD_SOR_COL", reverseAttribute = "rows")
	SoDRoleEntity column;
}
