package web.oficina.model;

public enum StatusEquipamento {

    EM_MANUTENCAO("Em manutenção"),
    DISPONIVEL("Disponível");

    private String descricao;

    private StatusEquipamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
