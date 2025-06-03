package edu.badpals.bobesponjau4.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceDTO {

    private Integer id;
    private String name;
    private String location;
    private String description;
    private Set<Integer> personajesIds; // IDs de personajes asociados
}

