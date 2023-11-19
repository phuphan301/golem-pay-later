package com.Spring_Security.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Spring_Security.Service.ICustomerService;
import com.Spring_Security.entities.Customer;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
//	@GetMapping("/all")
//	private ResponseEntity<List<Customer>> getStudents(){
//		return new ResponseEntity<>(customerService.getCustomer(), HttpStatus.FOUND);
//	}

	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Customer updateCustomer(@RequestBody Customer customer,@PathVariable Long id) {
		return customerService.updateCustomer(customer, id);
	}
	
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
	
	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Customer getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerGetById(id);
	}
}
