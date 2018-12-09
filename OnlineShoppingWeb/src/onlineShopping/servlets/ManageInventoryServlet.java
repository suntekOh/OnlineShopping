package onlineShopping.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * Servlet implementation class ManageInventoryServlet
 */
@WebServlet("/manageInventory")
public class ManageInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageInventoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String responsePage = "manageInventory.jsp";

		ProductManager pm = new ProductManager();

		try {
			request.setAttribute("inventory", pm.getAllInventory());

		} catch (ProductManagerException e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			responsePage = "errorPage.jsp";

		} finally {
			request.getRequestDispatcher(responsePage).forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductManager pm = new ProductManager();

		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("customer");

		String responsePage = "";

		try {
			List<Product> products = pm.findAllProducts("");

//			Enumeration<String> parameterNames = request.getParameterNames();
//			while (parameterNames.hasMoreElements()) {
//			    String key = (String) parameterNames.nextElement();
//			    String val = request.getParameter(key);
//			    System.out.println("A= <"+key+"> Value<"+val+">");
//			}			

			Set<Inventory> aSet = new HashSet<Inventory>();
			
			for (Product p : products) {
				
				String strValue = request.getParameter(Integer.toString(p.getId()));
				int quantity=0;
				if(strValue!=null && strValue.trim().length() > 0){
					quantity = Integer.parseInt(strValue);
				}

				if (quantity > 0) {
					aSet.add(new Inventory(new Date(), quantity, p,c.getId()));
				}
			}	
			pm.addInventory2(aSet);
			
//			for (Product p : products) {
//			
//				String strValue = request.getParameter(Integer.toString(p.getId()));
//				int quantity=0;
//				if(strValue!=null && strValue.trim().length() > 0){
//					quantity = Integer.parseInt(strValue);
//				}
//
//				if (quantity > 0) {
//					pm.addInventory(new Inventory(new Date(), quantity, p,c.getId()));
//				}
//				
//			
//			}

			responsePage = "done.jsp";
			request.setAttribute("from", "manageInventory");
		} catch (ProductManagerException e) {
			e.printStackTrace();
			responsePage = "errorPage.jsp";

		} finally {
			request.getRequestDispatcher(responsePage).forward(request, response);
		}
	}

}
