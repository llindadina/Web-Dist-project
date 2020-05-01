package com.example.demo.agent;


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

import com.example.demo.specialite.Specialites;
import com.fasterxml.jackson.annotation.JsonIgnore;



 
@Entity
@Table(name = "agents")
public class Agent  {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="Agent_Id")
  private long id;
 
 @Column(name = "Agent_Name")
  private String name;
 @Column(name = "Agent_Prenom")
 private String prenom;
  
 @Column(name = "Agent_Age")
 private int age;
 
  public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public Agent() {}
 
  public Agent(String name,String prenom,int age) {
    this.name = name;
    this.prenom=prenom;
    this.age=age;
    
  }
 
  public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
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

public Specialites getSpec() {
    return spec;
}

public void setSpec(Specialites specialites) {
    this.spec = specialites;
    specialites.getAgents().add(this);
}
 
@ManyToOne(cascade=CascadeType.ALL)

@JoinColumn(name = "Specialite_Id",referencedColumnName = "Specialite_Id")
private Specialites spec;


 
@Override
  public String toString() {
    return "Agent [id=" + id + ", name=" + name +",prenom="+prenom+"]";
  }
}