package com.seventh.icecastle.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest reqsreqs = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,x-requested-with");
        response.setHeader("Access-Control-Allow-Origin", reqsreqs.getHeader("Origin"));

        if (reqsreqs.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }
        chain.doFilter(reqsreqs, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("=======================================================chulaile=================================================");
    }
}
