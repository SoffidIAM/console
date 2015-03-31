//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_MAQUINALIAS", translatedName="HostAliasEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.AliasMaquina.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class AliasMaquinaEntity {

	@Column (name="MAL_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="MAL_ALIAS", length=2048)
	public java.lang.String alias;

	@Column (name="MAL_MAQID", translated="host")
	public es.caib.seycon.ng.model.MaquinaEntity maquina;

	@Operation(translated="findAliasByHostName")
	@DaoFinder("select alias\nfrom es.caib.seycon.ng.model.AliasMaquinaEntity alias where alias.maquina.nom = :nomMaquina\norder by alias.alias")
	public java.util.List<es.caib.seycon.ng.model.AliasMaquinaEntity> findAliasByNomMaquina(
		java.lang.String nomMaquina) {
	 return null;
	}
	@Operation(translated="findHostByAlias")
	@DaoFinder("select aliasM.maquina\nfrom es.caib.seycon.ng.model.AliasMaquinaEntity aliasM where aliasM.alias like :alias")
	public java.util.List<es.caib.seycon.ng.model.AliasMaquinaEntity> findMaquinaByAlias(
		java.lang.String alias) {
	 return null;
	}
	@Operation(translated="findAliasByHostNameAndAlias")
	@DaoFinder("select alias\nfrom es.caib.seycon.ng.model.AliasMaquinaEntity alias where alias.maquina.nom = :nomMaquina and alias.alias = :alias\norder by alias.alias")
	public java.util.List<es.caib.seycon.ng.model.AliasMaquinaEntity> findAliasByNomMaquinaAndAlias(
		java.lang.String nomMaquina, 
		java.lang.String alias) {
	 return null;
	}
}
