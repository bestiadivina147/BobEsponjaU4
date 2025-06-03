package edu.badpals.bobesponjau4.repository;


import edu.badpals.bobesponjau4.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {
}

