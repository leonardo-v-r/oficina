package web.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.oficina.model.Usuario;
import web.oficina.repository.helper.usuario.UsuarioQueries;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioQueries{
	
	List<Usuario> findByAtivo(Boolean ativo);
	Usuario findByLogin(String login);
}
