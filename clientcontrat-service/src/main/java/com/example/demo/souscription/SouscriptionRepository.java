package com.example.demo.souscription;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("http://localhost:4200")
public interface SouscriptionRepository extends JpaRepository<Souscription,Long> {

	//List<Souscription> findByFactureId(Long factureId);
	
}
