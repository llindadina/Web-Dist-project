package com.example.demo.souscription;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.contrat.Contrat;
import com.example.demo.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "souscriptions")
	public class Souscription {
		/*@Transient
		private Customer client;*/
		
		
		//private Facture facture;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "Souscription_Id")
		private long id;
		@Column(name = "Souscription_begin")
		private Date  beginsouscription=new Date();
		@Column(name = "Souscription_end")
		private Date endsouscription=new Date();
		@Column(name = "Souscription_price")
		private double price;

		public Souscription() {}
		public Souscription(Date beginsouscription,Date endsouscription, double price){
			super();
			this.beginsouscription=beginsouscription;
			this.endsouscription=endsouscription;
			this.price=price;
			
			}

		
		public Souscription(Date beginsouscription,Date endsouscription, double price,Customer owner,Contrat cont ){
			super();
			this.beginsouscription=beginsouscription;
			this.endsouscription=endsouscription;
			this.price=price;
			this.owner=owner;
			this.cont=cont;
			}
		
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		
		
		
		@Temporal(TemporalType.DATE)
		public Date getBeginsouscription() {
			return beginsouscription;
		}
		
		public void setBeginsouscription(Date beginSouscription) {
			this.beginsouscription = beginSouscription;
		}
		
		@Temporal(TemporalType.DATE)
		public Date getEndsouscription() {
			return endsouscription;
		}
		public void setEndsouscription(Date endsouscription) {
			this.endsouscription = endsouscription;
		}
		
		
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
		public Customer getOwner() {
			return owner;
		}
		public void setOwner(Customer owner) {
			this.owner = owner;
			
		}
		public Contrat getCont() {
			return cont;
		}
		public void setCont(Contrat cont) {
			this.cont = cont;
			cont.getSouscriptions().add(this);
			}
		
		 
	
		
	    /*foreign key de Customer (owner est une FK dans Souscription)*/
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name =" Customer_Id")
	    private Customer owner; 
	    
	    
	    /*foreign key de Contrat (cont est une FK dans Souscription)*/
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "Contrat_Id")
	    private Contrat cont;
	    
		
		@Override
		public String toString() {
			return "Souscription [id=" + id + ", Prix =" + price + ", Date de debut =" + beginsouscription + ", Date de fin=" + endsouscription + "]";
		}
	}

