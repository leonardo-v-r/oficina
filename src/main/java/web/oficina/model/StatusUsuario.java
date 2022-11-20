package web.oficina.model;

public enum StatusUsuario {

    ATIVO("Ativo"),
    EXCLUIDO("Excluido");

    private String descricao;

    private StatusUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
