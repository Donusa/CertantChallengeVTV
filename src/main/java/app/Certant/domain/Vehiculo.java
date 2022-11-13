package app.Certant.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_Vehiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;
    
    @Column(name = "Vehiculo_Dominio")
    @NotEmpty
    private String dominio; //domino o patente
    
    @Column(name = "Vehiculo_Marca")
    @NotEmpty
    private String marca;
    
    @Column(name = "Vehiculo_Modelo")
    @NotEmpty
    private String modelo;
    
    @ManyToOne
    @JoinColumn(name = "Vehiculo_DueñoID") //tiene id de dueño por tanto...
    Duenio duenio;
    
    @OneToMany(mappedBy="vehiculo")
    private List<Inspeccion> inspeccion;
    
}
