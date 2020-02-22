package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Ingredient;
import org.launchcode.Donation_organizer.models.IngredientList;
import org.launchcode.Donation_organizer.models.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    IngredientRepository ingredientRepository;
    @PostMapping("addItem")
    public List<Ingredient> addItem(@RequestParam String itemName){
      List<Ingredient> allFound= ingredientRepository.findAll();
     List<Ingredient> found = new ArrayList<>();
     for(int i=0;i<allFound.size();i++){
       if(allFound.get(i).toString().toLowerCase().contains(itemName.toLowerCase())){
           found.add(allFound.get(i));
       }
       if(found.size()>10){
           break;
       }
     }
     return found;
    }


}
