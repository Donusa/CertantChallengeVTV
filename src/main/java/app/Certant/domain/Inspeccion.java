package app.Certant.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "inspeccion")
public class Inspeccion implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Inspeccion")
    private Long inspeccionID;
    
    @Column(name="Inspeccion_Fecha")
    @NotNull
    private Date fechaInspeccion;
    
    @Column(name="Inspeccion_Estado")
    @NotNull
    private String estado;
    
    @Column(name="Inspeccion_Excento")
    @NotNull
    private short inspeccionExcento;
    
    @Column(name="Inspeccion_Observaciones")
    @NotNull
    private String observaciones;
    
    @Column(name = "Inspeccion_InspectorID")
    private String inspector;
    
    @ManyToOne
    @JoinColumn(name = "Inspeccion_VehiculoID")
    private Vehiculo vehiculo;
    
    @OneToOne
    @JoinColumn(name = "Inspeccion_dbidataID")
    Dbidata dbidata;
}
