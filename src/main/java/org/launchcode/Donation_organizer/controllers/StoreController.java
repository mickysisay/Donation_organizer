package org.launchcode.Donation_organizer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StoreController {
    @GetMapping("store")
    public String Store(HttpServletRequest request){
    return "storehome";
    }
}
