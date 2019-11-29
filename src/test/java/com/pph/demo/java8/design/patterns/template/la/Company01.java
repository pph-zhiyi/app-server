package com.pph.demo.java8.design.patterns.template.la;

import com.pph.demo.java8.design.patterns.template.ApplicationDenied;

/**
 * @author: pph
 * @date: 2019/11/29 09:32
 * @description:
 */
public class Company01 implements Company {
    @Override
    public void checkIdentity() throws ApplicationDenied {
        System.out.println("Company01.checkIdentity: 通过");
    }

    @Override
    public void checkProfitAndLoss() throws ApplicationDenied {
        System.out.println("Company01.checkProfitAndLoss: 通过");
    }

    @Override
    public void checkHistoricalDebt() throws ApplicationDenied {
        System.out.println("Company01.checkHistoricalDebt: 通过");
    }
}
