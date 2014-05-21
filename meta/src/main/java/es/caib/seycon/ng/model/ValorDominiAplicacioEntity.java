//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_VALOR_DOMINI" )
@Depends ({es.caib.seycon.ng.comu.ValorDomini.class,
	es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class})
public abstract class ValorDominiAplicacioEntity {

	@Column (name="VDO_VALOR", length=30)
	public java.lang.String valor;

	@Column (name="VDO_DOM")
	public es.caib.seycon.ng.model.DominiAplicacioEntity domini;

	@Column (name="VDO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="VDO_DESC", length=50)
	public java.lang.String descripcio;

	@DaoFinder("select valorDominiAplicacio from \nes.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio \nleft join valorDominiAplicacio.domini domini\nleft join valorDominiAplicacio.domini.rols rol\nwhere \ndomini.nom = :nomDomini and\nrol.nom = :nomRol and \nvalorDominiAplicacio.valor = :valor")
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity findValorDominiByNomDominiAndNomRolDominiAndValorDomini(
		java.lang.String nomDomini, 
		java.lang.String nomRol, 
		java.lang.String valor) {
	 return null;
	}
	@DaoFinder("select valorDominiAplicacio\nfrom\nes.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio \nleft join valorDominiAplicacio.domini domini\nleft join valorDominiAplicacio.domini.rols rol\nwhere\ndomini.nom = :nomDomini and\nrol.nom = :nomRol")
	public java.util.List<es.caib.seycon.ng.model.ValorDominiAplicacioEntity> findValorsDominisByNomDominiAndNomRolDomini(
		java.lang.String nomDomini, 
		java.lang.String nomRol) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.ValorDominiAplicacioEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
