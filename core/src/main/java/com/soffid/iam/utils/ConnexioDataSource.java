package com.soffid.iam.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnexioDataSource {
	
	
	public ConnexioDataSource() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection(String dataSource) throws Exception {
		DataSource ds = null;
		Connection con = null;
		InitialContext ic;
		
		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/"+dataSource); //$NON-NLS-1$
			con = ds.getConnection();
		} catch (Exception e) {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			throw e;

		}
		return con;		
		
	}
	
}
