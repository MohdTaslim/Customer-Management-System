package com.cetpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cetpa.models.Customer;
import com.cetpa.services.CustomerService;



@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/")
	public String getHomeView() {
		
		return "home";
	}
	

	@RequestMapping("add")
	public String getAddCustomerView(Model model) {
		model.addAttribute("cust",new Customer());
		return "add-customer";
	}
	
	@RequestMapping("save-record")
	public String saveCustomerView(@ModelAttribute("cust") Customer cust, Model model) {
		
		int cid =cust.getCid();
		Customer c=service.getRecord(cid);
		if (c!=null) {
			model.addAttribute("msg", "customer with id" +cid+ "does not exist");
			 return "add-customer";
		}
		service.saveRecord(cust);
		return "save";
	}
	
	@RequestMapping("list")
	public String getCustomerList(Model model) {
		List<Customer> list =service.getList();
		model.addAttribute("clist", list);
		return "customer-list";
	}

	
	@RequestMapping("delete-record")
	public String getDeleteCustomerView( int cid) {
		service.deleteRecord(cid);
		return "redirect:list";
	}
	
	@RequestMapping("edit")
	public String getCustomerView() {
		return "editt-customer";
	}
	
	
	@RequestMapping("edit-record")
	public String getEditCustomerView( int cid, Model model ) {
		Customer cust= service.getRecord(cid);
		model.addAttribute("cust", cust);
		return "edit-customer";
	}
	
	@RequestMapping("update-record")
	public String UpdateCustomerRecord( Customer cust) {
		
	      service.updateRecord(cust);
	      return "redirect:list";
	}
	
	@RequestMapping("search")
	public String getSearchCustomerView() {
		return "search-customer";
	}
	
	@RequestMapping("search-record")
	public String getCustomerRecord(int cid , Model model) {
		Customer cust = service.getRecord(cid);
		if( cust ==null) {
			model.addAttribute("msg", "customer record not found");
			model.addAttribute("cid", cid);
			return "search-customer";
		}
		model.addAttribute("customer" , cust);
		
		return "show-customer";
	}
	
	@RequestMapping("delete")
	public String getDeleteCustomerView() {
		return "delete-customer";
	}
	
	
	
	@RequestMapping("confirm-delete")
	public String getConfirmDeleteCustomer(int cid, Model model) {
		
		Customer cust = service.getRecord(cid);
		if(cust==null) {
			model.addAttribute("msg", "customer record not found");
			model.addAttribute("cid", cid);
			return "delete-customer";
		}
		model.addAttribute("customer", cust);
		return "confirm-delete";
	}
	
	@RequestMapping("delete-customer")
	public String deleteCustomerRecord1(int cid, Model model) {
		service.deleteRecord(cid);
		model.addAttribute("msg", "customer record has been deleted successfully");
		return "delete-customer";
	}
}
