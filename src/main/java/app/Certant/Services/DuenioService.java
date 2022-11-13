package app.Certant.Services;

import app.Certant.DAO.DuenioDao;
import app.Certant.domain.Duenio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DuenioService implements iDuenioService{

    @Autowired
    private DuenioDao duenioDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Duenio> listarDuenios() {
        return (List<Duenio>) duenioDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Duenio d) {
        duenioDao.save(d);
    }

    @Override
    @Transactional
    public void eliminar(Duenio d) {
        duenioDao.delete(d);
    }

    @Override
    @Transactional(readOnly = true)
    public Duenio encontrarDuenio(Duenio d) {
        return duenioDao.findById(d.getIdDuenio()).orElse(null); 
    }
    
}
