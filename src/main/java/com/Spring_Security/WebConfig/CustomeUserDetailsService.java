package com.Spring_Security.WebConfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.Spring_Security.Repository.CustomerRepository;
import com.Spring_Security.entities.Customer;

@Configuration
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepository.findByName(username);
		
        return customer.map(CustomerUser::new).orElseThrow(()->new UsernameNotFoundException("HEY username not found"));
	}

}
