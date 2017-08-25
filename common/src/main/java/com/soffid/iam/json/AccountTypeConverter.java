package com.soffid.iam.json;

import java.lang.reflect.Type;

import org.apache.johnzon.mapper.Converter;

import es.caib.seycon.ng.comu.AccountType;

public class AccountTypeConverter implements Converter<AccountType>, Converter.TypeAccess
{
	@Override
	public String toString(AccountType type) {
		return type.getValue();
	}

	@Override
	public AccountType fromString(String type) {
		return AccountType.fromString(type);
	}

	@Override
	public Type type() {
		return AccountType.class;
	}
}
