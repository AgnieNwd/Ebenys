package com.cours.ebenus.maven.ebenus.back.office.servlets.servletRole;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class CreateRoleServlet
 */
@WebServlet("/CreateRoleServlet")
public class CreateRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static IServiceFacade serviceFacade = null;

    private static String mode = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoleServlet() {
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

    		this.getServletContext().getRequestDispatcher("/pages/cruRole/addUpdateRole.jsp").forward(request, response);
		} else {
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
			return;
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("name");
		String description = request.getParameter("description");
        Role newRole = new Role(identifiant, description);
        
        newRole = serviceFacade.getRoleDao().createRole(newRole);
        
        if (newRole != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CruRoleServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/CreateRoleServlet").forward(request, response);
		}
	}
	
	 protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
