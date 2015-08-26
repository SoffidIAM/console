//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="AccessTree",
	 translatedPackage="com.soffid.iam.api")
public class PuntEntrada {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "code" )
	public java.lang.String codi;

	@Attribute(translated = "name" )
	public java.lang.String nom;

	public java.lang.String visible;

	public java.lang.String menu;

	@Nullable
	@Attribute(translated = "columnsNumber" )
	public java.lang.String numColumnes;

	@Attribute(translated = "isPublic" )
	public java.lang.String esPublic;

	@Nullable
	@Attribute(translated = "typeMenu" )
	public java.lang.String tipusMenu;

	@Nullable
	@Attribute(translated = "authorizations" )
	public java.util.Collection autoritzacions;

	@Nullable
	@Attribute(translated = "executions" )
	public java.util.Collection execucions;

	@Nullable
	@Attribute(translated = "icon1Image" )
	public byte[] imgIcona1;

	@Nullable
	@Attribute(translated = "icon2Image" )
	public byte[] imgIcona2;

	@Attribute(translated = "applicationId" )
	public java.lang.Long idAplicacio;

	@Nullable
	@Attribute(translated = "parentId" )
	public java.lang.Long idPare;

	@Nullable
	@Attribute(translated = "order" )
	public java.lang.String ordre;

	@Nullable
	@Attribute(translated = "icon1Id" )
	public java.lang.Long idIcona1;

	@Nullable
	@Attribute(translated = "icon2Id" )
	public java.lang.Long idIcona2;

	@Nullable
	@Attribute(translated = "xmlAccessTree" )
	public java.lang.String xmlPUE;

	@Nullable
	@Attribute(translated = "treeRoute" )
	public java.lang.String rutaArbre;

}
