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
 * Servlet implementation class CreateMatiereServlet
 */
@WebServlet("/CreateMatiereServlet")
public class CreateMatiereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static IServiceFacade serviceFacade = null;

    private static List<User> usersProf = null;
    private static String mode = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMatiereServlet() {
        super();
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	usersProf = serviceFacade.getUserDao().findUsersByIdentifiantRole("Professeur");

    	mode = "create";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mode = "create";
		if (this.checkSession(request.getSession())) {
			request.setAttribute("mode", mode);
    		request.setAttribute("allUsersProf", usersProf);

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

        String libelleMatiere = request.getParameter("name");
		
        Matiere newMatiere = new Matiere(libelleMatiere, user);
        newMatiere = serviceFacade.getMatiereDao().createMatiere(newMatiere);
        
        if (newMatiere != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudMatiereServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/CreateMatiereServlet").forward(request, response);
		}
	}
	
	 protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
