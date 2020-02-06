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
 * Servlet implementation class CreateClasseServlet
 */
@WebServlet("/CreateClasseServlet")
public class CreateClasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private static IServiceFacade serviceFacade = null;

    private static String mode = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClasseServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	
    	mode = "create";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mode = "create";
		if (this.checkSession(request.getSession())) {
			request.setAttribute("mode", mode);

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

        String libelleClasse = request.getParameter("name");
		
        Classe newClasse = new Classe(libelleClasse);
        newClasse = serviceFacade.getClasseDao().createClasse(newClasse);
        
        if (newClasse != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudClasseServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/CreateClasseServlet").forward(request, response);
		}
	}
	
	 protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
