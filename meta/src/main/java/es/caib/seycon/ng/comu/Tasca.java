//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Task",
	 translatedPackage="com.soffid.iam.api")
public class Tasca {

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "user" )
	public java.lang.String usuari;

	@Nullable
	@Attribute(translated = "password" )
	public java.lang.String contra;

	@Nullable
	@Attribute(translated = "passwordChange" )
	public java.lang.String cancon;

	@Nullable
	@Attribute(translated = "folder" )
	public java.lang.String carpet;

	@Nullable
	@Attribute(translated = "folderType" )
	public java.lang.String tipcar;

	@Nullable
	@Attribute(translated = "printer" )
	public java.lang.String impres;

	@Nullable
	@Attribute(translated = "host" )
	public java.lang.String maquin;

	@Nullable
	@Attribute(translated = "subnet" )
	public java.lang.String subxar;

	@Nullable
	@Attribute(translated = "message" )
	public java.lang.String missat;

	@Nullable
	public java.lang.String status;

	@Attribute(translated = "taskDate" )
	public java.util.Calendar dataTasca;

	@Attribute(translated = "transaction" )
	public java.lang.String transa;

	@Nullable
	@Attribute(translated = "group" )
	public java.lang.String grup;

	@Nullable
	@Attribute(translated = "alias" )
	public java.lang.String alies;

	@Nullable
	@Attribute(translated = "mailDomain" )
	public java.lang.String domcor;

	@Nullable
	public java.lang.String role;

	@Nullable
	@Attribute(translated = "database" )
	public java.lang.String bd;

	@Nullable
	@Attribute(translated = "systemName" )
	public java.lang.String coddis;

	@Nullable
	public java.lang.String server;

	@Nullable
	public java.lang.String serverInstance;

	@Nullable
	@Attribute(translated = "userDomain" )
	public java.lang.String dominiUsuaris;

	@Nullable
	@Attribute(translated = "passwordDomain" )
	public java.lang.String dominiContrasenyes;

	@Nullable
	public java.lang.String hash;

	@Nullable
	public java.util.Calendar expirationDate;

	@Nullable
	public java.lang.String entity;

	@Nullable
	public java.lang.Long primaryKeyValue;

	@Nullable
	public java.lang.String customObjectType;

	@Nullable
	public String customObjectName;

	
	@Nullable
	public java.lang.String customTaskName;
}
