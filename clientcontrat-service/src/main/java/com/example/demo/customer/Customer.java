package com.example.demo.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.souscription.Souscription;
import com.fasterxml.jackson.annotation.JsonIgnore;



 
@Entity
@Table(name = "customers")
public class Customer {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="Customer_Id")
  private long id;
 
 @Column(name = "Customer_Name")
  private String name;
 @Column(name = "Customer_Prenom")
  private String prenom;
 @Column(name="Customer_Email")
  private String email;
 @Column(name="Customer_nbr_souscriptions")
  private int achat;
@Column(name = "Customer_Age")
  private int age;
  

/* @Transient
 private List<Facture>factures=new ArrayList<Facture>();*/
 @Transient
private List<Souscription>souscriptions=new ArrayList<Souscription>();
 
  public Customer() {}
 
  public Customer(String name, String prenom,String email,int age,int achat) {
    this.name = name;
    this.prenom=prenom;
    this.email=email;
    this.age = age;
    this.achat=achat;
  }
 
  public long getId() {
    return id;
  }
  public void setId(long id) {
		this.id = id;
	}
 
  public void setName(String name) {
    this.name = name;
  }
 
  public String getName() {
    return this.name;
  }
 
  public void setAge(int age) {
    this.age = age;
  }
  public int getAge() {
	    return this.age;
	  }
  
  public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}


public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


public int getAchat() {
	return achat;
}

public void setAchat(int achat) {
	this.achat = achat;
}
 
/*	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	public List<Facture> getFacture() {
		return factures;
	}

	public void setFacture(List<Facture> factures) {
		this.factures = factures;
	}
	*/
	
@OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
@JsonIgnore
public List<Souscription> getSouscriptions() {
	return souscriptions;
}

public void setSouscriptions(List<Souscription> souscriptions) {
	this.souscriptions = souscriptions;
	for (Souscription sous : souscriptions)
	{
		
		sous.setOwner(this);
	}
}

  
@Override
  public String toString() {
    return "Customer [id=" + id + ", name=" + name + ",prenom="+ prenom+", age=" + age + ",email="+email+ ",achat="+achat +"]";
  }
}