package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda  {

    private Date dt_venda;
    private int codigo;
    private float comissao_venda;
    private float valor_venda;
    private String placa;
    private String codCliente;
    private String codFuncionario;

    public Date getDt_venda() {
        return dt_venda;
    }

    public void setDt_venda(Date dt_venda) {
        this.dt_venda = dt_venda;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getComissao_venda() {
        return comissao_venda;
    }

    public void setComissao_venda(float comissao_venda) {
        this.comissao_venda = comissao_venda;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(String codFuncionario) {
        this.codFuncionario = codFuncionario;
    }
}
