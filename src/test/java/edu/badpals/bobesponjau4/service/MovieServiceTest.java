package edu.badpals.bobesponjau4.service;

import edu.badpals.bobesponjau4.model.Movie;
import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie;
    private Personaje p1, p2;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1);
        movie.setTitle("Test Movie");
        movie.setDescription("Desc");

        p1 = new Personaje();
        p1.setId(10);
        p2 = new Personaje();
        p2.setId(20);

        // asociamos dos personajes
        movie.getPersonajes().add(p1);
        movie.getPersonajes().add(p2);
        p1.getMovies().add(movie);
        p2.getMovies().add(movie);
    }

    @Test
    void getAllMovies_ReturnsListFromRepo() {
        when(movieRepository.findAll()).thenReturn(List.of(movie));
        List<Movie> all = movieService.getAllMovies();
        assertThat(all).hasSize(1).containsExactly(movie);
        verify(movieRepository).findAll();
    }

    @Test
    void getMovieById_Existing_ReturnsOptional() {
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        Optional<Movie> opt = movieService.getMovieById(1);
        assertThat(opt).isPresent().get().isEqualTo(movie);
    }

    @Test
    void getMovieById_NotFound_ReturnsEmpty() {
        when(movieRepository.findById(99)).thenReturn(Optional.empty());
        Optional<Movie> opt = movieService.getMovieById(99);
        assertThat(opt).isEmpty();
    }

    @Test
    void saveMovie_CallsRepoSave() {
        when(movieRepository.save(movie)).thenReturn(movie);
        Movie saved = movieService.saveMovie(movie);
        assertThat(saved).isEqualTo(movie);
        verify(movieRepository).save(movie);
    }

    @Test
    void deleteMovie_RemovesAssociationsAndDeletes() {
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        movieService.deleteMovie(1);

        // Los personajes debieron quitar la película de su Set
        assertThat(p1.getMovies()).doesNotContain(movie);
        assertThat(p2.getMovies()).doesNotContain(movie);
        // La película debió vaciar su propio Set
        assertThat(movie.getPersonajes()).isEmpty();
        // Finalmente se llama a delete()
        verify(movieRepository).delete(movie);
    }

    @Test
    void deleteMovie_NotFound_Throws() {
        when(movieRepository.findById(2)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> movieService.deleteMovie(2))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("no encontrada");
    }
}
