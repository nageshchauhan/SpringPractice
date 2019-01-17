package com.tos.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/")
    public String user() {
        //return new ModelAndView("events/list", "events", calendarService.getEvents());
    	return "user/view";
    }
    
    @RequestMapping("/update")
    public String updateUser() {
        //return new ModelAndView("events/list", "events", calendarService.getEvents());
    	return "user/update";
    }
    
    @RequestMapping("/create")
    public String createUser() {
        //return new ModelAndView("events/list", "events", calendarService.getEvents());
    	return "user/create";
    }
    
    @RequestMapping("/viewall")
    public String viewAllUsers() {
        //return new ModelAndView("events/list", "events", calendarService.getEvents());
    	return "user/viewall";
    }
    
    @RequestMapping("/{userId}")
    public String viewUser() {
        //return new ModelAndView("events/list", "events", calendarService.getEvents());
    	return "user/view";
    }
}
