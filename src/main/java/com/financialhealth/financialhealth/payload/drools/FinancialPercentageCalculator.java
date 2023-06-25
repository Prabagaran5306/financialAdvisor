package com.financialhealth.financialhealth.payload.drools;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialPercentageCalculator {

	Double assets;
	Double liabilities;
	Double income;
	Double expenses;
	Integer financialPercentage;
}
