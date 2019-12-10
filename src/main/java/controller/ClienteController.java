package controller;

import entity.Cliente;
import model.ClienteModel;

import java.util.List;

public class ClienteController {
    ClienteModel clienteModel;

    public ClienteController(){
        this.clienteModel = new ClienteModel();
    }

    public List<Cliente> listar(){
        return clienteModel.listar();
    }

    public boolean cadastrar(Cliente cliente){
        return clienteModel.cadastrar(cliente);
    }

    public boolean alterar(Cliente c){

        return clienteModel.alterar(c);
    }

    public Cliente buscar(String codigo){

        return clienteModel.buscar(codigo);
    }

    public boolean excluir(String codigo){

        return clienteModel.excluir(codigo);
    }

    public boolean verifica(String codigo){
        return clienteModel.verifica(codigo);
    }
}
