package web.oficina.model.filter;

import web.oficina.model.Equipamento;
import web.oficina.model.PrioridadeManutencao;
import web.oficina.model.StatusManutencao;
import web.oficina.model.Usuario;

public class ManutencaoFilter {

    private Long codigo;
    private Usuario usuario;
    private Equipamento equipamento;
    private PrioridadeManutencao prioridade;
    private StatusManutencao status;
    private String obsUsuario;
    private String obsAdmin;
    
    
	@Override
	public String toString() {
		return "ManutencaoFilter [codigo=" + codigo + ", usuario=" + usuario + ", equipamento=" + equipamento
				+ ", prioridade=" + prioridade + ", status=" + status + ", obsUsuario=" + obsUsuario + ", obsAdmin="
				+ obsAdmin + "]";
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
}
