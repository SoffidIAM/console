//
// (C) 2013 Soffid
//
//

package com.soffid.iam.remote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;


/**
 * Locates and provides all available application services.
 */
public interface RemoteServiceLocatorProxy
{

	Object getService(String name);
}
