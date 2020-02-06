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
 * Servlet implementation class UpdateRoleServlet
 */
@WebServlet("/UpdateRoleServlet")
public class UpdateRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static IServiceFacade serviceFacade = null;

    private static List<User> usersProf = null;
    private static String mode = null;
    private static Role roleUpdate = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoleServlet() {
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
    		roleUpdate = serviceFacade.getRoleDao().findRoleById(idToUpdate);
        	
    		request.setAttribute("roleUpdate", roleUpdate);
    		
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
        roleUpdate.setIdentifiant(request.getParameter("name"));
        roleUpdate.setDescription(request.getParameter("description"));
		
		roleUpdate = serviceFacade.getRoleDao().updateRole(roleUpdate);
		if (roleUpdate != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CruRoleServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/UpdateRoleServlet").forward(request, response);
		}
	}


	protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }
}
