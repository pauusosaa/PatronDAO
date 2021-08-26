package com.dh.medicamentos.dao.impl;

import com.dh.medicamentos.dao.IDao;
import com.dh.medicamentos.model.Medicamento;
import org.apache.log4j.Logger;

import java.sql.*;


public class MedicamentoDaoH2 implements IDao<Medicamento> {

    private ConfiguracionJDBC configuracionJDBC;
    final static Logger log = Logger.getLogger(MedicamentoDaoH2.class);

    public MedicamentoDaoH2(ConfiguracionJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }


    @Override
    public Medicamento guardar(Medicamento medicamento) throws SQLException {
        log.debug("Registrando un nuevo medicamento...." + medicamento.toString());
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", null);

        Statement stmt = null;
        String query = String.format("INSERT INTO medicamentos(nombre,laboratorio,cantidad,precio) VALUES('%s','%s','%s','%s')",
                medicamento.getNombre(),medicamento.getLaboratorio(),medicamento.getCantidad(),medicamento.getPrecio());

        try{
            stmt = connection.createStatement();
            stmt.execute("create table if not exists medicamentos (id int auto_increment primary key,nombre varchar(255),laboratorio varchar (255),cantidad int,precio double)");
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next()){
                medicamento.setId(keys.getInt(1));
                stmt.close();
                connection.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return medicamento;
    }

    @Override
    public Medicamento buscar(Integer id) throws SQLException {
        log.debug("Buscando un medicamento con id: " + id );
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", null);
        Statement stmt = null;
        String query = String.format("SELECT * FROM MEDICAMENTOS WHERE ID = %s", id);
                Medicamento medicamento = null;
                try{
                    stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);
                    while (resultSet.next()) {
                        medicamento = new Medicamento(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("laboratorio"), resultSet.getInt("cantidad"), resultSet.getDouble("precio"));
                    }
                    stmt.close();
                }catch(SQLException throwables){
                    throwables.printStackTrace();

                }

        return medicamento;
    }
}
