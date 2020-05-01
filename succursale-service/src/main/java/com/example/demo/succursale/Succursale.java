package com.example.demo.succursale;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.agence.Agence;
import com.fasterxml.jackson.annotation.JsonIgnore;



 
@Entity
@Table(name = "succursales")
public class Succursale {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="Succursale_Id")
  private long id;
 
 @Column(name = "Succursale_Reference")
  private String reference;

 @Transient
 private List<Agence> agences=new ArrayList<Agence>();

  public Succursale() {}
 
  public Succursale(String reference) {
    this.reference= reference;
    
  }
 
  public long getId() {
    return id;
  }
 
  public void setReference(String reference) {
    this.reference= reference;
  }
 
  public String getReference() {
    return this.reference;
  }
 

public void setId(long id) {
	this.id = id;
}


@OneToMany(mappedBy="succ",cascade= CascadeType.ALL,fetch = FetchType.EAGER)
@JsonIgnore
public List<Agence> getAgences() {
    return agences;
}


public void setAgents( List<Agence> agences) {
    this.agences = agences;
    for (Agence agence : agences) {
        agence.setSucc(this);
    }
}



@Override
  public String toString() {
    return "Agent[id=" + id + ", reference=" + reference + "]";
  }


}