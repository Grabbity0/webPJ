package com.wp.board;

import java.util.List;

public interface BoardinfoDAO {

	List<BoardDO> selectAllBoard();
	
	BoardDO selectBoard(int board_id);

	int insertBoard(BoardDO board);

	int updateBoard(BoardDO board);

	int deleteBoard(int board_id);

}