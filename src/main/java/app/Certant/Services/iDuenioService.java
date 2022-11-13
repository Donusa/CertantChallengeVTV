package app.Certant.Services;

import app.Certant.domain.Duenio;
import java.util.List;

public interface iDuenioService {
 
    public List<Duenio> listarDuenios();
    
    public void guardar(Duenio d);
    
    public void eliminar(Duenio d);
    
    public Duenio encontrarDuenio(Duenio d);
}
