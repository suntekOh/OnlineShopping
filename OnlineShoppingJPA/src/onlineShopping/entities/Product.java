package onlineShopping.entities;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(schema="ONLINESHOPPING", name = "PRODUCT") 
@NamedQueries ({	
@NamedQuery( name="findAllProducts", query="select c from Product c WHERE lower(c.title) like :keyword")
})

public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name = "ID")	
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 	
	private int id;
	
	@Column(name = "DESCRIPTION")		
	private String description;

	@Column(name = "PRODUCTCODE")		
	private String productCode;

	@Column(name = "PRICE")		
	private double price;

	@Column(name = "TITLE")		
	private String title;
	
	@Column(name = "PIC")		
	private String pic;
	
	@OneToMany(mappedBy="product",fetch=FetchType.EAGER)
	private Set<Inventory> inventories;		

	public Product() {
		super();
	}

	public Product(String description, String productCode, double price, String title, String pic) {
		super();
		this.description = description;
		this.productCode = productCode;
		this.price = price;
		this.title = title;
		this.pic = pic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public Set <Inventory> getInventories() {
		return inventories;
	}

	public void setInventories( Set<Inventory> inventories) {
		this.inventories = inventories;
	}
	
	
	public void addInventory( Inventory inventory) {
		Set<Inventory> inventoryies = getInventories();
		if ( inventoryies == null) {
			inventoryies = new HashSet<Inventory>();
		}
		inventories.add( inventory );
		setInventories( inventories);
	}
	

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgLoc() {
		return "images"+File.separator+pic;
	}	
   
}
