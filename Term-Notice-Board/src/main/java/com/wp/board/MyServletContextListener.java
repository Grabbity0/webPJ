package com.wp.board;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println(">>> ServletContext object destroyed...");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println(">>> ServletContext object initialized...");
    	
    	ServletContext context = sce.getServletContext();
   /*
    	DBConnectionInfo dbInfo = new DBConnectionInfo();
    	dbInfo.setJdbcDriverName(context.getInitParameter("oracle_jdbc_driver"));
    	dbInfo.setUrl(context.getInitParameter("db_url"));
    	dbInfo.setUserId(context.getInitParameter("db_userid"));
    	dbInfo.setPassword(context.getInitParameter("db_password"));
    	
    	context.setAttribute("db_info", dbInfo);
   */
    	DBConnectionInfo dbInfo = new DBConnectionInfo();
    	dbInfo.setJdbcDriverName(context.getInitParameter("oracle_jdbc_driver"));
    	dbInfo.setUrl(context.getInitParameter("db_url"));
    	dbInfo.setUserId(context.getInitParameter("db_userid"));
    	dbInfo.setPassword(context.getInitParameter("db_password"));
    	
    	String daoUser = context.getInitParameter("dao_user_class");
    	try {
			Class clazz = Class.forName(daoUser);
			UsersinfoDAO userDao = (UsersinfoDAO)clazz.getConstructor(DBConnectionInfo.class).newInstance(dbInfo);
			context.setAttribute("user_dao", userDao);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String daoBoard = context.getInitParameter("dao_board_class");
    	try {
			Class clazz = Class.forName(daoBoard);
			BoardinfoDAO boardDao = (BoardinfoDAO)clazz.getConstructor(DBConnectionInfo.class).newInstance(dbInfo);
			context.setAttribute("board_dao", boardDao);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    	
	
}
