package com.cours.ebenus.servlets;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static IServiceFacade serviceFacade = null;
    private static List<Role> roles = null;

    private static String mode = null;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	roles = serviceFacade.getRoleDao().findAllRoles();

        mode = "create";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
    	request.setAttribute("mode", mode);

		this.getServletContext().getRequestDispatcher("/pages/crudUser/addUpdateUser.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prenom = request.getParameter("firstname");
        String nom = request.getParameter("lastname");
        String identifiant = request.getParameter("email");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("password_confirm");
        String role = request.getParameter("select_city");
        String sex = request.getParameter("sex");
        String dteNaiss = request.getParameter("dteNaiss");
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dteNaissance = null;
		try {
			dteNaissance = dateFormat.parse(dteNaiss);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<Role> rolesByIdentifiant = serviceFacade.getRoleDao().findRoleByIdentifiant(role);
		Utilisateur newUser = new Utilisateur(sex, prenom, nom, identifiant, password, dteNaissance, rolesByIdentifiant.get(0));
		
		newUser = serviceFacade.getUtilisateurDao().createUtilisateur(newUser);
		if (newUser!= null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/CreateUserServlet").forward(request, response);
		}
	}

}
