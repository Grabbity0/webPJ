package com.wp.board;

import java.util.List;

public interface UsersinfoDAO {

	List<UsersDO> selectAllUsers();

	UsersDO selectUser(String user_id);

	UsersDO loginID(String user_id, String user_pw);
	
	int insertUser(UsersDO users);

	int updateUser(UsersDO users);

	int deleteUser(String user_id);

}