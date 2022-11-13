package app.Certant.Services;

import app.Certant.DAO.InspectorDao;
import app.Certant.domain.Inspector;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InspectorService implements iInspectorService{

    @Autowired
    private InspectorDao inspectorDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Inspector> listarInspectores() {
        return (List<Inspector>) inspectorDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Inspector d) {
        inspectorDao.save(d);
    }

    @Override
    @Transactional
    public void eliminar(Inspector d) {
        inspectorDao.delete(d);
    }

    @Override
    @Transactional(readOnly = true)
    public Inspector encontrarInspector(Inspector d) {
        return inspectorDao.findById(d.getInspectorID()).orElse(null);
    }
    
}
