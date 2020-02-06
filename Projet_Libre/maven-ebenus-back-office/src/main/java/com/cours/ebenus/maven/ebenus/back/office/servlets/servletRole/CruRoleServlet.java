package com.cours.ebenus.maven.ebenus.back.office.servlets.servletRole;

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
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class CruRoleServlet
 */
@WebServlet("/CruRoleServlet")
public class CruRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static IServiceFacade serviceFacade = null;

    private static List<Role> roles = null;
    
    private static String mode = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CruRoleServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);

        roles = serviceFacade.getRoleDao().findAllRoles();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		roles = serviceFacade.getRoleDao().findAllRoles();
    	if (this.checkSession(request.getSession())) {
    		User user = (User) request.getSession().getAttribute("thisuser");	

    		request.setAttribute("allRoles", roles);
    		this.getServletContext().getRequestDispatcher("/pages/cruRole/allRole.jsp").forward(request, response);
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
