package com.pph.demo.aspect;

import com.pph.demo.utils.common.ApiResult;
import com.pph.demo.utils.common.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author pph
 * @date 2019/9/12 13:55
 * @Description:
 */
@Aspect
@Component
public class UnifyResultAspect {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UnifyResultAspect.class);

    /**
     * 切点（切被 @UnifyResult 注解的所有方法）
     */
    @Pointcut("@annotation(com.pph.demo.annotation.UnifyResult)")
    private void cut() {
    }

//    @AfterReturning(value = "cut()", returning = "obj")
    public Result<Object> doAfterReturningAdvice(JoinPoint joinPoint, Object obj) {
        return ApiResult.success(obj);
    }
}
