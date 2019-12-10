package view;

import controller.ClienteController;
import entity.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClienteView {

    private ClienteController clienteController;
    private Scanner sc;
    private Cliente cliente;

    public ClienteView(){
        this.clienteController = new ClienteController();
        this.sc = new Scanner(System.in);
        this.cliente = new Cliente();
    }

    public void menuCliente(){

        System.out.println("#Menu entity.Cliente");
        System.out.println("01- Listar");
        System.out.println("02- Cadastrar");
        System.out.println("03- Alterar");
        System.out.println("04- Buscar");
        System.out.println("05- Excluir");
        System.out.println("00- Voltar");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                listarCliente();
                menuCliente();
                break;
            case 2:
                cadastrarCliente();
                menuCliente();
                break;
            case 3:
                alterarCliente();
                menuCliente();
                break;
            case 4:
                buscarCliente();
                menuCliente();
                break;
            case 5:
                excluirCliente();
                menuCliente();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuCliente();
                break;
        }



    }


    public void listarCliente(){

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
    }

    public void cadastrarCliente()  {



        Scanner sc = new Scanner(System.in);

        System.out.println("------------------------------------------");

        System.out.println("> Informe o nome: ");
        cliente.setNome(sc.nextLine());

        System.out.println("> Informe o cpf: ");
        cliente.setCpf(sc.nextLine());

        System.out.println("> Informe o endereço: ");
        cliente.setEndereco(sc.nextLine());

        System.out.println("> Informe o telefone: ");
        cliente.setTelefone(sc.nextLine());

        try {
            System.out.print("> Informe a data de nascimento: ");
            String data = sc.nextLine();
            Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            cliente.setDt_nascimento(dt);
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }

        System.out.println("> Informe o codigo: ");
        cliente.setCodigo(sc.nextLine());

        if (clienteController.cadastrar(cliente)){
            System.out.println("Cliente cadastrado com sucesso");
        }else {
            System.out.println("Erro ao cadastrar cliente, tente novamente");
        }

        System.out.println("------------------------------------------");
    }

    public void alterarCliente(){

            Scanner sc = new Scanner(System.in);
            System.out.println("# Digite o codigo do cliente que deseja alterar");
            String codigo = sc.nextLine();

            Cliente c = clienteController.buscar(codigo);
            if (c==null){
                System.out.println("Cliente não encontrado");
            }else{
                System.out.println(
                        "Codigo: "+c.getCodigo()+
                                "\n"+"Nome: "+c.getNome()+
                                "\n"+"CPF: "+c.getCpf()+
                                "\n"+"Endereco: "+c.getEndereco()+
                                "\n"+"Telefone: "+c.getTelefone()+
                                "\n"+"Data Nascimento: "+c.getDt_nascimento()
                );
            }

        System.out.println("> Informe o novo nome: ");
        cliente.setNome(sc.nextLine());

        System.out.println("> Informe o novo cpf: ");
        cliente.setCpf(sc.nextLine());

        System.out.println("> Informe o novo endereço: ");
        cliente.setEndereco(sc.nextLine());

        System.out.println("> Informe o novo telefone: ");
        cliente.setTelefone(sc.nextLine());

        try {
            System.out.print("> Informe a nova data de nascimento: ");
            String data = sc.nextLine();
            Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            cliente.setDt_nascimento(dt);
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }

        cliente.setCodigo(codigo);

        if (clienteController.alterar(cliente)){
            System.out.println("Cliente alterado");
        }else{
            System.out.println("Erro ao alterar cliente, tente novamente");
        }

        }



    public void buscarCliente(){

            Scanner sc = new Scanner(System.in);
            System.out.println("# Digite o codigo do cliente que deseja buscar");
            String codigo = sc.nextLine();

            Cliente c = clienteController.buscar(codigo);

            if (c == null){
                System.out.println("Cliente não encontrado");
            }else {
                System.out.println(
                        "Codigo: "+c.getCodigo()+
                                "\n"+"Nome: "+c.getNome()+
                                "\n"+"CPF: "+c.getCpf()+
                                "\n"+"Endereco: "+c.getEndereco()+
                                "\n"+"Telefone: "+c.getTelefone()+
                                "\n"+"Data Nascimento: "+c.getDt_nascimento());
            }
        }


    public void excluirCliente(){


            Scanner sc = new Scanner(System.in);
            System.out.println("# Digite o codigo do cliente que deseja excluir");
            String codigo = sc.nextLine();

            if (clienteController.excluir(codigo)){
                System.out.println("Cliente excluido");
            }else{
                System.out.println("Erro ao excluir cliente, tente novamente");
            }

        }
    }


