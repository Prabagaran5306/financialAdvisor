package com.financialhealth.financialhealth.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financialhealth.financialhealth.payload.drools.FinancialPercentageCalculator;
import com.github.javaparser.utils.Log;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FinancialService {

	@Autowired
	private KieContainer kieContainer;
	
	public Integer calculatefinancialpercentage(Double assets,Double liabilities,Double income,Double expenses) {
		
		
		Integer financialPercentage = 5;
		FinancialPercentageCalculator financialpercentagecalcalculator = new FinancialPercentageCalculator();
		financialpercentagecalcalculator.setAssets(assets);
		financialpercentagecalcalculator.setLiabilities(liabilities);
		financialpercentagecalcalculator.setIncome(income);
		financialpercentagecalcalculator.setExpenses(expenses);
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.setGlobal("financialpercentagecalcalculator", financialpercentagecalcalculator);
		kieSession.insert(financialpercentagecalcalculator);
		kieSession.fireAllRules();
        financialPercentage = financialpercentagecalcalculator.getFinancialPercentage();
        
        log.info("percentage: {}", financialPercentage);
        kieSession.dispose();
        
		return financialPercentage;
	}

}
