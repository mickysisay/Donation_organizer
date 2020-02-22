package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.SearchAlgorithms;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/searches")
@RestController
public class RestSearchController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @GetMapping("users")
     public List<User> searchUser(@RequestParam String searchWord){
         SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
       List<User> result = new ArrayList<>();
       result =  searchAlgorithms.searchUser(userRepository.findAll(),searchWord);
       return result;
     }
    @GetMapping("Recipe")
    public List<Recipe> searchRecipes(@RequestParam String searchWord){
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
        List<Recipe> result = new ArrayList<>();
        result =  searchAlgorithms.searchRecipe(recipeRepository.findAll(),searchWord);
        return result;
    }
}
