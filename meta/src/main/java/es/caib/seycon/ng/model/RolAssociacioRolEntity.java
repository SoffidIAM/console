//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ROLROL" )
@Depends ({es.caib.seycon.ng.comu.RolGrant.class,
	es.caib.seycon.ng.comu.ContenidorRol.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class RolAssociacioRolEntity {

	@Column (name="RRL_CONTINGUT")
	public es.caib.seycon.ng.model.RolEntity rolContingut;

	@Column (name="RRL_CONTENIDOR")
	public es.caib.seycon.ng.model.RolEntity rolContenidor;

	@Column (name="RRL_ID")
	@Identifier
	public java.lang.Long id;


	@Column (name="RRL_APLICA2")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity granteeApplicationDomain;

	@Column (name="RRL_GRUP2")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity granteeGroupDomain;

	@Column (name="RRL_VALDOM2")
	@Nullable
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity granteeDomainValue;


	@Column (name="RRL_APLICA")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity grantedApplicationDomain;

	@Column (name="RRL_GRUP")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grantedGroupDomain;

	@Column (name="RRL_VALDOM")
	@Nullable
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity grantedDomainValue;

	@DaoFinder("select rolAssociacioRol.rolContenidor\nfrom es.caib.seycon.ng.model.RolAssociacioRolEntity rolAssociacioRol\nwhere \nrolAssociacioRol.rolContingut.nom = :nomRol and\nrolAssociacioRol.rolContingut.aplicacio.codi = :codiAplicacio and\nrolAssociacioRol.rolContingut.baseDeDades.codi = :codiBBDD\n")
	public java.util.List<es.caib.seycon.ng.model.RolAssociacioRolEntity> findContenidorsByNomRolCodiAplicacioCodiBBDD(
		java.lang.String nomRol, 
		java.lang.String codiAplicacio, 
		java.lang.String codiBBDD) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.RolAssociacioRolEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder("select rolAssociacioRol\nfrom es.caib.seycon.ng.model.RolAssociacioRolEntity rolAssociacioRol\nwhere \nrolAssociacioRol.rolContingut = :rolContingut\nand rolAssociacioRol.rolContenidor = :rolContenidor\n")
	public java.util.List<es.caib.seycon.ng.model.RolAssociacioRolEntity> findRolAssociacioRol(
		es.caib.seycon.ng.model.RolEntity rolContingut, 
		es.caib.seycon.ng.model.RolEntity rolContenidor) {
	 return null;
	}
	@DaoFinder("select rolAssociacioRol\nfrom es.caib.seycon.ng.model.RolAssociacioRolEntity rolAssociacioRol\nwhere \nrolAssociacioRol.rolContingut = :rolContingut\n")
	public java.util.List<es.caib.seycon.ng.model.RolAssociacioRolEntity> findRolAssociacioRolEsContingut(
		es.caib.seycon.ng.model.RolEntity rolContingut) {
	 return null;
	}
	public java.lang.String toString() {
	 return null;
	}
}
