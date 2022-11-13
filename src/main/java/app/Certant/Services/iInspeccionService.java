package app.Certant.Services;

import app.Certant.domain.Inspeccion;
import java.util.List;

public interface iInspeccionService {
    
    public List<Inspeccion> listarInspecciones();
    
    public void guardar(Inspeccion i);
    
    public void eliminar(Inspeccion i);
    
    public Inspeccion encontrarInspeccion(Inspeccion i);
}
