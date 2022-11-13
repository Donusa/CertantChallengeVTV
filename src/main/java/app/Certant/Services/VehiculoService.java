package app.Certant.Services;

import app.Certant.DAO.VehiculoDao;
import app.Certant.domain.Vehiculo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehiculoService implements iVehiculoService{

    @Autowired
    private VehiculoDao vehiculoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> listarVehiculos() {
        return (List<Vehiculo>) vehiculoDao.findAll();
        
    }

    @Override
    @Transactional
    public void guardar(Vehiculo v) {
        vehiculoDao.save(v);
    }

    @Override
    @Transactional
    public void eliminar(Vehiculo v) {
        vehiculoDao.delete(v);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo encontrarVehiculo(Vehiculo v) {
        return vehiculoDao.findById(v.getIdVehiculo()).orElse(null);
    }
    
}
