package one.digitalinovation.laboojava.entidade;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite
 */
public class Pedido {

    private String codigo;
    private List<Produto> produtos = new ArrayList<>();
    private Cliente cliente;
    private LocalDateTime data;
    private double total;

    public Pedido() {
    }

    public List<Produto> getProdutos() {

        return produtos;
    }

    public void adicionar(Produto produto){
        produtos.add(produto);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo='" + codigo + '\'' +
                ", produtos=" + produtos +
                ", cliente=" + cliente +
                ", data=" + data +
                ", total=" + total +
                '}';
    }

    //TODO Preencher esta classe

    //codigo
    //cliente
    //produtos
    //total
}
