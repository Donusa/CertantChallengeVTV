package app.Certant.Services;

import app.Certant.domain.Dbidata;
import java.util.List;

public interface iDbidataService {
    
    
    public List<Dbidata> listarDuenios();
    
    public void guardar(Dbidata d);
    
    public void eliminar(Dbidata d);
    
    public Dbidata encontrarDuenio(Dbidata d);
}
