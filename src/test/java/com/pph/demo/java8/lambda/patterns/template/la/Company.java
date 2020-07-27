package com.pph.demo.java8.lambda.patterns.template.la;

import com.pph.demo.java8.lambda.patterns.template.ApplicationDenied;

/**
 * @author: pph
 * @date 2019/11/29 09:24
 * @description:
 */
public interface Company {

    void checkIdentity() throws ApplicationDenied;

    void checkProfitAndLoss() throws ApplicationDenied;

    void checkHistoricalDebt() throws ApplicationDenied;
}
