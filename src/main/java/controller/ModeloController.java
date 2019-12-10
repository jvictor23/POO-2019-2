package controller;

import entity.Modelo;
import model.ModeloModel;

import java.util.List;

public class ModeloController {

    ModeloModel modeloModel;

    public ModeloController(){
        this.modeloModel = new ModeloModel();
    }

    public List<Modelo> listar() {

        return modeloModel.listar();
    }

    public boolean cadastrar(Modelo modelo, int idMarca) {
        return modeloModel.cadastrar(modelo, idMarca);
    }

    public boolean alterar(Modelo modelo) {
        return modeloModel.alterar(modelo);
    }

    public Modelo buscar(int id) {
        return modeloModel.buscar(id);
    }

    public boolean excluir(int id) {
      return modeloModel.excluir(id);
    }

}
