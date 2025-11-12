package com.mine.tradechamp.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GreetingController {
 
	@Value("${WELCOME_MESSAGE:def_value}")
	String welcomeMessage; 
	 
    @GetMapping("/greetings")
	public String greeting(Model model) {
    	System.out.println("inside GreetingController, message from env: " + welcomeMessage); 
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("system generated IP address: "+ ip); 
            
            model.addAttribute("welcomeMessage", welcomeMessage); 
            model.addAttribute("myIP", ip); 
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("returning greeting model"); 
        return "greeting";
    }
   
}