CodeMirrorJavaTypes['userObject'] = {
	'{"accountId"}' : 'java.lang.Long',
	'{"accountName"}' : 'java.lang.String',
	'{"system"}' : 'java.lang.String',
	'{"accountDescription"}' : 'java.lang.String',
	'{"accountDisabled"}' : 'java.lang.String',
	'{"active"}' : 'java.lang.Boolean',
	'{"mailAlias"}' : 'java.lang.String',
	'{"userName"}' : 'java.lang.String',
	'{"primaryGroup"}' : 'java.lang.String',
	'{"comments"}' : 'java.lang.String',
	'{"createdOn"}' : 'java.util.Calendar',
	'{"modifiedOn"}' : 'java.util.Calendar',
	'{"mailDomain"}' : 'java.lang.String',
	'{"fullName"}' : 'java.lang.String',
	'{"id"}' : 'java.lang.Long',
	'{"multiSession"}' : 'java.lang.Boolean',
	'{"firstName"}' : 'java.lang.String',
	'{"shortName"}' : 'java.lang.String',
	'{"lastName"}' : 'java.lang.String',
	'{"lastName2"}' : 'java.lang.String',
	'{"mailServer"}' : 'java.lang.String',
	'{"homeServer"}' : 'java.lang.String',
	'{"profileServer"}' : 'java.lang.String',
	'{"phone"}' : 'java.lang.String',
	'{"userType"}' : 'java.lang.String',
	'{"createdBy"}' : 'java.lang.String',
	'{"modifiedBy"}' : 'java.lang.String',
	'{"primaryGroupObject"}' : 'groupObject',
	'{"secondaryGroups"}' : 'list<groupObject>',
	'{"accountAttributes"}' : 'accountAttributes',
	'{"userAttributes"}' : 'userAttributes',
	'{"attributes"}' : 'userAttributes', 
	'{"grantedRoles"}' : 'list<grantObject>',
	'{"allGrantedRoles"}' : 'list<grantObject>',
	'{"granted"}' : 'list<grantObject>',
	'{"allGranted"}' : 'list<grantObject>'
};
CodeMirrorJavaTypes['list<userObject>'] = {
	'size' : 'int',
	'get' : 'userObject'
}

CodeMirrorJavaTypes['groupObject'] = {
	'{"groupId"}' : 'java.lang.Long',
	'{"name"}' : 'java.lang.String',
	'{"system"}' : 'java.lang.String',
	'{"description"}' : 'java.lang.String',
	'{"parent"}' : 'java.lang.String',
	'{"disabled"}' : 'java.lang.Boolean',
	'{"server"}' : 'java.lang.String',
	'{"accountingGroup"}' : 'java.lang.String',
	'{"type"}' : 'java.lang.String',
	'{"driveLetter"}' : 'java.lang.String',
	'{"users"}' : 'list<userObject>',
	'{"userNames"}' : 'java.util.List',
	'{"allUsers"}' : 'list<userObject>',
	'{"allUserNames"}' : 'java.util.List',
	'{"grantedRoles"}' : 'list<grantObject>',
	'{"grantedRoleNames"}' : 'java.util.List',
	'{"attributes"}': 'groupAttributes'
};
CodeMirrorJavaTypes['list<groupObject>'] = {
	'size' : 'int',
	'get' : 'groupObject'
};

CodeMirrorJavaTypes['grantObject'] = {
	'{"id"}' : 'java.lang.Long',
	'{"grantedRole"}' : 'java.lang.String',
	'{"grantedRoleSystem"}' : 'java.lang.String',
	'{"grantedRoleId"}' : 'java.lang.Long',
	'{"domainValue"}' : 'java.lang.String',
	'{"ownerAccount"}' : 'java.lang.String',
	'{"ownerSystem"}' : 'java.lang.String',
	'{"ownerGroup"}' : 'java.lang.String',
	'{"ownerRoleId"}' : 'java.lang.Long',
	'{"ownerRoleName"}' : 'java.lang.String',
	'{"ownerUserObject"}' : 'userObject',
	'{"ownerUser"}' : 'java.lang.String',
	'{"ownerAccountObject"}' : 'accountObject',
	'{"grantedRoleObject"}' : 'roleObject'
};
CodeMirrorJavaTypes['list<grantObject>'] = {
	'size' : 'int',
	'get' : 'grantObject'
};
CodeMirrorJavaTypes['roleObject'] = {
		'{"id"}' : 'java.lang.Long',
		'{"system"}' : 'java.lang.String',
		'{"name"}' : 'java.lang.String',
		'{"category"}' : 'java.lang.Long',
		'{"application"}' : 'java.lang.String',
		'{"passwordProtected"}' : 'java.lang.Boolean',
		'{"description"}' : 'java.lang.String',
		'{"wfmanaged"}' : 'java.lang.Boolean',
		'{"ownedRoles"}' : 'list<grantObject>',
		'{"ownerRoles"}' : 'list<grantObject>',
		'{"ownerGroups"}' : 'list<groupObject>',
		'{"domain"}' : 'java.lang.String',
		'{"grantedAccountNames"}' : 'java.util.List',
		'{"allGrantedAccountNames"}' : 'java.util.List',
		'{"grantedAccounts"}' : 'list<grantObject>',
		'{"allGrantedAccounts"}' : 'list<grantObject>',
		'{"attributes"}' : 'roleAttributes'
};
CodeMirrorJavaTypes['list<roleObject>'] = {
	'size' : 'int',
	'get' : 'roleObject'
};

CodeMirrorJavaTypes['accountObject'] = {
		'{"accountId"}' : 'java.lang.Long',
		'{"accountName"}' : 'java.lang.String',
		'{"passwordPolicy"}' : 'java.lang.String',
		'{"accountDescription"}' : 'java.lang.String',
		'{"accountDisabled"}' : 'java.lang.String',
		'{"active"}' : 'java.lang.Boolean',
		'{"type"}' : 'java.lang.String',
		'{"lastUpdate"}' : 'java.util.Calendar',
		'{"lastPasswordUpdate"}' : 'java.util.Calendar',
		'{"passwordExpiration"}' : 'java.util.Calendar',
		'{"attributes"}' : 'accountAttributes',
		'{"grantedRoles"}' : 'list<grantObject>',
		'{"allGrantedRoles"}' : 'list<grantObject>',
		'{"granted"}' : 'list<grantObject>',
		'{"allGranted"}' : 'list<grantObject>'
};
CodeMirrorJavaTypes['list<accountObject>'] = {
	'size' : 'int',
	'get' : 'accountObject'
};
CodeMirrorJavaTypes['es.caib.seycon.ng.sync.engine.extobj.BSHAgentbject'] = {
		'search':'java.util.Map',
		'soffidToSystem':'java.util.Map',
		'systemToSoffid':'java.util.Map'
};