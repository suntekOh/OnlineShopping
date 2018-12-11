package onlineShopping.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import onlineShopping.entities.Customer;
import onlineShopping.entities.MenuItem;
import onlineShopping.entities.Product;
import onlineShopping.exceptions.ProductManagerException;


public class CustomerManager { 
	
    public CustomerManager() {
        super();
    }
	
	public List<Customer> getCustomerByEmail(String email) throws ProductManagerException {


		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {
			
			TypedQuery<Customer> tq4GetCustomerByEmail = em.createNamedQuery(
					"getCustomerByEmail", Customer.class);	
			tq4GetCustomerByEmail.setParameter("email", email);		
			return tq4GetCustomerByEmail.getResultList();	

		} catch ( PersistenceException pe ) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");	
		} finally{
			em.close();
		}
	}	
	
	public List<MenuItem> getMenuItemByType(String customerType) throws ProductManagerException {


		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {
			
			TypedQuery<MenuItem> tq4GetMenuItemByType = em.createNamedQuery(
					"getMenuItemByType", MenuItem.class);	
			if(customerType.equals("C")) {
				//tq4GetMenuItemByType.setParameter("customerType", customerType);		
				tq4GetMenuItemByType.setParameter("customerType", "C");						
			}else {
				tq4GetMenuItemByType.setParameter("customerType", "%");				
			}
			return tq4GetMenuItemByType.getResultList();	

		} catch ( PersistenceException pe ) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");	
		} finally{
			em.close();
		}
	}	
	
	public Customer addUser(Customer c) throws ProductManagerException {


		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {
			
			EntityTransaction et = em.getTransaction();			
			et.begin();					
			em.persist(c);
			et.commit();

		} catch ( PersistenceException pe ) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");	
		} finally{
			em.close();
		}
		
		return c;		
	}
	
	public List<MenuItem> test4getMenuItemByType(String customerType, EntityManager em) throws ProductManagerException {

		try {
			
			TypedQuery<MenuItem> tq4GetMenuItemByType = em.createNamedQuery(
					"getMenuItemByType", MenuItem.class);	
			if(customerType.equals("C")) {
				//tq4GetMenuItemByType.setParameter("customerType", customerType);		
				tq4GetMenuItemByType.setParameter("customerType", "C");						
			}else {
				tq4GetMenuItemByType.setParameter("customerType", "%");				
			}
			return tq4GetMenuItemByType.getResultList();	

		} catch ( PersistenceException pe ) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");	
		} finally{
			
		}
	}		
}
