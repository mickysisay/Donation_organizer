package org.launchcode.Donation_organizer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Recipe extends AbstractEntity implements Comparable<Recipe> {

    @NotBlank
    @Size(min = 1, max = 5000, message = "instruction should be between 100 and 5000")
    private String instruction;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    //dates
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    public long getTimeCreateDate(){
        return createDate.getTime();
    }
    public String getCreateDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
       String output="Posted on ";
       output+= dateFormat.format(createDate);
       output+= " at ";
       output+= timeFormat.format(createDate);
     return output;
    }
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    //end dates
    //score
    private double score=0;
    public double getScore(){

   long diff = (this.createDate.getTime()-new Date().getTime())/1000;


       double something =(double) diff/45000;
    score = Math.log10(upvotedUsers.size())+ something;
    return score;
    }
    @Override
    public int compareTo(Recipe u) {
        if(this.getScore()>u.getScore())
            return -1;
        else if(this.getScore()<u.getScore())
            return 1;
        return 0;
    }
    //scoreEnd
    //list of people who upvoted it
   // @ManyToMany(mappedBy = "upvotedRecipes")
    @ElementCollection
    private List<Integer> upvotedUsers = new ArrayList<>();
    @JsonIgnore
    public List<Integer> getUpvotedUsers() {
        return upvotedUsers;
    }
    public void removeUpvotingUser(User upvotingUser) {

      int ind = upvotedUsers.indexOf(upvotingUser.getId());
        upvotedUsers.remove(ind);

    }
    public void addUpvotingUser(User upvotingUser) {
        upvotedUsers.add(upvotingUser.getId());
    }
    public boolean hasUserUpvoted(User user){
        return upvotedUsers.contains(user.getId());
    }
    public int upvoteCounter(){
        return upvotedUsers.size();
    }
    //end of list
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
     String allIng="Ingredients include : ";
        Collections.sort(ingredients);
   for(int i=0;i<ingredients.size();i++){

           allIng += ingredients.get(i).getName();
       if(i!=ingredients.size()-1) {
           allIng+=",";
       }
   }
   return allIng;
    }

}
