package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Caderno;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.constantes.Materias;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Produto}.
 * @author thiago leite
 */
public class ProdutoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os produtos
     */
    public ProdutoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Salva um novo produto(livro ou caderno) na loja.
     * @param novoProduto Livro ou caderno que pode ser vendido
     */
    public void salvar(Produto novoProduto) {

        String codigo = "PR%04d";
        codigo = String.format(codigo, bancoDados.getProdutos().length);
        novoProduto.setCodigo(codigo);

        boolean produtoRepetido = false;
        for (Produto produto: bancoDados.getProdutos()) {
            if (produto.getCodigo() == novoProduto.getCodigo()) {
                produtoRepetido = true;
                System.out.println("Produto já cadastrado.");
                break;
            }
        }

        if (!produtoRepetido) {
            this.bancoDados.adicionarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    /**
     * Exclui um produto pelo código de cadastro.
     * @param codigo Código de cadastro do produto
     */
    public void excluir(String codigo) {
        boolean statusExclusao = false;
        for (Produto produto: bancoDados.getProdutos()) {

            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                bancoDados.removerProduto(produto);
                System.out.println("Produto excluído com sucesso!");
                statusExclusao = true;
            }
        }
        if (!statusExclusao){
            System.out.println("Produto não encontrado!");
        }
    }

    /**
     * Obtem um produto a partir de seu código de cadastro.
     * @param codigo Código de cadastro do produto
     * @return Optional indicando a existência ou não do Produto
     */
    public Optional<Produto> consultar(String codigo) {

        for (Produto produto: bancoDados.getProdutos()) {

            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return  Optional.of(produto);
            }
        }
        return Optional.empty();
    }

    public Optional<List<Produto>> consultar(Materias materias) {
        List<Produto> cadernos = filtrarCadernosPorMaterias(materias);
        return cadernos.isEmpty() ? Optional.empty() : Optional.of(cadernos);
    }

    private List<Produto> filtrarCadernosPorMaterias(Materias materias) {
        List<Produto> cadernos = new ArrayList<>();
        for (Produto produto : bancoDados.getProdutos()) {
            if (produto instanceof Caderno && ((Caderno) produto).getMaterias() == materias) {
                cadernos.add(produto);
            }
        }
        return cadernos;
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    public void listarTodos() {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto: bancoDados.getProdutos()) {
                System.out.println(produto.toString());
            }
        }
    }
}
