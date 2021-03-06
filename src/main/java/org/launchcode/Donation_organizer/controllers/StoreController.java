package org.launchcode.Donation_organizer.controllers;


import org.launchcode.Donation_organizer.models.*;
import org.launchcode.Donation_organizer.models.data.IngredientRepository;
import org.launchcode.Donation_organizer.models.data.ItemRepository;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@RequestMapping("store")
@Controller
public class StoreController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("")
    public String Store(Model model, HttpServletRequest request){
   Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
   User theUser = (User) option.get();
   model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
       model.addAttribute("user",theUser);
        model.addAttribute(new Recipe());
        model.addAttribute("items",theUser.getRecipe());
        return "storehome";
    }
   //save fail
   @RequestMapping(value = "/save" ,method = RequestMethod.POST)
   public String failSave(@ModelAttribute @Valid Recipe newRecipe, Errors errors,
                          HttpServletRequest request, Model model){
        model.addAttribute("fail","sorry you have to add ingredients");
       return "addRecipe";
    }


    @RequestMapping(value = "/save" ,method = RequestMethod.POST,params = "ingredients")
    public String processAddEmployerForm(
            @ModelAttribute @Valid Recipe newRecipe, Errors errors,
            HttpServletRequest request, Model model,
            @RequestParam List<Integer> ingredients) {

        if (errors.hasErrors()) {
            return "addRecipe";
        }
        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        List<Ingredient> ingredients1 = (List<Ingredient>) ingredientRepository.findAllById(ingredients);
       for(int i=0;i<ingredients1.size();i++){
           ingredients1.get(i).setVotes(ingredients1.get(i).getVotes()+1);
           ingredientRepository.save(ingredients1.get(i));
       }
        newRecipe.setIngredients(ingredients1);
        theUser.UpdateUser(newRecipe);
        newRecipe.setUser(theUser);
        recipeRepository.save(newRecipe);
        userRepository.save(theUser);



        return "redirect:";
    }
    //edit
    @GetMapping("edit")
    public String editController(@RequestParam Integer recipeId, HttpServletRequest request, Model model ){
    Recipe recipe= new Recipe();

        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        recipe = recipeRepository.findById(recipeId).get();
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        if(recipe.getUser().getId() == theUser.getId()){
            model.addAttribute("recipe",recipe);
            return "edit/editRecipe";
        }
        return "redirect:";
    }

    @PostMapping("edit")
    public String editPostController(
            @ModelAttribute @Valid Recipe newRecipe, Errors errors,
            HttpServletRequest request, Model model,
            @RequestParam List<Integer> ingredients,@RequestParam Integer recipeId) {

        if (errors.hasErrors()) {
            return "edit/editRecipe";
        }
        Recipe recipe = new Recipe();


        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        recipe = recipeRepository.findById(recipeId).get();
        if(theUser.getId() == recipe.getUser().getId()) {
            List<Ingredient> ingredients1 = (List<Ingredient>) ingredientRepository.findAllById(ingredients);
            recipe.setIngredients(ingredients1);
            recipe.setName(newRecipe.getName());
            recipe.setInstruction(newRecipe.getInstruction());
            recipeRepository.save(recipe);
        }

        return "redirect:";
    }

    //edit end
    //delete
    @GetMapping("delete")
    public String deleteController(@RequestParam Integer recipeId, HttpServletRequest request, Model model ){
        Recipe recipe= new Recipe();

        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        recipe = recipeRepository.findById(recipeId).get();

        if(recipe.getUser().getId() == theUser.getId()){
            recipeRepository.deleteById(recipeId);
        }
        return "redirect:";
    }

    //delete end
    @GetMapping("/addRecipe")
    public String addRecipe(HttpServletRequest request, Model model){
        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        model.addAttribute(new Recipe());
       model.addAttribute("ingredients",ingredientRepository.findAll());

   return "addRecipe";
    }
    @GetMapping("/addItem")
    public String addItemGet(Model model){

        model.addAttribute(new Ingredient());
        return "addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute @Valid Ingredient newIngredient,
                          Errors errors,HttpServletRequest request, Model model){

        if (errors.hasErrors()) {
            return "addItem";
        }
      ingredientRepository.save(newIngredient);
        return "addItem";
    }
    //popular subscription
    @GetMapping("/popular")
    public String showPopular(Model model,HttpServletRequest request){
        Integer id = (Integer) request.getSession().getAttribute("reverseRecipeUser");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        model.addAttribute("user",theUser);
        model.addAttribute("subUsers", userRepository.findAllById(theUser.getSubscription()));
        List <Recipe> allRecipe = recipeRepository.findAll();
        Collections.sort(allRecipe);
        model.addAttribute("res", allRecipe);
        return "list/list-recipes.html";

    }

}
