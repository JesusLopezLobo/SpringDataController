package com.everis.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.spring.repository.EverisCustomer;
import com.everis.spring.services.EverisCustomerManagementServiceI;

@Controller
@RequestMapping("*")
public class EverisController {
	
	@Autowired
	private EverisCustomerManagementServiceI customerService;
	
	@GetMapping("/*")
	public String showViewIndex() {
		return "systemMenu";
	}
	
	@GetMapping("/newCustomerView")
	public String newCustomerByView() {
		
		return "newCustomer";
	}
	
	@RequestMapping(value="/actAddCustomer", method = RequestMethod.POST)
	public String newCustomerView(EverisCustomer newCustomer) {
		
		customerService.insertNewCustomer(newCustomer);
		return "ShowCustomers";
	}
	
	@RequestMapping(path="/actDropCustomer", method = RequestMethod.POST)
	public String deleteCustomerView(@RequestParam Long newCustomer) {
		System.out.println(newCustomer);
		//customerService.deleteById(Long.valueOf(newCustomer));
		customerService.deleteById(newCustomer);
		return "ShowCustomers";
	}
	
	@GetMapping("/searchCustomerByView")
	public String searchCustomerByView() {
		
		return "searchCustomerBy";
	}
	
	@GetMapping("/showCustomersView")
	public String showCustomresView(Model model) {
		
		final List<EverisCustomer> customerList = customerService.searchAllCustomers();
		
		//System.out.println(customerList);
		
		model.addAttribute("customersList", customerList);
		model.addAttribute("btnDropCustomerEnabled", false);
		
		return "showCustomers";
	}
	
	
}
