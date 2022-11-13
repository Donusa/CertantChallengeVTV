package app.Certant.Services;

import app.Certant.domain.Vehiculo;
import java.util.List;

public interface iVehiculoService {
    
    public List<Vehiculo> listarVehiculos();
    
    public void guardar(Vehiculo v);
    
    public void eliminar(Vehiculo v);
    
    public Vehiculo encontrarVehiculo(Vehiculo v);
}
