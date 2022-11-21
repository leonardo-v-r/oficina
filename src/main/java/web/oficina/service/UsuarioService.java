package web.oficina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.oficina.model.Usuario;
import web.oficina.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

	@Transactional
    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }   
	
	@Transactional
	public void alterar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
