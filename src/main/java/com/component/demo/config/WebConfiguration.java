package com.component.demo.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @Author: Dely
 * @Date: 2019/12/8 17:06
 */

/**
 * 自定义过滤器
 */

@Configuration
public class WebConfiguration {

/*@Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }*/

    /**
     * @return
     */

    /*@Bean
    public RegistrationBean testRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                                 FilterChain filterChain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                System.out.println("访问的路径为：" + request.getRequestURI());
                filterChain.doFilter(servletRequest, servletResponse);
            }
        });

        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("key", "value");
        registrationBean.setName("myFilter");
        registrationBean.setOrder(1);

        return registrationBean;
    }*/
}
