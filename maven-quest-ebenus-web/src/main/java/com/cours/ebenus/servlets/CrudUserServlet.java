/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.taglibs.standard.lang.jstl.Constants;
import org.apache.commons.io.output.*;


import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.google.gson.Gson;
/**
 *
 * @author elhad
 */
// @WebServlet(name = "CrudUserServlet", urlPatterns = {"/CrudUserServlet"})
public class CrudUserServlet extends HttpServlet {

    private static IServiceFacade serviceFacade = null;

    private static List<Utilisateur> utilisateurs = null;
    private static List<Role> roles = null;
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;
    
    /**
     * Méthode d'initialisation de la Servlet
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
    	roles = serviceFacade.getRoleDao().findAllRoles();
    	filePath = getServletContext().getInitParameter("file-upload"); 
    }

    /**
     * Méthode appelée lors d'une requête HTTP GET
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();

    	if(this.checkSession(request.getSession())){
    		System.out.println("------ doGet -----");
        	Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");	
        	try
        	{
        		String action = request.getParameter("action");
        		if(action != null) {	
	    			if(action.equals("csv")) {
	    				this.exportCSV();
	    				this.fileToDownload("EbenusCSV.csv", response, "text/csv");
	    			}
	    			else if(action.equals("json")) {
	    				this.exportJSON();
	    				this.fileToDownload("EbenusJSON.json", response, "application/json");
	    			}
        		}

        	} 
        	catch (Exception ex) {
        		System.out.println("Get Export Error");
        		ex.printStackTrace ();
        	}

        	request.setAttribute("allUsers", utilisateurs);
        	if (user.getRole().getIdRole() == 1) {
	    		if (request.getParameter("action") == null) {
	    	        System.out.println("action is null.");
	    	    } else { 
	    	    	switch (request.getParameter("action")) {
		                case "delete":
		                	int id = Integer.parseInt(request.getParameter("id"));
		            		System.out.println("id" + id);
		
		        	    	Utilisateur userDelete = serviceFacade.getUtilisateurDao().findUtilisateurById(id);
		        	    	boolean isDeleted = serviceFacade.getUtilisateurDao().deleteUtilisateur(userDelete);
		        	    	if (isDeleted) {
		        	    		utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
		       	    			request.setAttribute("allUsers", utilisateurs);
		       	    			response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
		       	    			return;
		        	    	} else {
		        	    		System.out.print("False ..... " + isDeleted);
		        	    	}
		                    break;
		                default:
		   	    			utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
		   	    			request.setAttribute("allUsers", utilisateurs);
		   	    			break;
		            }
	
	    	    }
        	}
    		this.getServletContext().getRequestDispatcher("/pages/crudUser/allUsers.jsp").forward(request, response);
    		
    	} else {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/");
    		return;
    	}
    	

    }
    
    /**
     * Méthode appelée lors d'une requête HTTP POST
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(this.checkSession(request.getSession())){
    		System.out.println("do post");
    		isMultipart = ServletFileUpload.isMultipartContent(request);
    	    response.setContentType("text/html");
    	    if( !isMultipart ) {
    	         return;
    	      }
    	    DiskFileItemFactory factory = new DiskFileItemFactory();
    	    
    	      // maximum size that will be stored in memory
    	      factory.setSizeThreshold(maxMemSize);

    	      // Create a new file upload handler
    	      ServletFileUpload upload = new ServletFileUpload(factory);
    	      try { 
    	          // Parse the request to get file items.
    	          List fileItems = upload.parseRequest(request);
    	 	
    	          // Process the uploaded file items
    	          Iterator i = fileItems.iterator();
    	          while ( i.hasNext () ) {
    	              FileItem fi = (FileItem)i.next();
    	              if ( !fi.isFormField () ) {
    	                 // Get the uploaded file parameters
    	                 String fieldName = fi.getFieldName();
    	                 String fileName = fi.getName();
    	                 String contentType = fi.getContentType();
    	                 boolean isInMemory = fi.isInMemory();
    	                 long sizeInBytes = fi.getSize();
    	              
    	                 // Write the file
    	                 if( fileName.lastIndexOf("\\") >= 0 ) {
    	                    file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
    	                 } else {
    	                    file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
    	                 }
    	                 fi.write( file ) ;
    	                 System.out.println("Uploaded Filename: " + fileName);
    	              }
    	          }
    	          
    	          Reader in = new FileReader(file); //The file is the file which you get from the request(java.io.File)
    	          Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
    	          List<Map<String, String>> data = new ArrayList<>();

    	          // Transform all data from csv in a array of dictionary
    	          for (CSVRecord record : records) {  
    	        	  for (Entry<String, String> column : record.toMap().entrySet()) {
    	                  Map<String, String> result = new HashMap<String, String>();
    	                  String[] keys = column.getKey().split(";");
    	                  String[] values = column.getValue ().split(";");

    	                  for(int index = 0; index < keys.length; index++) {
    	                	  result.put(keys[index], values[index]);
    	                  }
    	                  data.add(result);
    	              }
    	          }

    	          this.checkAndAddNewUsersViaImport(data, request, response);
	          } catch(Exception ex) {
	             System.out.println(ex);
	          }
    	      
      		utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
      		request.setAttribute("allUsers", utilisateurs);
    		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    	} else {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/");
    		return;
    	}

    }
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        return "testImport";
    }
    
    protected void exportCSV() throws IOException {
		String filename = "EbenusCSV.csv";
	   	  PrintWriter writer = new PrintWriter(new FileWriter(filename));
	   	  StringBuilder fw = new StringBuilder();
	   	 
	   	  fw.append("Civilite");
	   	  fw.append(',');
	   	  fw.append("Prénom");
	   	  fw.append(',');
	   	  fw.append("Nom");
	   	  fw.append(',');
	   	  fw.append("Identifiant");
	   	  fw.append(',');
	   	  fw.append("Date naissance");
	   	  fw.append(',');
	   	  fw.append("Date création");
	   	  fw.append(',');
	   	  fw.append("Date modification");
	   	  fw.append(',');
	   	  fw.append("Identifiant rôle");
	   	  fw.append(',');
	   	  fw.append("Description rôle");
	   	  fw.append('\n');
	   	  
	   	  for(int i =0; i <utilisateurs.size(); i++){
	    	  fw.append(utilisateurs.get(i).getCivilite());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getPrenom());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getNom());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getIdentifiant());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getDateNaissance());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getDateCreation());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getDateModification());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getRole().getIdentifiant());
	    	  fw.append(',');
	    	  fw.append(utilisateurs.get(i).getRole().getDescription());
	    	  fw.append('\n');
	   	  }
	   	  writer.write(fw.toString());
	   	  writer.close();
	   	  System.out.println("<b>Csv file Successfully created.</b>");
	}
	
	protected void exportJSON() throws IOException {
		String jsonUser = new Gson().toJson(utilisateurs);
		FileWriter writer = new FileWriter("EbenusJSON.json");
		try {
            writer.write(jsonUser);
            writer.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
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
    	if(session.getAttribute("user") != null) {
    		return true;
    	} else
    		return false;
    }
    
    private void checkAndAddNewUsersViaImport(List<Map<String, String>> data, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	for (Map<String, String> oneData : data) {
    		List<Utilisateur> usersByIdentifiant = serviceFacade.getUtilisateurDao().findUtilisateurByIdentifiant(oneData.get("identifiant"));
    		if (usersByIdentifiant.isEmpty()) {
    			System.out.println("not user found with email " + oneData.get("identifiant"));

    			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        Date dteNaissance = null;
				try {
					dteNaissance = dateFormat.parse(oneData.get("dateNaissance"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
				System.out.println(">>>>>>>>>>" + oneData.get("civilite"));
    	        List<Role> rolesByIdentifiant = serviceFacade.getRoleDao().findRoleByIdentifiant(oneData.get("identifiantRole"));
    			Utilisateur newUserToAdd = new Utilisateur(oneData.get("civilite"), oneData.get("prenom"), oneData.get("nom"), oneData.get("identifiant"), oneData.get("password"), dteNaissance, rolesByIdentifiant.get(0));
    			newUserToAdd = serviceFacade.getUtilisateurDao().createUtilisateur(newUserToAdd);	
    		} else {
    			System.out.println("user with email " + oneData.get("identifiant") + " already exist");
    		}
    	}

    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
