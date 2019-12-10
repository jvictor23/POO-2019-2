package model;

import config.SQLConnection;
import entity.Automovel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AutomovelModel {
	
	Connection connection;
	
	public AutomovelModel() {
		this.connection = SQLConnection.getConnection();
	}
	

    public List<Automovel> listar(){
    	
        String sql = "select nomeMarca, nomeModelo, tipo, cor, anoFab, anoMod, chassi, placa, km, valor from automovel a, modelo m, marca ma where a.modeloId = m.id and m.marcaId = ma.id ;";
    	
    	ArrayList<Automovel> automoveis = new ArrayList<Automovel>();
    	
    	
    	PreparedStatement pstm = null;
    	ResultSet rset = null;
    	
    	try {


    		 
    		 pstm = connection.prepareStatement(sql);
    		 
    		 rset = pstm.executeQuery();
    		 
    		 while(rset.next()){
    			 
    			 Automovel automovel = new Automovel();

				 automovel.setNomeMarca(rset.getString("nomeMarca"));

				 automovel.setNomeModelo(rset.getString("nomeModelo"));

				 automovel.setTipo(rset.getString("tipo"));

    			 automovel.setCor(rset.getString("cor"));
    			 
    			 automovel.setAno_fab(rset.getDate("anoFab"));
    			 
    			 automovel.setAno_modelo(rset.getDate("anoMod"));
    			 
    			 automovel.setChassi(rset.getString("chassi"));
    			 
    			 automovel.setPlaca(rset.getString("placa"));
    			 
    			 automovel.setKm(rset.getFloat("km"));
    			 
    			 automovel.setValor(rset.getFloat("valor"));

    			 
    			 automoveis.add(automovel);
    	
    			 }
    		 
    		 
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    		
    		return automoveis;
    }

    public boolean cadastrar(Automovel automovel, int idMod){

		String sql = "SELECT * FROM automovel";
		boolean a = false;
		boolean x = false;


		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {


			pstm = connection.prepareStatement(sql);

			rset = pstm.executeQuery();

			while(rset.next()){

				if (automovel.getPlaca().equals(rset.getString("placa"))){
					x = true;
				}


			}



		}catch(Exception e) {
			System.out.println(e.getMessage());
		}


		if (x){
			System.out.println("Placa ja cadastrada, verifique os dados e tente navamente");
		}else {

			String SQL = "INSERT INTO automovel (cor, anoFab, anoMod, chassi, placa, km, valor, modeloId) VALUES (?,?,?,?,?,?,?,?)";


			try {

				PreparedStatement ps = connection.prepareStatement(SQL);
				ps.setString(1, automovel.getCor());
				ps.setDate(2, new Date(automovel.getAno_fab().getTime()));
				ps.setDate(3, new Date(automovel.getAno_modelo().getTime()));
				ps.setString(4, automovel.getChassi());
				ps.setString(5, automovel.getPlaca());
				ps.setFloat(6, automovel.getKm());
				ps.setFloat(7,automovel.getValor());
				ps.setInt(8, idMod);

				ps.execute();
				a = true;


			}catch(Exception e){

				System.out.println(e.getMessage());

			}
		}


    	return a;
    }

    public boolean alterar(Automovel automovel, String placa){

		String sql = "UPDATE modelo SET cor = ?, anoFab = ?, anoMod = ?, chassi = ?, placa = ?, km = ?, valor = ? WHERE id = ?";

		boolean x = false;

		PreparedStatement pstm = null;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, automovel.getCor());
			ps.setDate(2, new Date(automovel.getAno_fab().getTime()));
			ps.setDate(3, new Date(automovel.getAno_modelo().getTime()));
			ps.setString(4, automovel.getChassi());
			ps.setString(5, automovel.getPlaca());
			ps.setFloat(6, automovel.getKm());
			ps.setFloat(7,automovel.getValor());
			ps.setString(8, placa);

			pstm.execute();
			x= true;

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return x;
    }

    public Automovel buscar(String placa){
    	
    	String sql = "SELECT nomeMarca, nomeModelo, tipo, cor, anoFab, anoMod, chassi, placa, km, valor FROM automovel a, modelo m, marca ma where a.modeloId = m.id and m.marcaId = ma.id and a.placa = ?";

		PreparedStatement pstm = null;
		ResultSet rset = null;
		Automovel automovel = new Automovel();

		try {

			pstm = connection.prepareStatement(sql);

			pstm.setString(1,placa);

			rset = pstm.executeQuery();

			while(rset.next()){


				automovel.setNomeMarca(rset.getString("nomeMarca"));

				automovel.setNomeModelo(rset.getString("nomeModelo"));

				automovel.setTipo(rset.getString("tipo"));

				automovel.setCor(rset.getString("cor"));

				automovel.setAno_fab(rset.getDate("anoFab"));

				automovel.setAno_modelo(rset.getDate("anoMod"));

				automovel.setChassi(rset.getString("chassi"));

				automovel.setPlaca(rset.getString("placa"));

				automovel.setKm(rset.getFloat("km"));

				automovel.setValor(rset.getFloat("valor"));


			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return automovel;
    	
    }

    public boolean excluir(String placa){
		boolean x = false;
		String sql = "DELETE FROM automovel WHERE placa = ?";


		PreparedStatement pstm = null;

		try {

			pstm = connection.prepareStatement(sql);

			pstm.setString(1,placa);

			pstm.execute();
			x = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return  x;
	}

	public boolean verifica(String placa){
		String sql = "SELECT * FROM automovel";
		boolean x = false;


		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {


			pstm = connection.prepareStatement(sql);

			rset = pstm.executeQuery();

			while(rset.next()){

				if (placa.equals(rset.getString("placa"))){
					x = true;
				}


			}



		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return x;
	}

}
