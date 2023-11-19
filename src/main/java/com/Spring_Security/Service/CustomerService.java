package com.Spring_Security.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Spring_Security.Repository.CustomerRepository;
import com.Spring_Security.entities.Customer;
@Service
public class CustomerService implements ICustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String password = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(password);
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Customer customer, Long id) {
		// TODO Auto-generated method stub
		String password = passwordEncoder.encode(customer.getPassword());
		
		return customerRepository.findById(id).map(user->{
			user.setName(customer.getName());
			user.setEmail(customer.getEmail());
			user.setPassword(password);
			user.setRoles(customer.getRoles());
			return customerRepository.save(user);
		}).orElseThrow();
	}

	@Override
	public Customer getCustomerGetById(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id).get();
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		customerRepository.deleteById(id);
	}

}
