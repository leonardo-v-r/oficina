package web.oficina.model;

public enum StatusEquipamento {

    MANUTENCAO("Em manutenção"),
    EXCLUIDO("Excluído"),
    DISPONIVEL("Disponível");

    private String descricao;

    private StatusEquipamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
