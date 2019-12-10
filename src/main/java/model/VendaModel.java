package model;

import config.SQLConnection;
import entity.RelatorioVenda;
import entity.Venda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendaModel {

    Connection connection;

    public VendaModel(){
        this.connection = SQLConnection.getConnection();
    }

    public List<Venda> listar(){
        String sql = "select * from venda";
        ArrayList<Venda> vendas = new ArrayList<Venda>();

        PreparedStatement pstm = null;
        ResultSet rset = null;


        try {

            pstm = connection.prepareStatement(sql);
            rset = pstm.executeQuery();

            while(rset.next()){

                Venda venda = new Venda();

                venda.setCodigo(rset.getInt("codVenda"));

                venda.setDt_venda(rset.getDate("dtVenda"));

                venda.setValor_venda(rset.getFloat("valor"));

                venda.setComissao_venda(rset.getFloat("comissao"));

                venda.setPlaca(rset.getString("placa"));

                venda.setCodFuncionario(rset.getString("codFuncionario"));

                venda.setCodCliente(rset.getString("codCliente"));

                vendas.add(venda);

            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }


        return vendas;
    }

    public boolean cadastrar(Venda venda){

        String sql = "INSERT INTO venda (codVenda, placa, codCliente, codFuncionario, " +
                "dtVenda, valor, comissao) VALUES (?,?,?,?,?,?,?)";

        boolean x = false;

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setInt(1,venda.getCodigo());
            ps.setString(2,venda.getPlaca());
            ps.setString(3,venda.getCodCliente());
            ps.setString(4,venda.getCodFuncionario());
            ps.setDate(5, new Date(venda.getDt_venda().getTime()));
            ps.setFloat(6,venda.getValor_venda());
            ps.setFloat(7,venda.getComissao_venda());

            ps.execute();

            x = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return x;

    }

    public boolean alterar(Venda v){

        String sql = "UPDATE venda SET placa = ?, codCliente =? , codFuncionario =? , dtVenda =? , valor =? , comissao =? WHERE id = ?";

        boolean x = false;

        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setString(1,v.getPlaca());
            pstm.setString(2,v.getCodCliente());
            pstm.setString(3,v.getCodFuncionario());
            pstm.setDate(4, new Date(v.getDt_venda().getTime()));
            pstm.setFloat(5,v.getValor_venda());
            pstm.setFloat(6,v.getComissao_venda());
            pstm.setInt(7,v.getCodigo());

            pstm.execute();
            x= true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }

    public Venda buscar(int codVenda){

        String sql = "SELECT * FROM venda WHERE codVenda=?";

        Venda venda = new Venda();
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, codVenda);

            rset = pstm.executeQuery();

            while(rset.next()) {
                venda.setCodigo(codVenda);
                venda.setPlaca(rset.getString("placa"));
                venda.setCodCliente(rset.getString("codCliente"));
                venda.setCodFuncionario(rset.getString("CodFuncionario"));
                venda.setDt_venda(rset.getDate("dtVenda"));
                venda.setValor_venda(rset.getFloat("valor"));
                venda.setComissao_venda(rset.getFloat("comissao"));

            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return venda;
    }

    public boolean cancelar(int codVenda){
        boolean x = false;
        String sql = "DELETE FROM venda WHERE codVenda = ?";


        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,codVenda);

            pstm.execute();
            x = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  x;
    }

    public List<RelatorioVenda> relatorioVenda(java.util.Date dtIV, java.util.Date dtFV){

        String sql = "select nome, sum(valor) as valorTotal, sum(comissao) as comissaoTotal from venda, funcionario where dtVenda between date(?) and date(?) and venda.codFuncionario = funcionario.codigoFuncionario group by venda.codFuncionario";
        ArrayList<RelatorioVenda> relatorioVendas = new ArrayList<RelatorioVenda>();

        PreparedStatement pstm = null;
        ResultSet rset = null;


        try {

            pstm = connection.prepareStatement(sql);
            pstm.setDate(1, new Date(dtIV.getTime()));
            pstm.setDate(2, new Date(dtFV.getTime()));
            rset = pstm.executeQuery();

            while(rset.next()){

               RelatorioVenda relatorioVenda = new RelatorioVenda();

                relatorioVenda.setNomeVendedor(rset.getString("nome"));
                relatorioVenda.setVendasTotal(rset.getFloat("valorTotal"));
                relatorioVenda.setComissao(rset.getFloat("comissaoTotal"));
                relatorioVendas.add(relatorioVenda);
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return relatorioVendas;
    }

}
