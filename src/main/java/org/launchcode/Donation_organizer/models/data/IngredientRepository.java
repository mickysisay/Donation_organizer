package org.launchcode.Donation_organizer.models.data;

import org.launchcode.Donation_organizer.models.Ingredient;
import org.launchcode.Donation_organizer.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
   @Override
    List<Ingredient> findAll();
}
