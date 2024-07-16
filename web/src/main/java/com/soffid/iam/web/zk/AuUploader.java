/* AuUploader.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Jan 11 18:53:30     2008, Created by tomyeh
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.soffid.iam.web.zk;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.au.http.AuProcessor;
import org.zkoss.zk.ui.Session;

/**
 * The utility used to process file upload.
 * 
 * @author tomyeh
 * @since 3.0.2
 */
public class AuUploader implements AuProcessor {
	public void process(Session sess, ServletContext ctx,
	HttpServletRequest request, HttpServletResponse response, String pathInfo)
	throws ServletException, IOException {
	}
}
