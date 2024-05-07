package com.soffid.iam.service.impl;

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RuleEntity;
import com.soffid.iam.model.UserEntity;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

public interface RuleEvaluatorGrantRevokeMethod
{
	void grant(RuleEntity rule, UserEntity user, RoleEntity role, String domainValue, AccountEntity account)
			throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException ;
	void revoke(UserEntity user, RoleAccountEntity role) throws InternalErrorException;
	void toEffectiveRole(UserEntity user, RoleAccountEntity role, RuleEntity rule) throws InternalErrorException;
}