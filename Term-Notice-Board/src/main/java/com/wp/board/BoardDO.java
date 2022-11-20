package com.wp.board;

import java.sql.Timestamp;

public class BoardDO {
	private int board_id;
	private String board_subj;
	private String board_cont;
	private Timestamp writeTime;
	private String user_id;
	
	public BoardDO() {
		
	}
	
	public BoardDO(int board_id, String board_subj, String board_cont, Timestamp writeTime, String user_id) {
		this.board_id = board_id;
		this.board_subj = board_subj;
		this.board_cont = board_cont;
		this.writeTime = writeTime;
		this.user_id = user_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_subj() {
		return board_subj;
	}

	public void setBoard_subj(String board_subj) {
		this.board_subj = board_subj;
	}

	public String getBoard_cont() {
		return board_cont;
	}

	public void setBoard_cont(String board_cont) {
		this.board_cont = board_cont;
	}

	public Timestamp getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


}
