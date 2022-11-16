package web.oficina.model;

public enum StatusManutencao {

    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em andamento"),
    FINALIZADA("Finalizada");

    private String descricao;

    private StatusManutencao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
