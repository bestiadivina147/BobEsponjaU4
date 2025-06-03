package edu.badpals.bobesponjau4.service;

import edu.badpals.bobesponjau4.model.Movie;
import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        // Desvincula la película de todos los personajes
        for (Personaje personaje : new HashSet<>(movie.getPersonajes())) {
            personaje.getMovies().remove(movie); // Eliminar desde el lado Personaje
        }

        movie.getPersonajes().clear(); // Limpiar también desde el lado Movie

        movieRepository.delete(movie);
    }

}

