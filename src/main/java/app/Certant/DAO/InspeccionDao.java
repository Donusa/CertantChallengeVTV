package app.Certant.DAO;

import app.Certant.domain.Inspeccion;
import org.springframework.data.repository.CrudRepository;

public interface InspeccionDao extends CrudRepository<Inspeccion,Long>{
    
}
