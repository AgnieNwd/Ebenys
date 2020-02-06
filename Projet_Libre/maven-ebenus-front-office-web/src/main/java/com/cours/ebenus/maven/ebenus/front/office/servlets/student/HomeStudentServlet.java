package com.cours.ebenus.maven.ebenus.front.office.servlets.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeStudentServlet")
public class HomeStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IServiceFacade serviceFacade = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeStudentServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.checkSession(request.getSession())) {
    		this.getServletContext().getRequestDispatcher("/pages/student/home/home.jsp").forward(request, response);
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
