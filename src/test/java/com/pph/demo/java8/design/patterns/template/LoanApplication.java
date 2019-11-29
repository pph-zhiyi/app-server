package com.pph.demo.java8.design.patterns.template;

/**
 * @author: pph
 * @date: 2019/11/28 17:23
 * @description:
 */
public abstract class LoanApplication {
    public void checkLoanApplication() throws ApplicationDenied {
        /*
         * checkIdentity 方法通过分析客户提供的纸本结算单，确认客 户地址是否真实有效。
         * checkIncomeHistory 方法通过检查工资条判断客户是否仍被雇佣。
         * checkCreditHistory 方法则会将工作交给外部的信用卡支付提供商。
         */
        checkIdentity();
        checkCreditHistory();
        checkIncomeHistory();
        reportFindings();
    }

    protected abstract void checkIdentity() throws ApplicationDenied;

    protected abstract void checkIncomeHistory() throws ApplicationDenied;

    protected abstract void checkCreditHistory() throws ApplicationDenied;

    private void reportFindings() {

    }
}
