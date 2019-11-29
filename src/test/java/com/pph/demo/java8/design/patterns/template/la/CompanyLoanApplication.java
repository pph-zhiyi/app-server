package com.pph.demo.java8.design.patterns.template.la;

/**
 * @author: pph
 * @date: 2019/11/29 09:23
 * @description:
 */
public class CompanyLoanApplication extends LoanApplication {

    public CompanyLoanApplication(Company company) {
        super(company::checkIdentity,
                company::checkHistoricalDebt,
                company::checkProfitAndLoss);
    }
}
