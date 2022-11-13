package app.Certant.DAO;

import app.Certant.domain.Vehiculo;
import org.springframework.data.repository.CrudRepository;


public interface VehiculoDao extends CrudRepository<Vehiculo, Long>{
    
}
