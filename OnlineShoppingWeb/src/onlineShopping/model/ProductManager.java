package onlineShopping.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import onlineShopping.entities.Customer;
import onlineShopping.entities.Inventory;
import onlineShopping.entities.Product;
import onlineShopping.exceptions.ProductManagerException;

public class ProductManager {

	public ProductManager() {
		super();
	}

	public List<Product> findAllProducts(String keyword) throws ProductManagerException {

		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {

			TypedQuery<Product> tq = em.createNamedQuery("findAllProducts", Product.class);
			if (keyword == null) {
				keyword = "";
			}
			tq.setParameter("keyword", "%" + keyword.toLowerCase() + "%");

			List<Product> products = tq.getResultList();

			return products;

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
	}
	
	public Map<Product, Long> findAllProducts2(String keyword) throws ProductManagerException {
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		Map<Product, Long> resultMap = null;
		try {

			TypedQuery<Object[]> tq = em.createQuery(
					"select p, coalesce(sum(q.qty),0) as qty "+
			        "from Product p left join p.inventories q "+
			        "WHERE lower(p.title) like :keyword "+
			        "group by p order by p", Object[].class);
			
			if (keyword == null) {
				keyword = "";
			}
			tq.setParameter("keyword", "%" + keyword.toLowerCase() + "%");			

			List<Object[]> resultList = tq.getResultList();
			resultMap = new HashMap<Product, Long>(resultList.size());

			for (Object[] result : resultList) {
				resultMap.put((Product) result[0], (Long) result[1]);				
			}

			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
		return resultMap;
	}	

	public Product findProductById(int productId) throws ProductManagerException {

		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {

			Product aProduct = em.find(Product.class, productId);

			return aProduct;

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
	}
	
	public Map<Product, Long> findProductById2(int productId) throws ProductManagerException {

		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		Map<Product, Long> resultMap = null;
		try {

			TypedQuery<Object[]> tq = em.createQuery(
					"select p, coalesce(sum(q.qty),0) as qty "+
			        "from Product p left join p.inventories q "+
			        "WHERE p.id = :productId "+
			        "group by p order by p", Object[].class);

			tq.setParameter("productId", productId);			

			List<Object[]> resultList = tq.getResultList();
			resultMap = new HashMap<Product, Long>(resultList.size());

			for (Object[] result : resultList) {
				resultMap.put((Product) result[0], (Long) result[1]);				
			}

			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
		return resultMap;
	}	

	public Product addProduct(Product p) throws ProductManagerException {

		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(p);
			et.commit();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}

		return p;
	}

	public Map<Product, Long> getAllInventory() throws ProductManagerException {
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		Map<Product, Long> resultMap = null;
		try {

			TypedQuery<Object[]> q = em.createQuery(
					"select p, coalesce(sum(q.qty),0) as qty from Product p left join p.inventories q group by p order by p", Object[].class);

			List<Object[]> resultList = q.getResultList();
			resultMap = new HashMap<Product, Long>(resultList.size());

			for (Object[] result : resultList)
				resultMap.put((Product) result[0], (Long) result[1]);
			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
		return resultMap;
	}
	
	public void addInventory(Inventory i) throws ProductManagerException {
		
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(i);
			et.commit();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
		
	}
	
	public void addInventory2(Set<Inventory> inventories) throws ProductManagerException {
		
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction et = em.getTransaction();
			et.begin();
			for(Inventory i: inventories) {
				Product p = em.find(Product.class, i.getProduct().getId());				
				i.setProduct(p);
				p.addInventory(i);
				em.persist(i);
			}
			et.commit();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
		
	}	
	
	public void addNegativeInventory(Inventory i) throws ProductManagerException, NullPointerException {
		
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction et = em.getTransaction();
			et.begin();
			
			TypedQuery<Object[]> tq = em.createQuery(
					"select p, coalesce(sum(q.qty),0) as qty "+
			        "from Product p left join p.inventories q "+
			        "WHERE p.id = :productId "+
			        "group by p order by p", Object[].class);

			tq.setParameter("productId", i.getProduct().getId());			

			List<Object[]> resultList = tq.getResultList();
			Long stock=0L;
			for (Object[] result : resultList) {
				stock = (Long) result[1];
				break;
			}	
			
			if(stock < Math.abs(i.getQty())) {
				throw new NullPointerException("");
			}
			
			

			Product p = em.find(Product.class, i.getProduct().getId());				
			i.setProduct(p);
			p.addInventory(i);
			em.persist(i);

			et.commit();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new ProductManagerException("There is an unexpected error. Please try it again few minutes later.");
		} finally {
			em.close();
		}
		
	}	
		
	
	

}
