package eci.aygo.eciuber.model.db;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import eci.aygo.eciuber.model.User;

@Repository
public interface DynamoDBUserRepository<T extends User> {
    // Core CRUD operations with the generic type T
    T save(T item);
    Optional<User> findById(String id);
    List<T> findAll();
    void deleteById(String id);  // Changed from delete for consistency
    List<T> findByType(String type);
	void delete(String id);
}
