//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_GRUPUE" , translatedName="EntryPointGroupEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.AutoritzacioPuntEntrada.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.PuntEntradaEntity.class})
public abstract class AutoritzacioPUEGrupEntity {

	@Column (name="GPE_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="GPE_NIVAUT", length=1, translated="auhtorizationLevel")
	public java.lang.String nivellAutoritzacio;

	@Column (name="GPE_IDPUE", translated="entryPoint")
	public es.caib.seycon.ng.model.PuntEntradaEntity puntEntrada;

	@Column (name="GPE_IDGRU", translated="groupId")
	public java.lang.Long idGrup;

}
