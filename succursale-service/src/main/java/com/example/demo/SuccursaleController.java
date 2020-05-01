package com.example.demo;



import java.util.ArrayList;



import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

import com.example.demo.succursale.Succursale;
import com.example.demo.succursale.SuccursaleRepository;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.env.Environment;
import org.springframework.cloud.client.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/agc")
public class SuccursaleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SuccursaleController.class);

	@Autowired
	Environment environment;
	@Autowired
	RestTemplate template;

	@GetMapping("")
	public String call() {
		String url = "http://clientcontrat-service/api";
		String callmeResponse = template.getForObject(url, String.class);
		LOGGER.info("Response: {}", callmeResponse);
		return "I'm the caller "+"Succursale-service"+" running on port " + environment.getProperty("local.server.port")
				+ " calling clientcontrat-service ,and it answers me with :-> " + callmeResponse;
	}

	@GetMapping("/slow")
	public String slow() {
		String url = "http://clientcontrat-service/api/slow";
		String callmeResponse = template.getForObject(url, String.class);
		LOGGER.info("Response: {}", callmeResponse);
		return "I'm the caller "+"Succursale-service"+" running on port " + environment.getProperty("local.server.port")
				+" calling clientcontrat-service ,and it answers me with :-> " + callmeResponse;
	}
	/*	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@GetMapping("/hi")
	
	public String hello() {
		ServiceInstance serviceInstance = loadBalancer.choose("clientcontrat-service");
		System.out.println(serviceInstance.getUri());
		String microservice1Address = serviceInstance.getUri().toString();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(microservice1Address, String.class);
		return "Je suis succursale service,je demande le service clientcontrat, Ribbon load balancer trouve l'hote  " +
				serviceInstance.getUri()+ ". il appelle le microservice de cet hote,et me repond : " + response.getBody();
				 
	}**/
	
  @Autowired
  SuccursaleRepository succursaleRepository;
 
  @GetMapping("/succursales")
  public ResponseEntity<List<Succursale>> getAllSuccursale() {
    List<Succursale> succursale = new ArrayList<>();
    
    try {
    	succursaleRepository.findAll().forEach(succursale::add);
      
      if (succursale.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(succursale, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/succursales/{id}")
  public ResponseEntity<Succursale> getSuccursaleById(@PathVariable("id") long id) {
    Optional<Succursale> specialitesData = succursaleRepository.findById(id);
 
    if (specialitesData.isPresent()) {
      return new ResponseEntity<>(specialitesData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 
  @PostMapping(value = "/succursales")
  public ResponseEntity<Succursale> postSuccursale(@RequestBody Succursale succursale) {
    try {
    	Succursale _succursale = succursaleRepository.save(new Succursale(succursale.getReference()));
      return new ResponseEntity<>(_succursale, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("/succursales/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
    try {
    	succursaleRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("/succursales")
  public ResponseEntity<HttpStatus> deleteAllSpecialites() {
    try {
    	succursaleRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
 
  }
 
  @GetMapping(value = "succursales/reference/{reference}")
  public ResponseEntity<List<Succursale>> findByAge(@PathVariable String reference) {
    try {
      List<Succursale> succursale = succursaleRepository.findByReference(reference);
 
      if (succursale.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(succursale, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @PutMapping("/succursales/{id}")
  public ResponseEntity<Succursale> updateSuccursale(@PathVariable("id") long id, @RequestBody Succursale succursale) {
    Optional<Succursale> succursaleData = succursaleRepository.findById(id);
 
    if (succursaleData.isPresent()) {
    	Succursale _succursale = succursaleData.get();
      _succursale.setReference(succursale.getReference());
   
    
      return new ResponseEntity<>(succursaleRepository.save(_succursale), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}