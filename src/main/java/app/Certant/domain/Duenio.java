package app.Certant.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "dueño")
public class Duenio implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_Dueño")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDuenio;
    
    @NotEmpty
    @Column(name ="Dueño_Nombre")
    private String nombre; // nombre y apellido
    
    @Column(name ="Dueño_Doc")
    @NotEmpty
    private String doc; //documentacion ya sea pasaporte o DNI
    
    @OneToMany(mappedBy = "duenio") // el vehiculo contiene el id por tanto...
    List<Vehiculo> vehiculos;
    
}
