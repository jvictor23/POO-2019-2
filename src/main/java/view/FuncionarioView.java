package view;

import controller.FuncionarioController;
import entity.Badeco;
import entity.Funcionario;
import entity.Gerente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FuncionarioView {
    private FuncionarioController funcionarioController;
    private Scanner sc;
    private Funcionario funcionario;

    public FuncionarioView(){
        this.funcionarioController = new FuncionarioController();
        this.sc = new Scanner(System.in);
        this.funcionario = new Funcionario();
    }

    public void menuFuncionario() {
        System.out.println("#Menu entity.Funcionario");
        System.out.println("01- Listar");
        System.out.println("02- Cadastrar");
        System.out.println("03- Alterar");
        System.out.println("04- Buscar");
        System.out.println("05- Excluir");
        System.out.println("06- Cadastrar Salario");
        System.out.println("07- Alterar Salario");
        System.out.println("00- Voltar");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        switch (op){
            case 1:
                listarFuncionarios();
                menuFuncionario();
                break;
            case 2:
                cadastrarFuncionario();
                menuFuncionario();
                break;
            case 3:
                alterarUsuario();
                menuFuncionario();
                break;
            case 4:
                buscarFuncionario();
                menuFuncionario();
                break;
            case 5:
                excluirFuncionario();
                menuFuncionario();
                break;
            case 6:
                cadastrarSalario();
                menuFuncionario();
                break;
            case 7:
                alterarSalario();
                menuFuncionario();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuFuncionario();
                break;
        }
    }

    public void listarFuncionarios(){

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
            System.out.println("*****************************************************");
        }
        }



    public void cadastrarFuncionario()  {

        System.out.println("Digite 1 para cadastrar Gerente");
        System.out.println("Digite 2 para cadastrar Funcionario");
        System.out.println("Digite 3 para cadastrar Badeco");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        switch (op){
            case 1:
                this.funcionario = new Gerente();
                funcionario.setCargo("Gerente");
                break;
            case 2:
                this.funcionario = new Funcionario();
                funcionario.setCargo("Funcionario");
                break;
            case 3:
                this.funcionario = new Badeco();
                funcionario.setCargo("Badeco");
                break;
        }

        sc.nextLine();

       System.out.println("> Informe o nome: ");
        funcionario.setNome(sc.nextLine());

        System.out.println("> Informe o cpf: ");
        funcionario.setCpf(sc.nextLine());

        System.out.println("> Informe o endereço: ");
        funcionario.setEndereco(sc.nextLine());

        System.out.println("> Informe o telefone: ");
        funcionario.setTelefone(sc.nextLine());

        try {
            System.out.print("> Informe a data de nascimento: ");
            String data = sc.nextLine();
            Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            funcionario.setDt_nascimento(dt);
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }

        System.out.println("> Informe o codigo: ");
        funcionario.setCodigo(sc.nextLine());

        System.out.println("> Informe o usuario: ");
        funcionario.setUsuario(sc.nextLine());

        System.out.println("> Informe a senha: ");
        funcionario.setSenha(sc.nextLine());

       if (funcionarioController.cadastrar(funcionario)){
           System.out.println("Sucesso ao cadastrar funcionario");
       }else{
           System.out.println("Erro ao cadastrar funcionario, tente novamente");
       }

    }

    public void alterarUsuario(){
        Scanner sc = new Scanner(System.in);

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
            System.out.println("*****************************************************");
        }
        System.out.println("# Digite o codigo do funcionário que deseja alterar");
        String codigo = sc.nextLine();

        System.out.println("Informe o novo cargo");
        funcionario.setCargo(sc.nextLine());

        System.out.println("> Informe o novo nome: ");
        funcionario.setNome(sc.nextLine());

        System.out.println("> Informe o novo cpf: ");
        funcionario.setCpf(sc.nextLine());

        System.out.println("> Informe o novo endereço: ");
        funcionario.setEndereco(sc.nextLine());

        System.out.println("> Informe o novo telefone: ");
        funcionario.setTelefone(sc.nextLine());

        try {
            System.out.print("> Informe a nova data de nascimento: ");
            String data = sc.nextLine();
            Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            funcionario.setDt_nascimento(dt);
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }

        funcionario.setCodigo(codigo);

        System.out.println("> Informe o novo usuario: ");
        funcionario.setUsuario(sc.nextLine());

        System.out.println("> Informe a nova senha: ");
        funcionario.setSenha(sc.nextLine());

        if (funcionarioController.alterar(funcionario)){
            System.out.println("Sucesso ao alterar usuario");
        }else{
            System.out.println("Erro ao alterar usuario, tente novamente");
        }

        }



    public void buscarFuncionario(){

            Scanner sc = new Scanner(System.in);
            System.out.println("# Digite o codigo do funcionário que deseja buscar");
            String codigo = sc.nextLine();


          Funcionario f =  funcionarioController.buscar(codigo);

          if (f == null){
              System.out.println("Funcionario não encontrado");
          }else{
              System.out.println(
                      "Codigo: "+f.getCodigo()+
                              "\n"+"Cargo: "+f.getCargo()+
                              "\n"+"Nome: "+f.getNome()+
                              "\n"+"CPF: "+f.getCpf()+
                              "\n"+"Endereco: "+f.getEndereco()+
                              "\n"+"Telefone: "+f.getTelefone()+
                              "\n"+"Data Nascimento: "+f.getDt_nascimento()+
                              "\n"+"Usuario: "+f.getUsuario());
          }

        }


    public void excluirFuncionario(){

            Scanner sc = new Scanner(System.in);
            System.out.println("# Digite o codigo do funcionário que deseja excluir");
            String codigo = sc.nextLine();

            if (funcionarioController.excluir(codigo)){
                System.out.println("Funcionario Excluido");
            }else {
                System.out.println("Erro ao excluir funcionario, verifique se o funcionario está cadastrado");
            }
        }

    public  void cadastrarSalario() {
        System.out.println("Digite o codigo do funcionario que deseja cadastrar o salario");
        listarFuncionarios();
        String id = sc.nextLine();
        Funcionario f = funcionarioController.buscar(id);
        System.out.println("Digite o valor do salario");
        float salario = sc.nextFloat();

        if (f.getCargo().equals("Badeco")) {
            float salarioMenorFuncionario = funcionarioController.salarioMenorFuncionario();
            if (salario >= salarioMenorFuncionario) {
                System.out.println("Erro ao cadastrar salario, um badeco não pode ganhar mais que um funcionario");
            } else {
                if (funcionarioController.cadastrarSalario(salario,id)){
                    System.out.println("Salario Cadastrado!");
                }else {
                    System.out.println("Erro ao cadastrar salario, tente novamente!");
                }
            }
        } else if (f.getCargo().equals("Funcionario")) {
            float salarioMenorGerente = funcionarioController.salarioMenorGerente();
            float salarioMaiorBadeco = funcionarioController.salarioMaiorBadeco();
            if (salario >= salarioMenorGerente) {
                System.out.println("Erro ao cadastrar salario, um funcionario não pode ganhar mais que um gerente");
            }else if(salario <= salarioMaiorBadeco) {
                System.out.println("Erro ao cadastrar salario, um funcionario não pode ganhar menos que um badeco");
            }else {
                if (funcionarioController.cadastrarSalario(salario,id)){
                    System.out.println("Salario Cadastrado!");
                }else {
                    System.out.println("Erro ao cadastrar salario, tente novamente!");
                }
            }
        } else if (f.getCargo().equals("Gerente")) {
            float salarioMaiorFuncionario = funcionarioController.salarioMaiorFuncionario();
            if (salario <= salarioMaiorFuncionario){
                System.out.println("Erro ao cadastrar salario, um Gerente não pode ganhar menos que um funcionario");
            }else {
                if (funcionarioController.cadastrarSalario(salario,id)){
                    System.out.println("Salario Cadastrado!");
                }else {
                    System.out.println("Erro ao cadastrar salario, tente novamente!");
                }
            }

        }

    }

    public void alterarSalario(){
        System.out.println("Digite o codigo do funcionario que deseja alterar o salario");
        String id = sc.nextLine();
        System.out.println("Digite o novo salario");
        float novoSalario = sc.nextFloat();
        Funcionario f = funcionarioController.buscar(id);

        if (f.getCargo().equals("Badeco")) {
            float salarioMenorFuncionario = funcionarioController.salarioMenorFuncionario();
            if (novoSalario >= salarioMenorFuncionario) {
                System.out.println("Erro ao alterar salario, um badeco não pode ganhar mais que um funcionario");
            } else {
                if (funcionarioController.alterarSalario(novoSalario,id)){
                    System.out.println("Salario Alterado!");
                }else {
                    System.out.println("Erro ao alterar salario, tente novamente!");
                }
            }
        } else if (f.getCargo().equals("Funcionario")) {
            float salarioMenorGerente = funcionarioController.salarioMenorGerente();
            float salarioMaiorBadeco = funcionarioController.salarioMaiorBadeco();
            if (novoSalario >= salarioMenorGerente) {
                System.out.println("Erro ao alterar salario, um funcionario não pode ganhar mais que um gerente");
            }else if(novoSalario <= salarioMaiorBadeco) {
                System.out.println("Erro ao alterar salario, um funcionario não pode ganhar menos que um badeco");
            }else {
                if (funcionarioController.alterarSalario(novoSalario,id)){
                    System.out.println("Salario Alterado!");
                }else {
                    System.out.println("Erro ao alterar salario, tente novamente!");
                }
            }
        } else if (f.getCargo().equals("Gerente")) {
            float salarioMaiorFuncionario = funcionarioController.salarioMaiorFuncionario();
            if (novoSalario <= salarioMaiorFuncionario){
                System.out.println("Erro ao alterar salario, um Gerente não pode ganhar menos que um funcionario");
            }else {
                if (funcionarioController.alterarSalario(novoSalario,id)){
                    System.out.println("Salario Alterado!");
                }else {
                    System.out.println("Erro ao alterar salario, tente novamente!");
                }
            }

        }

    }
}
