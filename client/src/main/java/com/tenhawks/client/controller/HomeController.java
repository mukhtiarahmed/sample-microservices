package com.tenhawks.client.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author Mukhtiar Ahmed
 */
@Controller
public class HomeController {


    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model, Authentication authentication) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }
    /**
     * Home
     * @return String view name
     */
    @GetMapping("/home")
    public String home(){
        return "index";
    }

    /**
     * Access Denied.
     * @return String view name
     */
    @GetMapping("/access-denied")
    public String accessDenied(){
        return "accessDenied";
    }


}
