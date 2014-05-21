//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DADUSU" )
@Depends ({es.caib.seycon.ng.comu.DadaUsuari.class,
	es.caib.seycon.ng.model.TipusDadaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class DadaUsuariEntity {

	@Column (name="DUS_VALOR", length=1024)
	@Nullable
	public java.lang.String valorDada;

	@Column (name="DUS_IDUSU")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="DUS_TDAID")
	public es.caib.seycon.ng.model.TipusDadaEntity tipusDada;

	@Column (name="DUS_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DUS_BLOB", length=400000)
	@Nullable
	public byte[] blobDataValue;

	@DaoFinder("select dadaUsuari from es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari,\nes.caib.seycon.ng.model.TipusDadaEntity tipusDada where usuari.codi = :codiUsuari and dadaUsuari.usuari = usuari and dadaUsuari.tipusDada = tipusDada and tipusDada.codi = :codiTipusDada")
	public es.caib.seycon.ng.model.DadaUsuariEntity findDadaByCodiTipusDada(
		java.lang.String codiUsuari, 
		java.lang.String codiTipusDada) {
	 return null;
	}
	@DaoFinder("select dadaUsuari from es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari, es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.TipusDadaEntity tipusDada where dadaUsuari.usuari = usuari and usuari.codi = :codiUsuari and tipusDada.codi = :codiTipusDada and dadaUsuari.tipusDada = tipusDada")
	public es.caib.seycon.ng.model.DadaUsuariEntity findDadaByCodiUsuariAndCodiTipusDada(
		java.lang.String codiUsuari, 
		java.lang.String codiTipusDada) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.DadaUsuariEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder("select dada\nfrom es.caib.seycon.ng.model.DadaUsuariEntity as dada\nwhere dada.valorDada = :value and\ndada.tipusDada.codi=:type")
	public java.util.List<es.caib.seycon.ng.model.DadaUsuariEntity> findByTypeAndValue(
		java.lang.String type, 
		java.lang.String value) {
	 return null;
	}
}
