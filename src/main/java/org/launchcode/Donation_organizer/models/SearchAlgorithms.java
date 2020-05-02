package org.launchcode.Donation_organizer.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchAlgorithms {
    public List<User> searchUser(List<User> allUsers,String searchWord){
       List<User> results=new ArrayList<>();
        for(int i=0;i<allUsers.size();i++){
         if(allUsers.get(i).getUsername().toLowerCase().contains(searchWord.toLowerCase())){
            results.add(allUsers.get(i));
         }
         if(results.size()>=3){
             break;
         }
        }
        return results;
    }
    public List<Recipe> searchRecipe(List<Recipe> allRecipes,String searchWord){
        List<Recipe> results=new ArrayList<>();
        for(int i=0;i<allRecipes.size();i++){
            if(allRecipes.get(i).getName().toLowerCase().contains(searchWord.toLowerCase())){
                results.add(allRecipes.get(i));
            }
            if(results.size()>15){
                break;
            }
        }
        return results;

    }
    public List<Recipe> searchByIng(List<Recipe> allRecipes,List<Ingredient> ing){
        List<Recipe> results = new ArrayList<>();
        for(int i=0;i<allRecipes.size();i++){

            if(allRecipes.get(i).getIngredients().size()== ing.size() && allRecipes.get(i).getIngredients().containsAll(ing)){
                results.add(allRecipes.get(i));

            }else if(ing.containsAll(allRecipes.get(i).getIngredients())){
                results.add(allRecipes.get(i));
            }
            if(results.size()>15){
                break;
            }
            //start
            Comparator<Recipe> newOrder =  new Comparator<Recipe>() {
                public int compare(Recipe r1, Recipe r2) {
                    if(r1.getIngredients().size() >r2.getIngredients().size()){
                        return -1;
                    }else if(r1.getIngredients().size()<r2.getIngredients().size()){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            };

            Collections.sort(results,newOrder);
            //end
        }
        return results;
    }
}
