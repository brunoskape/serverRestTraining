package tests.factory;

public class Car {

    private String produtos;
    private String idUsuario;
    private Integer precoTotal;
    private Integer quantidadeTotal;

    public Car(String produtos, String idUsuario, Integer precoTotal, Integer quantidadeTotal) {
        this.produtos = produtos;
        this.idUsuario = idUsuario;
        this.precoTotal = precoTotal;
        this.quantidadeTotal = quantidadeTotal;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Integer precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }
}
