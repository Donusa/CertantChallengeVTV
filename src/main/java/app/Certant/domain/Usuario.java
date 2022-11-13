package app.Certant.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;



@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id_Usuario")
    private Long userID;
    
    @Column(name = "Usuario_Username")
    @NotNull
    private String username;
    
    @Column(name = "Usuario_Password")
    @NotNull
    private String userPass;
    
    @OneToOne
    @JoinColumn(name = "Usuario_InspectorID")
    private Inspector inspector;
    
}
