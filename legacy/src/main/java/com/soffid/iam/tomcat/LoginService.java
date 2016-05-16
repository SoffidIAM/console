package com.soffid.iam.tomcat;

public interface LoginService {

	public SoffidPrincipal authenticate(String username, String credentials) ;

}
