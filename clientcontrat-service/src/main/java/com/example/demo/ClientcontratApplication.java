package com.example.demo;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import com.example.demo.contrat.Contrat;
import com.example.demo.contrat.ContratRepository;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.souscription.Souscription;
import com.example.demo.souscription.SouscriptionRepository;

@EnableDiscoveryClient
@SpringBootApplication

public class ClientcontratApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientcontratApplication.class, args);
	}
	
	  @Bean @Qualifier("runner")
	    CommandLineRunner init(ContratRepository contratrepository,CustomerRepository customerrepository
	    		,SouscriptionRepository souscriptionRepository) {
		  return args -> {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    Date date1 = dateFormat.parse("2019-10-09");
				Date date2= dateFormat.parse("2010-02-03");
			  
				/**/
				  Souscription newsouscription7 = new Souscription(date1,date2, (int)(Math.random() * ( 100 - 18)));
			      Customer newcustomer7 = new Customer("Helen","Helen" ,"Helen"+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
			      Contrat newcontrat7 = new Contrat("XAAD1","XAAD1XD460");

			      newsouscription7.setOwner(newcustomer7);
		          newsouscription7.setCont(newcontrat7);  
			      souscriptionRepository.save(newsouscription7);
			      customerrepository.save(newcustomer7);  
			      contratrepository.save(newcontrat7);
				/**/
			      Souscription newsouscription5 = new Souscription(date1,date2, (int)(Math.random() * ( 100 - 18)));
			      Customer newcustomer5= new Customer("Rachel","Rachel" ,"Rachel"+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
			      Contrat newcontrat5 = new Contrat("\"HEFFQ","HEFFQD460");

			      newsouscription5.setOwner(newcustomer5);
		          newsouscription5.setCont(newcontrat5);  
			      souscriptionRepository.save(newsouscription5);
			      customerrepository.save(newcustomer5);  
			      contratrepository.save(newcontrat5);
				/**/
			      Souscription newsouscription6 = new Souscription(date1,date2, (int)(Math.random() * ( 100 - 18)));
			      Customer newcustomer6 = new Customer("Jennifer","Jennifer" ,"Jennifer"+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
			      Contrat newcontrat6= new Contrat("Je5D5Dr","Je5D5DrD460");

			      newsouscription6.setOwner(newcustomer6);
		          newsouscription6.setCont(newcontrat6);  
			      souscriptionRepository.save(newsouscription6);
			      customerrepository.save(newcustomer6);  
			      contratrepository.save(newcontrat6);
		
	      
	      Souscription newsouscription3 = new Souscription(date1,date2, (int)(Math.random() * ( 100 - 18)));
	      Customer newcustomer3 = new Customer("John","John" ,"John"+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
	      Contrat newcontrat3 = new Contrat("X121","X121XD460");

	      newsouscription3.setOwner(newcustomer3);
          newsouscription3.setCont(newcontrat3);  
	      souscriptionRepository.save(newsouscription3);
	      customerrepository.save(newcustomer3);  
	      contratrepository.save(newcontrat3);
	      /**/
	      
	      Souscription newsouscription4 = new Souscription(date1,date2, (int)(Math.random() * ( 100 - 18)));
	      Customer newcustomer4 = new Customer("Julie","Julie" ,"Julie"+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
	      Contrat newcontrat4 = new Contrat("XAAD1","XAAD1XD460");

	      newsouscription4.setOwner(newcustomer4);
          newsouscription4.setCont(newcontrat4);  
	      souscriptionRepository.save(newsouscription4);
	      customerrepository.save(newcustomer4);  
	      contratrepository.save(newcontrat4);
	      
	      
	      /**/
	      Customer newcustomer = new Customer(" DL","Cons","yo"+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
	      Contrat newcontrat = new Contrat("X125","Garantie GHC");       
	     Souscription newsouscription = new Souscription(date1,date2, (int)(Math.random() * ( 100 - 18)),newcustomer,newcontrat);
	      
	      newsouscription.setOwner(newcustomer);
          newsouscription.setCont(newcontrat);  
	      souscriptionRepository.save(newsouscription);
	      customerrepository.save(newcustomer);  
	      contratrepository.save(newcontrat);
	      /**/
	      Customer newcustomer2 = new Customer(" Bayard,","Star","yo"+"@gmail.com", (int)(Math.random() * ( 100 - 18)),(int)(Math.random() * ( 100 - 18)));
	      Contrat newcontrat2 = new Contrat("X1","FJFLGHC"); 
	      Souscription s=new Souscription();
	      s.setBeginsouscription(date1);
	      s.setEndsouscription(date2);
	      s.setPrice(15);
	      s.setOwner(newcustomer2); s.setCont(newcontrat2);
	      souscriptionRepository.save(s);
	      customerrepository.save(newcustomer2);  
	      contratrepository.save(newcontrat2);
	      
	      /*
	      Souscription newsouscription2 = new Souscription(date1,date2, (int)(Math.random() * ( 100 - 18)));
	      newsouscription2.setOwner(newcustomer);
          newsouscription2.setCont(newcontrat);  
	      souscriptionRepository.save(newsouscription2);
	      customerrepository.save(newcustomer);  
	      contratrepository.save(newcontrat);
	      */
               }; }
	  @Bean @Qualifier ("register filter")
	    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	        config.setAllowedMethods(Collections.singletonList("*"));
	        config.setAllowedHeaders(Collections.singletonList("*"));
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
	        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        return bean;
	    }
}
