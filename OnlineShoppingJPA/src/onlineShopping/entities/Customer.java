package onlineShopping.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity
@Table(schema="ONLINESHOPPING", name = "CUSTOMER") 
@NamedQueries ({	
	@NamedQuery( name="getCustomerByEmail"
			   , query="select c "
			   		+ " from Customer c "
			   		+ " where c.email = :email")
})
public class Customer implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY) 
	@Column(name = "ID")		
	private int id;
	
	@Column(name = "EMAIL")			
	private String email;

	@Column(name = "PASSWORD")			
	private String password;
	
	@Column(name = "NICKNAME")			
	private String nickName;
	
	@Column(name = "TYPE")			
	private String type;
	

	public Customer() {
		super();
	}

	
	public Customer(String email, String password, String nickName, String type) {
		super();
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.type = type;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", password=" + password + ", nickName=" + nickName
				+ ", type=" + type + "]";
	}
   
}
