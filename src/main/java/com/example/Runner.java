package com.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * User: clean_brain
 * Date: 2023-11-18
 * Comments: 
 * </pre>
 */
@Configuration
@RequiredArgsConstructor
public class Runner {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private final CustomerQueryRepository customerQueryRepository;
	private final CustomerJpaRepository customerJpaRepository;

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			this.customerJpaRepository.save(new Customer("Jack", "Bauer"));
			this.customerJpaRepository.save(new Customer("Chloe", "O'Brian"));
			this.customerJpaRepository.save(new Customer("Kim", "Bauer"));
			this.customerJpaRepository.save(new Customer("David", "Palmer"));
			this.customerJpaRepository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");

			List<Customer> customerList = this.customerQueryRepository.getCustomerList();
			customerList.forEach(c -> {
				log.info(c.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Customer customer = this.customerQueryRepository.getCustomer(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			if (customer != null)
				log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			customerList = this.customerQueryRepository.getCustomerListByLastName("Bauer");
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			customerList.forEach(c -> {
				log.info(c.toString());
			});
			log.info("");
		};
	}
}
