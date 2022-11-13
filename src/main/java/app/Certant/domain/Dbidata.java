package app.Certant.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "dbidata")
public class Dbidata {
 
    
    @Id
    @Column(name = "id_dbidata")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String luces;
    @NotNull
    private String patente;
    @NotNull
    private String espejos;
    @NotNull
    private String chasis;
    @NotNull
    private String vidrios;
    @NotNull
    private String seguridadEmergencia;
    @NotNull
    private String suspension;
    @NotNull
    private String dirTrenDelantero;
    @NotNull
    private String frenos;
    @NotNull
    private String contaminacion;
    
    @OneToOne(mappedBy="dbidata")
    private Inspeccion inspeccion;
    
    
}
