package test.com.dg.medicamentos.service;

import com.dh.medicamentos.dao.IDao;
import com.dh.medicamentos.model.Medicamento;

import java.sql.SQLException;

public class MedicamentoService {

    private IDao<Medicamento> medicamentoIDao;

    public MedicamentoService(IDao<Medicamento> medicamentoIDao) {
        this.medicamentoIDao = medicamentoIDao;
    }

    public Medicamento guardar(Medicamento medicamento) throws SQLException {
        return medicamentoIDao.guardar(medicamento);
    }

    public Medicamento buscar(Integer id) throws SQLException {
        return medicamentoIDao.buscar(id);
    }

    public IDao<Medicamento> getMedicamentoIDao() {
        return medicamentoIDao;
    }

    public void setMedicamentoIDao(IDao<Medicamento> medicamentoIDao) {
        this.medicamentoIDao = medicamentoIDao;
    }

}
