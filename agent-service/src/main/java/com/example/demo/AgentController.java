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

import com.example.demo.agent.Agent;
import com.example.demo.agent.AgentRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/agt")
public class AgentController {
  
	
  @Autowired
  AgentRepository agentRepository;
 
  @GetMapping("/agents")
  public ResponseEntity<List<Agent>> getAllAgent() {
    List<Agent> agents = new ArrayList<>();
    
    try {
    	agentRepository.findAll().forEach(agents::add);
      
      if (agents.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(agents, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/agents/{id}")
  public ResponseEntity<Agent> getAgentById(@PathVariable("id") long id) {
    Optional<Agent> agentData = agentRepository.findById(id);
 
    if (agentData.isPresent()) {
      return new ResponseEntity<>(agentData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 
  @PostMapping(value = "/agents")
  public ResponseEntity<Agent> postAgent(@RequestBody Agent agent) {
    try {
    	Agent _agent= agentRepository.save(new Agent(agent.getName(),agent.getPrenom(),agent.getAge()));
      return new ResponseEntity<>(_agent, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("/agents/{id}")
  public ResponseEntity<HttpStatus> deleteAgent(@PathVariable("id") long id) {
    try {
       agentRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
 
  @DeleteMapping("/agents")
  public ResponseEntity<HttpStatus> deleteAllAgents() {
    try {
    	agentRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
 
  }
 
  @GetMapping(value = "agents/name/{name}")
  public ResponseEntity<List<Agent>> findByName(@PathVariable String name) {
    try {
      List<Agent> agents = agentRepository.findByName(name);
 
      if (agents.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(agents, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @GetMapping(value = "agents/age/{age}")
  public ResponseEntity<List<Agent>> findByAge(@PathVariable int age) {
    try {
      List<Agent> agents = agentRepository.findByAge(age);
 
      if (agents.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(agents, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  @PutMapping("/agents/{id}")
  public ResponseEntity<Agent> updateAgent(@PathVariable("id") long id, @RequestBody Agent agent) {
	
	  Optional<Agent> agentData = agentRepository.findById(id);
 
    if (agentData.isPresent()) {
    	Agent _agent = agentData.get();
      _agent.setName(agent.getName());
    
    
      return new ResponseEntity<>(agentRepository.save(_agent), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

	
}