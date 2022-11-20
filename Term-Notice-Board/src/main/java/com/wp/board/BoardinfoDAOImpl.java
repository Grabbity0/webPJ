package com.wp.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BoardinfoDAOImpl implements BoardinfoDAO {
	protected DBConnectionInfo dbInfo;
	
	protected Connection conn = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	
	public BoardinfoDAOImpl(DBConnectionInfo dbInfo) {
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
				dbInfo.getUrl(), dbInfo.getBoardId(), dbInfo.getPassword());
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
	public List<BoardDO> selectAllBoard() {
		List<BoardDO> boardList = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = "select * from board_table order by board_id";
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					boardList = new ArrayList<BoardDO>();
					
					while(rs.next()) {
						BoardDO board = new BoardDO();
						board.setBoard_id(rs.getInt("board_id"));
						board.setBoard_subj(rs.getString("board_subj"));
						board.setBoard_cont(rs.getString("board_cont"));
						board.setWriteTime(rs.getTimestamp("writeTime"));
						board.setUser_id(rs.getString("user_id"));
						boardList.add(board);
					}
				}
			}	
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return boardList;
		
	}
	
	@Override
	public BoardDO selectBoard(int board_id) {
		BoardDO board = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("select * from board_table where board_id = %d", board_id);
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					board = new BoardDO();
					
					rs.next();
						board.setBoard_id(rs.getInt("board_id"));
						board.setBoard_subj(rs.getString("board_subj"));
						board.setBoard_cont(rs.getString("board_cont"));
						board.setWriteTime(rs.getTimestamp("writeTime"));
						board.setUser_id(rs.getString("user_id"));
					}
				}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}
	
	@Override
	public int insertBoard(BoardDO board) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();

				
				String sql = String.format("insert into board_table(board_id, board_subj, board_cont, writeTime, user_id)"
						+ "values ( board_seq.NEXTVAL, '%s', '%s', sysdate, '%s') ", board.getBoard_subj(), board.getBoard_cont(), board.getUser_id());
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
	public int updateBoard(BoardDO board) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("update board_table set board_subj = '%s', "
						+ "board_cont = '%s' where board_id = %d", board.getBoard_subj(), board.getBoard_cont(), board.getBoard_id());
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
	public int deleteBoard(int board) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("delete from board_table where board_id = %d", board);
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
