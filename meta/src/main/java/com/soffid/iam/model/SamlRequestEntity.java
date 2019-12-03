package com.soffid.iam.model;

import java.util.Collection;
import java.util.Date;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.DaoOperation;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

@Entity (table="SC_SAMLREQ" )
@Depends ({})
public class SamlRequestEntity {
	@Column (name="REQ_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="REQ_EXTID")
	public String externalId;

	@Column (name="REQ_DATE")
	public Date date;

	@Column (name="REQ_EXPDAT")
	public Date expirationDate;

	@Column (name="REQ_FINISHED")
	public boolean finished;
	
	@Column (name="REQ_HOST")
	public String hostName;

	@Column (name="REQ_USER")
	@Nullable
	public String user;
	
	@Column (name="REQ_KEY")
	@Nullable
	public String key;
	
	@Column (name="REQ_TEN_ID")
	public TenantEntity tenant;
	

	@DaoFinder
	public SamlRequestEntity findByExternalId(String externalId) {return null;}

	@DaoFinder ("select a from com.soffid.iam.model.SamlRequestEntity as a where a.date < :d")
	public Collection<SamlRequestEntity> findExpired(Date d) {return null;}

	@DaoOperation
	public void deleteExpired () {}
}


@Index(entity=SamlRequestEntity.class, columns={"REQ_EXTID"}, name="SC_SAMLREQ_EXTID_UK", unique=true)
class SAMLRequestEntityIndexByExternalId
{
	
}
