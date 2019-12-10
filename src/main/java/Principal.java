import view.AutomovelView;
import view.ClienteView;
import view.FuncionarioView;
import view.VendaView;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args){

        Principal principal = new Principal();
        principal.menuPrincipal();

    }

    public void menuPrincipal() {
        System.out.println("#Menu Principal");
        System.out.println("01- Funcionario");
        System.out.println("02- Cliente");
        System.out.println("03- Automovel");
        System.out.println("04- Vendas");
        System.out.println("00- Sair");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op) {
            case 1:
                FuncionarioView funcionarioView = new FuncionarioView();
                funcionarioView.menuFuncionario();
                menuPrincipal();
                break;
            case 2:
                ClienteView clienteView = new ClienteView();
                clienteView.menuCliente();
                menuPrincipal();
                break;
            case 3:
                AutomovelView automovelView = new AutomovelView();
                automovelView.menuAutomovel();
                menuPrincipal();
                break;
            case 4:
                VendaView vendaView = new VendaView();
                vendaView.menuVenda();
                menuPrincipal();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção Inválida");
                menuPrincipal();
                break;
        }

    }
}
