package web.oficina.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="equipamento")
@DynamicUpdate
public class Equipamento implements Serializable {
	
	private static final long serialVersionUID = 6462379392589216109L;
	
	@Id
	@SequenceGenerator(name="gerador", sequenceName="equipamento_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy = GenerationType.SEQUENCE)
    private Long codigo;
    private String nome;
    private String marca;
	@Enumerated(EnumType.STRING)
    private StatusEquipamento status = StatusEquipamento.DISPONIVEL;
    
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
        Equipamento other = (Equipamento) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Equipamento [codigo=" + codigo + ", nome=" + nome + ", marca=" + marca + ", status=" + status.getDescricao() + "]";
    }
}
