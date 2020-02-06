package com.cours.ebenus.maven.ebenus.front.office.servlets.professor;

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
 * Servlet implementation class HomeProfessorServlet
 */
@WebServlet("/HomeProfessorServlet")
public class HomeProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IServiceFacade serviceFacade = null;
	
	private static List<Matiere> matiereList = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeProfessorServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.checkSession(request.getSession())) {
        	User user = (User) request.getSession().getAttribute("thisuser");	

            matiereList = serviceFacade.getMatiereDao().findMatiereByIdUser(user.getId());
    		request.setAttribute("matiereList", matiereList);


			this.getServletContext().getRequestDispatcher("/pages/professor/home/home.jsp").forward(request, response);
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
		User thisuser = (User) session.getAttribute("thisuser");
    	if (session.getAttribute("thisuser") != null && serviceFacade.getUserDao().findUserById(thisuser.getId()) != null) {
    		return true;
    	} else
    		return false;
    }

}
