package org.launchcode.Donation_organizer.models;

import java.util.ArrayList;

public class IngredientList {
  private static ArrayList<String> ingredients = new ArrayList<>();
   public IngredientList(){
       ingredients.add("Butter");
       ingredients.add("Olive oil");
       ingredients.add("potato");
       ingredients.add("pasta");
   }

    public static ArrayList<String> getIngredients() {
        return ingredients;
    }
}
