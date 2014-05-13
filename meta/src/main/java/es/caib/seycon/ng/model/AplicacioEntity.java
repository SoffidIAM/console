//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

import es.caib.bpm.servei.BpmEngine;

@Entity (table="SC_APLICA" )
@Depends ({es.caib.seycon.ng.comu.ValorDomini.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.Aplicacio.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.NotificacioEntity.class,
	es.caib.seycon.ng.model.SoDRuleEntity.class,
	BpmEngine.class })
public abstract class AplicacioEntity {

	@Column (name="APL_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="APL_CODI", length=20)
	public java.lang.String codi;

	@Column (name="APL_NOM", length=50)
	public java.lang.String nom;

	@Column (name="APL_DIRFON", length=50)
	@Nullable
	public java.lang.String directoriFonts;

	@Column (name="APL_DIRECT", length=50)
	@Nullable
	public java.lang.String directoriExecutable;

	@Column (name="APL_BD", length=25)
	@Nullable
	public java.lang.String bd;

	@ForeignKey (foreignColumn="ROL_IDAPL")
	public java.util.Collection<es.caib.seycon.ng.model.RolEntity> rols;

	@Column (name="APL_IDCONTACT")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity personaContacte;

	@Column (name="APL_GESTIONABLEWF", length=1)
	@Nullable
	public java.lang.String gestionableWF;

	@Column (name="APL_MAILNOTIF", length=512)
	@Nullable
	public java.lang.String correusNotificacions;
	
	@Description ("Approval process needed for workflow managed roles belonging to this application. Null value means no approval process is needed")
	@Column (name="APL_APRPRO", length=256)
	@Nullable
	public String approvalProcess;

	/************************** DAOS *********************************************************/
	
	@ForeignKey (foreignColumn="SOD_APL_ID")
	public java.util.Collection<es.caib.seycon.ng.model.SoDRuleEntity> sodRules;

	@DaoFinder
	public es.caib.seycon.ng.model.AplicacioEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder("select aplicacioEntity \nfrom \nes.caib.seycon.ng.model.AplicacioEntity aplicacioEntity \nleft join aplicacioEntity.personaContacte personaContacte \nwhere \n(:codi is null or aplicacioEntity.codi like :codi) and \n(:nom is null or aplicacioEntity.nom like :nom) and \n(:directoriFonts is null or aplicacioEntity.directoriFonts like :directoriFonts) and\n(:responsable is null or personaContacte.codi like :responsable) and\n(:directoriExecutable is null or aplicacioEntity.directoriExecutable like :directoriExecutable) and\n(:bd is null or aplicacioEntity.bd like :bd)  and (:gestionableWF is null or aplicacioEntity.gestionableWF like :gestionableWF)\norder by aplicacioEntity.codi")
	public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> findAplicacioByCriteri(
		java.lang.String codi, 
		java.lang.String nom, 
		java.lang.String directoriFonts, 
		java.lang.String responsable, 
		java.lang.String directoriExecutable, 
		java.lang.String bd, 
		java.lang.String gestionableWF) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder("select distinct aplicacio\nfrom es.caib.seycon.ng.model.UsuariEntity as usuari\njoin usuari.accounts as accounts\njoin accounts.account as account\njoin account.roles as roles\njoin roles.rol as rol\njoin rol.aplicacio as aplicacio with aplicacio.gestionableWF='S'\nwhere usuari.codi = :codiUsuari ")
	public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> findManageableByUser(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select distinct aplicacio\nfrom es.caib.seycon.ng.model.UsuariEntity as usuari\njoin usuari.accounts as accounts\njoin accounts.account as account\njoin account.roles as roles\njoin roles.rol as rol\njoin rol.aplicacio as aplicacio \nwhere usuari.codi = :codiUsuari ")
	public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> findByUser(
		java.lang.String codiUsuari) {
	 return null;
	}
}
