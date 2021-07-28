//package com.example.websocketdemo;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * @description: 拦截器
// * @author: wyj
// * @create: 2020-05-25 18:28
// */
////@Configuration
//public class InterceptorConfig extends WebMvcConfigurationSupport {
//
////    @Bean
////    public OpenInterceptor openInterceptor() {
////        return new OpenInterceptor();
////    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(openInterceptor())
////                // 要拦截的请求
////                .addPathPatterns("/**")
////                // 忽略的请求
////                .excludePathPatterns(
//////                        "/**",
////                        "/swagger/**",
////                        "/v2/**",
////                        "/webjars/**",
////                        "/swagger-resources/**",
////                        "/swagger-ui.html",
////                        "/doc.html/**"
////                );
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 添加文档对应的映射
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//}
