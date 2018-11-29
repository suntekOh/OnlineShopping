package onlineShopping.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import onlineShopping.entities.Product;


public class ProductManager { 
	
    public ProductManager() {
        super();
    }
    

	public List<Product> findAllProducts(String keyword) {


		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF(); 
		EntityManager em = emf.createEntityManager();
		try {
			

			
					TypedQuery<Product> tq = em.createNamedQuery("findAllProducts"
							,	Product.class);
			
					tq.setParameter("keyword", "%"+keyword.toLowerCase()+"%");
					
					List<Product> products = tq.getResultList();
					
//					String result="";
//					for ( Product p : products) {
//						result += "["+p.getTitle()+"]";
//					}
//					
//					System.out.println(result);
//		
					return products;


		} catch ( PersistenceException pe ) {
			pe.printStackTrace();
			return null;
		} finally{
			em.close();
		}
	}
}
