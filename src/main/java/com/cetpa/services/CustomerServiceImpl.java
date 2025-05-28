package com.cetpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.models.Customer;
import com.cetpa.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repo;

	public void saveRecord(Customer cust) {
		repo.save(cust);
	}


	public Customer getRecord(int cid) {
		Customer c = repo.findById(cid).orElse(null);
		return c;
	}

	public List<Customer> getList() {
		List<Customer> list = repo.findAll();
		return list;
	}


	
	public void deleteRecord(int cid) {
		repo.deleteById(cid);
	}


	public void updateRecord(Customer cust) {
		repo.save(cust);
		
	}

	
}
