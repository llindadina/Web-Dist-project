package com.example.demo.specialite;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


 @CrossOrigin("http://localhost:4200")
public interface SpecialitesRepository extends JpaRepository<Specialites, Long> {
  List<Specialites> findByName(String name);
}