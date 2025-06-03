package edu.badpals.bobesponjau4.controller;


import edu.badpals.bobesponjau4.model.Movie;
import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.service.MovieService;
import edu.badpals.bobesponjau4.service.PersonajeService;
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

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("/list")
    public String listMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/")
    public String getMovieById(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/movies/list"; // Si el ID no es válido, redirige al listado
        }

        Optional<Movie> movieOptional = movieService.getMovieById(id);
        if (movieOptional.isPresent()) {
            model.addAttribute("movie", movieOptional.get());
            List<Personaje> personajes = personajeService.getAllPersonajes();
            model.addAttribute("personajes", personajes);
            return "editPelicula";
        }

        return "redirect:/movies/list";
    }

    @PostMapping("/editar")
    public String updatePelicula(@ModelAttribute Movie movie) {
        if (movie.getId() == null) {
            return "redirect:/movies/list"; // Si el ID es nulo, vuelve al listado
        }

        Optional<Movie> movieOp = movieService.getMovieById(movie.getId());
        if (movieOp.isPresent()) {
            Movie movieExistente = movieOp.get();
            movieExistente.setTitle(movie.getTitle());
            movieExistente.setReleaseYear(movie.getReleaseYear());
            movieExistente.setDescription(movie.getDescription());

            movieService.saveMovie(movieExistente); // Guarda los cambios
            return "redirect:/movies/list"; // Redirige al listado después de actualizar
        }

        return "redirect:/movies/list"; // Si el movie no existe, vuelve al listado
    }

    @GetMapping("/peliculaform")
    public String showPeliculaForm(Model model) {
        model.addAttribute("movie", new Movie()); // Objeto vacío para el formulario
        return "peliculaform"; // Devuelve la plantilla
    }

    @PostMapping("/guardar")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/movies/list";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return "redirect:/movies/list";
    }
}

