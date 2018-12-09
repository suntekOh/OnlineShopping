package onlineShopping.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Inventory
 *
 */
@Entity
@Table(schema="ONLINESHOPPING", name = "INVENTORY") 

public class Inventory implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name = "ID")		
	private int id;
	
	@Column(name = "DATE_CREATED")		
    @Temporal(TemporalType.TIMESTAMP)	
	private Date date_created;
	
	@Column(name = "QTY")		
	private int qty;

	@Column(name = "CUSTOMER_ID")		
	private int customer_id;
	
	  @ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="PRODUCT_ID")	
	 private Product product;
	
	  
	public Inventory() {
		super();
	}

	public Inventory(Date date_created, int qty, Product product, int customer_id) {
		super();
		this.date_created = date_created;
		this.qty = qty;
		this.product = product;
		this.customer_id = customer_id;

	}


	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	
   
}
