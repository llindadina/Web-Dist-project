package com.example.demo;



import java.util.ArrayList;



import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
import org.springframework.web.client.RestTemplate;

import com.example.demo.specialite.Specialites;
import com.example.demo.specialite.SpecialitesRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/agt")
public class SpecialitesController {
	/*
@Autowired
	private DiscoveryClient discoveryClient;
	
	
	
	@HystrixCommand(fallbackMethod = "defaultMessage")
	@GetMapping("/")
	public String hello() {
		List<ServiceInstance> instances = discoveryClient.getInstances("clientcontrat-service");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://clientcontrat-service/api";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return "Je suis agent-service. je demande au clientcontrat service de me repondre, et il me repond: " + response.getBody();
	}
	
	public String defaultMessage() {
		return "si je dis Salut c'est que je suis innactif !";
	}

	
	*/
	
  @Autowired
  SpecialitesRepository specialitesRepository;
 
  @GetMapping("/specialites")
  public ResponseEntity<List<Specialites>> getAllSpecialites() {
    List<Specialites> specialites = new ArrayList<>();
    
    try {
    	specialitesRepository.findAll().forEach(specialites::add);
      
      if (specialites.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(specialites, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/specialites/{id}")
  public ResponseEntity<Specialites> getSpecialitesById(@PathVariable("id") long id) {
    Optional<Specialites> specialitesData = specialitesRepository.findById(id);
 
    if (specialitesData.isPresent()) {
      return new ResponseEntity<>(specialitesData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 
  @PostMapping(value = "/specialites")
  public ResponseEntity<Specialites> postCustomer(@RequestBody Specialites specialites) {
    try {
    	Specialites _specialites= specialitesRepository.save(new Specialites(specialites.getName()));
      return new ResponseEntity<>(_specialites, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("/specialites/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
    try {
    	specialitesRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("/specialites")
  public ResponseEntity<HttpStatus> deleteAllSpecialites() {
    try {
    	specialitesRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
 
  }
 
  @GetMapping(value = "specialites/name/{name}")
  public ResponseEntity<List<Specialites>> findByAge(@PathVariable String name) {
    try {
      List<Specialites> specialites = specialitesRepository.findByName(name);
 
      if (specialites.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(specialites, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @PutMapping("/specialites/{id}")
  public ResponseEntity<Specialites> updateCustomer(@PathVariable("id") long id, @RequestBody Specialites specialites) {
    Optional<Specialites> specialitesData = specialitesRepository.findById(id);
 
    if (specialitesData.isPresent()) {
    	Specialites _specialites = specialitesData.get();
      _specialites.setName(specialites.getName());
   
    
      return new ResponseEntity<>(specialitesRepository.save(_specialites), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}