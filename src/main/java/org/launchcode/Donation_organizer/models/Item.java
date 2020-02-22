package org.launchcode.Donation_organizer.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Item extends AbstractEntity {
    @NotBlank
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;
    @ManyToOne
    private Recipe recipe;


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public Item(String aName){
        this.name = aName;
    }
    public Item(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
