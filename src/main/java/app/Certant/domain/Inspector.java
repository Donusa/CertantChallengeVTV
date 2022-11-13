package app.Certant.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "inspector", uniqueConstraints = @UniqueConstraint(columnNames = "Inspector_User"))
public class Inspector implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Inspector")
    private Long inspectorID;
    
    @Column(name = "Inspector_Nombre")
    @NotNull
    private String nombre; 
    
    @Column(name = "Inspector_User")
    @NotNull
    private String user;
    
    @OneToOne(mappedBy="inspector")
    private Usuario usuario;
    
    
}
