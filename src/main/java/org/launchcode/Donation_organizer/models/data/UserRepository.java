package org.launchcode.Donation_organizer.models.data;


import org.launchcode.Donation_organizer.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    @Override
    List<User> findAll();
}

