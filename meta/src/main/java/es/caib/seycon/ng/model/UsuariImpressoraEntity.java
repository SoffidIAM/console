//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUIMP" )
@Depends ({es.caib.seycon.ng.comu.UsuariImpressora.class,
	es.caib.seycon.ng.model.ImpressoraEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class UsuariImpressoraEntity {

	@Column (name="UIM_ORDRE")
	@Nullable
	public java.lang.Long uimOrdre;

	@Column (name="UIM_IDIMP")
	public es.caib.seycon.ng.model.ImpressoraEntity impressora;

	@Column (name="UIM_IDUSU")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="UIM_ID")
	@Identifier
	public java.lang.Long id;

	@DaoFinder("select usuariImpressora\nfrom es.caib.seycon.ng.model.ImpressoraEntity impressora, es.caib.seycon.ng.model.UsuariEntity usuari,  es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressora where usuariImpressora.usuari = usuari and usuariImpressora.impressora = impressora and usuari.codi = :codiUsuari and impressora.codi = :codiImpressora \norder by usuariImpressora.usuari.codi, usuariImpressora.impressora.codi")
	public es.caib.seycon.ng.model.UsuariImpressoraEntity findUsuariImpressoraByCodiUsuariAndCodiImpressora(
		java.lang.String codiUsuari, 
		java.lang.String codiImpressora) {
	 return null;
	}
	@DaoFinder("select usuariImpressora\nfrom es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressora where usuariImpressora.impressora.codi = :codiImpressora \norder by usuariImpressora.usuari.codi, usuariImpressora.impressora.codi")
	public java.util.List<es.caib.seycon.ng.model.UsuariImpressoraEntity> findUsuariImpressoresByCodiImpressora(
		java.lang.String codiImpressora) {
	 return null;
	}
	@DaoFinder("select usuariImpressora\nfrom es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressora where usuariImpressora.usuari.codi = :codiUsuari \norder by usuariImpressora.usuari.codi, usuariImpressora.impressora.codi")
	public java.util.List<es.caib.seycon.ng.model.UsuariImpressoraEntity> findUsuariImpressoresByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.UsuariImpressoraEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
