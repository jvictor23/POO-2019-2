package controller;

import entity.Automovel;
import model.AutomovelModel;

import java.util.List;


public class AutomovelController {

    AutomovelModel automovelModel;

    public AutomovelController(){
        this.automovelModel= new AutomovelModel();
    }

    public List<Automovel> listar(){
        return automovelModel.listar();
    }

    public boolean cadastrar(Automovel automovel, int idMod){
        return automovelModel.cadastrar(automovel, idMod);
    }

    public boolean alterar(Automovel a, String placa){

        return automovelModel.alterar(a, placa);
    }

    public Automovel buscar(String placa){
    	
       return automovelModel.buscar(placa);
        
    }

    public boolean excluir(String placa){

        return automovelModel.excluir(placa);
    }

    public boolean verifica(String placa){
        return automovelModel.verifica(placa);
    }

}
