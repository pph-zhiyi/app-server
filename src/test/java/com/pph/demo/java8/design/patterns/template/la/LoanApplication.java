package com.pph.demo.java8.design.patterns.template.la;

import com.pph.demo.java8.design.patterns.template.ApplicationDenied;

/**
 * @author: pph
 * @date: 2019/11/29 09:20
 * @description:
 */
public class LoanApplication {

    private final Criteria identity;
    private final Criteria creditHistory;
    private final Criteria incomeHistory;

    public LoanApplication(Criteria identity, Criteria creditHistory, Criteria incomeHistory) {
        this.identity = identity;
        this.creditHistory = creditHistory;
        this.incomeHistory = incomeHistory;
    }

    public void checkLoanApplication() throws ApplicationDenied {
        identity.check();
        creditHistory.check();
        incomeHistory.check();
        reportFindings();
    }

    private void reportFindings() {
        System.out.println("申请成功");
    }
}
