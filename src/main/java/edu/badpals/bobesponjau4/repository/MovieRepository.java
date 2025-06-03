package edu.badpals.bobesponjau4.repository;

import edu.badpals.bobesponjau4.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
