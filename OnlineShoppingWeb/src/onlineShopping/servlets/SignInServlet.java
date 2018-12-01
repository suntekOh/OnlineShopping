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
		
		TypedQuery<Customer> tq4GetCustomerByEmail = em.createNamedQuery(
				"getCustomerByEmail", Customer.class);	
		tq4GetCustomerByEmail.setParameter("email", email);		
		List<Customer> customers = tq4GetCustomerByEmail.getResultList();		
						
		try {
			
			if(operType.equals("Sign in")) {
				
				if(email.equals("") || password.equals("")) {
					request.setAttribute("message", "you should input all fields to do login.");					
					throw new NumberFormatException("");
				}		
				
				if(customers.size()==0) {
					request.setAttribute("message", "There is no such user.");		
					responsePage ="signin.jsp";			
					throw new NumberFormatException();
					
				}else {
					for(Customer i:customers) {
						if(i.getPassword().equals(password)) {
							responsePage="welcome.jsp";
							

							session.setAttribute("customer", i);								
						}else {
							request.setAttribute("message", "you entered a wrong password.");		
							responsePage ="signin.jsp";					
							throw new NumberFormatException();							
						}
						break;
					}
				}
				

			}else if(operType.equals("register")){
				if(customers.size() > 0) {
					request.setAttribute("message", "There is already a user using the same email address.");		
					responsePage ="register.jsp";			
					throw new NumberFormatException();	
				}else {
					String type ="C";
					
					EntityTransaction et = em.getTransaction();
					et.begin();					
					em.persist(new Customer(email, password, nickName,type));
					et.commit();
					
					Customer c=null;
					for(Customer i:tq4GetCustomerByEmail.getResultList()) {
						c=i;
						break;
					}
					
					session.setAttribute("customer", c);		
					
					responsePage="welcome.jsp";
				}
				
			}

			
		}catch(NumberFormatException e){	
			
		}catch (PersistenceException pe ) {
			pe.printStackTrace();
		
		}finally {
			request.getRequestDispatcher(responsePage).forward(request,
					response);			
		}

	}

}
