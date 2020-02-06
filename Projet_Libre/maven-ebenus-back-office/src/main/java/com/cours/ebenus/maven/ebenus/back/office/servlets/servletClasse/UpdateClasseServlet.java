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
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class UpdateClasseServlet
 */
@WebServlet("/UpdateClasseServlet")
public class UpdateClasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static IServiceFacade serviceFacade = null;

    private static List<User> usersProf = null;
    private static String mode = null;
    private static Classe classeUpdate = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClasseServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);

    	mode = "update";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.checkSession(request.getSession())) {
    		request.setAttribute("mode", mode);

    		int idToUpdate = Integer.parseInt(request.getParameter("id"));
    		classeUpdate = serviceFacade.getClasseDao().findClasseById(idToUpdate);
        	
    		request.setAttribute("classeUpdate", classeUpdate);
    		
    		this.getServletContext().getRequestDispatcher("/pages/crudClasse/addUpdateClasse.jsp").forward(request, response);
		} else {
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        classeUpdate.setLibelleClasse(request.getParameter("name"));
		
		classeUpdate = serviceFacade.getClasseDao().updateClasse(classeUpdate);
		if (classeUpdate != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudClasseServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/UpdateClasseServlet").forward(request, response);
		}
	}


	protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }
}
