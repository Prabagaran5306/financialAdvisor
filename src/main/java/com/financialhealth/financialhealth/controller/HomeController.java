package com.financialhealth.financialhealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculateFinancialStatus")
    public String calculateFinancialStatus(@RequestParam("assets") double assets, @RequestParam("liabilities") double liabilities, @RequestParam("income") double income, @RequestParam("expenses") double expenses, Model model) {
        int financialPercentage = 5;
        if (expenses > income) {
            financialPercentage = 1;
        } else if (liabilities > assets) {
            financialPercentage = 2;
        } else if (liabilities + income > assets) {
            financialPercentage = 3;
        } else if (income > (expenses + liabilities + assets)) {
            financialPercentage = 4;
        }

        String imageUrl = "/image" + financialPercentage + ".jpg";

        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("message", "An investment in knowledge pays the best interest. — Benjamin Franklin.");

        return "index";
    }

}
