package onlineShopping.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineShopping.entities.Product;
import onlineShopping.exceptions.ProductManagerException;
import onlineShopping.model.ProductManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/processMaster")
public class ProcessMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessMasterServlet() {
		super();
	}

	// store names for ballot form in application scope
	public void init() {
//		CandidateManager cm = new CandidateManager();
//		List<Candidate> candidates = null;
//			candidates = cm.getCandidates();
//			getServletContext().setAttribute("candidates", candidates); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		HttpSession session = request.getSession();
		session.invalidate();
		String responsePage = "bye.jsp";


		request.getRequestDispatcher(responsePage).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String operType = request.getParameter("oper").trim();
		String keyword = request.getParameter("keyword").trim();

		String responsePage = "";

		try {

			if (operType.equals("SEARCH")) {
				ProductManager pm = new ProductManager();

				request.setAttribute("products", pm.findAllProducts2(keyword));
				request.setAttribute("searchYN", "Y");
				responsePage = "searchProduct.jsp";
			}

		} catch (ProductManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			responsePage = "errorPage.jsp";
		} finally {
			request.getRequestDispatcher(responsePage).forward(request, response);
		}

		return;
	}
}
