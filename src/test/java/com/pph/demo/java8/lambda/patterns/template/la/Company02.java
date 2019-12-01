package com.pph.demo.java8.lambda.patterns.template.la;

import com.pph.demo.java8.lambda.patterns.template.ApplicationDenied;

/**
 * @author: pph
 * @date: 2019/11/29 09:37
 * @description:
 */
public class Company02 implements Company {

    @Override
    public void checkIdentity() throws ApplicationDenied {
        System.out.println("Company02.checkIdentity: 通过");
    }

    @Override
    public void checkProfitAndLoss() throws ApplicationDenied {
        System.out.println("Company02.checkIdentity: 通过");
    }

    @Override
    public void checkHistoricalDebt() throws ApplicationDenied {
        throw new ApplicationDenied("checkHistoricalDebt 异常");
    }
}
