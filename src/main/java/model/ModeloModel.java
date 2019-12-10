package model;

import config.SQLConnection;
import entity.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeloModel {
    Connection connection;
    int idMarca;


    public ModeloModel(){
        this.connection = SQLConnection.getConnection();
    }

    public List<Modelo> listar() {
        ArrayList<Modelo> modelos = new ArrayList<Modelo>();

        PreparedStatement pstm = null;
        ResultSet rset = null;


                String sql = "select * from modelo m  inner join marca c on m.marcaId = c.id ;";

                try {

                    pstm = connection.prepareStatement(sql);
                    rset = pstm.executeQuery();

                    while(rset.next()){

                        Modelo modelo = new Modelo();

                        modelo.setId(rset.getInt("id"));

                        modelo.setNomeModelo(rset.getString("nomeModelo"));

                        modelo.setTipo(rset.getString("tipo"));

                        modelo.setNomeMarca(rset.getString("nomeMarca"));

                        modelos.add(modelo);
                    }

                }catch(Exception e) {
                    System.out.println(e.getMessage());
                }


        return modelos;
    }

    public boolean cadastrar(Modelo modelo, int idMarca) {
        String sql = "INSERT INTO modelo (nomeModelo,tipo, marcaId) VALUES (?,?,?)";
        boolean x = false;

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, modelo.getNomeModelo());
            ps.setString(2, modelo.getTipo());
            ps.setInt(3, idMarca);

            ps.execute();

            x = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return x;
    }

    public boolean alterar(Modelo modelo) {
        String sql = "UPDATE modelo SET nomeModelo = ?, tipo =? WHERE id = ?";

        boolean x = false;

        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setString(1,modelo.getNomeModelo());
            pstm.setString(2,modelo.getTipo());
            pstm.setInt(3,modelo.getId());


            pstm.execute();
            x= true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }

    public Modelo buscar(int id) {
        String sql = "SELECT * FROM modelo WHERE id=?";

        Modelo modelo = new Modelo();
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            rset = pstm.executeQuery();

            while(rset.next()) {

             idMarca = rset.getInt("marcaId");

            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        String SQL = "select * from modelo m  inner join marca c on m.marcaId = c.id where m.marcaId = ?";

        try {

            pstm = connection.prepareStatement(SQL);
            pstm.setInt(1,idMarca);
            rset = pstm.executeQuery();

            while(rset.next()){



                modelo.setId(rset.getInt("id"));

                modelo.setNomeModelo(rset.getString("nomeModelo"));

                modelo.setTipo(rset.getString("tipo"));

                modelo.setNomeMarca(rset.getString("nomeMarca"));

            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return modelo;
    }

    public boolean excluir(int id) {
        boolean x = false;
        String sql = "DELETE FROM modelo WHERE id = ?";


        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,id);

            pstm.execute();
            x = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  x;
    }
}
