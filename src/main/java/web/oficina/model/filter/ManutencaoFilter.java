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
    private String solucao;
    private String problema;
    
    
	@Override
	public String toString() {
		return "ManutencaoFilter [codigo=" + codigo + ", usuario=" + usuario + ", equipamento=" + equipamento
				+ ", prioridade=" + prioridade + ", status=" + status + ", solucao=" + solucao + ", problema="
				+ problema + "]";
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
	public String getSolucao() {
		return solucao;
	}
	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	
}
