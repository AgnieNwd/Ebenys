package com.cours.ebenus.servlets;

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

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private IServiceFacade serviceFacade = null;

    private static String mode = null;
    private static Utilisateur userUpdate = null;
    private static List<Role> roles = null;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	roles = serviceFacade.getRoleDao().findAllRoles();

        mode = "update";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	request.setAttribute("mode", mode);
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	userUpdate = serviceFacade.getUtilisateurDao().findUtilisateurById(id);
		request.setAttribute("userUpdate", userUpdate);

		this.getServletContext().getRequestDispatcher("/pages/crudUser/addUpdateUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("select_city");
        String dteNaiss = request.getParameter("dteNaiss");
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dteNaissance = null;
		try {
			dteNaissance = dateFormat.parse(dteNaiss);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		userUpdate.setPrenom(request.getParameter("firstname"));
		userUpdate.setNom(request.getParameter("lastname"));
		userUpdate.setIdentifiant(request.getParameter("email"));
		userUpdate.setCivilite(request.getParameter("sex"));
		userUpdate.setDateNaissance(dteNaissance);
		
		List<Role> rolesByIdentifiant = serviceFacade.getRoleDao().findRoleByIdentifiant(role);
		userUpdate.setRole(rolesByIdentifiant.get(0));
		
		userUpdate = serviceFacade.getUtilisateurDao().updateUserWithoutPassword(userUpdate);
		if (userUpdate!= null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/UpdateUserServlet").forward(request, response);
		}
	}

}
