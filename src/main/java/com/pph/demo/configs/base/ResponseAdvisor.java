package com.pph.demo.configs.base;

import com.pph.demo.utils.common.ApiResult;
import com.pph.demo.utils.common.Result;
import com.pph.demo.utils.yml.SkipUniformResultProcessingUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: pph
 * @Date: 2019/9/12 17:13
 * @Description:
 */
@ControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

    @Autowired
    private SkipUniformResultProcessingUri uriList;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        /*
         * 如果该 URI 在 application-skip-result-processing-uri.yml 中配置统一不走后置处理，直接返回原始值
         */
        if (body instanceof Result
                || uriList.getList().stream().anyMatch(request.getURI().getPath()::equals)) {
            return body;
        }

        return ApiResult.success(body);
    }
}
