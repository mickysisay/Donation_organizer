package org.launchcode.Donation_organizer.controllers;


import org.launchcode.Donation_organizer.models.Ingredient;
import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.SearchAlgorithms;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.IngredientRepository;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/search")
@Controller
public class SearchController {
    @Autowired
    UserRepository userRepository;
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
    public String recipeIngSearch(HttpServletRequest request, Model model){
        Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        return "search/recipeIng";
    }
    @PostMapping("recipeIng")
    public String recipeIngSearchResults(Model model,@RequestParam String ingList,HttpServletRequest request){
        if(ingList.isEmpty()){
            return "search/recipeing";
        }
        //finding user
        Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("user",theUser);
        //


        List<String> tempList = Arrays.asList(ingList.split(","));
        List<Integer> intList = new ArrayList<>();
        for(String s : tempList) intList.add(Integer.valueOf(s));
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
        List<Recipe> result = new ArrayList<>();
        if(intList.size()!=0) {
            List<Ingredient> toBeSearched = (List<Ingredient>) ingredientRepository.findAllById(intList);
            result = searchAlgorithms.searchByIng(recipeRepository.findAll(), toBeSearched);

        }
        Collections.sort(result);
       // model.addAttribute("ed",ingList);
        model.addAttribute("res",result);
        return "search/recipeIng";
    }
    @GetMapping("recipe")
    public String searchRecipe(HttpServletRequest request, Model model){
        Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        return "search/recipe";
    }
    @PostMapping("recipe")
    public String SearchRecipeResult(Model model,@RequestParam String searchword,HttpServletRequest request){
       //finding user
        Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("user",theUser);
       //
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
        List<Recipe> result = new ArrayList<>();
        result =  searchAlgorithms.searchRecipe(recipeRepository.findAll(),searchword);
       model.addAttribute("res",result);
       return "search/recipe";
    }




}
