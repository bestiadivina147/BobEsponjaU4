package edu.badpals.bobesponjau4.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Integer id;
    private String title;
    private Integer releaseYear;
    private String description;
    private Set<Integer> personajesIds; // Solo IDs de personajes para evitar ciclos
}

