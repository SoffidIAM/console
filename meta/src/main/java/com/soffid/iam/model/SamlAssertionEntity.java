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

@Entity (table="SC_SAMLASS" )
@Depends ({})
public class SamlAssertionEntity {
	@Column (name="ASS_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="ASS_EXTID")
	public String externalId;

	@Column (name="ASS_DATE")
	public Date date;

	@Column (name="ASS_ISSUER")
	public String issuer;
	
	@DaoFinder
	public SamlAssertionEntity findByExternalId(String externalId) {return null;}

	@DaoFinder ("select a from com.soffid.iam.model.SamlAssertionEntity as a where a.date < :d")
	public Collection<SamlAssertionEntity> findExpired(Date d) {return null;}

	@DaoOperation
	public void deleteExpired () {}
}


@Index(entity=SamlAssertionEntity.class, columns={"ASS_EXTID"}, name="SC_SAMLASS_EXTID_UK", unique=true)
class SAMLAssertionEntityIndexByExternalId
{
	
}
