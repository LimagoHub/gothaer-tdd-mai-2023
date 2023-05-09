package de.gothaer.persistence;

import java.util.List;
import java.util.Optional;

public interface PersonenRepository {

    void save(Person person);  // Create
    boolean update(Person person); // Update
    boolean delete(Person person); // Delete
    boolean deleteById(String id); // Delete

    Optional<Person> findById(String id); // Read

    List<Person> findAlL(); // Read
}
