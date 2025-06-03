package edu.badpals.bobesponjau4.service;


import edu.badpals.bobesponjau4.model.Workplace;
import edu.badpals.bobesponjau4.repository.WorkplaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class WorkplaceService {

    @Autowired
    private WorkplaceRepository workplaceRepository;

    public List<Workplace> getAllWorkplaces() {
        return workplaceRepository.findAll();
    }

    public Optional<Workplace> getWorkplaceById(Integer id) {
        return workplaceRepository.findById(id);
    }

    public Workplace saveWorkplace(Workplace workplace) {
        return workplaceRepository.save(workplace);
    }

    public void deleteWorkplace(Integer id) {
        workplaceRepository.deleteById(id);
    }
}

