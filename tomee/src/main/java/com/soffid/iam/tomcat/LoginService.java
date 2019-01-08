package com.soffid.iam.tomcat;

import java.security.Principal;

public interface LoginService {

	public Principal authenticate(String username, String credentials) ;

}
