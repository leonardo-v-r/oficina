package web.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.oficina.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
