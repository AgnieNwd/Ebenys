package com.cours.ebenus.maven.ebenus.back.office.servlets.servletClasse;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class CrudClasseServlet
 */
@WebServlet("/CrudClasseServlet")
public class CrudClasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static IServiceFacade serviceFacade = null;

    private static List<Classe> classes = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudClasseServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	
        classes = serviceFacade.getClasseDao().findAllClasses();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        classes = serviceFacade.getClasseDao().findAllClasses();
    	if (this.checkSession(request.getSession())) {
    		User user = (User) request.getSession().getAttribute("thisuser");	

    		request.setAttribute("allClasses", classes);
    		
    		if (request.getParameter("action") == null) {
    	        //System.out.println("action is null.");
    	    } else { 
    	    	switch (request.getParameter("action")) {
	    	    	case "delete":
	                	int idToDelete = Integer.parseInt(request.getParameter("id"));
	                	
	        	    	Classe matiereDelete = serviceFacade.getClasseDao().findClasseById(idToDelete);
	        	    	boolean isDeleted = serviceFacade.getClasseDao().deleteClasse(matiereDelete);
	        	    	if (isDeleted) {
	        	    		classes = serviceFacade.getClasseDao().findAllClasses();
	       	    			request.setAttribute("allClasses", classes);
	       	    			response.sendRedirect(this.getServletContext().getContextPath() + "/CrudClasseServlet");
	       	    			return;
	        	    	} else {
	        	    		//System.out.print("False ..... " + isDeleted);
	        	    	}
	                    break;
	                    
	    	    	default:
	    	    		classes = serviceFacade.getClasseDao().findAllClasses();
	   	    			request.setAttribute("allClasses", classes);
	   	    			break;
				}
			}
    		this.getServletContext().getRequestDispatcher("/pages/crudClasse/allClasse.jsp").forward(request, response);
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
