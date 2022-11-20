package com.wp.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class UsersinfoDAOImpl implements UsersinfoDAO {
	protected DBConnectionInfo dbInfo;
	
	protected Connection conn = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	
	public UsersinfoDAOImpl(DBConnectionInfo dbInfo) {
		super();
		this.dbInfo = dbInfo;
	}
	
	protected abstract void connect() throws ClassNotFoundException, SQLException;
/*
	protected void connect() throws ClassNotFoundException, SQLException {
		// load jdbc driver class
		Class.forName(dbInfo.getJdbcDriverName());
		
		// connect to DB server
		conn = DriverManager.getConnection(
				dbInfo.getUrl(), dbInfo.getUserId(), dbInfo.getPassword());
	}
	*/
	protected void disconnect() throws SQLException{
		// disconnect from DB server
		if(rs != null){
			rs.close();
		}
		if(stmt != null){
			stmt.close();
		}
		if(conn != null){
			conn.close();	
		}
	}	
	
	@Override
	public List<UsersDO> selectAllUsers() {
		List<UsersDO> usersList = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = "select * from user_table order by user_id";
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					usersList = new ArrayList<UsersDO>();
					
					while(rs.next()) {
						UsersDO users = new UsersDO();
						users.setUser_id(rs.getString("user_id"));
						users.setUser_pw(rs.getString("user_pw"));
						users.setNickname(rs.getString("nickname"));
						users.setEmail(rs.getString("email"));
						users.setAdmin(rs.getBoolean("admin"));
						
						usersList.add(users);
					}
				}
			}	
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return usersList;
		
	}
	
	@Override
	public UsersDO selectUser(String user_id) {
		UsersDO users = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("select * from user_table where user_id = '%s'", user_id);
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					users = new UsersDO();
					
					rs.next();
						users.setUser_id(rs.getString("user_id"));
						users.setUser_pw(rs.getString("user_pw"));
						users.setEmail(rs.getString("email"));
						users.setAdmin(rs.getBoolean("admin"));
					}
				}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public UsersDO loginID(String user_id, String user_pw) {
		UsersDO users = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("select * from user_table where user_id = '%s' and user_pw = '%s'", user_id, user_pw);
				rs = stmt.executeQuery(sql);

				if(rs.isBeforeFirst()){
					users = new UsersDO();
					
					rs.next();
					users.setUser_id(rs.getString("user_id"));
					users.setUser_pw(rs.getString("user_pw"));
					users.setNickname(rs.getString("nickname"));
					users.setEmail(rs.getString("email"));
					users.setAdmin(rs.getBoolean("admin"));
				}
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public int insertUser(UsersDO user) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("insert into user_table(user_id, user_pw, nickname, email, admin)"
						+ "values ('%s', '%s', '%s', '%s', 0) ", user.getUser_id(), user.getUser_pw(), user.getNickname(), user.getEmail());
				result = stmt.executeUpdate(sql);
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int updateUser(UsersDO user) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("update user_table set user_pw = '%s', "
						+ "email = '%s', nickname = '%s' where user_id = '%s'", user.getUser_pw(), user.getEmail(), user.getNickname(), user.getUser_id());
				result = stmt.executeUpdate(sql);
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int deleteUser(String user) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("delete from user_table where user_id = '%s'", user);
				result = stmt.executeUpdate(sql);
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	
	
	
	
}
