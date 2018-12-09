package onlineShopping.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineShopping.entities.Customer;
import onlineShopping.entities.Inventory;
import onlineShopping.entities.Product;
import onlineShopping.exceptions.ProductManagerException;
import onlineShopping.model.ProductManager;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId").trim());
		
		
		String responsePage ="";
		
		try {

				ProductManager pm = new ProductManager();

				request.setAttribute("item", pm.findProductById2(productId));
				responsePage ="productDetail.jsp";
			
		} catch (ProductManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			responsePage ="errorPage.jsp";
		} finally{
			request.getRequestDispatcher(responsePage).forward(request,
					response);				
		}			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		
//		Enumeration<String> parameterNames = request.getParameterNames();
//		while (parameterNames.hasMoreElements()) {
//		    String key = (String) parameterNames.nextElement();
//		    String val = request.getParameter(key);
//		    System.out.println("A= <"+key+"> Value<"+val+">");
//		}				
		int productId = Integer.parseInt(request.getParameter("productId"));
		String responsePage="";
		HttpSession session = request.getSession();		
		Customer c = (Customer)session.getAttribute("customer");
		ProductManager pm = new ProductManager();			
		
		try {
			request.setAttribute("item", pm.findProductById2(productId));				
			if(c==null) {
				request.setAttribute("message", "You must sign in");	
				throw new NumberFormatException("");			
				}
			
			
			if(request.getParameter("numberOfItems")==null||request.getParameter("numberOfItems").trim().length()==0) {
				request.setAttribute("message", "You must enter quantity to checkout");				
				throw new NumberFormatException("");
				
			}else {
				int quantity = Integer.parseInt(request.getParameter("numberOfItems"));		
				
				if (quantity > 0) {
					
					pm.addNegativeInventory(new Inventory(new Date(), quantity*-1, pm.findProductById(productId),c.getId()));
					responsePage = "done.jsp";
					request.setAttribute("from", "productDetail");					
				}				
			}
			
		}catch(NullPointerException e) {		
			responsePage="productDetail.jsp";
			request.setAttribute("message", "Requested quantity is more than stock");			
		
			
		}catch(NumberFormatException e) {

			responsePage="productDetail.jsp";
			
		} catch (ProductManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "An unexpected error happened.");
			responsePage="errorPage.jsp";
		}finally {
			request.getRequestDispatcher(responsePage).forward(request, response);       			
			
		}

		
		
	}

}
