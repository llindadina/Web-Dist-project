package com.example.demo.agent;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;


 @CrossOrigin("http://localhost:4200")
 
public interface AgentRepository extends JpaRepository<Agent, Long> {
  List<Agent> findByName(String Name);
  List<Agent> findByAge(int age);
}

