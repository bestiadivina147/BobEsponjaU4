package edu.badpals.bobesponjau4.repository;

import edu.badpals.bobesponjau4.model.Movie;
import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    List<Personaje> findByWorkplace(Workplace workplace);
    List<Personaje> findByMoviesContains(Movie movie);
}
