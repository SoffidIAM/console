//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_AUDITO" )
@Depends ({es.caib.seycon.ng.comu.Auditoria.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.ImpressoraEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	es.caib.seycon.ng.servei.PasswordService.class})
public abstract class AuditoriaEntity {

	@Column (name="AUD_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="AUD_DATA")
	public java.util.Date data;

	@Column (name="AUD_USUAUD", length=100)
	@Nullable
	public java.lang.String usuari;

	@Column (name="AUD_ACCIO", length=1)
	public java.lang.String accio;

	@Column (name="AUD_INFO", length=1024)
	@Nullable
	public java.lang.String objecte;

	@Column (name="AUD_IDGRU")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column (name="AUD_DOMRLU", length=100)
	@Nullable
	public java.lang.String domini;

	@Column (name="AUD_VALDOMRLU", length=100)
	@Nullable
	public java.lang.String valorDomini;

	@Column (name="AUD_CON", length=100)
	@Nullable
	public java.lang.String parametreConfiguracio;

	@Column (name="AUD_DIS", length=150)
	@Nullable
	public java.lang.String bbdd;

	@Column (name="AUD_IMP", length=100)
	@Nullable
	public java.lang.String impressora;

	@Column (name="AUD_APL", length=100)
	@Nullable
	public java.lang.String aplicacio;

	@Column (name="AUD_DCO", length=100)
	@Nullable
	public java.lang.String dominiCorreu;

	@Column (name="AUD_LCO", length=100)
	@Nullable
	public java.lang.String llistaCorreu;

	@Column (name="AUD_XAR", length=100)
	@Nullable
	public java.lang.String xarxa;

	@Column (name="AUD_ROL", length=100)
	@Nullable
	public java.lang.String rol;

	@Column (name="AUD_MAQ", length=100)
	@Nullable
	public java.lang.String maquina;

	@Column (name="AUD_AUT", length=100)
	@Nullable
	public java.lang.String autoritzacio;

	@Column (name="AUD_FITXER")
	@Nullable
	public java.lang.Long fitxerId;

	@Column (name="AUD_FED", length=100)
	@Nullable
	public java.lang.String federacioIdentitats;

	@Column (name="AUD_LCO2", length=100)
	@Nullable
	public java.lang.String llistaCorreuPertany;

	@Column (name="AUD_DCO2", length=100)
	@Nullable
	public java.lang.String dominiCorreuPertany;

	@Column (name="AUD_ACCOUN", length=128)
	@Nullable
	public String account;

	@Column (name="AUD_ACC_ID")
	@Nullable
	public es.caib.seycon.ng.model.AccountEntity accountAssoc;

	@Column (name="AUD_MESSAG", length=1024)
	@Nullable
	public java.lang.String message;

	@Column (name="AUD_TIPUSU", length=255)
	@Nullable
	public java.lang.String userType;

	@Column (name="AUD_DOMUSU", length=100)
	@Nullable
	public java.lang.String userDomain;

	@Column (name="AUD_DOMCON", length=100)
	@Nullable
	public java.lang.String passwordDomain;

	@Column (name="AUD_RULE", length=200)
	@Nullable
	public java.lang.String rule;

	@Column (name="AUD_SCHTAS")
	@Nullable
	public java.lang.String scheduledTask;

	@DaoFinder
	public es.caib.seycon.ng.model.AuditoriaEntity findById(
		java.lang.Long id) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder
	public java.lang.String[] find(
		java.lang.String sqlQuery) {
	 return null;
	}
	@DaoFinder("select auditoria from es.caib.seycon.ng.model.AuditoriaEntity auditoria\n left join auditoria.accountAssoc accountAssoc \nwhere \n  (:usuari is null or auditoria.usuari like :usuari) and \n (:objecte is null or auditoria.objecte like :objecte) and \n (:autor is null or accountAssoc.name like :autor)  and\n (:accio is null or auditoria.accio=:accio)  \norder by auditoria.data asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri1(
		java.lang.String autor, 
		java.lang.String objecte, 
		java.lang.String usuari, 
		java.lang.String accio) {
	 return null;
	}
	@DaoFinder("select auditoria \nfrom es.caib.seycon.ng.model.AuditoriaEntity auditoria \nleft join auditoria.accountAssoc accountAssoc\nleft join auditoria.grup grupAssoc \nwhere \n (:usuari is null or auditoria.usuari like :usuari) \nand (:objecte is null or auditoria.objecte like :objecte) \nand (:autor is null or accountAssoc.name like :autor)  \nand (:accio is null or auditoria.accio=:accio)  \nand (:objecteAuditat is null or \n   (:objecteAuditat is not null  and  \n    ( \n      (:objecteAuditat='domini' and auditoria.domini like :valorOA) \n      or (:objecteAuditat='valorDomini' and auditoria.valorDomini like :valorOA) \n      or (:objecteAuditat='parametreConfiguracio' and auditoria.parametreConfiguracio like :valorOA) \n      or (:objecteAuditat='bbdd' and auditoria.bbdd like :valorOA) \n      or (:objecteAuditat='impressora' and auditoria.impressora like :valorOA) \n      or (:objecteAuditat='aplicacio' and auditoria.aplicacio like :valorOA) \n      or (:objecteAuditat='dominiCorreu' and auditoria.dominiCorreu like :valorOA) \n      or (:objecteAuditat='llistaCorreu' and auditoria.llistaCorreu like :valorOA) \n      or (:objecteAuditat='fitxer' and auditoria.fitxerId like :valorOA) \n      or (:objecteAuditat='xarxa' and auditoria.xarxa like :valorOA) \n      or (:objecteAuditat='rol' and auditoria.rol like :valorOA) \n      or (:objecteAuditat='maquina' and auditoria.maquina like :valorOA) \n      or (:objecteAuditat='autoritzacio' and auditoria.autoritzacio like :valorOA) \n      or (:objecteAuditat='grup' and grupAssoc is not null and grupAssoc.codi like :valorOA)\n      or (:objecteAuditat='userType' and auditoria.userType like :valorOA)\n      or (:objecteAuditat='passwordDomain' and auditoria.passwordDomain like :valorOA)\n      or (:objecteAuditat='userDomain' and auditoria.userDomain like :valorOA)\n    )\n   )\n)\norder by auditoria.data asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri2(
		java.lang.String autor, 
		java.lang.String objecte, 
		java.lang.String usuari, 
		java.lang.String objecteAuditat, 
		java.lang.String valorOA, 
		java.lang.String accio) {
	 return null;
	}
	@DaoFinder("select auditoria \nfrom\nes.caib.seycon.ng.model.AuditoriaEntity auditoria left join\nauditoria.accountAssoc accountAssoc\nwhere\n(:dataMax = :nullDate or auditoria.data < :dataMax ) and\n(:dataMin = :nullDate or auditoria.data > :dataMin ) and\n (:usuari is null or auditoria.usuari like :usuari) \nand (:objecte is null or auditoria.objecte like :objecte) \nand (:autor is null or accountAssoc.name like :autor)  and\n(:accio is null or auditoria.accio=:accio)  \norder by auditoria.data asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri3(
		java.util.Date nullDate, 
		java.util.Date dataMax, 
		java.util.Date dataMin, 
		java.lang.String autor, 
		java.lang.String objecte, 
		java.lang.String usuari, 
		java.lang.String accio) {
	 return null;
	}
	@DaoFinder("select auditoria \nfrom\nes.caib.seycon.ng.model.AuditoriaEntity auditoria \nleft join auditoria.accountAssoc accountAssoc\nleft join auditoria.grup grupAssoc \nwhere\n (:dataMax = :nullDate or auditoria.data < :dataMax ) and\n (:dataMin = :nullDate or auditoria.data > :dataMin ) and\n (:usuari is null or auditoria.usuari like :usuari) and \n (:objecte is null or auditoria.objecte like :objecte) and \n (:autor is null or accountAssoc.name like :autor)  and\n (:accio is null or auditoria.accio=:accio)  and\n (:objecteAuditat is null or \n   (:objecteAuditat is not null  and  \n    ( \n      (:objecteAuditat='domini' and auditoria.domini like :valorOA) \n      or (:objecteAuditat='valorDomini' and auditoria.valorDomini like :valorOA) \n      or (:objecteAuditat='parametreConfiguracio' and auditoria.parametreConfiguracio like :valorOA) \n      or (:objecteAuditat='bbdd' and auditoria.bbdd like :valorOA) \n      or (:objecteAuditat='impressora' and auditoria.impressora like :valorOA) \n      or (:objecteAuditat='aplicacio' and auditoria.aplicacio like :valorOA) \n      or (:objecteAuditat='dominiCorreu' and auditoria.dominiCorreu like :valorOA) \n      or (:objecteAuditat='llistaCorreu' and auditoria.llistaCorreu like :valorOA) \n      or (:objecteAuditat='fitxer' and auditoria.fitxerId like :valorOA) \n      or (:objecteAuditat='xarxa' and auditoria.xarxa like :valorOA) \n      or (:objecteAuditat='rol' and auditoria.rol like :valorOA) \n      or (:objecteAuditat='maquina' and auditoria.maquina like :valorOA) \n      or (:objecteAuditat='autoritzacio' and auditoria.autoritzacio like :valorOA) \n      or (:objecteAuditat='grup' and grupAssoc is not null and grupAssoc.codi like :valorOA)\n      or (:objecteAuditat='userType' and auditoria.userType like :valorOA)\n      or (:objecteAuditat='passwordDomain' and auditoria.passwordDomain like :valorOA)\n      or (:objecteAuditat='userDomain' and auditoria.userDomain like :valorOA)\n      or (:objecteAuditat='account' and auditoria.account like :valorOA)\n    )\n   )\n)\norder by auditoria.data asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri4(
		java.util.Date nullDate, 
		java.util.Date dataMax, 
		java.util.Date dataMin, 
		java.lang.String autor, 
		java.lang.String objecte, 
		java.lang.String usuari, 
		java.lang.String objecteAuditat, 
		java.lang.String valorOA, 
		java.lang.String accio) {
	 return null;
	}
}
