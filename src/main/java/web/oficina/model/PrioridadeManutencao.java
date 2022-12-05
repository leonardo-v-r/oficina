package web.oficina.model;

public enum PrioridadeManutencao {
	
    URGENTE("Urgente"),
    ALTA("Alta"),
    MEDIA("Média"),
    BAIXA("Baixa");

    private String descricao;

    private PrioridadeManutencao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
