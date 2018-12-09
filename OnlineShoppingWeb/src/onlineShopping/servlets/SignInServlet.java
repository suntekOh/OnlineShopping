package onlineShopping.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineShopping.entities.Customer;
import onlineShopping.entities.Product;
import onlineShopping.exceptions.ProductManagerException;
import onlineShopping.model.CustomerManager;
import onlineShopping.model.EMFSupplier;
import onlineShopping.model.ProductManager;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
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
		HttpSession session = request.getSession();				
		String operType = request.getParameter("oper").trim();
		
		String responsePage ="";
		
		String email = request.getParameter("email").trim();	
		String password = request.getParameter("password").trim();	
		String nickName = request.getParameter("nickName");			
		if(nickName!=null) {
			nickName = nickName.trim();
		}
	
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();

		responsePage="";	
		
		try {
		CustomerManager cm = new CustomerManager();
		List<Customer> customers = cm.getCustomerByEmail(email);
						

			
			if(operType.equals("Sign in")) {
				
				if(email.equals("") || password.equals("")) {
					request.setAttribute("message", "you should input all fields to do login.");					
					throw new NumberFormatException("");
				}		
				
				if(customers.size()==0) {
					request.setAttribute("message", "There is no such user.");		
					throw new NumberFormatException();
					
				}else {
					for(Customer i:customers) {
						if(i.getPassword().equals(password)) {
							responsePage="searchProduct.jsp";
							
							session.setAttribute("menu", cm.getMenuItemByType(i.getType()));							
							session.setAttribute("customer", i);								
						}else {
							request.setAttribute("message", "you entered a wrong password.");		
							throw new NumberFormatException();							
						}
						break;
					}
				}
				

			}else if(operType.equals("register")){
				if(customers.size() > 0) {
					request.setAttribute("message", "There is already a user using the same email address.");		
					throw new NullPointerException();	
				}else {
					String type ="C";
					
					cm.addUser(new Customer(email, password, nickName,type));

					Customer c=null;
					for(Customer i:cm.getCustomerByEmail(email)) {
						c=i;
						break;
					}
				
					session.setAttribute("menu", cm.getMenuItemByType(c.getType()));
					session.setAttribute("customer", c);		
					
					responsePage="searchProduct.jsp";
				}
				
			}

			
		}catch(NumberFormatException e){	
			e.printStackTrace();				
			responsePage ="signin.jsp";		
			
		}catch(NullPointerException e){
			e.printStackTrace();					
			responsePage ="register.jsp";				
			
		}catch(ProductManagerException e){
			e.printStackTrace();			
			request.setAttribute("message", e.getMessage());
			responsePage="errorPage.jsp";	
		
		}finally {
			request.getRequestDispatcher(responsePage).forward(request,
					response);			
		}

	}

}
