//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_VALOR_DOMINI", translatedName="DomainValueEntity", translatedPackage="com.soffid.iam.model"  )
@Depends ({es.caib.seycon.ng.comu.ValorDomini.class,
	es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class})
public abstract class ValorDominiAplicacioEntity {

	@Column (name="VDO_VALOR", length=30, translated="value")
	public java.lang.String valor;

	@Column (name="VDO_DOM", translated="domain")
	public es.caib.seycon.ng.model.DominiAplicacioEntity domini;

	@Column (name="VDO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="VDO_DESC", length=50, translated="description")
	public java.lang.String descripcio;

	@Operation(translated="findDomainValueAndDomainNameAndDomainRoleNameAndDomainValue")
	@DaoFinder("select valorDominiAplicacio from \nes.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio \n"
			+ "left join valorDominiAplicacio.domini domini\n"
			+ "left join valorDominiAplicacio.domini.rols rol\n"
			+ "where \n"
				+ "domini.nom = :nomDomini and\n"
				+ "rol.nom = :nomRol and \n"
				+ "valorDominiAplicacio.valor = :valor")
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity findValorDominiByNomDominiAndNomRolDominiAndValorDomini(
		java.lang.String nomDomini, 
		java.lang.String nomRol, 
		java.lang.String valor) {
	 return null;
	}
	
	@DaoFinder("select value from \n"
			+ "es.caib.seycon.ng.model.ValorDominiAplicacioEntity as value \n"
			+ "left join value.domini as domain\n"
			+ "left join domain.aplicacio as app\n"
			+ "where \n"
				+ "app.codi = :app and\n"
				+ "domain.nom = :domain and\n"
				+ "value.valor = :value")
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity findByApplicationDomainValue(
		java.lang.String app, 
		java.lang.String domain, 
		java.lang.String value) {
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
