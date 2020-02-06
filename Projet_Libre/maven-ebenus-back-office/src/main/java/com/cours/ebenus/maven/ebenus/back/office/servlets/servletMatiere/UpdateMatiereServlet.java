package com.cours.ebenus.maven.ebenus.back.office.servlets.servletMatiere;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class UpdateMatiereServlet
 */
@WebServlet("/UpdateMatiereServlet")
public class UpdateMatiereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static IServiceFacade serviceFacade = null;

    private static List<User> usersProf = null;
    private static String mode = null;
    private static Matiere matiereUpdate = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMatiereServlet() {
        super();
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);

    	mode = "update";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.checkSession(request.getSession())) {
	    	usersProf = serviceFacade.getUserDao().findUsersByIdentifiantRole("Professeur");

			request.setAttribute("mode", mode);
    		request.setAttribute("allUsersProf", usersProf);

    		int idToUpdate = Integer.parseInt(request.getParameter("id"));
        	matiereUpdate = serviceFacade.getMatiereDao().findMatiereById(idToUpdate);
        	
    		request.setAttribute("matiereUpdate", matiereUpdate);
    		
    		this.getServletContext().getRequestDispatcher("/pages/crudMatiere/addUpdateMatiere.jsp").forward(request, response);
		} else {
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
			return;
		}	
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userProf = request.getParameter("validationUserProf");
        int IdUserProf = Integer.parseInt(userProf);	
        User user = serviceFacade.getUserDao().findUserById(IdUserProf);
		
      
        matiereUpdate.setLibelleMatiere(request.getParameter("name"));
		matiereUpdate.setUser(user);
		
		matiereUpdate = serviceFacade.getMatiereDao().updateMatiere(matiereUpdate);
		if (matiereUpdate != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudMatiereServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/UpdateMatiereServlet").forward(request, response);
		}
	}
	
	protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
