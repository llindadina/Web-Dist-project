package com.example.demo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.demo.agence.Agence;
import com.example.demo.agence.AgenceRepository;
import com.example.demo.succursale.Succursale;
import com.example.demo.succursale.SuccursaleRepository;



@EnableDiscoveryClient
@SpringBootApplication
public class SuccursaleApplication {
	


		public static void main(String[] args) {
		SpringApplication.run(SuccursaleApplication.class, args);
		
	}
		
		@LoadBalanced
		@Bean
		RestTemplate template() {
			return new RestTemplate();
		}

		  @Bean @Qualifier("runner")
	public CommandLineRunner init( AgenceRepository agencerepo,SuccursaleRepository succrepo) {
		return args -> {
			
			
			Succursale newsucc = new Succursale("Siege Paris");  
		            
		      Agence newagence= new Agence("N°001");
		      newagence.setSucc(newsucc);
	           
		      agencerepo.save(newagence);
		      succrepo.save(newsucc);  
		  
			 	
		      Succursale newsucc2 = new Succursale("Siege W");  
		            
		      Agence newagence2= new Agence("N°002");
		      newagence2.setSucc(newsucc2);
	           
		      agencerepo.save(newagence2);
		      succrepo.save(newsucc2);  
           
	

		};
	}

}