package org.launchcode.Donation_organizer.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("Search")
@Controller
public class SearchController {
    @GetMapping("/profiles")
    public String profileSearchView(){
        return "search/profiles";
    }
    @PostMapping("/profiles")
    public String profileSearch(Model model, @RequestParam String name){

    return "search/profiles";
    }

}
