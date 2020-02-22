package org.launchcode.Donation_organizer.models;

import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithms {
    public List<User> searchUser(List<User> allUsers,String searchWord){
       List<User> results=new ArrayList<>();
        for(int i=0;i<allUsers.size();i++){
         if(allUsers.get(i).getUsername().contains(searchWord)){
            results.add(allUsers.get(i));
         }
         if(results.size()>10){
             break;
         }
        }
        return results;
    }
}
