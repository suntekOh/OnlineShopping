package onlineShopping.tests;

import org.junit.Assert;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import onlineShopping.entities.MenuItem;
import onlineShopping.exceptions.ProductManagerException;
import onlineShopping.model.CustomerManager;



public class test4OnlineShopping {

//	 private java.sql.Connection conn;
//	 private Context ctx;
//	 
//	private static CustomerManager cm;
//	static {
//		try {
//			cm = (CustomerManager) new InitialContext()
//					.lookup("java:app/OnlineShoppingWeb/CustomerManager");
//		} catch (NamingException ne) {
//			cm = null;
//		}
//	}	 
	
	private EntityManagerFactory emf = null;
	private String EMFJndiName ="java:/OnlineShoppingEMF";	

	@Before
	public void setUp() throws Exception {

//        try {
////            ctx = null;
////            Hashtable<String,String> ht = new Hashtable<String,String>();
////            ht.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
////            ht.put(Context.PROVIDER_URL, "http://localhost:8088");
////            ctx = new InitialContext(ht);
////            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup ("java:/OnlineShoppingDS");
////
////            conn = ds.getConnection();
//       
//
//        } catch(Exception e) {
//
//        }		
				try {
					
		            Hashtable<String,String> ht = new Hashtable<String,String>();
		            ht.put(Context.INITIAL_CONTEXT_FACTORY, InitialContextFactory.class.getName());
		            ht.put(Context.PROVIDER_URL, "http://localhost:8088");
		            InitialContext ctx = new InitialContext(ht);
					
					
					emf = (EntityManagerFactory) ctx.lookup( EMFJndiName);
				} catch (NamingException ne) {
					System.out.println("Please correct persistence unit: check persistence.xml");
					ne.printStackTrace();
				} 
				
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void test4getCustomerByEmail() {
		
		EntityManager em = emf.createEntityManager();
		CustomerManager cm = new CustomerManager();
		try {
			List<MenuItem> list4Customer = cm.test4getMenuItemByType("C", em);
			List<MenuItem> list4Admin = cm.test4getMenuItemByType("A", em);			
			Assert.assertTrue(list4Customer.size() != list4Admin.size());	
		} catch (ProductManagerException e) {
	          Assert.fail(e.getMessage());
		}
		
		
		
		
//		try {
//	
//				List<Customer> aList = cm.getCustomerByEmail("ost925@gmail.com");
//	
//				int count=0;
//				for(Customer i : aList) {
//					count++;
//				}
//				Assert.assertTrue(count!=0);				
//	        } catch (Exception e) {
//	          Assert.fail(e.toString());
//	        }finally {
//	        	
//	        }
		
//			
//			int count = 0;
//			String sql = "select *  from Customer c  where c.email = ?";
//			PreparedStatement ps = conn.prepareStatement(sql); 
//			ps.setString(1, "ost925@gmail.com"); 					
//			ResultSet rs = ps.executeQuery(sql);
//			while ( rs.next() ) { 
//				count++;
//			}			
//			Assert.assertTrue(count!=0);
//	        
//	        ps.close();
//	        conn.close();
//	        } catch (SQLException e) {
//	          Assert.fail(e.getMessage());
//	        } finally {
//	            try {ctx.close();
//	            } catch (Exception e) {
//	            }
//	        }
		

	}

//	@Test
//	public void test4getMenuItemByType() {
//		try {
//			
//			
//			
//			int count4Customer = 0;
//			int count4Admin = 0;
//			
//			String sql = "select c  from MenuItem c  where c.customerType like :customerType";
//			PreparedStatement ps = conn.prepareStatement(sql); 
//			ps.setString(1, "C"); 					
//			ResultSet rs4Customer = ps.executeQuery(sql);
//			
//			while ( rs4Customer.next() ) { 
//				count4Customer++;
//			}					
//			
//			ps.setString(1, "A"); 					
//			ResultSet rs4Admin = ps.executeQuery(sql);
//			
//			while ( rs4Admin.next() ) { 
//				count4Admin++;
//			}				
//			
//			
//	
//	        Assert.assertTrue(count4Customer!=count4Admin);	        
//	        
//	        ps.close();
//	        conn.close();
//	        } catch (Exception e) {
//	            // a failure occurred
//	        } finally {
//	            try {ctx.close();
//	            } catch (Exception e) {
//	            }
//	        }
//		
//
//	}	
	
//	@Test
//	public void test4getAllInventory() {
//		try {
//			
//			
//			
//			int count = 0;
//
//			
//			String sql = "select p, coalesce(sum(q.qty),0) as qty from Product p left join p.inventories q group by p order by p";
//			PreparedStatement ps = conn.prepareStatement(sql); 
//				
//			ResultSet rs = ps.executeQuery(sql);
//			
//			while ( rs.next() ) { 
//				count++;
//			}					
//			
//			
//	
//	        Assert.assertTrue(count > 0);	        
//	        
//	        ps.close();
//	        conn.close();
//	        } catch (Exception e) {
//	        } finally {
//	            try {ctx.close();
//	            } catch (Exception e) {
//	            }
//        
//	        }
//		
//
//	}		

}
