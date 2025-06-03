package edu.badpals.bobesponjau4.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workplaces")
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workplace_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Lob
    @Column(name = "description")
    private String description;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @OneToMany(mappedBy = "workplace", fetch = FetchType.EAGER)
    private Set<Personaje> personajes = new HashSet<>();

    public Workplace(Integer id, String name, String location, String description, Set<Personaje> personajes) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.personajes = personajes;
    }

    public Workplace() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }
}
