package app.Certant.Services;

import app.Certant.domain.Inspector;
import java.util.List;


public interface iInspectorService {
    
    public List<Inspector> listarInspectores();
    
    public void guardar(Inspector d);
    
    public void eliminar(Inspector d);
    
    public Inspector encontrarInspector(Inspector d);
}
