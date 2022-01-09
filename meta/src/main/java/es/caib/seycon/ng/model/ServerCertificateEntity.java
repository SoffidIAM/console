//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Date;

import com.soffid.mda.annotation.*;

@Entity (table="SC_SRVCER", translatedName="ServerCertificateEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.ServerEntity.class})
public abstract class ServerCertificateEntity {

	@Column (name="SCR_SER_ID", reverseAttribute = "certificates")
	public es.caib.seycon.ng.model.ServerEntity server;

	@Column (name="SCR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SCR_CERT", length=64000)
	public byte[] cert;
	
	@Column (name="SCR_SINCE")
	Date since;

	@Column (name="SCR_UNTIL")
	Date until;

	@DaoFinder("select cert\n"
			+ "from com.soffid.iam.model.ServerCertificateEntity as cert\n"
			+ "where cert.server.id=:serverId ")
	public es.caib.seycon.ng.model.ServerCertificateEntity findByServer(
		long serverId) {
	 return null;
	}
}
