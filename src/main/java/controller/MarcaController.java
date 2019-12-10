package controller;

import entity.Marca;
import model.MarcaModel;

import java.util.List;

public class MarcaController {

   private MarcaModel marcaModel;

    public MarcaController(){
        this.marcaModel = new MarcaModel();
    }

    public List<Marca> listar() {
       return marcaModel.listar();
    }

    public boolean cadastrar(Marca m) {
       return marcaModel.cadastrar(m);
    }

    public boolean alterar(Marca m) {

       return marcaModel.alterar(m);
    }

    public Marca buscar(int codigo) {
        return marcaModel.buscar(codigo);
    }

    public boolean excluir(int codigo) {
        return marcaModel.excluir(codigo);
    }

}
