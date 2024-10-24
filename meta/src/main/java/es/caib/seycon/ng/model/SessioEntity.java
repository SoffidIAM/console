//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;

import com.soffid.mda.annotation.*;

@Entity (table="SC_SESSIO", translatedName="SessionEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.Sessio.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.RegistreAccesEntity.class})
public abstract class SessioEntity {

	@Column (name="SES_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SES_PORT")
	@Nullable
	public java.lang.Long port;

	@Column (name="SES_IDUSU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Nullable
	@Description("Account used in PAM sessions")
	@Column (name="SES_ACC_ID")
	public AccountEntity account;

	@Column (name="SES_IDMACL", translated="clientHost")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity maquinaClient;

	@Column (name="SES_IDMAQ", translated="host")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity maquina;

	@Column (name="SES_DATA", translated="startDate")
	public java.util.Date dataInici;

	@Column (name="SES_DAKEAL", translated="keepAliveDate")
	@Nullable
	public java.util.Date dataKeepAlive;

	@Column (name="SES_EXCLIP", length=60)
	@Nullable
	public java.lang.String externalClientIp;

	@Column (name="SES_KEY2", length=50, translated="newKey")
	@Nullable
	public java.lang.String novaClau;

	@Column (name="SES_TIPUS", translated="type")
	@Nullable
	public es.caib.seycon.ng.comu.TipusSessio tipus;

	@Column (name="SES_WEBURL", length=4000)
	@Nullable
	public java.lang.String webHandler;

	@Column (name="SES_KEY", length=50, translated="key")
	@Nullable
	public java.lang.String clau;

	@Column (name="SES_IDRAC", translated="loginLogInfo")
	@Nullable
	public es.caib.seycon.ng.model.RegistreAccesEntity regIstreLogin;

	@Column (name="SES_CLIADR", length=128)
	@Nullable
	public java.lang.String clientAddress;

	@Column (name="SES_CLINAM", length=128)
	@Nullable
	public java.lang.String clientHostName;

	@Column (name="SES_HOSNAM", length=128)
	@Nullable
	public java.lang.String hostName;

	@Column (name="SES_HOSADR", length=128)
	@Nullable
	public java.lang.String hostAddress;

	@Column (name="SES_AUTHMT", length=4)
	@Nullable
	public String authenticationMethod;

	@Column (name="SES_MONURL", length=150)
	@Nullable
	public String monitorUrl;
	
	@Nullable
	@Column (name="SES_JITTR", length=2000)
	public String justInTimePermissionToRemove;

	@Operation(translated="findSessionByUserName")
	@DaoFinder("select session\n"
			+ "from com.soffid.iam.model.SessionEntity session "
			+ "where session.user.userName=:userName")
	public java.util.List<es.caib.seycon.ng.model.SessioEntity> findSessionsByCodiUsuari(
		java.lang.String userName) {
	 return null;
	}
	@Operation(translated="findSessionsByCriteria")
	@DaoFinder("select session \n"
			+ "from com.soffid.iam.model.SessionEntity session \n"
			+ "where (:port is null or session.port like :port) \n"
			+ "and (:clientHostName is null \n"
			+ "         or (session.clientHostName like :clientHostName)) \n"
			+ "and (:serverHostName is null \n  or (session.hostName like :serverHostName)) \n"
			+ "and (:userName is null or session.user.userName like :userName)\n")
	public java.util.List<es.caib.seycon.ng.model.SessioEntity> findSessionsByCriteri(
		java.lang.Long port, 
		java.lang.String userName, 
		java.lang.String serverHostName, 
		java.lang.String clientHostName) {
	 return null;
	}
	@Operation(translated="findSessionByCriteria")
	@DaoFinder("select session \n"
			+ "from com.soffid.iam.model.SessionEntity session \n"
			+ "where session.port = :port and "
			+ " session.clientHostName = :clientHostName and "
			+ " session.hostName = :serverHostName and "
			+ " session.user.userName = :userName")
	public es.caib.seycon.ng.model.SessioEntity findSessioByCriteri(
		java.lang.Long port, 
		java.lang.String userName, 
		java.lang.String serverHostName, 
		java.lang.String clientHostName) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.SessioEntity findById(
		java.lang.Long id) {
	 return null;
	}

	@DaoFinder
	public Collection<es.caib.seycon.ng.model.SessioEntity> findByKey(
		String key) {
	 return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}
