package tests.factory;


public class Product {

    private String nome;
    private Integer preco;
    private String descricao;
    private Integer quantidade;

    public Product(String name, Integer price, String description, Integer quantity){
        this.nome = name;
        this.preco = price;
        this.descricao = description;
        this.quantidade = quantity;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
