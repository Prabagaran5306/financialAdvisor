package com.financialhealth.financialhealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.financialhealth.financialhealth.services.FinancialService;

@Controller
public class HomeController {

	@Autowired 
	FinancialService financialservices;
	
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculateFinancialStatus")
    public String calculateFinancialStatus(@RequestParam("assets") double assets,
    		@RequestParam("liabilities") double liabilities,
    		@RequestParam("income") double income,
    		@RequestParam("expenses") double expenses, Model model) {
        
    	Integer financialPercentage = financialservices.calculatefinancialpercentage(assets,liabilities,income,expenses);
        String imageUrl = "/image" + financialPercentage + ".jpg";

        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("message", "An investment in knowledge pays the best interest. — Benjamin Franklin.");
        System.out.println(imageUrl);
        return "index";
    }

}
