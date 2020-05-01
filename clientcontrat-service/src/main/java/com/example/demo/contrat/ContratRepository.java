package com.example.demo.contrat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")


public interface ContratRepository extends JpaRepository<Contrat,Long>{
	
	//List<Contrat> findByNomcontrat(String Nomcontrat);
}


