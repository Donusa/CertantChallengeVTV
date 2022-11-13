package app.Certant.Services;

import app.Certant.DAO.DbidataDao;
import app.Certant.domain.Dbidata;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbidataService implements iDbidataService{

    @Autowired 
    private DbidataDao dbidataDao;
    
    @Override
    public List<Dbidata> listarDuenios() {
        return (List<Dbidata>) dbidataDao.findAll();
    }

    @Override
    public void guardar(Dbidata d) {
        dbidataDao.save(d);
    }

    @Override
    public void eliminar(Dbidata d) {
        dbidataDao.delete(d);
    }

    @Override
    public Dbidata encontrarDuenio(Dbidata d) {
        return dbidataDao.findById(d.getId()).orElse(null);
    }
    
    
}
