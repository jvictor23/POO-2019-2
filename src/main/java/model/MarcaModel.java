package model;

import config.SQLConnection;
import entity.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaModel {

    Connection connection;

    public MarcaModel(){
        this.connection = SQLConnection.getConnection();
    }

    public List<Marca> listar() {
        String sql = "SELECT * FROM marca ";

        ArrayList<Marca> marcas = new ArrayList<Marca>();

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {


            pstm = connection.prepareStatement(sql);

            rset = pstm.executeQuery();

            while(rset.next()){

                Marca marca = new Marca();


                marca.setNomeMarca(rset.getString("nomeMarca"));

                marca.setId(rset.getInt("id"));



                marcas.add(marca);
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return marcas;
    }

    public boolean cadastrar(Marca m) {
        String sql = "INSERT INTO marca (nomeMarca) VALUES (?)";
        boolean x = false;

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, m.getNomeMarca());

            ps.execute();

            x = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return x;
    }

    public boolean alterar(Marca m) {
        String sql = "UPDATE marca SET nomeMarca = ? WHERE id = ?";

        boolean x = false;

        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setString(1,m.getNomeMarca());
            pstm.setInt(2,m.getId());


            pstm.execute();
            x= true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }

    public Marca buscar(int codigo) {

        String sql = "SELECT * FROM marca WHERE id=?";

        Marca marca = new Marca();
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, codigo);

            rset = pstm.executeQuery();

            while(rset.next()) {

                marca.setNomeMarca(rset.getString("nome"));

                marca.setId(rset.getInt("id"));

            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return marca;
    }

    public boolean excluir(int codigo) {
        boolean x = false;
        String sql = "DELETE FROM marca WHERE id = ?";


        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,codigo);

            pstm.execute();
            x = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  x;
    }
}
