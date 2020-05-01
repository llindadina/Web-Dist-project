package com.example.demo.agence;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;


 @CrossOrigin("http://localhost:4200")
 
public interface AgenceRepository extends JpaRepository<Agence, Long> {
  List<Agence> findByName(String Name);

}

