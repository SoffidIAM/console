//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.ExecucioPuntEntradaEntity;
import es.caib.seycon.ng.model.PuntEntradaEntity;

@ValueObject ( translatedName="AccessTree",
	 translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass = PuntEntradaEntity.class)
public class PuntEntrada {

	@Attribute(hidden=true)
	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Attribute(translated = "name" )
	public java.lang.String nom;

	public boolean visible;

	public boolean menu;

	@Nullable
	@Attribute(translated = "columnsNumber" )
	public Long numColumnes;

	@Attribute(translated = "publicAccess" )
	public boolean esPublic;

	@Nullable
	@Attribute(translated = "menuType" )
	public java.lang.String tipusMenu;

	@Nullable
	@Attribute(translated = "authorizations", hidden=true )
	public java.util.Collection<AutoritzacioPuntEntrada> autoritzacions;

	@Nullable
	@Attribute(translated = "executions", hidden=true )
	public java.util.Collection<ExecucioPuntEntrada> execucions;

	@Nullable
	@Attribute(translated = "icon1Image", hidden=true )
	public byte[] imgIcona1;

	@Nullable
	@Attribute(translated = "icon2Image", hidden=true )
	public byte[] imgIcona2;

	@Nullable
	@JsonAttribute(hibernateAttribute = "informationSystem.name")
	public String informationSystem;

	@Nullable
	@Attribute(translated = "parentId",hidden=true )
	public java.lang.Long idPare;

	@Nullable
	@Attribute(translated = "order" )
	public java.lang.String ordre;

	@Nullable
	@Attribute(translated = "icon1Id", hidden=true )
	public java.lang.Long idIcona1;

	@Nullable
	@Attribute(translated = "icon2Id", hidden=true )
	public java.lang.Long idIcona2;

	@Nullable
	@Attribute(translated = "xmlAccessTree", hidden=true )
	public java.lang.String xmlPUE;

	@Nullable
	@JsonAttribute(hibernateAttribute = "system.name")
	public java.lang.String system;

}
