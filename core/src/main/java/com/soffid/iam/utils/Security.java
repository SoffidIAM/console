package com.soffid.iam.utils;

/**
 * @author u88683
 * 
 */
public class Security {
	public static boolean isSyncServer() {
		return true;
	}

	public static boolean isSyncProxy() {
		return true;
	}

    
  public static boolean isUserInRole(String role) {
      return true;
  }


    public static void onSyncServer() {
    }

    public static void onSyncProxy() {
    }

    public static long getCurrentTenantId ()
    {
      return 0;
    }
    
    public static String getCurrentTenantName () 
    {
      return null;
    }
    
    public static boolean isAuthorizedTenant
      (com.soffid.iam.base.model.TenantEntity tenant)
    {
      return true;
    }

    public static String getCurrentAccount () 
    {
      return null;
    }

    public static com.soffid.iam.common.security.SoffidPrincipal getSoffidPrincipal() {
      return null;
    }
}
