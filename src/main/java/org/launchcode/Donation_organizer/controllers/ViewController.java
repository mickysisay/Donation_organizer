package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/view")
@Controller
public class ViewController {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/recipe/{recipeid}")
    public String viewRecipe(@PathVariable int recipeid, Model model){
       Recipe recipe = recipeRepository.findById(recipeid).get();
       model.addAttribute("recipe",recipe);
       System.out.println(recipe.getUser().getUsername());
       return "view/showRecipe";
    }
    @GetMapping("/profile/{userid}")
    public String viewProfile(@PathVariable int userid,Model model){
        User user = userRepository.findById(userid).get();
        model.addAttribute("user",user);

        return "view/showProfile";
    }

}
