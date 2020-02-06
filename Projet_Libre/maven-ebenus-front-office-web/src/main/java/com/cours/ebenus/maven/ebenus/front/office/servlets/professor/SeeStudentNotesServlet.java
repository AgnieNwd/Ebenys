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
 * Servlet implementation class StudentNoteProfessorServlet
 */
@WebServlet("/SeeStudentNotesServlet")
public class SeeStudentNotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	private IServiceFacade serviceFacade = null;

    private static User userRead = null;

    private static List<Note> noteList = null;
    private static List<Matiere> matiereProfList = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeStudentNotesServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.checkSession(request.getSession())) {
			User user = (User) request.getSession().getAttribute("thisuser");

			int id = Integer.parseInt(request.getParameter("id"));
        	userRead = serviceFacade.getUserDao().findUserById(id);
        	matiereProfList = serviceFacade.getMatiereDao().findMatiereByIdUser(user.getId());
			noteList = serviceFacade.getNoteDao().findNoteByEmailUser(userRead.getEmail());
			
    		request.setAttribute("userRead", userRead);
    		request.setAttribute("matiereProfList", matiereProfList);
    		request.setAttribute("noteList", noteList);

    		if (request.getParameter("action") == null) {
    	        //System.out.println("action is null.");
    	    } else { 
    	    	switch (request.getParameter("action")) {
	    	    	case "delete":
	                	int idNote = Integer.parseInt(request.getParameter("idNote"));
	
	        	    	Note noteDelete = serviceFacade.getNoteDao().findNoteById(idNote);
	        	    	boolean isDeleted = serviceFacade.getNoteDao().deleteNote(noteDelete);
	        	    	if (isDeleted) {
	        				noteList = serviceFacade.getNoteDao().findNoteByEmailUser(userRead.getEmail());
	        	    		request.setAttribute("noteList", noteList);
	       	    			response.sendRedirect(this.getServletContext().getContextPath() + "/SeeStudentNotesServlet?id=" + userRead.getId());
	       	    			return;
	        	    	} else {
	        	    		//System.out.print("idDeleted User False ..... " + isDeleted);
	        	    	}
	                    break;
	    	    	default:
        				noteList = serviceFacade.getNoteDao().findNoteByEmailUser(userRead.getEmail());
	    	    		request.setAttribute("noteList", noteList);
	   	    			break;
				}
			}
    		
			this.getServletContext().getRequestDispatcher("/pages/professor/classe/studentsNotes.jsp").forward(request, response);
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
		User thisuser = (User) session.getAttribute("thisuser");
    	if (session.getAttribute("thisuser") != null && serviceFacade.getUserDao().findUserById(thisuser.getId()) != null) {
    		return true;
    	} else
    		return false;
    }

}
