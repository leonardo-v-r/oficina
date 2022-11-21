package web.oficina.repository.helper.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.oficina.model.Usuario;
import web.oficina.model.filter.UsuarioFilter;

public interface UsuarioQueries {

	Page<Usuario> pesquisar(UsuarioFilter usuario, Pageable pageable);
	
}