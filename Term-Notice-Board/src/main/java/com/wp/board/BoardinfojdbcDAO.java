package com.wp.board;

import java.sql.DriverManager;
import java.sql.SQLException;

public class BoardinfojdbcDAO extends BoardinfoDAOImpl {

	public BoardinfojdbcDAO(DBConnectionInfo dbInfo) {
		super(dbInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void connect() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// load jdbc driver class
		Class.forName(dbInfo.getJdbcDriverName());
				
		// connect to DB server
		conn = DriverManager.getConnection(
				dbInfo.getUrl(), dbInfo.getUserId(), dbInfo.getPassword());
	}

}
