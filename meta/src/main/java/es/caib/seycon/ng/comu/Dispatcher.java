//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="System",
	 translatedPackage="com.soffid.iam.api")
public class Dispatcher {

	@Attribute(translated = "name" )
	public java.lang.String codi;

	@Nullable
	@Attribute(translated = "className" )
	public java.lang.String nomCla;

	@Nullable
	public java.lang.String url;

	@Nullable
	public java.lang.String param0;

	@Nullable
	public java.lang.String param1;

	@Nullable
	public java.lang.String param2;

	@Nullable
	public java.lang.String param3;

	@Nullable
	public java.lang.String param4;

	@Nullable
	public java.lang.String param5;

	@Nullable
	public java.lang.String param6;

	@Nullable
	public java.lang.String param7;

	@Nullable
	public java.lang.String param8;

	@Nullable
	public java.lang.String param9;

	@Nullable
	@Attribute(translated = "rolebased" )
	public java.lang.Boolean basRol;

	@Nullable
	@Attribute(translated = "trusted" )
	public java.lang.Boolean segur;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "userTypes" )
	public java.lang.String relacioLaboral;

	@Nullable
	@Attribute(translated = "groups" )
	public java.lang.String grups;

	@Nullable
	@Attribute(translated = "accessControl" )
	public java.lang.Boolean controlAccess;

	@Nullable
	@Attribute(translated = "passwordsDomainId" )
	public java.lang.Long idDominiContrasenyes;

	@Nullable
	@Attribute(translated = "passwordsDomain" )
	public String dominiContrasenyes;

	@Nullable
	@Attribute(translated = "usersDomain" )
	public java.lang.String dominiUsuaris;

	@Attribute(defaultValue = "false")
	public boolean readOnly;

	@Attribute(defaultValue = "false")
	public boolean fullReconciliation;

	@Nullable
	public java.lang.Long databaseReplicaId;

	@Nullable
	@Attribute(defaultValue = "false")
	public boolean authoritative;

	@Nullable
	public byte[] blobParam;

	@Nullable
	public java.util.Calendar timeStamp;

	@Nullable
	public java.lang.String description;

	@Nullable
	public String authoritativeProcess;

	@Nullable
	public Boolean manualAccountCreation;
	
	@Nullable
	@Description ("false to use a dedicated server thread. true to use a shared server thread")
	public Boolean sharedDispatcher;

	@Description("Time out for normal operations (milliseconds)")
	@Nullable
	public Long timeout;
	
	@Description("Time out for long operations (milliseconds)")
	@Nullable
	public Long longTimeout;	
	
	@Nullable
	@Description("Owner tenant")
	public String tenant;
}
