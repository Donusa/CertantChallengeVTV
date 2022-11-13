package app.Certant.Services;
import app.Certant.DAO.InspeccionDao;
import app.Certant.domain.Inspeccion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InspeccionService implements iInspeccionService{

    @Autowired
    private InspeccionDao inspeccionDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Inspeccion> listarInspecciones() {
        return (List<Inspeccion>) inspeccionDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Inspeccion i) {
        inspeccionDao.save(i);
    }

    @Override
    @Transactional
    public void eliminar(Inspeccion i) {
        inspeccionDao.delete(i);
    }

    @Override
    @Transactional(readOnly = true)
    public Inspeccion encontrarInspeccion(Inspeccion i) {
        return inspeccionDao.findById(i.getInspeccionID()).orElse(null);
    }
    
}
