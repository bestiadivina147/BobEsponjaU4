package edu.badpals.bobesponjau4.service;

import edu.badpals.bobesponjau4.model.*;
import edu.badpals.bobesponjau4.repository.PersonajeRepository;
import edu.badpals.bobesponjau4.repository.MovieRepository;
import edu.badpals.bobesponjau4.repository.UsuarioRepository;
import edu.badpals.bobesponjau4.repository.WorkplaceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("dev") // Solo se ejecutará en el perfil de desarrollo
public class DbH2Init {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private WorkplaceRepository workplaceRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    // Se ejecuta tras crear el contexto de la aplicación para inicializar la base de datos
    @PostConstruct
    public void initDatabase() {
        initWorkplaces();
        initPersonajes();
        initMovies();
        assignMoviesToCharacters();
        initUsuarios();
    }

    private void initWorkplaces() {
        List<Workplace> workplaces = List.of(
                new Workplace(null, "El Crustáceo Cascarudo", "Fondo de Bikini", "Famoso restaurante de hamburguesas", new HashSet<>()),
                new Workplace(null, "El Balde de Carnada", "Fondo de Bikini", "Restaurante rival, dirigido por Plankton", new HashSet<>()),
                new Workplace(null, "Casa de Calamardo", "Fondo de Bikini", "Lugar donde Calamardo disfruta de su soledad", new HashSet<>()),
                new Workplace(null, "Casa de Patricio", "Fondo de Bikini", "Debajo de una roca, donde Patricio descansa", new HashSet<>()),
                new Workplace(null, "Casa de Bob Esponja", "Fondo de Bikini", "Piña icónica con muebles submarinos", new HashSet<>()),
                new Workplace(null, "Laboratorio de Plankton", "Fondo de Bikini", "Centro de operaciones para robar la receta", new HashSet<>()),
                new Workplace(null, "Bote Escuela", "Fondo de Bikini", "Escuela de conducción de la Sra. Puff", new HashSet<>()),
                new Workplace(null, "Banco de Fondo de Bikini", "Fondo de Bikini", "El principal banco del área", new HashSet<>()),
                new Workplace(null, "Fondo de Bikini Gym", "Fondo de Bikini", "Gimnasio submarino de entrenamiento", new HashSet<>()),
                new Workplace(null, "Parque de Medusas", "Fondo de Bikini", "Lugar donde Bob Esponja caza medusas", new HashSet<>()),
                new Workplace(null, "Cueva de Tritón Man", "Fondo de Bikini", "Refugio de los héroes Tritón Man y Percebe", new HashSet<>()),
                new Workplace(null, "Supermercado Marítimo", "Fondo de Bikini", "Lugar de compras para los habitantes", new HashSet<>()),
                new Workplace(null, "Zoo Marino", "Fondo de Bikini", "Hábitat de criaturas marinas exóticas", new HashSet<>()),
                new Workplace(null, "Estación de Radio Bikini FM", "Fondo de Bikini", "Transmisión de música submarina", new HashSet<>()),
                new Workplace(null, "Museo de Fondo de Bikini", "Fondo de Bikini", "Exhibición de historia submarina", new HashSet<>()),
                new Workplace(null, "Playas de Fondo de Bikini", "Fondo de Bikini", "Zona de descanso y recreación", new HashSet<>()),
                new Workplace(null, "Centro Científico Marino", "Fondo de Bikini", "Donde Arenita investiga el océano", new HashSet<>()),
                new Workplace(null, "Hotel Coral", "Fondo de Bikini", "Lugar de descanso para visitantes", new HashSet<>()),
                new Workplace(null, "Estación de Policía", "Fondo de Bikini", "Centro de seguridad de la comunidad", new HashSet<>()),
                new Workplace(null, "Gran Mercado Submarino", "Fondo de Bikini", "El mejor lugar para comprar productos exclusivos", new HashSet<>())
        );

        workplaceRepository.saveAll(workplaces);
    }

    private void initPersonajes() {
        List<Personaje> personajes = List.of(
                new Personaje(null, "Bob Esponja", "Esponja", 20, "Cocinero optimista", getWorkplace(1), new HashSet<>()),
                new Personaje(null, "Patricio Estrella", "Estrella de Mar", 22, "Amigo torpe de Bob", getWorkplace(4), new HashSet<>()),
                new Personaje(null, "Calamardo Tentáculos", "Pulpo", 30, "Malhumorado amante de la música", getWorkplace(3), new HashSet<>()),
                new Personaje(null, "Plankton", "Copépodo", 45, "Villano que quiere la receta secreta", getWorkplace(6), new HashSet<>()),
                new Personaje(null, "Arenita Mejillas", "Ardilla", 28, "Científica y luchadora", getWorkplace(17), new HashSet<>()),
                new Personaje(null, "Tritón Man", "Humano", 70, "Héroe submarino retirado", getWorkplace(11), new HashSet<>()),
                new Personaje(null, "Percebe", "Crustáceo", 65, "Compañero de Tritón Man", getWorkplace(11), new HashSet<>()),
                new Personaje(null, "Sra. Puff", "Pez Globo", 50, "Profesora de conducción", getWorkplace(7), new HashSet<>()),
                new Personaje(null, "Larry la Langosta", "Langosta", 35, "Entrenador de gimnasio", getWorkplace(9), new HashSet<>()),
                new Personaje(null, "Don Cangrejo", "Cangrejo", 60, "Dueño del Crustáceo Cascarudo", getWorkplace(1), new HashSet<>()),
                new Personaje(null, "Karen", "Computadora", 40, "Esposa de Plankton y asistente AI", getWorkplace(6), new HashSet<>()),
                new Personaje(null, "Burbuja", "Burbuja parlante", 10, "Amigo de Bob", null, new HashSet<>()),
                new Personaje(null, "Mantarraya", "Raya", 45, "Villano ocasional", null, new HashSet<>()),
                new Personaje(null, "Medusas", "Criatura marina", 5, "Criaturas eléctricas", null, new HashSet<>()),
                new Personaje(null, "Rey Neptuno", "Dios del mar", 1000, "Gobernante del océano", null, new HashSet<>()),
                new Personaje(null, "Chico Percebe", "Humano", 18, "Superhéroe aprendiz", getWorkplace(11), new HashSet<>()),
                new Personaje(null, "Gary", "Caracol", 5, "Mascota de Bob", getWorkplace(5), new HashSet<>()),
                new Personaje(null, "Artistas del Circo Marino", "Varios", 30, "Actúan en eventos", null, new HashSet<>()),
                new Personaje(null, "Bote Fantasma", "Espectro", 500, "Barco embrujado", null, new HashSet<>()),
                new Personaje(null, "Criatura Abisal", "Ser desconocido", 200, "Misterioso ser de las profundidades", null, new HashSet<>())
        );

        personajeRepository.saveAll(personajes);
    }

    private Workplace getWorkplace(Integer id) {
        Optional<Workplace> workplace = workplaceRepository.findById(id);
        return workplace.orElse(null);
    }

    private void initMovies() {
        List<Movie> movies = List.of(
                new Movie(null, "Bob Esponja: La Película", 2004, "Bob y Patricio en una gran aventura", new HashSet<>()),
                new Movie(null, "Bob Esponja: Un Héroe Fuera del Agua", 2015, "Los héroes viajan fuera del océano", new HashSet<>()),
                new Movie(null, "Bob Esponja: Al Rescate", 2020, "Bob busca a su mascota perdida", new HashSet<>()),
                new Movie(null, "La Venganza de Plankton", 2022, "Plankton crea un nuevo plan", new HashSet<>()),
                new Movie(null, "Las Locuras de Fondo de Bikini", 2018, "Día a día de los personajes", new HashSet<>()),
                new Movie(null, "Calamardo: El Musical", 2019, "Historia de Calamardo en Broadway", new HashSet<>()),
                new Movie(null, "Las Aventuras de Tritón Man", 2021, "El pasado del superhéroe", new HashSet<>()),
                new Movie(null, "El Crustáceo Cascarudo en Crisis", 2017, "Problemas económicos de Don Cangrejo", new HashSet<>()),
                new Movie(null, "El Misterio del Balde de Carnada", 2016, "Secretos del restaurante de Plankton", new HashSet<>()),
                new Movie(null, "Bob Esponja y los Medusas", 2023, "Batalla épica con medusas salvajes", new HashSet<>())
        );

        movieRepository.saveAll(movies);
    }

    private void assignMoviesToCharacters() {
        Set<Integer[]> relaciones = Set.of(
                new Integer[]{1, 1}, new Integer[]{2, 1}, new Integer[]{3, 1}, new Integer[]{4, 1},
                new Integer[]{1, 2}, new Integer[]{2, 2}, new Integer[]{3, 2}, new Integer[]{1, 3},
                new Integer[]{5, 3}, new Integer[]{6, 3}, new Integer[]{7, 4}, new Integer[]{8, 4},
                new Integer[]{1, 5}, new Integer[]{2, 5}, new Integer[]{3, 5}, new Integer[]{4, 5},
                new Integer[]{9, 6}, new Integer[]{10, 6}, new Integer[]{11, 6}, new Integer[]{12, 7}
        );

        for (Integer[] relacion : relaciones) {
            Optional<Personaje> personajeOpt = personajeRepository.findById(relacion[0]);
            Optional<Movie> movieOpt = movieRepository.findById(relacion[1]);

            if (personajeOpt.isPresent() && movieOpt.isPresent()) {
                Personaje personaje = personajeOpt.get();
                Movie movie = movieOpt.get();

                personaje.getMovies().add(movie);
                personajeRepository.save(personaje);
            }
        }
    }

    private void initUsuarios() {
        List<Usuario> usuarios = List.of(
                new Usuario(null,"a@a.com", "a", "a", new Date(1999, 1, 1)),
                new Usuario(null,"bob@example.com", "Bob Esponja", "bob123", new Date(1999, 1, 1)),
                new Usuario(null,"patricio@example.com", "Patricio Estrella", "estrella456", new Date(1998, 5, 21)),
                new Usuario(null,"calamardo@example.com", "Calamardo Tentáculos", "tentaculos789", new Date(1992, 8, 15)),
                new Usuario(null,"arenita@example.com", "Arenita Mejillas", "ardilla321", new Date(1995, 3, 10)),
                new Usuario(null,"don_cangrejo@example.com", "Don Cangrejo", "dinero999", new Date(1980, 11, 25))
        );

        usuarioRepository.saveAll(usuarios);
    }


}
