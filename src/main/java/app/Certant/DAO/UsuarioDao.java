package app.Certant.DAO;

import app.Certant.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario,Long>{
    
    Usuario findByUsername(String username);
}
