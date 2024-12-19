package com.zongzewu.bettercallwu.filter;

import com.alibaba.fastjson.JSON;
import com.zongzewu.bettercallwu.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        //get URI of this request
        String requestURI = request.getRequestURI();
        log.info("Intercept the request: {}", requestURI);
        //those requests do not need to do anything
        String[] urls = new String[]{
          "/employee/login",
          "/employee/logout",
          "/backend/**",
          "/front/**"
        };

        // Determine if the request is in the urls
        boolean check = check(urls, requestURI);

        if(check){
            log.info("This request does not need to be processed");
            filterChain.doFilter(request, response);
            return;
        }
        //check whether logged in
        if(request.getSession().getAttribute("employee") != null){
            log.info("User has already logged in, id: {}", request.getSession().getAttribute("employee"));
            filterChain.doFilter(request, response);
            return;
        }
        log.info("User hasn't logged in yet");
        //if haven't logged in yet, then use output stream to response data
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * Path matching, check whether the current request is allowed
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
