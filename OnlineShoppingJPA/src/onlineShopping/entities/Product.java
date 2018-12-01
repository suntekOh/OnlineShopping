package onlineShopping.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(schema="ONLINESHOPPING", name = "PRODUCT") 
@NamedQuery( name="findAllProducts", query="select c from Product c WHERE lower(c.title) like :keyword")	

public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name = "ID")		
	private int id;
	
	@Column(name = "DESCRIPTION")		
	private String description;

	@Column(name = "PRODUCTCODE")		
	private String productCode;

	@Column(name = "PRICE")		
	private int price;

	@Column(name = "TITLE")		
	private String title;
	
	@Column(name = "PIC")		
	private String pic;

	public Product() {
		super();
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

	public int getPrice() {
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
	
	public String getImgLoc() {
		return "images/"+pic;
	}	
   
}
