package com.zabdev.biblioteca.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fiestas")
public class Fiesta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fiesta_id")
    private Long id;
    
    private String ubicacion;
    
    @Column(name = "fiesta_fecha")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date fecha;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "personas_fiestas", 
            joinColumns = @JoinColumn(name = "fiesta_id",//"persona_id" entidad propietaria
                    referencedColumnName = "fiesta_id"), //a  la que se relacionara
            inverseJoinColumns = @JoinColumn(name = "persona_id",//entidad secundaria
                    referencedColumnName = "persona_id"))
    private Set<Persona> personas = new HashSet<>();
    
    

}
