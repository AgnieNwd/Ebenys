/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    
    private static IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	if(this.checkSession(request.getSession()) == true) {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    		return;
    	} else 
    		this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	if(this.checkSession(request.getSession()) == true) {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    		return;
    	}
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        Utilisateur utilisateur = serviceFacade.getUtilisateurDao().authenticate(email, password);
        if (utilisateur != null) {
            request.getSession().setAttribute("user", utilisateur);
            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
        }
        else {
            request.setAttribute("error", "On ne connait pas l'utilisateur");
            request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
        }
    }
    
    protected boolean checkSession(HttpSession session) {
    	if(session.getAttribute("user") != null) {
    		return true;
    	} else
    		return false;
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
