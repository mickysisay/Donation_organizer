package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.RecipeRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("upvote")
@RestController
public class UpvoteController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @GetMapping("")
    public List<String> upvotePostController(@RequestParam Integer recipeId, HttpServletRequest request, Model model){
        Integer userId = (Integer) request.getSession().getAttribute("user");
        User user = new User();
      Recipe recipe= new Recipe();
        List<String> response = new ArrayList<>();
      try {
          user = userRepository.findById(userId).get();
          recipe = recipeRepository.findById(recipeId).get();
      }catch(Exception e){
         response.add("failed");
          return response;
      }
      if(!recipe.hasUserUpvoted(user)){
          recipe.addUpvotingUser(user);
          user.addUpvotedRecipe(recipe);
          recipeRepository.save(recipe);
          userRepository.save(user);
          response.add("upvoted");
         response.add(Integer.toString(recipe.upvoteCounter()));
          return response;
      }else{
          recipe.removeUpvotingUser(user);
          user.removeUpvotedRecipe(recipe);
          recipeRepository.save(recipe);
          userRepository.save(user);
          response.add("removed");
          response.add(Integer.toString(recipe.upvoteCounter()));
          return response;
      }
    }
}
