//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TARGET" )
@Depends ({es.caib.seycon.ng.model.ScContar.class,
	es.caib.seycon.ng.comu.TargetaExtranet.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class ScTarget {

	@Column (name="TAR_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TAR_CODI", length=10)
	public java.lang.String codi;

	@Column (name="TAR_DATEMI")
	public java.util.Date dataEmissio;

	@Column (name="TAR_DATCAD")
	public java.util.Date dataCaducitat;

	@Column (name="TAR_ACTIVA", length=1)
	public java.lang.String actiu;

	@Column (name="TAR_IDUSU")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@ForeignKey (foreignColumn="CTA_IDTAR")
	public java.util.Collection<es.caib.seycon.ng.model.ScContar> contingut;

	@DaoFinder
	public es.caib.seycon.ng.model.ScTarget creaTargetaExtranet(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.ScTarget findById(
		java.lang.Long id) {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.ScTarget targeta, es.caib.seycon.ng.model.UsuariEntity usuari where targeta.codi = :codiTargeta and targeta.usuari=usuari and usuari.codi = :codiUsuari")
	public es.caib.seycon.ng.model.ScTarget findByCodiTargetaAndCodiUsuari(
		java.lang.String codiTargeta, 
		java.lang.String codiUsuari) {
	 return null;
	}
}
