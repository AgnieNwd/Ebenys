package com.cours.ebenus.maven.ebenus.back.office.servlets.servletUser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private IServiceFacade serviceFacade = null;

    private static String mode = null;
    private static User userUpdate = null;
    private static List<Role> roles = null;
    private static List<Classe> classes = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	
        mode = "update";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (this.checkSession(request.getSession())) {
        	roles = serviceFacade.getRoleDao().findAllRoles();
        	classes = serviceFacade.getClasseDao().findAllClasses();
    		request.setAttribute("mode", mode);
        	
        	int id = Integer.parseInt(request.getParameter("id"));
        	userUpdate = serviceFacade.getUserDao().findUserById(id);
    		request.setAttribute("userUpdate", userUpdate);
    		request.setAttribute("classes", classes);

    		this.getServletContext().getRequestDispatcher("/pages/crudUser/addUpdateUser.jsp").forward(request, response);
    	} else {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/");
    		return;
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("validationRole");
        String classe = request.getParameter("validationClasse");

        String dteNaiss = request.getParameter("dateNaiss");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dteNaissance = null;
		try {
			dteNaissance = dateFormat.parse(dteNaiss);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		userUpdate.setPrenom(request.getParameter("firstname"));
		userUpdate.setNom(request.getParameter("lastname"));
		userUpdate.setEmail(request.getParameter("email"));
		userUpdate.setDateNaissance(dteNaissance);
		
		if (request.getParameter("password") != "") {
			userUpdate.setPassword(request.getParameter("password"));
		}
				
		List<Role> rolesByIdentifiant = serviceFacade.getRoleDao().findRoleByIdentifiant(role);
		userUpdate.setRole(rolesByIdentifiant.get(0));
		
		int idClasse = Integer.parseInt(classe);
        Classe classesById = serviceFacade.getClasseDao().findClasseById(idClasse);
		userUpdate.setClasse(classesById);
		
		// Update with password
		if (request.getParameter("password") != "") {
			userUpdate = serviceFacade.getUserDao().updateUser(userUpdate);
		} 
		// Update without password
		else {
			userUpdate = serviceFacade.getUserDao().updateUserWithoutPassword(userUpdate);			
		}
		
		if (userUpdate != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/UpdateUserServlet").forward(request, response);
		}
	}
	
    
    protected boolean checkSession(HttpSession session) {
    	if (session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
