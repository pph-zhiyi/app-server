package com.pph.demo.utils.oval;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: PPH
 * @Date: 2019-05-24 10:29
 * @Description:
 */
public final class OvalVerify {
    private OvalVerify() {

    }

    /**
     * 校验对象参数是否合法
     *
     * @param o
     */
    public static void verifyObj(Object o) {
        Validator v = new Validator();
        List<ConstraintViolation> m = v.validate(o);
        if (Objects.nonNull(m) && !m.isEmpty())
            throw new IllegalArgumentException(m.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList())
                    .toString());
    }
}
