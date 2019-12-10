package view;

import controller.AutomovelController;
import controller.ClienteController;
import controller.FuncionarioController;
import controller.VendaController;
import entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VendaView {
    private VendaController vendaController;
    private Scanner sc;
    private Venda venda = new Venda();
    private AutomovelController automovelController = new AutomovelController();
    private ClienteController clienteController = new ClienteController();
    private FuncionarioController funcionarioController = new FuncionarioController();

    public VendaView() {
        this.vendaController = new VendaController();
        this.sc = new Scanner(System.in);
        this.automovelController = new AutomovelController();
        this.clienteController = new ClienteController();
        this.funcionarioController = new FuncionarioController();
    }


    public void menuVenda() {
        System.out.println("#Menu entity.Venda");
        System.out.println("01 - Realizar Vendas");
        System.out.println("02 - Listar Venda");
        System.out.println("03 - Alterar Venda");
        System.out.println("04 - Buscar Vendas");
        System.out.println("05 - Cancelar Venda");
        System.out.println("06 - Relatorio de Venda");
        System.out.println("0  - Sair");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op) {
            case 1:
                realizarVenda();
                menuVenda();
                break;
            case 2:
                listarVenda();
                menuVenda();
                break;
            case 3:
                alterarVenda();
                menuVenda();
                break;
            case 4:
                buscarVenda();
                menuVenda();
                break;
            case 5:
                cancelarVenda();
                menuVenda();
                break;
            case 6:
                relatorioVenda();
                menuVenda();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuVenda();
                break;
        }

    }


    private void listarVenda() {

        System.out.println("********************************************************");

        for (Venda v : vendaController.listar()) {
            Automovel automovel = automovelController.buscar(v.getPlaca());
            Cliente cliente = clienteController.buscar(v.getCodCliente());
            Funcionario funcionario = funcionarioController.buscar(v.getCodFuncionario());

            System.out.println(
            "Código venda: " + v.getCodigo() +
            "\n" + "Nome funcionario: " + funcionario.getNome() +
            "\n" + "Nome Cliente: " + cliente.getNome() +
            "\n" + "Nome entity.Marca: " + automovel.getNomeMarca() +
            "\n" + "Nome entity.Modelo: " + automovel.getNomeModelo() +
            "\n" + "Cor: " + automovel.getCor() +
            "\n" + "Valor da Venda: " + v.getValor_venda() +
            "\n" + "Valor da Comissão: " + v.getComissao_venda() +
            "\n" + "Data da entity.Venda: " + v.getDt_venda()
            );
            System.out.println("********************************************************");
}
    }

    private void realizarVenda() {
        String codigoCliente;
        String codigoFuncionario;
        String placa;
        boolean dadosPrimarios = false;

        for (Automovel a : automovelController.listar()) {
            System.out.println(
                    "\nNomeMarca - " + a.getNomeMarca() +
                            "\nNomeModelo - " + a.getNomeModelo() +
                            "\nTipo - " + a.getTipo() +
                            "\nCor - " + a.getCor() +
                            "\nAno Fabricacao - " + a.getAno_fab() +
                            "\nAno Modelo - " + a.getAno_modelo() +
                            "\nChassi - " + a.getChassi() +
                            "\nPlaca - " + a.getPlaca() +
                            "\nKm - " + a.getKm() +
                            "\nValor - " + a.getValor()
            );
            System.out.println("----------------------------------------");
        }

            System.out.println("Digite a placa do veiculo");
            placa = sc.nextLine();

        for(Cliente c:  clienteController.listar()){
            System.out.println(
                    "Codigo: "+c.getCodigo()+
                            "\n"+"Nome: "+c.getNome()+
                            "\n"+"CPF: "+c.getCpf()+
                            "\n"+"Endereco: "+c.getEndereco()+
                            "\n"+"Telefone: "+c.getTelefone()+
                            "\n"+"Data Nascimento: "+c.getDt_nascimento()
            );
            System.out.println("----------------------------------------");
        }

            System.out.println("Digite o codigo do cliente");
            codigoCliente = sc.nextLine();

        for (Funcionario f : funcionarioController.listar()){
            System.out.println(
                    "Codigo: "+f.getCodigo()+
                            "\n"+"Cargo: "+f.getCargo()+
                            "\n"+"Nome: "+f.getNome()+
                            "\n"+"CPF: "+f.getCpf()+
                            "\n"+"Endereco: "+f.getEndereco()+
                            "\n"+"Telefone: "+f.getTelefone()+
                            "\n"+"Data Nascimento: "+f.getDt_nascimento()+
                            "\n"+"Usuario: "+f.getUsuario()
            );
            System.out.println("----------------------------------------");
        }
            System.out.println("Digite o codigo do funcionario");
            codigoFuncionario = sc.nextLine();

            if (automovelController.verifica(placa)) {
                if (clienteController.verifica(codigoCliente)) {
                    if (funcionarioController.verifica(codigoFuncionario)) {
                        dadosPrimarios = true;
                    } else {
                        System.out.println("Funcionario não encontrado");
                    }
                } else {
                    System.out.println("Cliente nao encontrado");
                }
            } else {
                System.out.println("Veiculo nao encontrado");
            }


            if (dadosPrimarios) {

                venda.setPlaca(placa);
                venda.setCodCliente(codigoCliente);
                venda.setCodFuncionario(codigoFuncionario);

                System.out.println("Digite o codigo da venda");
                venda.setCodigo(sc.nextInt());

                sc.nextLine();

                try {
                    System.out.println("Digite a data da venda");
                    String dataVenda = sc.nextLine();
                    Date dtV = new SimpleDateFormat("dd/MM/yyyy").parse(dataVenda);
                    venda.setDt_venda(dtV);
                } catch (ParseException e) {
                    e.getMessage();
                }

                System.out.println("Digite o valor da venda");
                venda.setValor_venda(sc.nextFloat());
                System.out.println("Digite a comissão da venda");
                venda.setComissao_venda(sc.nextFloat());


                if (vendaController.cadastrar(venda)) {
                    System.out.println("Venda realizada");
                } else {
                    System.out.println("Erro ao realizar venda, verifique os dados e tente novamente");
                }
            }
        }


        private void alterarVenda(){
            System.out.println("Digite o codigo da venda que deseja alterar");
            int codVenda = sc.nextInt();

            Venda v = vendaController.buscar(codVenda);
            if (v == null) {
                System.out.println("Venda não encontrada");
            } else {
                Automovel automovel = automovelController.buscar(v.getPlaca());
                Cliente cliente = clienteController.buscar(v.getCodCliente());
                Funcionario funcionario = funcionarioController.buscar(v.getCodFuncionario());

                System.out.println(
                        "Código venda: " + v.getCodigo() +
                                "\n" + "Nome funcionario: " + funcionario.getNome() +
                                "\n" + "Nome Cliente: " + cliente.getNome() +
                                "\n" + "Nome entity.Marca: " + automovel.getNomeMarca() +
                                "\n" + "Nome entity.Modelo: " + automovel.getNomeModelo() +
                                "\n" + "Cor: " + automovel.getCor() +
                                "\n" + "Valor da Venda: " + v.getValor_venda() +
                                "\n" + "Valor da Comissão: " + v.getComissao_venda() +
                                "\n" + "Data da entity.Venda: " + v.getDt_venda()
                );
            }

            String codigoCliente;
            String codigoFuncionario;
            String placa;
            boolean dadosPrimarios = false;

            System.out.println("Digite a nova placa do veiculo");
            placa = sc.nextLine();
            System.out.println("Digite o novo codigo do cliente");
            codigoCliente = sc.nextLine();
            System.out.println("Digite o novo codigo do funcionario");
            codigoFuncionario = sc.nextLine();

            if (automovelController.verifica(placa)) {
                if (clienteController.verifica(codigoCliente)) {
                    if (funcionarioController.verifica(codigoFuncionario)) {
                        dadosPrimarios = true;
                    } else {
                        System.out.println("Funcionario não encontrado");
                    }
                } else {
                    System.out.println("Cliente nao encontrado");
                }
            } else {
                System.out.println("Veiculo nao encontrado");
            }


            if (dadosPrimarios) {

                venda.setPlaca(placa);
                venda.setCodCliente(codigoCliente);
                venda.setCodFuncionario(codigoFuncionario);

                try {
                    System.out.println("Digite a nova data da venda");
                    String dataVenda = sc.nextLine();
                    Date dtV = new SimpleDateFormat("dd/MM/yyyy").parse(dataVenda);
                    venda.setDt_venda(dtV);
                } catch (ParseException e) {
                    e.getMessage();
                }

                System.out.println("Digite o novo valor da venda");
                venda.setValor_venda(sc.nextFloat());
                System.out.println("Digite a nova comissão da venda");
                venda.setComissao_venda(sc.nextFloat());


                if (vendaController.alterar(venda)) {
                    System.out.println("Venda alterada");
                } else {
                    System.out.println("Erro ao alterar venda, verifique os dados e tente novamente");
                }

            }

        }

        private void buscarVenda () {
            System.out.println("Digite o codigo da venda que deseja buscar");
            int codVenda = sc.nextInt();


            Venda v = vendaController.buscar(codVenda);
            if (v == null) {
                System.out.println("Venda não encontrada");
            } else {
                Automovel automovel = automovelController.buscar(v.getPlaca());
                Cliente cliente = clienteController.buscar(v.getCodCliente());
                Funcionario funcionario = funcionarioController.buscar(v.getCodFuncionario());

                System.out.println(
                        "Código venda: " + v.getCodigo() +
                                "\n" + "Nome funcionario: " + funcionario.getNome() +
                                "\n" + "Nome Cliente: " + cliente.getNome() +
                                "\n" + "Nome entity.Marca: " + automovel.getNomeMarca() +
                                "\n" + "Nome entity.Modelo: " + automovel.getNomeModelo() +
                                "\n" + "Cor: " + automovel.getCor() +
                                "\n" + "Valor da Venda: " + v.getValor_venda() +
                                "\n" + "Valor da Comissão: " + v.getComissao_venda() +
                                "\n" + "Data da entity.Venda: " + v.getDt_venda()
                );
            }

        }

        private void cancelarVenda () {

            System.out.println("Digite o codigo da venda que deseja cancelar");
            int codVenda = sc.nextInt();

            if (vendaController.cancelar(codVenda)) {
                System.out.println("Venda cancelada!");
            } else {
                System.out.println("Erro ao cancelar venda, verifique os dados e tente novamente");
            }

        }

        private void relatorioVenda () {

            List<RelatorioVenda> relatorioVendas = new ArrayList<RelatorioVenda>();
            Date dtIV = null;
            Date dtFV = null;
            try {
                System.out.println("Digite a data inicial");
                String dataInicialVenda = sc.nextLine();
                dtIV = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicialVenda);
                System.out.println("Digite a data inicial");
                String dataFinallVenda = sc.nextLine();
                dtFV = new SimpleDateFormat("dd/MM/yyyy").parse(dataFinallVenda);
                relatorioVendas = vendaController.relatorioVenda(dtIV, dtFV);

            } catch (ParseException e) {
                e.getMessage();
            }


            System.out.println("*****************************************************************");

            System.out.println(
                    "Data inicial: " + dtIV + "\t\t\t" + "Data Final:" + dtFV + "\n" +
                            "Vendedor" + "\t\t\t\t" + "Vendas Total" + "\t\t\t\t" + "Comissao"
            );

            for (RelatorioVenda rv : relatorioVendas) {
                System.out.println(rv.getNomeVendedor() + "\t\t\t\t\t" + rv.getVendasTotal() + "\t\t\t\t\t" + rv.getComissao());
            }

            System.out.println("*****************************************************************");

        }


    }






