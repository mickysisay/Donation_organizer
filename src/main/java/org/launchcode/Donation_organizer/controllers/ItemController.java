package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Ingredient;
import org.launchcode.Donation_organizer.models.IngredientList;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.IngredientRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("look")
@RestController
public class ItemController {
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("searchItem")
    public List<Ingredient> addItem(@RequestParam String searchWord){
      List<Ingredient> allFound= ingredientRepository.findAll();
     List<Ingredient> found = new ArrayList<>();
        if(!searchWord.isEmpty()) {
     for(int i=0;i<allFound.size();i++){
       if(allFound.get(i).toString().toLowerCase().contains(searchWord.toLowerCase())){
           found.add(allFound.get(i));
       }
       if(found.size()>10){
           break;
       }
     }}
     return found;
    }
    @PostMapping("addItem")
    public String addIngRest(@RequestParam String ingredientName){
        if(ingredientName.trim().length()>3){
           Ingredient ingredient = new Ingredient();
           ingredient.setName(ingredientName);
           ingredientRepository.save(ingredient);
           return "ingredient added succesfully";
        }else{
            return "there was a problem adding ingredient";
        }
    }

    @GetMapping("subscribe")

    public String subscriptionAdder(@RequestParam Integer userId, HttpServletRequest request, Model model){
        try{
            Integer subbingUserId = (Integer) request.getSession().getAttribute("user");
            User user = userRepository.findById(userId).get();
            User addingUser = userRepository.findById(subbingUserId).get();
            addingUser.editSubscription(user.getId());
            userRepository.save(addingUser);
            return "success";
        }catch(Exception e){
            return "fail";
        }


    }


}
