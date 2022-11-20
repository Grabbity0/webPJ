package com.wp.board;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UsersinfoDbcpDAO extends UsersinfoDAOImpl {

	public UsersinfoDbcpDAO(DBConnectionInfo dbInfo) {
		super(dbInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void connect() throws ClassNotFoundException, SQLException {
		try {
			Context initContext =  new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/oracle_xe");
			
			conn = ds.getConnection();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
