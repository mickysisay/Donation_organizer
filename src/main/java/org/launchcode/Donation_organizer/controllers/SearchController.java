package org.launchcode.Donation_organizer.controllers;


import org.launchcode.Donation_organizer.models.Ingredient;
import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.SearchAlgorithms;
import org.launchcode.Donation_organizer.models.data.IngredientRepository;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/search")
@Controller
public class SearchController {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @GetMapping("/profiles")
    public String profileSearchView(){
        return "search/profile";
    }
    @PostMapping("/profiles")
    public String profileSearch(Model model, @RequestParam String name){

    return "search/profile";
    }
    @GetMapping("/recipeIng")
    public String recipeIngSearch(){
        return "search/recipeIng";
    }
    @PostMapping("recipeIng")
    public String recipeIngSearchResults(Model model,@RequestParam String ingList){
        List<String> tempList = Arrays.asList(ingList.split(","));
        List<Integer> intList = new ArrayList<>();
        for(String s : tempList) intList.add(Integer.valueOf(s));
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
        List<Recipe> result = new ArrayList<>();
        if(intList.size()!=0) {
            List<Ingredient> toBeSearched = (List<Ingredient>) ingredientRepository.findAllById(intList);
            result = searchAlgorithms.searchByIng(recipeRepository.findAll(), toBeSearched);

        }

        model.addAttribute("res",result);
        return "search/recipeIng";
    }




}
