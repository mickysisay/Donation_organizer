package org.launchcode.Donation_organizer.models.data;

import org.launchcode.Donation_organizer.models.Item;
import org.launchcode.Donation_organizer.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
