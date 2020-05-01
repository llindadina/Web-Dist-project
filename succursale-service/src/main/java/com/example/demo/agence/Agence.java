package com.example.demo.agence;


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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.succursale.Succursale;
import com.fasterxml.jackson.annotation.JsonIgnore;



 
@Entity
@Table(name = "agences")
public class Agence  {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="Agence_Id")
  private long id;
 
 @Column(name = "Agent_Name")
  private String name;
 
  public Agence() {}

 

 
  public Agence(String name) {
    this.name = name;
    
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

public Succursale getSucc() {
    return succ;
}

public void setSucc(Succursale succursale) {
    this.succ = succursale;
    succursale.getAgences().add(this);
}
 
@ManyToOne(cascade=CascadeType.ALL)

@JoinColumn(name = "Succursale_Id",referencedColumnName = "Succursale_Id")
private Succursale succ;


 
@Override
  public String toString() {
    return "Agent [id=" + id + ", name=" + name + "]";
  }
}