package com.cetpa.services;

import java.util.List;

import com.cetpa.models.Customer;

public interface CustomerService {

	void saveRecord(Customer cust);

	Customer getRecord(int cid);

	List<Customer> getList();

	void deleteRecord(int cid);

	void updateRecord(Customer cust);
	

	
}
