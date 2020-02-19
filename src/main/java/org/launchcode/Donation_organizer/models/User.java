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
    private List<Item> items= new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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
    public void UpdateUser(Item item){
        items.add(item);
    }

}