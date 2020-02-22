package org.launchcode.Donation_organizer.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Recipe extends AbstractEntity  {


    @ManyToOne
    private User user;
    @NotBlank
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addItem(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }
    @ManyToMany
    private List<Ingredient> ingredients= new ArrayList<>();
    public Recipe(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
     String allIng="";
        Collections.sort(ingredients);
   for(int i=0;i<ingredients.size();i++){
       allIng+=ingredients.get(i).getName();
   }
   return allIng;
    }

}