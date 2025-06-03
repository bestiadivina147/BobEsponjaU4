package edu.badpals.bobesponjau4.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeDTO {

    private Integer id;
    private String name;
    private String species;
    private Integer age;
    private String description;
    private Integer workplaceId; // Solo ID para evitar ciclos de referencia
    private Set<Integer> movieIds; // Solo IDs para evitar carga excesiva
}

