package web.oficina.model.filter;

import web.oficina.model.StatusEquipamento;

public class EquipamentoFilter {
    private Long codigo;
    private String nome;
    private String marca;
    private StatusEquipamento status;

    public Long getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public StatusEquipamento getStatus() {
        return status;
    }
    public void setStatus(StatusEquipamento status) {
        this.status = status;
    }
}
