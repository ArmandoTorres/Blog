package com.blog.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Armando
 */
public class DownloadFilesServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String filename = (String) request.getParameter("fileName");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                           "attachement;filename="+filename);
        
        File file = new File( getServletContext().getRealPath("")+File.separator + UPLOAD_DIRECTORY + 
                                                        File.separator + request.getParameter("autor")+ File.separator + filename);
        
        FileInputStream fileIn  = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        
        byte[] outputByte = new byte[(int)file.length()];
        //copy binary contect to output stream
        while(fileIn.read(outputByte,0,(int)file.length()) != -1){
            out.write(outputByte, 0,(int)file.length());
        }
        out.flush();
    }
}