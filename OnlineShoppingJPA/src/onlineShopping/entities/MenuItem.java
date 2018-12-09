package onlineShopping.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MenuItem
 *
 */
@Entity
@Table(schema="ONLINESHOPPING", name = "MENUITEM")
@NamedQueries ({	
	@NamedQuery( name="getMenuItemByType"
			   , query="select c "
			   		+ " from MenuItem c "
			   		+ " where c.customerType like :customerType")
})
public class MenuItem implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name = "ID")		
	private int id;
	
	@Column(name = "NAME")			
	private String name;

	@Column(name = "ORD")			
	private int ord;		
	
	@Column(name = "CUSTOMERTYPE")			
	private String customerType;		
	
	@Column(name = "DESTINATIONTYPE")			
	private String destinationType;	

	@Column(name = "LOCATION")			
	private String location;			

	public MenuItem() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
   
}
