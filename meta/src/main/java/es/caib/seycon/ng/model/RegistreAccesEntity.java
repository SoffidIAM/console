//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_REGACC" )
@Depends ({es.caib.seycon.ng.model.ServeiEntity.class,
	es.caib.seycon.ng.comu.RegistreAcces.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class RegistreAccesEntity {

	@Column (name="RAC_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="RAC_IDSES", length=200)
	@Nullable
	public java.lang.String idSessio;

	@Column (name="RAC_DATINI")
	@Nullable
	public java.util.Date dataInici;

	@Column (name="RAC_DATFI")
	@Nullable
	public java.util.Date dataFi;

	@Column (name="RAC_CODAGE", length=50)
	@Nullable
	public java.lang.String codeAge;

	@Column (name="RAC_INFO", length=1024)
	@Nullable
	public java.lang.String informacio;

	@Column (name="RAC_IDMAQ")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidor;

	@Column (name="RAC_IDMAOR")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity client;

	@Column (name="RAC_IDSER")
	@Nullable
	public es.caib.seycon.ng.model.ServeiEntity protocol;

	@Column (name="RAC_IDUSU")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="RAC_TIPUSACC", length=1)
	@Nullable
	public java.lang.String tipusAcces;

	@Column (name="RAC_CLIADR", length=128)
	@Nullable
	public java.lang.String clientAddress;

	@Column (name="RAC_CLINAM", length=128)
	@Nullable
	public java.lang.String clientHostName;

	@Column (name="RAC_HOSADR", length=128)
	@Nullable
	public java.lang.String hostAddress;

	@Column (name="RAC_HOSNAM", length=128)
	@Nullable
	public java.lang.String hostName;

	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nleft join registreAcces.usuari usuari\nwhere\n(:nomClient is null or registreAcces.clientHostName like :nomClient) and\n(:nomServidor is null or registreAcces.hostName like :nomServidor) and\n(:codiUsuari is null or usuari.codi like :codiUsuari) and\n(:maxDate = :nullDate or registreAcces.dataInici < :maxDate ) and\n(:minDate = :nullDate or registreAcces.dataInici > :minDate ) order by registreAcces.dataInici")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltre(
		java.util.Date nullDate, 
		java.util.Date maxDate, 
		java.util.Date minDate, 
		java.lang.String nomClient, 
		java.lang.String nomServidor, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nleft join registreAcces.usuari usuari\nwhere\n(:nomClient is null or registreAcces.clientHostName like :nomClient) and\n(:nomServidor is null or registreAcces.hostName  like :nomServidor) and\n(:codiUsuari is null or usuari.codi like :codiUsuari) order by registreAcces.dataInici\n")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltre(
		java.lang.String nomClient, 
		java.lang.String nomServidor, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nleft join registreAcces.usuari usuari\nwhere\n(:nomClient is null or registreAcces.clientHostName like :nomClient) and\n(:nomServidor is null or registreAcces.hostName like :nomServidor) and\n(:codiUsuari is null or usuari.codi like :codiUsuari) and\n(:dataIni = :nullDate or registreAcces.dataInici >= :dataIni ) and\n(:dataFi = :nullDate or registreAcces.dataFi <= :dataFi )  order by registreAcces.dataInici")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltre2Datas(
		java.util.Date nullDate, 
		java.util.Date dataIni, 
		java.util.Date dataFi, 
		java.lang.String nomClient, 
		java.lang.String nomServidor, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nwhere\n(:nomServidor is null or registreAcces.hostName like :nomServidor) and\n(:dataIni = :nullDate or registreAcces.dataInici >= :dataIni) \nand (:protocolAcces is null or\nregistreAcces.protocol.codi = :protocolAcces)\norder by registreAcces.dataInici")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByMaquina(
		java.util.Date nullDate, 
		java.util.Date dataIni, 
		java.lang.String nomServidor, 
		java.lang.String protocolAcces) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nwhere\n(:nomServidor is null or registreAcces.hostName like :nomServidor) and\n(:dataIni = :nullDate or registreAcces.dataInici >= :dataIni) \nand (:protocolAcces is null or\nregistreAcces.protocol.codi = :protocolAcces) \n order by registreAcces.dataInici desc")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByMaquinaDataIniDesc(
		java.util.Date nullDate, 
		java.util.Date dataIni, 
		java.lang.String nomServidor, 
		java.lang.String protocolAcces) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nleft join registreAcces.usuari\nwhere\n(:dataIni = :nullDate or registreAcces.dataInici >= :dataIni) \nand (:codiUsuari is null or (registreAcces.usuari is not null and registreAcces.usuari.codi = :codiUsuari)) \norder by registreAcces.dataInici\n\n")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByDataIniAndCodiUsuari(
		java.util.Date nullDate, 
		java.util.Date dataIni, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nleft join registreAcces.usuari\nleft join registreAcces.protocol\nwhere\n(:dataIni = :nullDate or registreAcces.dataInici >= :dataIni) \nand (:codiUsuari is null or (registreAcces.usuari is not null and registreAcces.usuari.codi = :codiUsuari)) \nand (:codiProtocolAcces is null or (registreAcces.protocol is not null\nand registreAcces.protocol.codi= :codiProtocolAcces))\norder by registreAcces.dataInici desc\n\n")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByDataIniDescAndCodiUsuari(
		java.util.Date nullDate, 
		java.util.Date dataIni, 
		java.lang.String codiUsuari, 
		java.lang.String codiProtocolAcces) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nleft join registreAcces.usuari\nleft join registreAcces.protocol\nwhere\n(:codiUsuari is null or (registreAcces.usuari is not null and registreAcces.usuari.codi = :codiUsuari)) \nand (:codiProtocolAcces is null or (registreAcces.protocol is not null and registreAcces.protocol.codi= :codiProtocolAcces))\norder by registreAcces.dataInici desc\n\n")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findDarrersRegistresByCodiUsuari(
		java.lang.String codiUsuari, 
		java.lang.String codiProtocolAcces) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom \nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nwhere\n(:nomServidor is null or registreAcces.hostName like :nomServidor)\nand (:protocolAcces is null or\nregistreAcces.protocol.codi = :protocolAcces) \n order by registreAcces.dataInici desc\n")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findDarrersRegistresAccesMaquinaProtocol(
		java.lang.String nomServidor, 
		java.lang.String protocolAcces) {
	 return null;
	}
	@DaoFinder("select registreAcces\nfrom\nes.caib.seycon.ng.model.RegistreAccesEntity registreAcces\nleft join registreAcces.usuari usuari\nwhere\n(:nomClient is null or registreAcces.clientHostName like :nomClient) and\n(:nomServidor is null or registreAcces.hostName like :nomServidor) and\n(:codiUsuari is null or usuari.codi like :codiUsuari) and\n(:dataIni = :nullDate or registreAcces.dataFi >= :dataIni ) and\n(:dataFi = :nullDate or registreAcces.dataInici <= :dataFi ) order by registreAcces.dataInici")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findRegistreByFiltreNou(
		java.util.Date nullDate, 
		java.util.Date dataIni, 
		java.util.Date dataFi, 
		java.lang.String nomClient, 
		java.lang.String nomServidor, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select max(rac.dataInici) as dataInici\nfrom  es.caib.seycon.ng.model.RegistreAccesEntity rac\nwhere rac.codeAge=:dispatcher")
	public java.util.Date findLastDateByDispatcher(
		java.lang.String dispatcher) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.RegistreAccesEntity where idSessio=:sessioId and codeAge = :agent and (dataFi is null or dataFi=:data) and servidor=:server")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findByAgentSessioIdEndDate(
		java.lang.String agent, 
		java.lang.String sessioId, 
		java.util.Date data, 
		es.caib.seycon.ng.model.MaquinaEntity server) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.RegistreAccesEntity where idSessio=:sessioId and codeAge = :agent and (dataInici=:data) and servidor=:server")
	public java.util.List<es.caib.seycon.ng.model.RegistreAccesEntity> findByAgentSessioIdStartDate(
		java.lang.String agent, 
		java.lang.String sessioId, 
		java.util.Date data, 
		es.caib.seycon.ng.model.MaquinaEntity server) {
	 return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}
