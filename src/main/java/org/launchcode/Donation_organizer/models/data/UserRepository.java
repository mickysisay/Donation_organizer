package org.launchcode.Donation_organizer.models.data;


import org.launchcode.Donation_organizer.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}

