package onlineShopping.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
///**
import org.apache.commons.io.IOUtils;

import onlineShopping.entities.Product;
import onlineShopping.exceptions.ProductManagerException;
import onlineShopping.model.ProductManager;

@WebServlet("/manageProduct")
@MultipartConfig
public class ManageProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "images";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String responsePage="";
		try {
			
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String text4UniqueName = dateFormat.format(date);
		
	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    String orgFileName = filePart.getSubmittedFileName().trim();
	    String fileName = text4UniqueName+"_"+orgFileName;
	    InputStream fileContent4Forever = filePart.getInputStream();
	    
	    //Save an image in a disk
        File uploads = new File(System.getProperty("jboss.server.data.dir"), "uploads");
       
        File file = new File(uploads, fileName);

        try (InputStream input = fileContent4Forever) {
            Files.copy(input, file.toPath());
        }   
        
	    InputStream fileContentTemp = filePart.getInputStream();        
        //Save an image in a war file
        //When it is redeployed, this image file is removed.
        // constructs the folder where uploaded file will be stored
        String fileUploadLocation = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY + File.separator + fileName;	
                
        
        File fileToUpload = new File(fileUploadLocation);
        FileUtils.copyInputStreamToFile(fileContentTemp, fileToUpload);

 
        String productCode = request.getParameter("productCode").trim();
        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        String strPrice = request.getParameter("price").trim();
        String pic = fileName;
        
		if(productCode.equals("") || title.equals("") || description.equals("") ||
				 strPrice.equals("") || orgFileName.equals("")) {
			request.setAttribute("message", "you should input all fields to register.");					
			throw new NullPointerException("");
		}	     
		
        double price = Double.parseDouble(request.getParameter("price").trim());   		
        
        ProductManager pm = new ProductManager();
        pm.addProduct(new Product(description, productCode, price, title, pic));
        
        responsePage = "done.jsp";            
        request.setAttribute("from", "manageProduct");    
//
//            // displays done.jsp page after upload finished
//            getServletContext().getRequestDispatcher("/done.jsp").forward(
//                    request, response);
		}catch(NumberFormatException e) {        
			
			request.setAttribute("message", "You must enter the numeric value for the Price field.");
			responsePage ="manageProduct.jsp";		
			
		}catch(NullPointerException e) {        
		
			responsePage ="manageProduct.jsp";			

		}catch(ProductManagerException e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			responsePage ="errorPage.jsp";
        }finally {
			request.getRequestDispatcher(responsePage).forward(request, response);        	
        	
        }
	}

}
