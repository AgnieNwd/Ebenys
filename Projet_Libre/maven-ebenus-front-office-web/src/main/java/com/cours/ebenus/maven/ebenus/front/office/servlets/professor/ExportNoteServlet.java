package com.cours.ebenus.maven.ebenus.front.office.servlets.professor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cours.ebenus.maven.ebenus.dao.entities.Note;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;



/**
 * Servlet implementation class ExportNoteServlet
 */
@WebServlet("/ExportNoteServlet")
public class ExportNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IServiceFacade serviceFacade = null;

	private static List<Note> notes = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportNoteServlet() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(this.checkSession(request.getSession())){
			User user = serviceFacade.getUserDao().findUserById(Integer.parseInt(request.getParameter("idUser")));
        	try
        	{
        		String action = request.getParameter("action");
        		if(action != null) {	
	    			if(action.equals("csv")) {
	    				this.exportCSV(request.getParameter("idUser"));
	    				this.fileToDownload(user.getNom() + "_" + user.getPrenom() + ".csv", response, "text/csv");
	    			}
        		}

        	} 
        	catch (Exception ex) {
        		System.out.println("Get Export Error");
        		ex.printStackTrace ();
        	}
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
		
    }
    
    protected void exportCSV(String id_user) throws IOException {
    	User user = serviceFacade.getUserDao().findUserById(Integer.parseInt(id_user));
    	notes = serviceFacade.getNoteDao().findNoteByIdUser(Integer.parseInt(id_user));
		String filename = user.getNom()+ "_"+ user.getPrenom() + ".csv";
	   	  PrintWriter writer = new PrintWriter(new FileWriter(filename));
	   	  StringBuilder fw = new StringBuilder();
	   	 
	   	  fw.append("Matière");
	   	  fw.append(',');
	   	  fw.append("Note");
	   	  fw.append('\n');
	   	  
	   	  for(int i =0; i <notes.size(); i++){
	    	  fw.append(notes.get(i).getMatiere().getLibelleMatiere());
	    	  fw.append(',');
	    	  fw.append(notes.get(i).getLibelleNote());
	    	  fw.append('\n');
	   	  }
	   	  writer.write(fw.toString());
	   	  writer.close();
	}
    private void fileToDownload(String file, HttpServletResponse response, String contentType) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=" + file);
        response.setContentType(contentType);
        OutputStream out = response.getOutputStream();
        FileInputStream input = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = input.read(buffer)) > 0) {
        	response.getOutputStream().write(buffer, 0, length);
        }
        input.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }


	
	protected boolean checkSession(HttpSession session) {
		User thisuser = (User) session.getAttribute("thisuser");
    	if (session.getAttribute("thisuser") != null && serviceFacade.getUserDao().findUserById(thisuser.getId()) != null) {
    		return true;
    	} else
    		return false;
    }


}
