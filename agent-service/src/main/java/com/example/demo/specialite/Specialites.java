package com.example.demo.specialite;


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

import com.example.demo.agent.Agent;

import com.fasterxml.jackson.annotation.JsonIgnore;



 
@Entity
@Table(name = "specialites")
public class Specialites {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="Specialite_Id")
  private long id;
 
 @Column(name = "Specialite_Name")
  private String name;

 @Transient
 private List<Agent> agents=new ArrayList<Agent>();

  public Specialites() {}
 
  public Specialites(String name) {
    this.name = name;
    
  }
 
  public long getId() {
    return id;
  }
 
  public void setName(String name) {
    this.name = name;
  }
 
  public String getName() {
    return this.name;
  }
 

public void setId(long id) {
	this.id = id;
}


@OneToMany(mappedBy="spec",cascade= CascadeType.ALL,fetch = FetchType.EAGER)
@JsonIgnore
public List<Agent> getAgents() {
    return agents;
}


public void setAgents( List<Agent> agents) {
    this.agents = agents;
    for (Agent agent : agents) {
        agent.setSpec(this);
    }
}



@Override
  public String toString() {
    return "Agent[id=" + id + ", name=" + name + "]";
  }


}