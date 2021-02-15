package com.soffid.iam.model;

import java.util.Collection;
import java.util.List;

import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ACLService;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.DaoOperation;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.ForeignKey;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.UsuariService;

@Entity (table="SC_STATS")
public class StatsEntity {
	@Identifier
	@Column(name="STA_ID")
	Long id;
	
	@Column(name="STA_NAME", length=256)
	String name;
	
	@Description("Date format is YYYYmmDDHHMM")
	@Column(name="STA_DATE", length=16)
	String date;
	
	@Nullable
	@Column(name="STA_SERIE", length=128)
	String serie;
	
	@Nullable
	@Column(name="STA_VALUE", defaultValue="0L")
	Long value;

	@Column(name="STA_TEN_ID")
	TenantEntity tenant;

	@DaoOperation
	public void purge (int days) {}
	@DaoFinder("select x from com.soffid.iam.model.StatsEntity as x "
			+ "where x.name=:name and x.date between :since and :until and tenant.id=:tenantId "
			+ "order by x.date, x.serie")
	public Collection<StatsEntity> findByName(String name, String since, String until)
	{return null;}
}
