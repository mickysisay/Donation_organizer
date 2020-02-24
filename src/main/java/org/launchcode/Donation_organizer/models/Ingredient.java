package org.launchcode.Donation_organizer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient extends AbstractEntity implements Comparable<Ingredient> {
    @NotBlank
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> Recipe= new ArrayList<>();

    @JsonIgnore
    public List<org.launchcode.Donation_organizer.models.Recipe> getRecipe() {
        return Recipe;
    }

    public void setRecipe(List<org.launchcode.Donation_organizer.models.Recipe> recipe) {
        Recipe = recipe;
    }

    private int votes;



    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient (){
        votes=0;
    }
    @Override
    public String toString(){
        return name;
    }
    @Override
    public int compareTo(Ingredient o1) {
        return o1.getName().compareTo(this.getName());
    }

}
