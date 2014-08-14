package com.blog.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Armando
 */
public class UploadFile {
    
    // UPLOAD SETTINGS
    private static final int MEMORY_THRESHOLD  = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE     = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE  = 1024 * 1024 * 50; // 50MB
    
    public static String uploadFile(HttpServletRequest request, String uploadPath, FileItem item){
        
        String fileName = "";
        
        if (ServletFileUpload.isMultipartContent(request)){
            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //sets memory threshold - beyond which files are stored in disk
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);

            // sets maximum size of upload file
            upload.setFileSizeMax(MAX_FILE_SIZE);

            //sets maximun size of request (include file + form data)
            upload.setSizeMax(MAX_REQUEST_SIZE);

            //create the directory is it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            
            String ext = FilenameUtils.getExtension(new File(item.getName()).getName());
            fileName = new SimpleDateFormat("dd-MM-yyyy hhmmss").format(new Date())+"."+ext;
            String filePath = uploadPath + File.separator + fileName;
            File storeFile = new File(filePath);
            
            try {
                //save the file on disk
                item.write(storeFile);
            } catch (Exception ex) {
                Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fileName;
    }
}
