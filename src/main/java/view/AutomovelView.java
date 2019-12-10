package view;

import controller.AutomovelController;
import entity.Automovel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AutomovelView {

    AutomovelController automovelController;
    Scanner sc;

    public AutomovelView(){
        this.automovelController = new AutomovelController();
        this.sc = new Scanner(System.in);
    }

    public void menuAutomovel(){
        System.out.println("#Menu Automovel");
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
                menuListar();
                menuAutomovel();
                break;
            case 2:
                menuCadastrar();
                menuAutomovel();
                break;
            case 3:
                menuAlterar();
                menuAutomovel();
                break;
            case 4:
                menuBuscar();
                menuAutomovel();
                break;
            case 5:
               menuExcluir();
                menuAutomovel();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuAutomovel();
                break;
        }

    }

    public void menuListar(){
        System.out.println("Digite 1 para listar marcas");
        System.out.println("Digite 2 para listar modelos");
        System.out.println("Digite 3 para listar automoveis");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                MarcaView marcaView = new MarcaView();
                marcaView.listarMarca();
                break;
            case 2:
                ModeloView modeloView = new ModeloView();
                modeloView.listarModelo();
                break;
            case 3:
                listarAutomovel();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuListar();
                break;
        }
    }

    public void menuCadastrar(){

        System.out.println("Digite 1 para cadastrar marcas");
        System.out.println("Digite 2 para cadastrar modelos");
        System.out.println("Digite 3 para cadastrar automoveis");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                MarcaView marcaView = new MarcaView();
                marcaView.cadastrarMarca();
                break;
            case 2:
                ModeloView modeloView = new ModeloView();
                modeloView.cadastrarModelo();
                break;
            case 3:
                cadastrarAutomovel();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuCadastrar();
                break;
        }

    }
    public void menuAlterar(){

        System.out.println("Digite 1 para alterar marcas");
        System.out.println("Digite 2 para alterar modelos");
        System.out.println("Digite 3 para alterar automoveis");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                MarcaView marcaView = new MarcaView();
                marcaView.alterarMarca();
                break;
            case 2:
                ModeloView modeloView = new ModeloView();
                modeloView.alterarModelo();
                break;
            case 3:
                alterarAutomovel();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuAlterar();
                break;
        }

    }

    public void menuBuscar(){

        System.out.println("Digite 1 para buscar marcas");
        System.out.println("Digite 2 para buscar modelos");
        System.out.println("Digite 3 para buscar automoveis");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                MarcaView marcaView = new MarcaView();
                marcaView.buscarMarca();
                break;
            case 2:
                ModeloView modeloView = new ModeloView();
                modeloView.buscarModelo();
                break;
            case 3:
                buscarAutomovel();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuBuscar();
                break;
        }

    }
    public void menuExcluir(){

        System.out.println("Digite 1 para excluir marcas");
        System.out.println("Digite 2 para excluir modelos");
        System.out.println("Digite 3 para excluir automoveis");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                MarcaView marcaView = new MarcaView();
                marcaView.excluirMarca();
                break;
            case 2:
                ModeloView modeloView = new ModeloView();
                modeloView.excluirModelo();
                break;
            case 3:
                excluirAutomovel();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
                menuExcluir();
                break;
        }

    }

    public void listarAutomovel(){
        for(Automovel a : automovelController.listar()){
            System.out.println(
                            "\nNomeMarca - " + a.getNomeMarca()+
                            "\nNomeModelo - " + a.getNomeModelo()+
                            "\nTipo - "+ a.getTipo()+
                            "\nCor - "+ a.getCor()+
                            "\nAno Fabricacao - "+ a.getAno_fab()+
                            "\nAno Modelo - "+ a.getAno_modelo()+
                            "\nChassi - "+ a.getChassi()+
                            "\nPlaca - "+ a.getPlaca()+
                            "\nKm - "+ a.getKm()+
                            "\nValor - "+ a.getValor()+
                            "\n------------------------------"
            );
        }
    }


    public void cadastrarAutomovel()  {

        Automovel automovel = new Automovel();
        int idMod;

        System.out.println("# Cadastro Automovel ");

        System.out.println("> Informe id do modelo");

        ModeloView modeloView = new ModeloView();
        modeloView.listarModelo();



        idMod = sc.nextInt();

        sc.nextLine();

        System.out.println("> Informe a cor do automovel: ");
        automovel.setCor(sc.nextLine());

        try {
            System.out.print("> Informe o ano da fabricaçao ");
            String dataFab = sc.nextLine();
            Date anF = new SimpleDateFormat("dd/MM/yyyy").parse(dataFab);
            automovel.setAno_fab(anF);

            System.out.print("> Informe o ano do modelo ");
            String dataMod = sc.nextLine();
            Date anM = new SimpleDateFormat("dd/MM/yyyy").parse(dataMod);
            automovel.setAno_modelo(anM);
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }

        System.out.println("> Informe o chassi: ");
        automovel.setChassi(sc.nextLine());

        System.out.println("> Informe a placa: ");
        automovel.setPlaca(sc.nextLine());

        System.out.println("> Informe a quilometragem: ");
        automovel.setKm(sc.nextFloat());

        System.out.println("> Informe o valor: ");
        automovel.setValor(sc.nextFloat());

        automovelController.cadastrar(automovel, idMod);

    }


    public void alterarAutomovel(){

        System.out.println("Digite a placa do automovel que deseja alterar");
        String placa = sc.nextLine();

        Automovel a = automovelController.buscar(placa);
        if(a == null){
            System.out.println("Automovel não encontrado");
        }else{
            System.out.println(

                    "\nNomeMarca - " + a.getNomeMarca()+
                            "\nNomeModelo - " + a.getNomeModelo()+
                            "\nTipo - "+ a.getTipo()+
                            "\nCor - "+ a.getCor()+
                            "\nAno Fabricacao - "+ a.getAno_fab()+
                            "\nAno Modelo - "+ a.getAno_modelo()+
                            "\nChassi - "+ a.getChassi()+
                            "\nPlaca - "+ a.getPlaca()+
                            "\nKm - "+ a.getKm()+
                            "\nValor - "+ a.getValor()+
                            "\n------------------------------"
            );
        }

        Automovel automovel = new Automovel();

        System.out.println("> Informe a nova cor do automovel: ");
        automovel.setCor(sc.nextLine());

        try {
            System.out.print("> Informe o novo ano da fabricaçao ");
            String dataFab = sc.nextLine();
            Date anF = new SimpleDateFormat("dd/MM/yyyy").parse(dataFab);
            automovel.setAno_fab(anF);

            System.out.print("> Informe o novo ano do modelo ");
            String dataMod = sc.nextLine();
            Date anM = new SimpleDateFormat("dd/MM/yyyy").parse(dataMod);
            automovel.setAno_modelo(anM);
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }

        System.out.println("> Informe o novo chassi: ");
        automovel.setChassi(sc.nextLine());

        System.out.println("> Informe a nova placa: ");
        automovel.setPlaca(sc.nextLine());

        System.out.println("> Informe a nova quilometragem: ");
        automovel.setKm(sc.nextFloat());

        System.out.println("> Informe o novo valor: ");
        automovel.setValor(sc.nextFloat());

        if (automovelController.alterar(automovel,placa)){
            System.out.println("Automovel alterado");
        }else {
            System.out.println("Erro ao alterar automovel, tente novamente");
        }

    }



    public void buscarAutomovel(){

        System.out.println("Digite a placa do automovel que deseja buscar");
        String placa = sc.nextLine();
        Automovel a = automovelController.buscar(placa);
            System.out.println(

                    "\nNomeMarca - " + a.getNomeMarca()+
                            "\nNomeModelo - " + a.getNomeModelo()+
                            "\nTipo - "+ a.getTipo()+
                            "\nCor - "+ a.getCor()+
                            "\nAno Fabricacao - "+ a.getAno_fab()+
                            "\nAno Modelo - "+ a.getAno_modelo()+
                            "\nChassi - "+ a.getChassi()+
                            "\nPlaca - "+ a.getPlaca()+
                            "\nKm - "+ a.getKm()+
                            "\nValor - "+ a.getValor()+
                            "\n------------------------------"
            );

    }


    public void excluirAutomovel(){

        System.out.println("# Digite a placa do automovel que deseja excluir");
        String placa = sc.nextLine();

        if (automovelController.excluir(placa)){
            System.out.println("Automovel Excluido!");
        }else {
            System.out.println("Automovel não encontrado, tente novamente");
        }
    }

}
