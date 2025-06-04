package edu.badpals.bobesponjau4.service;

import edu.badpals.bobesponjau4.model.Movie;
import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.model.Workplace;
import edu.badpals.bobesponjau4.repository.MovieRepository;
import edu.badpals.bobesponjau4.repository.PersonajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Personaje> getAllPersonajes() {
        return personajeRepository.findAll();
    }

    public Optional<Personaje> getPersonajeById(Integer id) {
        return personajeRepository.findById(id);
    }

    public Personaje savePersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    public void deletePersonaje(Integer id) {
        personajeRepository.deleteById(id);
    }
    public List<Personaje> getPersonajesByWorkplace(Workplace workplace) {
        return personajeRepository.findByWorkplace(workplace);
    }


}

