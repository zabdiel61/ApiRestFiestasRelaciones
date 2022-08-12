package com.zabdev.biblioteca.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long id;
    
    private String nombre;
    
    private Integer edad;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private Set<Habilidad> habilidades = new HashSet<>();
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference//para evitar problemas de recursividad, y evitar que se serialize
    @JoinTable(name = "personas_fiestas", 
            joinColumns = @JoinColumn(name = "persona_id",//"persona_id" entidad propietaria
                    referencedColumnName = "persona_id"), //a  la que se relacionara
            inverseJoinColumns = @JoinColumn(name = "fiesta_id",//entidad secundaria
                    referencedColumnName = "fiesta_id"))
    private Set<Fiesta> fiestas = new HashSet<>();
}
