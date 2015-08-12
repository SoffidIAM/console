//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SESSIO" )
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

	@Column (name="SES_IDUSU")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="SES_IDMACL")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity maquinaClient;

	@Column (name="SES_IDMAQ")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity maquina;

	@Column (name="SES_DATA")
	public java.util.Date dataInici;

	@Column (name="SES_DAKEAL")
	@Nullable
	public java.util.Date dataKeepAlive;

	@Column (name="SES_EXCLIP", length=30)
	@Nullable
	public java.lang.String externalClientIp;

	@Column (name="SES_KEY2", length=50)
	@Nullable
	public java.lang.String novaClau;

	@Column (name="SES_TIPUS")
	@Nullable
	public es.caib.seycon.ng.comu.TipusSessio tipus;

	@Column (name="SES_WEBURL", length=4000)
	@Nullable
	public java.lang.String webHandler;

	@Column (name="SES_KEY", length=50)
	@Nullable
	public java.lang.String clau;

	@Column (name="SES_IDRAC")
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

	@DaoFinder("select sessio\nfrom es.caib.seycon.ng.model.SessioEntity sessio, es.caib.seycon.ng.model.UsuariEntity usuari where  usuari.codi = :codiUsuari and sessio.usuari = usuari")
	public java.util.List<es.caib.seycon.ng.model.SessioEntity> findSessionsByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select sessio \nfrom \nes.caib.seycon.ng.model.SessioEntity sessio \nwhere (:port is null or sessio.port like :port) \nand (:nomMaquinaClient is null \n         or (sessio.clientHostName like :nomMaquinaClient)) \nand (:nomMaquinaServidora is null \n  or (sessio.hostName like :nomMaquinaServidora)) \nand (:codiUsuari is null or sessio.usuari.codi like :codiUsuari)\n")
	public java.util.List<es.caib.seycon.ng.model.SessioEntity> findSessionsByCriteri(
		java.lang.Long port, 
		java.lang.String codiUsuari, 
		java.lang.String nomMaquinaServidora, 
		java.lang.String nomMaquinaClient) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.SessioEntity sessio \nwhere sessio.port = :port and sessio.clientHostName = :nomMaquinaClient and sessio.hostName = :nomMaquinaServidora and sessio.usuari.codi = :codiUsuari")
	public es.caib.seycon.ng.model.SessioEntity findSessioByCriteri(
		java.lang.Long port, 
		java.lang.String codiUsuari, 
		java.lang.String nomMaquinaServidora, 
		java.lang.String nomMaquinaClient) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.SessioEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.SessioEntity findById(
		java.lang.Long id) {
	 return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}
