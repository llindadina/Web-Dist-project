package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


import com.example.demo.contrat.ContratRepository;
import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.souscription.Souscription;
import com.example.demo.souscription.SouscriptionRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SouscriptionWebServiceController {
	@Autowired
	SouscriptionRepository souscriptionRepository;
    @Autowired
    CustomerRepository customerrpository;
    @Autowired
    ContratRepository contratpository;
	
    //soussription
	 
	 @GetMapping("/souscriptions")
	  public ResponseEntity<List<Souscription>> getAllSouscriptions() {
	    List<Souscription> sous = new ArrayList<>();
	    
	    try {
	    	souscriptionRepository.findAll().forEach(sous::add);
	      
	      if (sous.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(sous, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/souscriptions/{id}")
	  public ResponseEntity<Souscription> getSouscriptionById(@PathVariable("id") long id) {
	    Optional<Souscription> sousData = souscriptionRepository.findById(id);
	 
	    if (sousData.isPresent()) {
	      return new ResponseEntity<>(sousData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  //
	/*  @PostMapping(value = "/souscriptions")
	  public ResponseEntity<Souscription> postSouscription(@RequestBody Souscription sous) {
	    try {
	      Souscription _sous = souscriptionRepository.save(new Souscription(sous.getBeginsouscription(),sous.getEndsouscription() ,sous.getPrice()));
	      return new ResponseEntity<>(_sous, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }*/
	 
	  @PostMapping(value = "/souscriptions")
	  public ResponseEntity<Souscription> postSouscription(@RequestBody Souscription sous) {
	    try {
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date date1 = dateFormat.parse("2019-11-09");
		    
		    Date date2 = dateFormat.parse("2019-01-09");
	      Souscription _sous = souscriptionRepository.save(new Souscription(date1,date2 ,sous.getPrice(),sous.getOwner(),sous.getCont()));
	      return new ResponseEntity<>(_sous, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
/*	  @DeleteMapping("/souscriptions/{id}")
	  public ResponseEntity<HttpStatus> deleteSouscription(@PathVariable("id") long id) {
	    try {
	    	souscriptionRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 */

	  @PutMapping("/souscriptions/{id}")
	  public ResponseEntity<Souscription> updateSouscription(@PathVariable("id") long id, @RequestBody Souscription sous) {
	    Optional<Souscription> sousData = souscriptionRepository.findById(id);
	 
	    if (sousData.isPresent()) {
	      Souscription _sous = sousData.get();
	      _sous.setBeginsouscription(sous.getBeginsouscription());
	      _sous.setEndsouscription(sous.getEndsouscription());
	    
	      return new ResponseEntity<>(souscriptionRepository.save(_sous), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }


    @DeleteMapping("souscriptions/{id}")
     void deleteSouscription(@PathVariable Long id) {
 		souscriptionRepository.deleteById(id);}
     
    
    
}