package controller;

import entity.RelatorioVenda;
import entity.Venda;
import model.VendaModel;

import java.util.Date;
import java.util.List;

public class VendaController {

    VendaModel vendaModel;

    public VendaController(){
        this.vendaModel = new VendaModel();
    }

    public List<Venda> listar(){
        return vendaModel.listar();
    }

    public boolean cadastrar(Venda venda){
        return vendaModel.cadastrar(venda);
    }

    public boolean alterar(Venda v){

        return vendaModel.alterar(v);
    }

    public Venda buscar(int codVenda){
     return vendaModel.buscar(codVenda);
    }

    public boolean cancelar(int codVenda){

        return vendaModel.cancelar(codVenda);
    }

    public  List<RelatorioVenda> relatorioVenda(Date dtIV, Date dtFV){
        return vendaModel.relatorioVenda(dtIV,dtFV);
    }

}
