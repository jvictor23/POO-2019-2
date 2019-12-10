package model;

import config.SQLConnection;
import entity.Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioModel {

    Connection connection;

    public FuncionarioModel(){
        this.connection = SQLConnection.getConnection();
    }

    public List<Funcionario> listar(){
        String sql = "SELECT * FROM funcionario ";

        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {


            pstm = connection.prepareStatement(sql);

            rset = pstm.executeQuery();

            while(rset.next()){

                Funcionario funcionario = new Funcionario();


                funcionario.setCargo(rset.getString("cargo"));

                funcionario.setNome(rset.getString("nome"));

                funcionario.setCpf(rset.getString("cpf"));

                funcionario.setEndereco(rset.getString("endereco"));

                funcionario.setTelefone(rset.getString("telefone"));

                funcionario.setDt_nascimento(rset.getDate("dtNascimento"));

                funcionario.setCodigo(rset.getString("codigoFuncionario"));

                funcionario.setUsuario(rset.getString("usuario"));

                funcionario.setSenha(rset.getString("senha"));



                funcionarios.add(funcionario);

            }



        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

            return funcionarios;
    }

    public boolean cadastrar(Funcionario funcionario){

        String sql = "SELECT * FROM funcionario";
        boolean a = false;
        boolean x = false;


        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {


            pstm = connection.prepareStatement(sql);

            rset = pstm.executeQuery();

            while(rset.next()){

                if (funcionario.getCpf().equals(rset.getString("cpf"))){
                    x = true;
                }


            }



        }catch(Exception e) {
            System.out.println(e.getMessage());
        }


        if (x){
            System.out.println("CPF ja cadastrado, verifique os dados e tente navamente");
        }else {
            String SQL = "INSERT INTO funcionario (cargo, nome, cpf, endereco, telefone, dtNascimento, codigoFuncionario, usuario, senha) VALUES (?,?,?,?,?,?,?,?,?)";


            try {
                PreparedStatement ps  = connection.prepareStatement(SQL);
                ps.setString(1,funcionario.getCargo());
                ps.setString(2,funcionario.getNome());
                ps.setString(3,funcionario.getCpf());
                ps.setString(4,funcionario.getEndereco());
                ps.setString(5,funcionario.getTelefone());
                ps.setDate(6, new Date(funcionario.getDt_nascimento().getTime()));
                ps.setString(7,funcionario.getCodigo());
                ps.setString(8,funcionario.getUsuario());
                ps.setString(9,funcionario.getSenha());

                ps.execute();

                a = true;

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }



       return a;
    }

    public boolean alterar(Funcionario f){
        String sql = "UPDATE funcionario SET cargo = ?, nome = ?, cpf = ?, endereco = ?, telefone = ?, dtNascimento = ?, " +
                "usuario = ?, senha = ? WHERE codigoFuncionario = ?";
        boolean x = false;

        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setString(1,f.getCargo());
            pstm.setString(2,f.getNome());
            pstm.setString(3,f.getCpf());
            pstm.setString(4,f.getEndereco());
            pstm.setString(5,f.getTelefone());
            pstm.setDate(6, new Date(f.getDt_nascimento().getTime()));
            pstm.setString(7,f.getUsuario());
            pstm.setString(8,f.getSenha());
            pstm.setString(9,f.getCodigo());

            pstm.execute();
            x= true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }

    public Funcionario buscar(String codigo){

        String sql = "SELECT * FROM funcionario WHERE codigoFuncionario=?";

        Funcionario funcionario = new Funcionario();
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, codigo);

            rset = pstm.executeQuery();

            while(rset.next()) {

                funcionario.setCargo(rset.getString("cargo"));

                funcionario.setNome(rset.getString("nome"));

                funcionario.setCpf(rset.getString("cpf"));

                funcionario.setEndereco(rset.getString("endereco"));

                funcionario.setTelefone(rset.getString("telefone"));

                funcionario.setDt_nascimento(rset.getDate("dtNascimento"));

                funcionario.setCodigo(rset.getString("codigoFuncionario"));

                funcionario.setUsuario(rset.getString("usuario"));

                funcionario.setSenha(rset.getString("senha"));
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return funcionario;
    }

    public boolean excluir(String codigo){

        boolean x = false;
        String sql = "DELETE FROM funcionario WHERE codigoFuncionario= ?";


        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);

            pstm.setString(1,codigo);

            pstm.execute();
            x = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       return  x;
    }

    public boolean verifica(String codigo){
        String sql = "SELECT * FROM funcionario";
        boolean x = false;


        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {


            pstm = connection.prepareStatement(sql);

            rset = pstm.executeQuery();

            while(rset.next()){

                if (codigo.equals(rset.getString("codigoFuncionario"))){
                    x = true;
                }


            }



        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public boolean cadastarSalario(float salario, String id){
        String SQL = "INSERT INTO salario (salario,idFuncionario) VALUES (?,?)";
        boolean a = false;


        try {
            PreparedStatement ps  = connection.prepareStatement(SQL);
            ps.setFloat(1,salario);
            ps.setString(2,id);
            ps.execute();

            a = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return a;
    }


    public float salarioMaiorBadeco(){
        String sql = "select max(salario) salario,cargo from salario s,funcionario f where s.idFuncionario = f.codigoFuncionario and f.cargo = ?";
        String badeco = "Badeco";

        PreparedStatement pstm = null;
        ResultSet rset = null;
        float salarioMaiorBadeco = 0;

        try {


            pstm = connection.prepareStatement(sql);
            pstm.setString(1, badeco);

            rset = pstm.executeQuery();

            while(rset.next()){

                salarioMaiorBadeco = rset.getFloat("salario");

            }



        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

            return salarioMaiorBadeco;

    }

    public float salarioMenorFuncionario(){
        String sql = "select min(salario) salario,cargo from salario s,funcionario f where s.idFuncionario = f.codigoFuncionario and f.cargo = ?";
        String funcionario = "Funcionario";

        PreparedStatement pstm = null;
        ResultSet rset = null;
        float salarioMenorFuncionario = 0;

        try {


            pstm = connection.prepareStatement(sql);
            pstm.setString(1, funcionario);

            rset = pstm.executeQuery();

            while(rset.next()){

                salarioMenorFuncionario = rset.getFloat("salario");

            }



        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return salarioMenorFuncionario;
    }

    public float salarioMenorGerente(){

        String sql = "select min(salario) salario,cargo from salario s,funcionario f where s.idFuncionario = f.codigoFuncionario and f.cargo = ?";
        String gerente = "Gerente";

        PreparedStatement pstm = null;
        ResultSet rset = null;
        float salarioMenorGerente = 0;

        try {


            pstm = connection.prepareStatement(sql);
            pstm.setString(1, gerente);

            rset = pstm.executeQuery();

            while(rset.next()){

                salarioMenorGerente = rset.getFloat("salario");

            }



        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return salarioMenorGerente;

    }

    public float salarioMaiorFuncionario(){
        String sql = "select max(salario) salario,cargo from salario s,funcionario f where s.idFuncionario = f.codigoFuncionario and f.cargo = ?";
        String funcionario = "Funcionario";

        PreparedStatement pstm = null;
        ResultSet rset = null;
        float salarioMaiorFuncionario = 0;

        try {


            pstm = connection.prepareStatement(sql);
            pstm.setString(1, funcionario);

            rset = pstm.executeQuery();

            while(rset.next()){

                salarioMaiorFuncionario = rset.getFloat("salario");

            }



        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return salarioMaiorFuncionario;
    }

    public boolean alterarSalario(float salario, String id){
        String sql = "UPDATE salario SET salario = ? WHERE idFuncionario = ?";
        boolean x = false;

        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setFloat(1,salario);
            pstm.setString(2,id);
            pstm.execute();
            x= true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }

}
