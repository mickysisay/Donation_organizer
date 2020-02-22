package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Ingredient;
import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.SearchAlgorithms;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.IngredientRepository;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/searches")
@RestController
public class RestSearchController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;
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
