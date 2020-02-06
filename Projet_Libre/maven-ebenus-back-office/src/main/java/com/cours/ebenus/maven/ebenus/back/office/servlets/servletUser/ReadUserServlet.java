package com.cours.ebenus.maven.ebenus.back.office.servlets.servletUser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.Note;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class ReadUserServlet
 */
@WebServlet("/ReadUserServlet")
public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IServiceFacade serviceFacade = null;

    private static User userRead = null;
    private static List<Matiere> matiereList = null;
    private static List<User> eleveListByClasse = null;
    private static List<Note> noteList = null;
    
    private static String mode = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadUserServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	mode = request.getParameter("mode");
		request.setAttribute("mode", mode);

		if (this.checkSession(request.getSession())) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	userRead = serviceFacade.getUserDao().findUserById(id);
            matiereList = serviceFacade.getMatiereDao().findMatiereByIdUser(id);
            eleveListByClasse = serviceFacade.getUserDao().findUsersByLibelleClasseIdentifiantRole(userRead.getClasse().getLibelleClasse(), "Eleve");
            noteList = serviceFacade.getNoteDao().findNoteByEmailUser(userRead.getEmail());
        	
    		request.setAttribute("userRead", userRead);
    		request.setAttribute("matiereList", matiereList);
    		request.setAttribute("eleveListByClasse", eleveListByClasse);
    		request.setAttribute("noteList", noteList);

    		this.getServletContext().getRequestDispatcher("/pages/crudUser/readUser.jsp").forward(request, response);
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
