package test.com.dg.medicamentos.service;

import com.dh.medicamentos.dao.impl.ConfiguracionJDBC;
import com.dh.medicamentos.dao.impl.MedicamentoDaoH2;
import com.dh.medicamentos.model.Medicamento;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MedicamentoServiceTest {

    private MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDaoH2(new ConfiguracionJDBC()));

    @Test
    public  void guardarMedicamento() throws SQLException {
        Medicamento medicamento = new Medicamento("Ibuprofeno", "lab123", 1000, 200.0);
        medicamentoService.guardar(medicamento);
        Assert.assertTrue(medicamento.getId() != null);

    }

    @Test
    public void buscar () throws SQLException {
        Medicamento medicamento = medicamentoService.buscar(1);
        Assert.assertTrue(medicamento != null);
    }
}