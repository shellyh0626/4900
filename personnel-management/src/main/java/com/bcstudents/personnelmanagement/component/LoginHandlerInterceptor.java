package com.bcstudents.personnelmanagement.component;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//login
public class LoginHandlerInterceptor implements HandlerInterceptor {
    // Before executed
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //havent login, back to login page
            request.setAttribute("msg","no permission pls login first");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //login
            return true;
        }
    }
}
