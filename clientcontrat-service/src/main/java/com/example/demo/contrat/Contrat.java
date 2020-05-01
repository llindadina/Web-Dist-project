package com.example.demo.contrat;

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
@Table(name = "contrats")
public class Contrat {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Contrat_Id")
	private long id ;
	
	 
	@Column(name = "Contrat_Garantie")
	private String garantie;
	
	@Column(name = "Contrat_NomContrat")
	private String contrat;
	
	
	@Transient
	private List<Souscription>souscriptions=new ArrayList<Souscription>();
	
	public Contrat() {}
	
	public Contrat(String NomContrat,String NomGarantie ) {
		this.contrat= NomContrat;
		this.garantie = NomGarantie;
		
		}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getGarantie() {
		return garantie;
	}

	public void setGarantie(String garantie) {
		this.garantie = garantie;
	}

	public String getContrat() {
		return contrat;
	}

	public void setContrat(String contrat) {
		this.contrat = contrat;
	}

	/**mappe**/
	/*pour la foreign key placée dans souscription */

	/* @Transient
	 @OneToOne(mappedBy = "cont")
	    private Souscription souscription;
	*/


	@OneToMany(mappedBy="cont", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	
	@JsonIgnore
	public List<Souscription> getSouscriptions() {
		return souscriptions;
	}
	public void setSouscriptions(List<Souscription> souscriptions) {
		this.souscriptions = souscriptions;
		for (Souscription sous : souscriptions)
		{
			
			sous.setCont(this);
		}
	}
	
	
	
	@Override
	  public String toString() {
	    return "Contrat [id=" + id + ", Garantie=" + garantie + ",Contrat="+ contrat +"]";
	  }


	
	
}
