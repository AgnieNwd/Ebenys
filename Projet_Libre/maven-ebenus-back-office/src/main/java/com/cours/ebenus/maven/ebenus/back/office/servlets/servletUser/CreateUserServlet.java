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
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static IServiceFacade serviceFacade = null;
    private static List<Role> roles = null;
    private static List<Classe> classes = null;

    private static String mode = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);

        mode = "create";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (this.checkSession(request.getSession())) {
    		roles = serviceFacade.getRoleDao().findAllRoles();
        	classes = serviceFacade.getClasseDao().findAllClasses();
        	
    		request.setAttribute("mode", mode);
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
		// TODO Auto-generated method stub
		String prenom = request.getParameter("firstname");
        String nom = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("validationRole");
        String classe = request.getParameter("validationClasse");
        String dteNaiss = request.getParameter("dateNaiss");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dteNaissance = null;
		try {
			dteNaissance = dateFormat.parse(dteNaiss);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        List<Role> rolesByIdentifiant = serviceFacade.getRoleDao().findRoleByIdentifiant(role);
        int idClasse = Integer.parseInt(classe);
        Classe classesById = serviceFacade.getClasseDao().findClasseById(idClasse);        
		
		User newUser = new User(nom, prenom, email, password, dteNaissance, rolesByIdentifiant.get(0), classesById);
		
		newUser = serviceFacade.getUserDao().createUser(newUser);
		if (newUser!= null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/CreateUserServlet").forward(request, response);
		}
	}
	
    protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("thisuser") != null) {
    		return true;
    	} else
    		return false;
    }

}
