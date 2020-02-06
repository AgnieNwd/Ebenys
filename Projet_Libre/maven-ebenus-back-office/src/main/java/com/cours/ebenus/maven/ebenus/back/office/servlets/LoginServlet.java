package com.cours.ebenus.maven.ebenus.back.office.servlets;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    
    private static IServiceFacade serviceFacade = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (this.checkSession(request.getSession()) == true) {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    		return;
    	} else 
    		this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	if (this.checkSession(request.getSession()) == true) {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    		return;
    	}
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        User utilisateur = serviceFacade.getUserDao().authenticate(email, password);
        
        if (utilisateur != null && utilisateur.getRole().getIdRole() < 3) {
            request.getSession().setAttribute("thisuser", utilisateur);
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
        }
        else {
            request.setAttribute("error", "On ne connait pas l'utilisateur");
            request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
        }
	}
	
    protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
