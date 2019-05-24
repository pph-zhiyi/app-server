package com.pph.demo.utils.oval;

import net.sf.oval.constraint.CheckWithCheck;

/**
 * @Author: PPH
 * @Date: 2019-05-24 10:56
 * @Description:
 */
public class CheckBeanUtil {

    /**
     * 校验性别
     */
    public static class CheckSex implements CheckWithCheck.SimpleCheck {
        @Override
        public boolean isSatisfied(Object o, Object o1) {
            String sex = o1.toString();
            return sex.equals("男") || sex.equals("女");
        }
    }

    /**
     * 校验年龄范围
     */
    public static class CheckAge implements CheckWithCheck.SimpleCheck {
        @Override
        public boolean isSatisfied(Object o, Object o1) {
            Integer age = Integer.valueOf(o1.toString());
            return age >= 18 && age <= 85;
        }
    }
}
