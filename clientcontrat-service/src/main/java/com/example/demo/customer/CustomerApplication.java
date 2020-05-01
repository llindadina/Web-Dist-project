/*package com.example.demo.customer;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/*


@EnableDiscoveryClient
@SpringBootApplication
public class CustomerApplication {
	@Autowired
		public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	  @Bean
	    CommandLineRunner init(CustomerRepository customerrepository) {
	        return args -> {
	        	
	            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
	               Customer newcustomer = new Customer(name,name ,name+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
	            	
	                customerrepository.save(newcustomer);
	            });
	            customerrepository.findAll().forEach(System.out::println);
	        };
	    }

}*/
