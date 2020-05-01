package com.example.demo;



import java.util.ArrayList;



import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.agence.Agence;
import com.example.demo.agence.AgenceRepository;





@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/agc")
public class AgenceController {
 
  @Autowired
  AgenceRepository agenceRepository;
 
  @GetMapping("/agences")
  public ResponseEntity<List<Agence>> getAllAgences() {
    List<Agence> agences = new ArrayList<>();
    
    try {
    	agenceRepository.findAll().forEach(agences::add);
      
      if (agences.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(agences, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/agences/{id}")
  public ResponseEntity<Agence> getAgenceId(@PathVariable("id") long id) {
    Optional<Agence> agenceData = agenceRepository.findById(id);
 
    if (agenceData.isPresent()) {
      return new ResponseEntity<>(agenceData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 
  @PostMapping(value = "/agences")
  public ResponseEntity<Agence> postAgence(@RequestBody Agence agence) {
    try {
    	Agence _agence= agenceRepository.save(new Agence(agence.getName()));
      return new ResponseEntity<>(_agence, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("/agences/{id}")
  public ResponseEntity<HttpStatus> deleteAgence(@PathVariable("id") long id) {
    try {
       agenceRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("agences")
  public ResponseEntity<HttpStatus> deleteAllAgents() {
    try {
    	agenceRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
 
  }
 
  @GetMapping(value = "agences/name/{name}")
  public ResponseEntity<List<Agence>> findByName(@PathVariable String name) {
    try {
      List<Agence> agents = agenceRepository.findByName(name);
 
      if (agents.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(agents, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @PutMapping("/agences/{id}")
  public ResponseEntity<Agence> updateAgence(@PathVariable("id") long id, @RequestBody Agence agence) {
	
	  Optional<Agence> agenceData = agenceRepository.findById(id);
 

	    if (agenceData.isPresent()) {
	    	Agence _agence = agenceData.get();
	      _agence.setName(agence.getName());
	    
    
      return new ResponseEntity<>(agenceRepository.save(_agence), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}