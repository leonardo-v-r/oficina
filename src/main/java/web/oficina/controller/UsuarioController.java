package web.oficina.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oficina.ajax.NotificacaoAlertify;
import web.oficina.ajax.TipoNotificaoAlertify;
import web.oficina.model.Equipamento;
import web.oficina.model.Usuario;
import web.oficina.repository.UsuarioRepository;
import web.oficina.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/abrircadastrar")
	public String abrirCadastro(Usuario usuario) {
		return "usuario/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Usuario usuario, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("O usuário recabido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "usuario/cadastrar";
		} else {
			List<Usuario> usuarios = usuarioRepository.findAll();
			for (Usuario usuariosList : usuarios) {
				if (usuariosList.getLogin().equals(usuario.getLogin())) {
					return "redirect:/usuario/cadastro/falha";
				}
			}
			usuarioService.salvar(usuario);
			return "redirect:/usuario/cadastro/sucesso";
		}
	}

	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Usuario usuario, Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Cadastro de usuário efetuado com sucesso.",
				TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "usuario/cadastrar";
	}

	@GetMapping("/cadastro/falha")
	public String mostrarMensagemCadastroFalha(Usuario usuario, Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Login já está sendo utilizado",
				TipoNotificaoAlertify.ERRO);
		model.addAttribute("notificacao", notificacao);
		return "usuario/cadastrar";
	}

}
