package com.example;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Iterator;
import java.io.*;
  
public class FileUploadDemoServlet extends HttpServlet {
    private static final long serialVersionUID = -3208409086358916855L;
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
            {
    	
    	//security
    	System.out.println("v06");
    	System.out.println(request.getRequestURL());
    	
 
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        response.setContentType("text/html");
        String docType =
        	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
        	      "transitional//en\">\n";
        
        PrintWriter out = response.getWriter();
        
        out.println(docType +
                "<html>\n" +
                "<head><title>Upload pliku</title></head>\n" +
                "<body onload='close();'>" +
                "<h1 align=\"center\">Plik zapisywany na serwerze. Poczekaj chwile.</h1>\n"
                + "<br><center><img src='" + request.getContextPath() + "/images/loader.gif' style='width:256px;height:256px;'/></center>" 
               // "<ul>\n" +
               // "  <li><b>P - </b>: "
               // + request.getParameter("filename_1") + "\n"
               // + "<input type='button' value='Close this window' onclick='window.parent.close();'>" 
                + "</body>"
                + "<script type='text/javascript'>"
                //+ "function close(){window.open('','_parent',''); window.close();}"
                //+ "function close(){}"
                + "$(window).bind ('beforeunload',  function (zEvent) {"
                + "setTimeout ( function () {"
                + "var doClose = confirm ('Close this window?');"
                + "if (doClose) {"
                + "window.close ();"
                + "}"
                + "},"
                + "444"
                + ");"
                + " } );"
                + ""
                + ""
                + "</script>"
                + "</html>");
        
    
      
      
        
        
        //OutputStream out = response.getOutputStream();
       /* PrintWriter out = response.getWriter();
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
            alert("This will close the window");
            window.open('','_self');
            window.close();
        * 
        out.write("<html>");
       // out.write("<body onLoad='window.close();'>");
        out.write("<button>closeme</button>");
        out.write("</html>");*/
        
        
  
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
  
            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext())
                 {
                    FileItem item = (FileItem) iterator.next();
  
                    if (!item.isFormField()) 
                     {
                    	
                        String fileName = item.getName();
                        String root = getServletContext().getRealPath("/");
                        char needle = '\\';
                        char blockSecurity = '?';
                        
                        //for Internet Explorer or path with C://
                        int lastChar = 0;
                        for (int i=0; i < fileName.length(); i++)
                        {
                        	if (fileName.charAt(i) == blockSecurity)
                            {
                        		 System.out.println("Block atack - err345");
                                 return;
                            }
                        	
                            if (fileName.charAt(i) == needle)
                            {
                                 lastChar = i;
                            }
                        }
                        
                        if (lastChar != 0)
                        {
                        	fileName = fileName.substring(lastChar+1, fileName.length());
                        	System.out.println("internetExplorer shrink " + fileName);
                        }
                        
                        
                        
                        String sprPDF = fileName.substring(fileName.length()-4, fileName.length());
                        
                        System.out.println(sprPDF);
                        //jpg, jpeg, png, doc, pdf, gif
                        if ( !sprPDF.equals(".pdf") 
                        		&& !sprPDF.equals(".jpg")
                        		&& !sprPDF.equals("jpeg")
                        		&& !sprPDF.equals(".png")
                        		&& !sprPDF.equals(".doc")
                        		&& !sprPDF.equals(".gif") )
                        {
                        	System.out.println("Zapisac mo¿na tylko pliki jpg, jpeg, png, doc, pdf, gif - err234c2");
                        	return;
                        }
                         
                        //path where the file will be stored
                        //File path = new File("C:\\programs\\java_documents" + "/uploads");
                        File path = new File("C:\\temp");
                        if (!path.exists())
                        {
                            boolean status = path.mkdirs();
                        }
  
                        File uploadedFile = new File(path + "/" + fileName);
                        System.out.println("logi - KS:");
                        System.out.println(uploadedFile.getAbsolutePath() + " v09");
                        item.write(uploadedFile);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     //   out.close(); 
    }
}