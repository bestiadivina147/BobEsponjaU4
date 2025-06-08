// PersonajeServiceTest.java
package edu.badpals.bobesponjau4.service;

import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.model.Workplace;
import edu.badpals.bobesponjau4.repository.MovieRepository;
import edu.badpals.bobesponjau4.repository.PersonajeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonajeServiceTest {

    @Mock
    private PersonajeRepository personajeRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private PersonajeService personajeService;

    private Personaje personaje;
    private Workplace wp;

    @BeforeEach
    void setUp() {
        wp = new Workplace();
        wp.setId(5);
        personaje = new Personaje();
        personaje.setId(1);
        personaje.setName("Bob");
        personaje.setWorkplace(wp);
    }

    @Test
    void getAllPersonajes_ReturnsList() {
        when(personajeRepository.findAll()).thenReturn(List.of(personaje));
        List<Personaje> list = personajeService.getAllPersonajes();
        assertThat(list).containsExactly(personaje);
    }

    @Test
    void getPersonajeById_Existing() {
        when(personajeRepository.findById(1)).thenReturn(Optional.of(personaje));
        Optional<Personaje> opt = personajeService.getPersonajeById(1);
        assertThat(opt).isPresent().get().isEqualTo(personaje);
    }

    @Test
    void getPersonajeById_NotFound() {
        when(personajeRepository.findById(2)).thenReturn(Optional.empty());
        assertThat(personajeService.getPersonajeById(2)).isEmpty();
    }

    @Test
    void savePersonaje_CallsRepo() {
        when(personajeRepository.save(personaje)).thenReturn(personaje);
        Personaje saved = personajeService.savePersonaje(personaje);
        assertThat(saved).isEqualTo(personaje);
        verify(personajeRepository).save(personaje);
    }

    @Test
    void deletePersonaje_CallsRepoDeleteById() {
        personajeService.deletePersonaje(1);
        verify(personajeRepository).deleteById(1);
    }

    @Test
    void getPersonajesByWorkplace_ReturnsFiltered() {
        when(personajeRepository.findByWorkplace(wp)).thenReturn(List.of(personaje));
        List<Personaje> filtered = personajeService.getPersonajesByWorkplace(wp);
        assertThat(filtered).containsExactly(personaje);
    }
}
