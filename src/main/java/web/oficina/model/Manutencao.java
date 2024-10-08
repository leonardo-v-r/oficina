package web.oficina.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="manutencao")
@DynamicUpdate
public class Manutencao implements Serializable{

	private static final long serialVersionUID = 6462379392589216109L;

	@Id
	@SequenceGenerator(name="gerador", sequenceName="equipamento_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy = GenerationType.SEQUENCE)
    private Long codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_usuario")
    private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_equipamento")
    private Equipamento equipamento;
	
	@Enumerated(EnumType.STRING)
    private PrioridadeManutencao prioridade;
	
	@Enumerated(EnumType.STRING)
    private StatusManutencao situacao;
	
	@NotBlank(message = "É necessário descrever o que foi feito")
    private String solucao = "Em andamento";
	@NotBlank(message = "É necessário descrever o problema")
    private String problema;
    

    

	@Override
	public String toString() {
		return "Manutencao [codigo=" + codigo + ", usuario=" + usuario + ", equipamento=" + equipamento
				+ ", prioridade=" + prioridade + ", situacao=" + situacao + ", solucao=" + solucao + ", problema="
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

	public StatusManutencao getSituacao() {
		return situacao;
	}

	public void setSituacao(StatusManutencao situacao) {
		this.situacao = situacao;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
    

}
