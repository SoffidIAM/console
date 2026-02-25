//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model.criteria;
/**
 * Stores the embedded values and asssociations of all entities in the system by type.  
 * Is used to determine the appropriate parameter name when an embedded value's property 
 * is referenced as the attribute to search by (as opposed to an association).
 * 
 * @author Chad Brandon
 */
@SuppressWarnings({"unchecked"})
public class CriteriaSearchProperties
{
	private static final java.util.Map embeddedValuesByType = new java.util.HashMap();
	private static final java.util.Map navigableAssociationEndsByType = new java.util.HashMap();
	
	static
	{
		initialize1();
		initialize2();
		initialize3();
		initialize4();
		initialize5();
		initialize6();
		initialize7();
		initialize8();
		initialize9();
		initialize10();
		initialize11();
		initialize12();
		initialize13();
		initialize14();
		initialize15();
		initialize16();
		initialize17();
		initialize18();
		initialize19();
		initialize20();
		initialize21();
		initialize22();
		initialize23();
		initialize24();
		initialize25();
		initialize26();
		initialize27();
		initialize28();
		initialize29();
		initialize30();
		initialize31();
		initialize32();
		initialize33();
		initialize34();
		initialize35();
		initialize36();
		initialize37();
		initialize38();
		initialize39();
		initialize40();
		initialize41();
		initialize42();
		initialize43();
		initialize44();
		initialize45();
		initialize46();
		initialize47();
		initialize48();
		initialize49();
		initialize50();
		initialize51();
		initialize52();
		initialize53();
		initialize54();
		initialize55();
		initialize56();
		initialize57();
		initialize58();
		initialize59();
		initialize60();
		initialize61();
		initialize62();
		initialize63();
		initialize64();
		initialize65();
		initialize66();
		initialize67();
		initialize68();
		initialize69();
		initialize70();
		initialize71();
		initialize72();
		initialize73();
		initialize74();
		initialize75();
		initialize76();
		initialize77();
		initialize78();
		initialize79();
		initialize80();
		initialize81();
		initialize82();
		initialize83();
		initialize84();
		initialize85();
		initialize86();
		initialize87();
		initialize88();
		initialize89();
		initialize90();
		initialize91();
		initialize92();
		initialize93();
		initialize94();
		initialize95();
		initialize96();
		initialize97();
		initialize98();
		initialize99();
		initialize100();
		initialize101();
		initialize102();
		initialize103();
		initialize104();
		initialize105();
		initialize106();
		initialize107();
		initialize108();
		initialize109();
		initialize110();
		initialize111();
		initialize112();
		initialize113();
		initialize114();
		initialize115();
		initialize116();
		initialize117();
		initialize118();
		initialize119();
		initialize120();
		initialize121();
		initialize122();
		initialize123();
		initialize124();
		initialize125();
		initialize126();
		initialize127();
		initialize128();
		initialize129();
		initialize130();
		initialize131();
		initialize132();
		initialize133();
		initialize134();
		initialize135();
		initialize136();
		initialize137();
		initialize138();
		initialize139();
		initialize140();
		initialize141();
		initialize142();
		initialize143();
		initialize144();
		initialize145();
		initialize146();
		initialize147();
		initialize148();
		initialize149();
	}
	
	private static final void initialize1()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.AccessLogEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.AccessLogEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("server", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("client", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("browser", com.soffid.iam.am.model.BrowserEntityImpl.class),
					new AssociationType("protocol", com.soffid.iam.am.model.ServiceEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize2()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.AccountPasswordEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.AccountPasswordEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize3()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.BrowserEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.BrowserEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("lastUser", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize4()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.ChallengeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.ChallengeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("clientHost", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize5()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointAccountEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointAccountEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("entryPoint", com.soffid.iam.am.model.EntryPointEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize6()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class),
					new AssociationType("informationSystem", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize7()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointExecutableEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointExecutableEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("entryPoint", com.soffid.iam.am.model.EntryPointEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize8()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointExecutionTypeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointExecutionTypeEntityImpl.class,
			null);
	}
	private static final void initialize9()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointGroupEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointGroupEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("entryPoint", com.soffid.iam.am.model.EntryPointEntityImpl.class),
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize10()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointIconEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointIconEntityImpl.class,
			null);
	}
	private static final void initialize11()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointRoleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointRoleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("entryPoint", com.soffid.iam.am.model.EntryPointEntityImpl.class),
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize12()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointTreeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointTreeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("parent", com.soffid.iam.am.model.EntryPointEntityImpl.class),
					new AssociationType("child", com.soffid.iam.am.model.EntryPointEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize13()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.EntryPointUserEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.EntryPointUserEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("entryPoint", com.soffid.iam.am.model.EntryPointEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize14()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.ForbiddenWordEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.ForbiddenWordEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize15()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.HostAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.HostAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize16()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.HostEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.HostEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("network", com.soffid.iam.am.model.NetworkEntityImpl.class),
					new AssociationType("operatingSystem", com.soffid.iam.am.model.OsTypeEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize17()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.HostEntryPointEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.HostEntryPointEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("entryPoint", com.soffid.iam.am.model.EntryPointEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize18()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.NetworkAuthorizationEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.NetworkAuthorizationEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("network", com.soffid.iam.am.model.NetworkEntityImpl.class),
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize19()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.NetworkEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.NetworkEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class),
					new AssociationType("discoveryServer", com.soffid.iam.sync.model.ServerEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize20()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.OsTypeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.OsTypeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize21()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.PasswordDomainEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.PasswordDomainEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize22()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.PasswordEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.PasswordEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("domain", com.soffid.iam.am.model.PasswordDomainEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize23()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.PasswordManagerTokenEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.PasswordManagerTokenEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize24()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.PasswordPolicyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.PasswordPolicyEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("userType", com.soffid.iam.base.model.UserTypeEntityImpl.class),
					new AssociationType("passwordDomain", com.soffid.iam.am.model.PasswordDomainEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize25()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.PolicyForbiddenWordEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.PolicyForbiddenWordEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("forbiddenWord", com.soffid.iam.am.model.ForbiddenWordEntityImpl.class),
					new AssociationType("passwordPolicy", com.soffid.iam.am.model.PasswordPolicyEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize26()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.SamlAssertionEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.SamlAssertionEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize27()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.SamlRequestEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.SamlRequestEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize28()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.SecretEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.SecretEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("server", com.soffid.iam.sync.model.ServerEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize29()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.ServiceEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.ServiceEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize30()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.SessionEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.SessionEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("clientHost", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("browser", com.soffid.iam.am.model.BrowserEntityImpl.class),
					new AssociationType("loginLogInfo", com.soffid.iam.am.model.AccessLogEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize31()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.VaultFolderAccessEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.VaultFolderAccessEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("vault", com.soffid.iam.am.model.VaultFolderEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize32()
	{
		embeddedValuesByType.put(
			com.soffid.iam.am.model.VaultFolderEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.am.model.VaultFolderEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("parent", com.soffid.iam.am.model.VaultFolderEntityImpl.class),
					new AssociationType("pamPolicy", com.soffid.iam.pam.model.PamPolicyEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize33()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.AccountAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.AccountAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("systemMetadata", com.soffid.iam.base.model.AccountMetadataEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize34()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.AccountEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.AccountEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class),
					new AssociationType("folder", com.soffid.iam.am.model.VaultFolderEntityImpl.class),
					new AssociationType("jumpServerGroup", com.soffid.iam.pam.model.JumpServerGroupEntityImpl.class),
					new AssociationType("passwordPolicy", com.soffid.iam.base.model.UserTypeEntityImpl.class),
					new AssociationType("snapshot", com.soffid.iam.iga.model.AccountSnapshotEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize35()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.AccountMetadataEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.AccountMetadataEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class),
					new AssociationType("dataObjectType", com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize36()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.AgentDescriptorEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.AgentDescriptorEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("plugin", com.soffid.iam.base.model.ServerPluginEntityImpl.class),
					new AssociationType("module", com.soffid.iam.base.model.ServerPluginModuleEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize37()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.AgentPropertyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.AgentPropertyEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("agent", com.soffid.iam.base.model.AgentDescriptorEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize38()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.AuthorizationEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.AuthorizationEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize39()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.BlobConfigurationEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.BlobConfigurationEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize40()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.ConfigEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.ConfigEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("network", com.soffid.iam.am.model.NetworkEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize41()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.DefaultAttributeMappingEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.DefaultAttributeMappingEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("defaultObjectMapping", com.soffid.iam.base.model.DefaultObjectMappingEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize42()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.DefaultObjectMappingEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.DefaultObjectMappingEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("agentDescriptor", com.soffid.iam.base.model.AgentDescriptorEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize43()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("object", com.soffid.iam.base.model.DefaultObjectMappingEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize44()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.LuceneIndexEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.LuceneIndexEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize45()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.LuceneIndexPartEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.LuceneIndexPartEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("index", com.soffid.iam.base.model.LuceneIndexEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize46()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.ServerPluginEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.ServerPluginEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize47()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.ServerPluginModuleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.ServerPluginModuleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("plugin", com.soffid.iam.base.model.ServerPluginEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize48()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.SoffidLicenseEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.SoffidLicenseEntityImpl.class,
			null);
	}
	private static final void initialize49()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.StatsEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.StatsEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize50()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.TenantDisabledPermissionEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.TenantDisabledPermissionEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("appliesTo", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize51()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.TenantEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.TenantEntityImpl.class,
			null);
	}
	private static final void initialize52()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.TenantServerEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.TenantServerEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("serverTenant", com.soffid.iam.base.model.TenantEntityImpl.class),
					new AssociationType("tenantServer", com.soffid.iam.sync.model.ServerEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize53()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.UserAccountEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.UserAccountEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize54()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.UserDataEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.UserDataEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("dataType", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize55()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.UserEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.UserEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("mailServer", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("homeServer", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("mailDomain", com.soffid.iam.iga.model.MailDomainEntityImpl.class),
					new AssociationType("profileServer", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("primaryGroup", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class),
					new AssociationType("userType", com.soffid.iam.base.model.UserTypeEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize56()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.UserPreferenceEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.UserPreferenceEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize57()
	{
		embeddedValuesByType.put(
			com.soffid.iam.base.model.UserTypeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.base.model.UserTypeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize58()
	{
		embeddedValuesByType.put(
			com.soffid.iam.doc.model.DocSignImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.doc.model.DocSignImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("bpmDocument", com.soffid.iam.doc.model.DocumentEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize59()
	{
		embeddedValuesByType.put(
			com.soffid.iam.doc.model.DocumentBlockEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.doc.model.DocumentBlockEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize60()
	{
		embeddedValuesByType.put(
			com.soffid.iam.doc.model.DocumentEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.doc.model.DocumentEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize61()
	{
		embeddedValuesByType.put(
			com.soffid.iam.doc.model.FileSystemImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.doc.model.FileSystemImpl.class,
			null);
	}
	private static final void initialize62()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.AccessControlEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.AccessControlEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("agent", com.soffid.iam.iga.model.SystemEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize63()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.AccountSnapshotEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.AccountSnapshotEntityImpl.class,
			null);
	}
	private static final void initialize64()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ApplicationDomainEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ApplicationDomainEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("informationSystem", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize65()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.AttributeMappingEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.AttributeMappingEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("object", com.soffid.iam.iga.model.ObjectMappingEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize66()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.AttributeTranslationEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.AttributeTranslationEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize67()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.AuthoritativeChangeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.AuthoritativeChangeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("dispatcher", com.soffid.iam.iga.model.SystemEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize68()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.CustomObjectAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.CustomObjectAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("customObject", com.soffid.iam.iga.model.CustomObjectEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize69()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.CustomObjectEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.CustomObjectEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("type", com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize70()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.CustomObjectRoleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.CustomObjectRoleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("customObjectType", com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class),
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize71()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize72()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.DomainValueEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.DomainValueEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("domain", com.soffid.iam.iga.model.ApplicationDomainEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize73()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ExternalNameEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ExternalNameEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("mailList", com.soffid.iam.iga.model.MailListEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize74()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.GroupAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.GroupAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize75()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.GroupEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.GroupEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("parent", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("driveServer", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("unitType", com.soffid.iam.iga.model.GroupTypeEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize76()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.GroupTypeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.GroupTypeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize77()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.HostAliasEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.HostAliasEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize78()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.InformationSystemAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.InformationSystemAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("informationSystem", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize79()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.InformationSystemEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.InformationSystemEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("parent", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("contactPerson", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize80()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.MailDomainEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.MailDomainEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize81()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.MailListAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.MailListAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("mailList", com.soffid.iam.iga.model.MailListEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize82()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.MailListContainerEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.MailListContainerEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("contains", com.soffid.iam.iga.model.MailListEntityImpl.class),
					new AssociationType("pertains", com.soffid.iam.iga.model.MailListEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize83()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.MailListEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.MailListEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("domain", com.soffid.iam.iga.model.MailDomainEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize84()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.MailListGroupMemberEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.MailListGroupMemberEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("mailList", com.soffid.iam.iga.model.MailListEntityImpl.class),
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize85()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.MailListRoleMemberEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.MailListRoleMemberEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("mailList", com.soffid.iam.iga.model.MailListEntityImpl.class),
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("informationSystemScope", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("groupScope", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("domainValueScope", com.soffid.iam.iga.model.DomainValueEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize86()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.MetaDataEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.MetaDataEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("objectType", com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class),
					new AssociationType("dataObjectType", com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize87()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.NoticeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.NoticeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("application", com.soffid.iam.iga.model.InformationSystemEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize88()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ObjectMappingEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ObjectMappingEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("soffidCustomObject", com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class),
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize89()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ObjectMappingPropertyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ObjectMappingPropertyEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("object", com.soffid.iam.iga.model.ObjectMappingEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize90()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ObjectMappingTriggerEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ObjectMappingTriggerEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("object", com.soffid.iam.iga.model.ObjectMappingEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize91()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.PrinterEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.PrinterEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("server", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize92()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.PrinterGroupEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.PrinterGroupEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("printer", com.soffid.iam.iga.model.PrinterEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize93()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ProcessHierarchyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ProcessHierarchyEntityImpl.class,
			null);
	}
	private static final void initialize94()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ReconcileAccountAttributesEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ReconcileAccountAttributesEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("account", com.soffid.iam.iga.model.ReconcileAccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize95()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ReconcileAccountEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ReconcileAccountEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize96()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ReconcileAssignmentEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ReconcileAssignmentEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize97()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ReconcileRoleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ReconcileRoleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize98()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.ReconcileTriggerEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.ReconcileTriggerEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize99()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RoleAccountAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RoleAccountAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("grant", com.soffid.iam.iga.model.RoleAccountEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize100()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RoleAccountEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RoleAccountEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("domainValue", com.soffid.iam.iga.model.DomainValueEntityImpl.class),
					new AssociationType("informationSystem", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("rule", com.soffid.iam.iga.model.RuleEntityImpl.class),
					new AssociationType("holderGroup", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("parent", com.soffid.iam.iga.model.RoleAccountEntityImpl.class),
					new AssociationType("ownerAccount", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("delegateAccount", com.soffid.iam.base.model.AccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize101()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RoleAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RoleAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize102()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RoleDependencyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RoleDependencyEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("contained", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("container", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("granteeApplicationDomain", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("granteeGroupDomain", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("granteeDomainValue", com.soffid.iam.iga.model.DomainValueEntityImpl.class),
					new AssociationType("domainApplication", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("domainGroup", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("domainApplicationValue", com.soffid.iam.iga.model.DomainValueEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize103()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RoleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RoleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("informationSystem", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class),
					new AssociationType("applicationDomain", com.soffid.iam.iga.model.ApplicationDomainEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize104()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RoleGroupEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RoleGroupEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("grantedRole", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("grantedApplicationDomain", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("grantedGroupDomain", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("grantedDomainValue", com.soffid.iam.iga.model.DomainValueEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize105()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RuleAssignedRoleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RuleAssignedRoleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("rule", com.soffid.iam.iga.model.RuleEntityImpl.class),
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize106()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.RuleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.RuleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize107()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.SystemEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.SystemEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class),
					new AssociationType("passwordDomain", com.soffid.iam.am.model.PasswordDomainEntityImpl.class),
					new AssociationType("userDomain", com.soffid.iam.iga.model.UserDomainEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize108()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.SystemGroupEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.SystemGroupEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize109()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.TranslatedLabelEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.TranslatedLabelEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("customObjectType", com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class),
					new AssociationType("accountMetadata", com.soffid.iam.base.model.AccountMetadataEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize110()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.UserDomainEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.UserDomainEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize111()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.UserGroupAttributeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.UserGroupAttributeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("userGroup", com.soffid.iam.iga.model.UserGroupEntityImpl.class),
					new AssociationType("metadata", com.soffid.iam.iga.model.MetaDataEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize112()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.UserGroupEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.UserGroupEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize113()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.UserMailEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.UserMailEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("mailList", com.soffid.iam.iga.model.MailListEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize114()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.UserPrinterEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.UserPrinterEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("printer", com.soffid.iam.iga.model.PrinterEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize115()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.UserProcessEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.UserProcessEntityImpl.class,
			null);
	}
	private static final void initialize116()
	{
		embeddedValuesByType.put(
			com.soffid.iam.iga.model.UserTypeSystemEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.iga.model.UserTypeSystemEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class),
					new AssociationType("userType", com.soffid.iam.base.model.UserTypeEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize117()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.AccountAccessEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.AccountAccessEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize118()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.HostAdminEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.HostAdminEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class),
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize119()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.HostPortEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.HostPortEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize120()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.HostServiceEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.HostServiceEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize121()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.HostSystemEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.HostSystemEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class),
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize122()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.JumpServerEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.JumpServerEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("jumpServerGroup", com.soffid.iam.pam.model.JumpServerGroupEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize123()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.JumpServerGroupEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.JumpServerGroupEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize124()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.NetworkDiscoverRangeEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.NetworkDiscoverRangeEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("network", com.soffid.iam.am.model.NetworkEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize125()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("network", com.soffid.iam.am.model.NetworkEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize126()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.PamActionEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.PamActionEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("policy", com.soffid.iam.pam.model.PamPolicyEntityImpl.class),
					new AssociationType("rule", com.soffid.iam.pam.model.PamRuleEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize127()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.PamPolicyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.PamPolicyEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize128()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.PamPolicyJITPermissionEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.PamPolicyJITPermissionEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("policy", com.soffid.iam.pam.model.PamPolicyEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize129()
	{
		embeddedValuesByType.put(
			com.soffid.iam.pam.model.PamRuleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.pam.model.PamRuleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize130()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.AuditEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.AuditEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("group", com.soffid.iam.iga.model.GroupEntityImpl.class),
					new AssociationType("accountAssoc", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize131()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.GeoInformationEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.GeoInformationEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize132()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.IssueBrowserEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.IssueBrowserEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("issue", com.soffid.iam.rc.model.IssueEntityImpl.class),
					new AssociationType("browser", com.soffid.iam.am.model.BrowserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize133()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.IssueEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.IssueEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class),
					new AssociationType("roleAccount", com.soffid.iam.iga.model.RoleAccountEntityImpl.class),
					new AssociationType("rule", com.soffid.iam.pam.model.PamRuleEntityImpl.class),
					new AssociationType("account", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("requester", com.soffid.iam.base.model.AccountEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize134()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.IssueHostEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.IssueHostEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("issue", com.soffid.iam.rc.model.IssueEntityImpl.class),
					new AssociationType("host", com.soffid.iam.am.model.HostEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize135()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.IssuePolicyActionEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.IssuePolicyActionEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("issuePolicy", com.soffid.iam.rc.model.IssuePolicyEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize136()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.IssuePolicyEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.IssuePolicyEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize137()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.IssueUserEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.IssueUserEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("issue", com.soffid.iam.rc.model.IssueEntityImpl.class),
					new AssociationType("user", com.soffid.iam.base.model.UserEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize138()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.SoDRoleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.SoDRoleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("role", com.soffid.iam.iga.model.RoleEntityImpl.class),
					new AssociationType("rule", com.soffid.iam.rc.model.SoDRuleEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize139()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.SoDRuleEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.SoDRuleEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("application", com.soffid.iam.iga.model.InformationSystemEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize140()
	{
		embeddedValuesByType.put(
			com.soffid.iam.rc.model.SoDRuleMatrixEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.rc.model.SoDRuleMatrixEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("rule", com.soffid.iam.rc.model.SoDRuleEntityImpl.class),
					new AssociationType("row", com.soffid.iam.rc.model.SoDRoleEntityImpl.class),
					new AssociationType("column", com.soffid.iam.rc.model.SoDRoleEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize141()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.ScheduledTaskEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.ScheduledTaskEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("handler", com.soffid.iam.sync.model.ScheduledTaskHandlerEntityImpl.class),
					new AssociationType("server", com.soffid.iam.sync.model.ServerEntityImpl.class),
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize142()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.ScheduledTaskHandlerEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.ScheduledTaskHandlerEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize143()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.ScheduledTaskLogEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.ScheduledTaskLogEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("task", com.soffid.iam.sync.model.ScheduledTaskEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize144()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.ServerCertificateEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.ServerCertificateEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("server", com.soffid.iam.sync.model.ServerEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize145()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.ServerEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.ServerEntityImpl.class,
			null);
	}
	private static final void initialize146()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.ServerInstanceEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.ServerInstanceEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("server", com.soffid.iam.sync.model.ServerEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize147()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.ServerRegistrationTokenEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.ServerRegistrationTokenEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize148()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.TaskEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.TaskEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("tenant", com.soffid.iam.base.model.TenantEntityImpl.class)
				}
			)
		);
	}
	private static final void initialize149()
	{
		embeddedValuesByType.put(
			com.soffid.iam.sync.model.TaskLogEntityImpl.class,
			null);
		navigableAssociationEndsByType.put(
			com.soffid.iam.sync.model.TaskLogEntityImpl.class,
			java.util.Arrays.asList(
				new AssociationType[] 
				{
					new AssociationType("task", com.soffid.iam.sync.model.TaskEntityImpl.class),
					new AssociationType("system", com.soffid.iam.iga.model.SystemEntityImpl.class)
				}
			)
		);
	}
	
	/**
	 * Attempts to get the embedded value list for the given type (or returns null
	 * if one doesn't exist).
	 * 
	 * @param type the type of which to retrieve the value.
	 * @return the collection of embedded value names.
	 */
	public static java.util.Collection getEmbeddedValues(final Class type)
	{
		return (java.util.Collection)embeddedValuesByType.get(type);
	}
	
	/**
	 * Gets the type of the navigable association end given the <code>ownerType</code>
	 * and <code>name</code>
	 *
	 * @param ownerType the owner of the association.
	 * @param name the name of the association end to find.
	 * @return the type of the association end.
	 */
	public static Class getNavigableAssociationEndType(final Class ownerType, final String name)
	{
		final java.util.Collection ends = (java.util.Collection)navigableAssociationEndsByType.get(ownerType);
		final AssociationType type = (AssociationType)org.apache.commons.collections.CollectionUtils.find(
			ends,
			new org.apache.commons.collections.Predicate()
			{
				public boolean evaluate(final Object object)
				{
					return ((AssociationType)object).name.equals(name);
				}
			});
		return type != null ? type.type : null;
	}

	/**
	 * A private class storing the association name and type.
	 */
	protected static final class AssociationType
	{
		protected AssociationType(final String name, final Class type)
		{
			this.name = name;
			this.type = type;
		}
		protected String name;
		protected Class type;
	}
}
