//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPDIS" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.comu.TipusUsuariDispatcher.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
public abstract class TipusUsuariDispatcherEntity {

	@Column (name="TPD_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TPD_IDDIS")
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

	@Column (name="TPD_TUS_ID")
	public es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuari;

	@DaoFinder("select tu from es.caib.seycon.ng.model.TipusUsuariDispatcherEntity tu where tu.dispatcher.codi=:codiAgent")
	public java.util.Collection<es.caib.seycon.ng.model.TipusUsuariDispatcherEntity> findByCodiAgent(
		java.lang.String codiAgent) {
	 return null;
	}
}
