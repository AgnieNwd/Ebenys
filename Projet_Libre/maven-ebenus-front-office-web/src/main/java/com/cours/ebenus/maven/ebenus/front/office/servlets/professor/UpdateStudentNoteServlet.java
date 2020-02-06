package com.cours.ebenus.maven.ebenus.front.office.servlets.professor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.Note;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class UpdateStudentNoteServlet
 */
@WebServlet("/UpdateStudentNoteServlet")
public class UpdateStudentNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IServiceFacade serviceFacade = null;

    private static User userRead = null;
    private static Note noteUpdate = null;
    private static Matiere matieredelanote = null;

    private static String mode = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentNoteServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        
    	mode = "update";

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	mode = "update";
		request.setAttribute("mode", mode);

		if (this.checkSession(request.getSession())) {
			User user = (User) request.getSession().getAttribute("thisuser");

			int idUser = Integer.parseInt(request.getParameter("idUser"));
        	userRead = serviceFacade.getUserDao().findUserById(idUser);
        	
        	int idNote = Integer.parseInt(request.getParameter("idNote"));
        	noteUpdate = serviceFacade.getNoteDao().findNoteById(idNote);
        	
        	int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        	matieredelanote = serviceFacade.getMatiereDao().findMatiereById(idMatiere);
        				
    		request.setAttribute("userRead", userRead);
    		request.setAttribute("noteUpdate", noteUpdate);
    		request.setAttribute("matieredelanote", matieredelanote);

			this.getServletContext().getRequestDispatcher("/pages/professor/classe/addUpdateStudentNote.jsp").forward(request, response);
		} else {
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String libelleNote = request.getParameter("note");
		Double note = Double.parseDouble(libelleNote);
		
		noteUpdate.setLibelleNote(note);
		noteUpdate.setUser(userRead);
		
		noteUpdate = serviceFacade.getNoteDao().updateNote(noteUpdate);
		if (noteUpdate != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/SeeStudentNotesServlet?id=" + userRead.getId());
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/UpdateMatiereServlet").forward(request, response);
		}
	}
	
	protected boolean checkSession(HttpSession session) {
		User thisuser = (User) session.getAttribute("thisuser");
    	if (session.getAttribute("thisuser") != null && serviceFacade.getUserDao().findUserById(thisuser.getId()) != null) {
    		return true;
    	} else
    		return false;
    }

}
