package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.contrat.Contrat;
import com.example.demo.contrat.ContratRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class ContratWebServiceController {
	
	 @Autowired
	    Environment environment;

	    @GetMapping("")
	    public String call() {
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {

	        }
	        return "Hey ,I'm clientcontrat-service running on port " + environment.getProperty("local.server.port");
	    }

	    @GetMapping("/slow")
	    public String slow() {
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {

	        }
	        return "I'm Slow from clientcontrat-service  running on port " + environment.getProperty("local.server.port");
	    }

	
/*	@GetMapping("/hi")
	  public String hello() {
	return "Hello,je suis client-contrat-service";
	}*/
	
	@Autowired
	ContratRepository contratrepository;
	
	
	  @GetMapping("/contrats")
	  public ResponseEntity<List<Contrat>> getAllContrats() {
	    List<Contrat> contrats = new ArrayList<>();
	    
	    try {
	    	contratrepository.findAll().forEach(contrats::add);
	      
	      if (contrats.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(contrats, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/contrats/{id}")
	  public ResponseEntity<Contrat> getContratById(@PathVariable("id") long id) {
	    Optional<Contrat> contratData = contratrepository.findById(id);
	 
	    if (contratData.isPresent()) {
	      return new ResponseEntity<>(contratData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	  @PostMapping(value = "/contrats")
	  public ResponseEntity<Contrat> createOrSaveContrat(@RequestBody Contrat contrat) {
	    try {
	      Contrat _contrat = contratrepository.save(contrat);
	      return new ResponseEntity<>(_contrat, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	   
	 
	  @DeleteMapping("/contrats/{id}")
	  public ResponseEntity<HttpStatus> deleteContrat(@PathVariable("id") long id) {
	    try {
	    	contratrepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	
	/* 
	  @GetMapping(value = "contrats/{NomGarantie}")
	  public ResponseEntity<List<Contrat>> findByNomGarantie(@PathVariable String NomGarantie) {
	    try {
	      List<Contrat> contrats = contratrepository.findByNomGarantie(NomGarantie);
	 
	      if (contrats.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(contrats, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }*/
	 
	  @PutMapping("/contrats/{id}")
	  public ResponseEntity<Contrat> updateContrat(@PathVariable("id") long id, @RequestBody Contrat contrat) {
	    Optional<Contrat> contratData = contratrepository.findById(id);
	 
	    if (contratData.isPresent()) {
	      Contrat _contrat = contratData.get();
	      _contrat.setGarantie(contrat.getGarantie());
	      _contrat.setContrat(contrat.getContrat());
	    
	      return new ResponseEntity<>(contratrepository.save(contrat), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
}
