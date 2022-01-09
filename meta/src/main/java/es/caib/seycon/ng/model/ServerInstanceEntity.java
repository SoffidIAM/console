//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SERINS", translatedName="ServerInstanceEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({
	ServerEntity.class,
	es.caib.seycon.ng.model.SecretEntity.class,
	com.soffid.iam.model.ScheduledTaskEntity.class})
public abstract class ServerInstanceEntity {

	@Column (name="SRE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SRE_NOM", length=100, translated="name")
	public java.lang.String nom;

	@Column (name="SRE_URL", length = 128)
	@Nullable
	String url;
	
	@Column(name="SRE_SRV_ID", reverseAttribute = "instances")
	ServerEntity server;

	@Column (name="SRE_LASSEE")
	Date lastSeen;
	
	@Nullable @Column (name="SRE_TOKEN")
	String auth;
	
	@Column(name="SRE_TASKS")
	int tasks;
	
	ServerInstanceEntity findByName(String name) {return null;}
	
	ServerInstanceEntity findByUrl(String url) {return null;}

	@DaoFinder("select si "
			+ "from com.soffid.iam.model.ServerInstanceEntityImpl as si "
			+ "where si.server.name=:serverName")
	Collection<ServerInstanceEntity> findByServerName(String serverName) {return null;}
	

	@DaoFinder("select si "
			+ "from com.soffid.iam.model.ServerInstanceEntityImpl as si "
			+ "where si.name=:instanceName and si.server.name=:serverName")
	ServerInstanceEntity findByServerNameAndInstanceName(String serverName, String instanceName) {return null;}

	@DaoFinder("select si "
			+ "from com.soffid.iam.model.ServerInstanceEntityImpl as si "
			+ "where si.lastSeen < :lastSeen")
	List<ServerInstanceEntity> findExpired (Date lastSeen) {return null;}


	@DaoFinder("select si "
			+ "from com.soffid.iam.model.ServerInstanceEntityImpl as si "
			+ "where si.server.name=:serverName "
			+ "order by tasks, id desc")
	List<ServerInstanceEntity> findBestServerInstances (String serverName) {return null;}
}

@Index( entity = ServerInstanceEntity.class, columns = {"SRE_NOM"}, name = "SC_SERINS_UK", unique = true )
class ServerInstanceNameIndex{}