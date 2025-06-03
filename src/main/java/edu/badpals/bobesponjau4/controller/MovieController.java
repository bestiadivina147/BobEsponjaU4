package edu.badpals.bobesponjau4.controller;


import edu.badpals.bobesponjau4.model.Movie;
import edu.badpals.bobesponjau4.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies") // Ruta base
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/{id}")
    public String getMovieById(@PathVariable Integer id, Model model) {
        Optional<Movie> movie = movieService.getMovieById(id);
        movie.ifPresent(m -> model.addAttribute("movie", m));
        return "movie-detalle";
    }

    @PostMapping("/guardar")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}

