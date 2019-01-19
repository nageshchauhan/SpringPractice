package com.tos.hrms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tos.hrms.exception.AuthenticationFailure;
import com.tos.hrms.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value="/")
    public String welcomePage(){
        return "index";
    }

    @RequestMapping(value="/checkUser", method=RequestMethod.POST)
    public ModelAndView checkUser(HttpServletRequest request, 
                                  @RequestParam("principal")String username,
                                  @RequestParam("value")String password) {

        System.out.println("username: "+username);
        System.out.println("password: "+password );

        ModelAndView mv = new ModelAndView();

        if(username.equals("") || password.equals("")){
            mv.setViewName("login");
            mv.addObject("errorMsg","username and password is mandatory");
            return mv;
        }

        try {
            loginService.checkUser(username, password);
            System.out.println("Login successful");
            mv.setViewName("index");
            mv.addObject("username", username);
            mv.addObject("user_detail",loginService.getUserDtlsByUsername(username));
        } catch (AuthenticationFailure e) {
            mv.setViewName("login");
            mv.addObject("errorMsg","invalid username and password");
            System.out.println("invalid username and password");
        }

        return mv;



    }

}
