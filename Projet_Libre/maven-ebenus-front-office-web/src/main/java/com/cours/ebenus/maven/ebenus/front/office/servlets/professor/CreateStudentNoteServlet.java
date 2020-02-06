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
 * Servlet implementation class AddStudentNoteServlet
 */
@WebServlet("/CreateStudentNoteServlet")
public class CreateStudentNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IServiceFacade serviceFacade = null;

    private static User userRead = null;
    private static List<Matiere> matiereProfList = null;
    private static String mode = null;



    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStudentNoteServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	mode = "create";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mode = "create";
		request.setAttribute("mode", mode);

		if (this.checkSession(request.getSession())) {
			User user = (User) request.getSession().getAttribute("thisuser");

			int id = Integer.parseInt(request.getParameter("id"));
        	userRead = serviceFacade.getUserDao().findUserById(id);
        	matiereProfList = serviceFacade.getMatiereDao().findMatiereByIdUser(user.getId());
			
    		request.setAttribute("userRead", userRead);
    		request.setAttribute("matiereProfList", matiereProfList);

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
		String matiereChoisi = request.getParameter("validationMatiere");
        int IdMatiere = Integer.parseInt(matiereChoisi);	
        Matiere matiere = serviceFacade.getMatiereDao().findMatiereById(IdMatiere);
        
        String libelleNote = request.getParameter("note");
		Double note = Double.parseDouble(libelleNote);
		
        Note newNote = new Note(note, userRead, matiere);
        newNote = serviceFacade.getNoteDao().createNote(newNote);
        
        if (newNote != null) {
            response.sendRedirect(this.getServletContext().getContextPath() + "/SeeStudentNotesServlet?id=" + userRead.getId());
		} else {
            request.getRequestDispatcher(this.getServletContext().getContextPath() + "/CreateStudentNoteServlet").forward(request, response);
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
