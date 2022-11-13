package app.Certant.DAO;

import app.Certant.domain.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectorDao extends JpaRepository<Inspector,Long>{
    
}
