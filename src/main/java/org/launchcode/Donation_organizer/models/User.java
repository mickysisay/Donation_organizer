package org.launchcode.Donation_organizer.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;
     //saved recipes
    @ElementCollection
   private List<Integer> savedRecipes = new ArrayList<>();
    public List<Integer> getSavedRecipes(){
        return savedRecipes;
    }
    public boolean isRecipeSaved(int recipeId){
        return this.savedRecipes.contains(recipeId);
    }
    public void editSavedRecipes(int recipeId){
        if(isRecipeSaved(recipeId)){
           this.savedRecipes.remove(this.savedRecipes.indexOf(recipeId));
        }else{
            this.savedRecipes.add(recipeId);
        }
    }
    //end of saved recipes
    //user subscription
     @ElementCollection
     List <Integer> subscription = new ArrayList<>();

    public List<Integer> getSubscription() {
        return subscription;
    }

    public void editSubscription(int subscribed) {
        if(isSubscribedTo(subscribed)){
            this.subscription.remove(this.subscription.indexOf(subscribed));
        }else{
            this.subscription.add(subscribed);
        }
    }
    public boolean isSubscribedTo(int userId){
        return this.subscription.contains(userId);
    }

    //user subscription
    @NotNull
    private String pwHash;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Recipe> recipe= new ArrayList<>();
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn
    @JsonIgnore
    public List<Recipe> getRecipe() {
        return recipe;
    }

    public void setItems(List<Recipe> items) {
        this.recipe = recipe;
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
    public void UpdateUser(Recipe item){
        recipe.add(item);
    }

}