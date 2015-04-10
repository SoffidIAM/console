//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPDIS",
	translatedName="UserTypeSystemEntity",
	translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.comu.TipusUsuariDispatcher.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
public abstract class TipusUsuariDispatcherEntity {

	@Column (name="TPD_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TPD_IDDIS", translated="system")
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

	@Column (name="TPD_TUS_ID", translated="userType")
	public es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuari;

	@Operation(translated="findBySystem")
	@DaoFinder("select tu from com.soffid.iam.model.UserTypeSystemEntity tu where tu.system.name=:systemName")
	public java.util.Collection<es.caib.seycon.ng.model.TipusUsuariDispatcherEntity> findByCodiAgent(
		java.lang.String systemName) {
	 return null;
	}
}
