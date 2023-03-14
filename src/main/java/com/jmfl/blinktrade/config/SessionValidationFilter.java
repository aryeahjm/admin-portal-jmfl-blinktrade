package com.jmfl.blinktrade.config;


import com.google.cloud.datastore.Value;
import com.jmfl.blinktrade.constants.Values;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*
    Every request after login needs to be authenticated so defined filter
 */

@Component
public class SessionValidationFilter implements Filter {

    private static final List<String> ALLOWED_URL_LIST   = Arrays.asList(
            "/",
            "/register"
    );
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession(false);
        String url  = servletRequest.getRequestURI();
        if(ALLOWED_URL_LIST.contains(url)||url.contains("/test/") || url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png")
                || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".ttf") || url.endsWith(".woff")
                || url.endsWith(".csv")) {

            System.out.println("Allowed");
            chain.doFilter(request, response);
        }
        else if (session == null || session.getAttribute(Values.SESSION_KEY_PROP_NAME)==null){
            // means user is unauthorized
            System.out.println("Unauthorized");
            ((HttpServletResponse)response).sendRedirect("/");
        }else{
            chain.doFilter(request,response);
        }
    }
}
