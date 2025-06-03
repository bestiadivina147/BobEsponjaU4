package edu.badpals.bobesponjau4.repository;

import edu.badpals.bobesponjau4.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
}
