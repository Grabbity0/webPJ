package com.wp.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/*")
public class ManagerControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		UsersinfoDAO user_dao = (UsersinfoDAO)getServletContext().getAttribute("user_dao");
		BoardinfoDAO board_dao = (BoardinfoDAO)getServletContext().getAttribute("board_dao");
		
		// get routing information
		String pathInfo = request.getPathInfo();
		String action = request.getParameter("action");
		
		HttpSession httpSession = request.getSession(false);
		Global g = new Global();
		String viewName = null;

		if(pathInfo != null && pathInfo.length() > 0) { // 메인화면
			if(pathInfo.equals("/main")) {
				UsersDO users = new UsersDO();
				List<BoardDO> boardList = board_dao.selectAllBoard();
				
				users.setUser_id(request.getParameter("user_id"));
				users.setNickname(request.getParameter("nickname"));
				users.setAdmin(Boolean.parseBoolean(request.getParameter("admin")));
				
				request.setAttribute("users", users);
				request.setAttribute("board_list", boardList);
				
				viewName = "board_main.jsp";
			}
		}
		else {
	
			if(action.equals("user_register_form")) { // 가입 화면		
				viewName = "register.jsp";
			}
			else if(action.equals("register")) { // 계정 추가
				UsersDO user = new UsersDO();
				user.setUser_id(request.getParameter("user_id"));
				
				if(user_dao.selectUser(user.getUser_id()) != null) {
					g.jsmessage(response, "해당 아이디가 이미 존재합니다.");
				}
				else {
					user.setUser_pw(request.getParameter("user_pw"));
					user.setNickname(request.getParameter("nickname"));
					user.setEmail(request.getParameter("email"));
					user.setAdmin(false);
	
					user_dao.insertUser(user);

					viewName = "redirect:/board/main";
				}
	
			}
			else if(action.equals("login_form")) { // 로그인 화면
				viewName = "login.jsp";
			}
			else if(action.equals("login")) { // 로그인 과정
				String user_id = request.getParameter("user_id");
				String user_pw = request.getParameter("user_pw");
				
				UsersDO user = user_dao.loginID(user_id, user_pw);
				
				if(user != null) {
					httpSession.setAttribute("user_id", user.getUser_id());
					httpSession.setAttribute("user_pw", user.getUser_pw());
					httpSession.setAttribute("nickname", user.getNickname());
					httpSession.setAttribute("email", user.getEmail());
					httpSession.setAttribute("admin", user.getAdmin());
					
					viewName = "redirect:/board/main";
				}
				else {
					g.jsmessage(response, "아이디/비밀번호가 일치하지 않습니다.");
				}
			}
			else if(action.equals("logout")) {
				
				httpSession.invalidate();
				
				viewName = "redirect:/board/main";
			}
			else if(action.equals("user_form")) { // 회원 정보 화면
				
				viewName = "user_info.jsp";
			}
			else if (action.equals("user_update")) { // 회원 정보 수정 
		
				UsersDO users = new UsersDO();
				users.setUser_pw(request.getParameter("user_id"));
				users.setUser_pw(request.getParameter("user_pw"));
				users.setNickname(request.getParameter("nickname"));
				users.setEmail(request.getParameter("email"));
				
				user_dao.updateUser(users);
				
				httpSession.setAttribute("user_id", users.getUser_id());
				httpSession.setAttribute("user_pw", users.getUser_pw());
				httpSession.setAttribute("nickname", users.getNickname());
				httpSession.setAttribute("email", users.getEmail());
				httpSession.setAttribute("admin", users.getAdmin());
				
				g.jsmessage(response, "변경이 완료되었습니다.");
			}
			else if (action.equals("user_delete")){	 // 회원 정보 삭제
				
				String user = request.getParameter("user_id");
				
	
				user_dao.deleteUser(user);
		
				viewName = "redirect:/board?action=user_management";
			}
			else if(action.equals("user_management")){
				List<UsersDO> userList = user_dao.selectAllUsers();
				
				request.setAttribute("user_list", userList);
				
				viewName = "user_management.jsp";
			}
			else if(action.equals("board_insert")) { // 게시글 추가
	
				BoardDO board = new BoardDO();
				
				board.setBoard_subj(request.getParameter("board_subj"));
				board.setBoard_cont(request.getParameter("board_cont"));
				board.setUser_id(request.getParameter("user_id"));
	
				board_dao.insertBoard(board);

				viewName = "redirect:/board/main";
			}
			else if(action.equals("board_form")) { // 게시글

				int board = Integer.parseInt(request.getParameter("board_id"));

				BoardDO boards = board_dao.selectBoard(board);

				request.setAttribute("board", boards);
				
				viewName = "boardInfo.jsp";
			}
			else if(action.equals("board_modify_form")) { // 게시글 작성
				
				if(request.getParameter("board_id") != null) {
				int board = Integer.parseInt(request.getParameter("board_id"));
				
				BoardDO boards = board_dao.selectBoard(board);
		
				request.setAttribute("board", boards);
				}
				viewName = "modify_board.jsp";
			}
			else if (action.equals("board_update")) { // 게시판 수정
				// get request parameters
				BoardDO board = new BoardDO();
				
				board.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
				board.setBoard_subj(request.getParameter("board_subj"));
				board.setBoard_cont(request.getParameter("board_cont"));
				board.setUser_id(request.getParameter("user_id"));
			
				board_dao.updateBoard(board);
				
				viewName = "redirect:/board/main";
			}
			else if (action.equals("board_delete")){ // 게시판 삭제
				
				int board = Integer.parseInt(request.getParameter("board_id"));
				
				board_dao.deleteBoard(board);
				g.jsmessage(response, "게시물이 삭제되었습니다.");
				viewName = "redirect:/board/main";
			}
			
		}
		
		if (viewName != null) {
			if(viewName.contains("redirect:")) {
				response.sendRedirect(request.getContextPath() + viewName.split(":")[1]);
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/" + viewName);
				view.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
