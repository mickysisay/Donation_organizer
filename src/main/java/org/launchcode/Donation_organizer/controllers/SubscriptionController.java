package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("subscription")
@Controller
public class SubscriptionController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @GetMapping("")
    public String getSubscription(Model model,HttpServletRequest request){
        Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("user",theUser);
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        List<User> allUsers = (List<User>) userRepository.findAllById(theUser.getSubscription());
        List<Recipe> allRecipes = new ArrayList<>();
        for(int i=0;i<allUsers.size();i++){
            allRecipes.addAll(allUsers.get(i).getRecipe());
        }
        Comparator<Recipe> newOrder =  new Comparator<Recipe>() {
            public int compare(Recipe r1, Recipe r2) {
                if(r1.getTimeCreateDate()>r2.getTimeCreateDate()){
                    return -1;
                }else if(r1.getTimeCreateDate()<r2.getTimeCreateDate()){
                    return 1;
                }else{
                 return 0;
                }
               // return  r1.getTimeCreateDate() - r2.getTimeCreateDate();
            }
        };

        Collections.sort(allRecipes,newOrder);
        model.addAttribute("res", allRecipes);
        return "list/list-recipes";
    }
    @GetMapping("saved")
    public String savedRecipes(Model model,HttpServletRequest request){
        Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        List<Recipe> allRecipes = (List<Recipe>) recipeRepository.findAllById(theUser.getSavedRecipes());
        model.addAttribute("user",theUser);
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        model.addAttribute("res",allRecipes);
        return "list/list-recipes";
    }
}
