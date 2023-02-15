package com.example.habit.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(
//                new SaInterceptor(handler -> StpUtil.checkLogin())) // 所有页面都要登录
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login");

                new SaInterceptor(handler -> {
                    SaRouter.match("/**")                 // 所有页面
                            .notMatch("/user/login")
                            .notMatch("/user/register")  // 忽略登录
                            .notMatch("/api")
                            .notMatch("/img/**")
                            .notMatch("/swagger-ui/**")   // Swagger
                            .notMatch("/v3/api-docs/**")  // OpenAPI
//                            .check(r -> StpUtil.checkLogin());     // 都要登录
                            .check(r -> System.out.println(r));

                }).isAnnotation(false)                             // 关闭注解拦截
                ).addPathPatterns("/**");
    }
}
