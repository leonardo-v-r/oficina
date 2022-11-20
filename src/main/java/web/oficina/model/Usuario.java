package web.oficina.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "usuario_seq_gen", sequenceName = "usuario_codigo_seq", allocationSize = 1)
    @GeneratedValue(generator = "usuario_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo")
    private Long codigo;

	@NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome")
    private String nome;

	@Pattern(regexp="^[a-z0-9]*$", message = "Login deve conter apenas letras minúsculas e números")
    @NotBlank(message = "Login é obrigatório")
    @Column(name = "login")
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    @Column(name = "senha")
    private String senha;

    @NotNull(message = "Esse campo é obrigatório")
    @Column(name = "admin")
    private boolean admin = false;
    
	@Enumerated(EnumType.STRING)
    private StatusUsuario status = StatusUsuario.ATIVO;
    
    public StatusUsuario getStatus() {
        return status;
    }
    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

    public Long getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
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
        Usuario other = (Usuario) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", admin="
				+ admin + ", status=" + status + "]";
	}  
}
