package com.cours.ebenus.maven.ebenus.back.office.servlets.servletMatiere;

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
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class CrudMatiereServlet
 */
@WebServlet("/CrudMatiereServlet")
public class CrudMatiereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static IServiceFacade serviceFacade = null;

    private static List<User> users = null;
    private static List<Role> roles = null;
    private static List<Matiere> matieres = null;
    
    private static String mode = null;
    private static Matiere matiereUpdate = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudMatiereServlet() {
        super();
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	users = serviceFacade.getUserDao().findAllUsers();
    	roles = serviceFacade.getRoleDao().findAllRoles();
    	matieres = serviceFacade.getMatiereDao().findAllMatieres();
    	
    	mode = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	matieres = serviceFacade.getMatiereDao().findAllMatieres();
    	mode = "";
    	request.setAttribute("mode", mode);

    	if (this.checkSession(request.getSession())) {
        	User user = (User) request.getSession().getAttribute("thisuser");	

    		request.setAttribute("allMatieres", matieres);

    		if (request.getParameter("action") == null) {
    	        //System.out.println("action is null.");
    	    } else { 
    	    	switch (request.getParameter("action")) {
	    	    	case "delete":
	                	int idToDelete = Integer.parseInt(request.getParameter("id"));
	                	
	        	    	Matiere matiereDelete = serviceFacade.getMatiereDao().findMatiereById(idToDelete);
	        	    	boolean isDeleted = serviceFacade.getMatiereDao().deleteMatiere(matiereDelete);
	        	    	if (isDeleted) {
	        	    		matieres = serviceFacade.getMatiereDao().findAllMatieres();
	       	    			request.setAttribute("allMatieres", matieres);
	       	    			response.sendRedirect(this.getServletContext().getContextPath() + "/CrudMatiereServlet");
	       	    			return;
	        	    	} else {
	        	    		//System.out.print("False ..... " + isDeleted);
	        	    	}
	                    break;
	                    
	    	    	default:
        	    		matieres = serviceFacade.getMatiereDao().findAllMatieres();
	   	    			request.setAttribute("allMatieres", matieres);
	   	    			break;
				}
			}
    		this.getServletContext().getRequestDispatcher("/pages/crudMatiere/allMatiere.jsp").forward(request, response);
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
