package com.example.demo;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.agent.Agent;
import com.example.demo.agent.AgentRepository;

import com.example.demo.specialite.Specialites;
import com.example.demo.specialite.SpecialitesRepository;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class AgentApplication {
	


		public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
		
	}

		  @Bean @Qualifier("runner")
	public CommandLineRunner init( AgentRepository agentrepo,SpecialitesRepository specrepo) {
		return args -> {
			
			
			 Specialites newspec = new Specialites("Assurance Habitat");  
		            
		      Agent newagent= new Agent("LAMARI","Aldjiya",23);
		      newagent.setSpec(newspec);
	           
		      agentrepo.save(newagent);
		      specrepo.save(newspec);  
		  
			 	
			 Specialites newspec2 = new Specialites("Assurance ETS");  
		            
		      Agent newagent2= new Agent("ALAIN","Toulon",30);
		      newagent2.setSpec(newspec2);
	           
		      agentrepo.save(newagent2);
		      specrepo.save(newspec2);  
           
	

		};
	}

			
}