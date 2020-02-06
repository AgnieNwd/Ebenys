package com.cours.ebenus.maven.ebenus.back.office.servlets.servletUser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;

/**
 * Servlet implementation class CrudUserServlet
 */
@WebServlet("/CrudUserServlet")
public class CrudUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static IServiceFacade serviceFacade = null;

    private static List<User> users = null;
    private static List<Role> roles = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudUserServlet() {
        super();
        // TODO Auto-generated constructor stub   
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	users = serviceFacade.getUserDao().findAllUsers();
    	roles = serviceFacade.getRoleDao().findAllRoles();

    	if (this.checkSession(request.getSession())) {
        	User user = (User) request.getSession().getAttribute("thisuser");	

    		request.setAttribute("allUsers", users);

    		if (request.getParameter("action") == null) {
    	        //System.out.println("action is null.");
    	    } else { 
    	    	switch (request.getParameter("action")) {
	    	    	case "delete":
	                	int id = Integer.parseInt(request.getParameter("id"));
	
	        	    	User userDelete = serviceFacade.getUserDao().findUserById(id);
	        	    	boolean isDeleted = serviceFacade.getUserDao().deleteUser(userDelete);
	        	    	if (isDeleted) {
	        	    		users = serviceFacade.getUserDao().findAllUsers();
	       	    			request.setAttribute("allUsers", users);
	       	    			response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
	       	    			return;
	        	    	} else {
	        	    		//System.out.print("idDeleted User False ..... " + isDeleted);
	        	    	}
	                    break;
	    	    	default:
	    	    		users = serviceFacade.getUserDao().findAllUsers();
	   	    			request.setAttribute("allUsers", users);
	   	    			break;
				}
			}
    		this.getServletContext().getRequestDispatcher("/pages/crudUser/allUser.jsp").forward(request, response);
    	} else {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/");
    		return;
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    protected boolean checkSession(HttpSession session) {
    	if (session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
