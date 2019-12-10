package entity;

public class RelatorioVenda {
    private String nomeVendedor;
    private float vendasTotal;
    private float comissao;

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public float getVendasTotal() {
        return vendasTotal;
    }

    public void setVendasTotal(float vendasTotal) {
        this.vendasTotal = vendasTotal;
    }

    public float getComissao() {
        return comissao;
    }

    public void setComissao(float comissao) {
        this.comissao = comissao;
    }
}
