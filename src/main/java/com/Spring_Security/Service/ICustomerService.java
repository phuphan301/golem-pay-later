package com.Spring_Security.Service;

import java.util.List;

import com.Spring_Security.entities.Customer;


public interface ICustomerService {
	Customer addCustomer(Customer customer);
	List<Customer> getCustomer();
	Customer updateCustomer(Customer customer, Long id);
	Customer getCustomerGetById(Long id);
	void deleteCustomer(Long id);
}
