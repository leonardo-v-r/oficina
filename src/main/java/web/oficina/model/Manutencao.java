package web.oficina.model;

public class Manutencao {

    private Long codigo;
    private Usuario usuario;
    private Equipamento equipamento;
    private PrioridadeManutencao prioridade;
    private StatusManutencao status;
    private String obsUsuario;
    private String obsAdmin;

    public Long getCodigo() {
        return codigo;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Equipamento getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
    public PrioridadeManutencao getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(PrioridadeManutencao prioridade) {
        this.prioridade = prioridade;
    }
    public StatusManutencao getStatus() {
        return status;
    }
    public void setStatus(StatusManutencao status) {
        this.status = status;
    }
    public String getObsUsuario() {
        return obsUsuario;
    }
    public void setObsUsuario(String obsUsuario) {
        this.obsUsuario = obsUsuario;
    }
    public String getObsAdmin() {
        return obsAdmin;
    }
    public void setObsAdmin(String obsAdmin) {
        this.obsAdmin = obsAdmin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Manutencao other = (Manutencao) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Manutencao [codigo=" + codigo + ", usuario=" + usuario.getCodigo() + ", equipamento=" + equipamento.getCodigo()
                + ", prioridade=" + prioridade + ", status=" + status + ", obsUsuario=" + obsUsuario + ", obsAdmin="
                + obsAdmin + "]";
    } 
}
