package org.launchcode.Donation_organizer.models.data;

import org.launchcode.Donation_organizer.models.Item;
import org.launchcode.Donation_organizer.models.Recipe;
import org.launchcode.Donation_organizer.models.User;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
