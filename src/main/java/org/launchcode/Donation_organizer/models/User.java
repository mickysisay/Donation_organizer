package org.launchcode.Donation_organizer.models;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Recipe> recipe= new ArrayList<>();
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn

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