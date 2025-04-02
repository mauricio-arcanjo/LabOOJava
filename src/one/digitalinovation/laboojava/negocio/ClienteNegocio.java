package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;

import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Cliente}.
 * @author thiago leite
 */
public class ClienteNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter acesso aos clientes cadastrados
     */
    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */
    public Optional<Cliente> consultar(String cpf) {

        for (Cliente c : bancoDados.getCliente()){
            if (c.getCpf().equals(cpf)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    /**
     * Cadastra um novo cliente.
     * @param cliente Novo cliente que terá acesso a aplicação
     */
    public void cadastrar(Cliente cliente) {

        boolean clienteExistente = false;

        for (Cliente c : bancoDados.getCliente()) {
            if (c.getCpf().equals(cliente.getCpf())) {
                System.out.println("Cliente já cadastrado anteriormente!");
                clienteExistente = true;
            }
        }
        if (!clienteExistente) {
            System.out.println("teste");
            bancoDados.adicionarCliente(cliente);
        }
    }
    /**
     * Exclui um cliente específico.
     * @param cpf CPF do cliente
     */
    public void excluir(String cpf) {
        for (Cliente c : bancoDados.getCliente()) {
            if (c.getCpf().equals(cpf)) {
                bancoDados.excluirCliente(c);
                System.out.println("Cliente excluído com sucesso!");
            }
        }
    }
}
