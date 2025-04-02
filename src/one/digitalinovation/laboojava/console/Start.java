package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.entidade.constantes.Materias;
import one.digitalinovation.laboojava.negocio.ClienteNegocio;
import one.digitalinovation.laboojava.negocio.PedidoNegocio;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        System.out.println("Bem vindo ao e-Compras");

        String opcao = "";

        while(true) {
            if (clienteLogado == null) {

                System.out.println("Digite o cpf:");

                String cpf = "";
                cpf = LeitoraDados.lerDado();

                identificarUsuario(cpf);
            }

            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Consultar Livro");
            System.out.println("3 - Excluir Livro");
            System.out.println("4 - Cadastrar Caderno");
            System.out.println("5 - Consultar Caderno");
            System.out.println("6 - Excluir Caderno");
            System.out.println("7 - Fazer pedido");
            System.out.println("8 - Consultar Pedido");
            System.out.println("9 - Excluir pedido");
            System.out.println("10 - Listar produtos");
            System.out.println("11 - Listar pedidos");
            System.out.println("12 - Deslogar");
            System.out.println("13 - Cadastrar Cliente");
            System.out.println("14 - Excluir Cliente");
            System.out.println("15 - Sair");

            opcao = LeitoraDados.lerDado();

            Livro livro;
            String codigo = "";

            switch (opcao) {
                case "1":
                    cadastrarLivro();
                    break;
                case "2":
                    consultarLivro();
                    break;
                case "3":
                    excluirProduto();
                    break;
                case "4":
                    cadastrarCaderno();
                    break;
                case "5":
                    consultarCaderno();
                    break;
                case "6":
                    excluirProduto();
                    break;
                case "7":
                    fazerPedido();
                    break;
                case "8":
                    consultarPedido();
                    break;
                case "9":
                    excluirPedido();
                    break;
                case "10":
                    produtoNegocio.listarTodos();
                    break;
                case "11":
                    pedidoNegocio.listarTodos();
                    break;
                case "12":
                    deslogar();
                    break;
                case "13":
                    cadastrarCliente();
                    break;
                case "14":
                    excluirCliente();
                    break;

                case "15":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void cadastrarLivro() {
        Livro livro = LeitoraDados.lerLivro();
        produtoNegocio.salvar(livro);
    }

    private static void consultarLivro() {
        System.out.println("Digite o código do livro");
        String codigo = LeitoraDados.lerDado();
        Produto livroConsulta = produtoNegocio.consultar(codigo).get();
        System.out.println(livroConsulta);
    }

    private static void excluirProduto() {
        System.out.println("Digite o código do produto");
        String codigo = LeitoraDados.lerDado();
        produtoNegocio.excluir(codigo);
    }

    private static void cadastrarCaderno() {
        Caderno caderno = LeitoraDados.lerCarderno();
        produtoNegocio.salvar(caderno);
    }

    private static void consultarCaderno() {
        System.out.println("Digite a quantidade de matérias (M2, M5 ou M10): ");
        String materias = LeitoraDados.lerDado();
        List<Produto> cadernos =
                produtoNegocio.consultar(Materias.valueOf(materias.toUpperCase()))
                        .orElse(new ArrayList<>());
        System.out.println(cadernos);
    }

    private static void fazerPedido() {
        Pedido pedido = LeitoraDados.lerPedido(banco, clienteLogado);
        Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

        if (cupom.isPresent()) {
            pedidoNegocio.salvar(pedido, cupom.get());
        } else {
            pedidoNegocio.salvar(pedido);
        }
    }

    private static void consultarPedido() {
        System.out.println("Digite o código do pedido");
        String codigo = LeitoraDados.lerDado();
        Pedido pedidoConsulta = pedidoNegocio.consultar(codigo).get();
        System.out.println(pedidoConsulta);
    }

    private static void excluirPedido() {
        System.out.println("Digite o código do pedido");
        String codigoPedido = LeitoraDados.lerDado();
        pedidoNegocio.excluir(codigoPedido);
    }

    private static void deslogar() {
        System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
        clienteLogado = null;
    }

    private static void cadastrarCliente() {
        Cliente cliente = LeitoraDados.lerCliente();
        clienteNegocio.cadastrar(cliente);
//        banco.adicionarCliente(cliente);
    }

    private static void excluirCliente() {
        System.out.println("Digite o CPF: ");
        String cpf = LeitoraDados.lerDado();
        clienteNegocio.excluir(cpf);
        clienteLogado = null;
    }

    /**
     * Procura o usuário na base de dados.
     * @param cpf CPF do usuário que deseja logar na aplicação
     */
    private static void identificarUsuario(String cpf) {

        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
        } else {
            System.out.println("Usuário não cadastrado.");
            System.exit(0);
        }
    }
}
