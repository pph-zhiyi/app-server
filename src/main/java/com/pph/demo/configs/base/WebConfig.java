/*
 *     WebMvcConfigurationSupport 比较常用的重写接口
 *
 *     解决跨域问题
 *     public void addCorsMappings(CorsRegistry registry) ;
 *     添加拦截器
 *     void addInterceptors(InterceptorRegistry registry);
 *     这里配置视图解析器
 *     void configureViewResolvers(ViewResolverRegistry registry);
 *     配置内容裁决的一些选项
 *     void configureContentNegotiation(ContentNegotiationConfigurer configurer);
 *     视图跳转控制器
 *     void addViewControllers(ViewControllerRegistry registry);
 *     静态资源处理
 *     void addResourceHandlers(ResourceHandlerRegistry registry);
 *     默认静态资源处理器
 *     void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);
 */
package com.pph.demo.configs.base;

import com.pph.demo.configs.jwt.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @Author: PPH
 * @date: 2019-05-26 13:01
 * @Description:
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * 请求方式
     */
    private static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE"};
    /**
     * 登录 API URI 默认不走 JWT Token 校验
     */
    @Value("${def.login.uri}")
    private String DefLoginUri;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)
                .allowedMethods(ORIGINS)
                .maxAge(3600);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor())
//                配置拦截所有请求　
                .addPathPatterns("/**")
//                不拦截登录请求
                .excludePathPatterns(DefLoginUri);
    }

    @Bean
    public JwtInterceptor getInterceptor() {
        return new JwtInterceptor();
    }
}
