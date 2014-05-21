//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_MAQUINALIAS" )
@Depends ({es.caib.seycon.ng.comu.AliasMaquina.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class AliasMaquinaEntity {

	@Column (name="MAL_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="MAL_ALIAS", length=2048)
	public java.lang.String alias;

	@Column (name="MAL_MAQID")
	public es.caib.seycon.ng.model.MaquinaEntity maquina;

	@DaoFinder("select alias\nfrom es.caib.seycon.ng.model.AliasMaquinaEntity alias where alias.maquina.nom = :nomMaquina\norder by alias.alias")
	public java.util.List<es.caib.seycon.ng.model.AliasMaquinaEntity> findAliasByNomMaquina(
		java.lang.String nomMaquina) {
	 return null;
	}
	@DaoFinder("select aliasM.maquina\nfrom es.caib.seycon.ng.model.AliasMaquinaEntity aliasM where aliasM.alias like :alias")
	public java.util.List<es.caib.seycon.ng.model.AliasMaquinaEntity> findMaquinaByAlias(
		java.lang.String alias) {
	 return null;
	}
	@DaoFinder("select alias\nfrom es.caib.seycon.ng.model.AliasMaquinaEntity alias where alias.maquina.nom = :nomMaquina and alias.alias = :alias\norder by alias.alias")
	public java.util.List<es.caib.seycon.ng.model.AliasMaquinaEntity> findAliasByNomMaquinaAndAlias(
		java.lang.String nomMaquina, 
		java.lang.String alias) {
	 return null;
	}
}
