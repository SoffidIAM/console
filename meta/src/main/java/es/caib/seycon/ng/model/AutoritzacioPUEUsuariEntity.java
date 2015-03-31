//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUPUE", translatedName="EntryPointUserEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.AutoritzacioPuntEntrada.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.PuntEntradaEntity.class})
public abstract class AutoritzacioPUEUsuariEntity {

	@Column (name="UPE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="UPE_NIVAUT", length=1, translated="authorizationLevel")
	public java.lang.String nivellAutoritzacio;

	@Column (name="UPE_IDPUE", translated="entryPoint")
	public es.caib.seycon.ng.model.PuntEntradaEntity puntEntrada;

	@Column (name="UPE_IDUSU", translated="userID")
	public java.lang.Long idUsuari;

	@DaoFinder("from es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity")
	public java.util.List<es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity> findAll() {
	 return null;
	}
}
