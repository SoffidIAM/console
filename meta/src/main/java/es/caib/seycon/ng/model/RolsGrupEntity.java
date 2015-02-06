//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ROLGRUP" )
@Depends ({es.caib.seycon.ng.comu.RolGrant.class,
	es.caib.seycon.ng.comu.RolsGrup.class,
	es.caib.seycon.ng.comu.ContenidorRol.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class})
public abstract class RolsGrupEntity {

	@Column (name="RLG_GRUP")
	public es.caib.seycon.ng.model.GrupEntity grupPosseidor;

	@Column (name="RLG_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="RLG_ROL")
	public es.caib.seycon.ng.model.RolEntity rolOtorgat;

	@Column (name="RLG_APL_ID")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity grantedApplicationDomain;

	@Column (name="RLG_GRU_ID")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grantedGroupDomain;

	@Column (name="RLG_VDO_ID")
	@Nullable
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity grantedDomainValue;


	@DaoFinder("select rolsgrup from es.caib.seycon.ng.model.RolsGrupEntity rolsgrup where rolsgrup.rolOtorgat = :rolOtorgat")
	public java.util.List<es.caib.seycon.ng.model.RolsGrupEntity> findGrupsPosseidorsRol(
		es.caib.seycon.ng.model.RolEntity rolOtorgat) {
	 return null;
	}
	public java.lang.String toString() {
	 return null;
	}
	@DaoFinder("select rolsgrup from es.caib.seycon.ng.model.RolsGrupEntity rolsgrup where rolsgrup.grupPosseidor = :grup")
	public java.util.List<es.caib.seycon.ng.model.RolsGrupEntity> findRolsAtorgatsGrup(
		es.caib.seycon.ng.model.GrupEntity grup) {
	 return null;
	}
}
