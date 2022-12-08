package web.oficina.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oficina.ajax.NotificacaoAlertify;
import web.oficina.ajax.TipoNotificaoAlertify;
import web.oficina.model.Papel;
import web.oficina.model.Usuario;
import web.oficina.model.filter.UsuarioFilter;
import web.oficina.pagination.PageWrapper;
import web.oficina.repository.PapelRepository;
import web.oficina.repository.UsuarioRepository;
import web.oficina.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private PapelRepository papelRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired PasswordEncoder passwordEncoder;

	@GetMapping("/abrircadastrar")
	public String abrirCadastro(Usuario usuario, Model model) {
		List<Papel> papeis = papelRepository.findAll();
		model.addAttribute("papeis", papeis);	
		return "usuario/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Usuario usuario, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("O usuário recebido para cadastrar não é válido.");
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

	@PostMapping("/cadastroNovo")
	public String cadastroNovo(Usuario usuario, BindingResult resultado) {

		if (resultado.hasErrors()) {
			logger.info("O usuário recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "usuario/cadastrar";
		}

		// Define a role USER caso o usuário não tenha preenchido
		if (usuario.getPapeis().isEmpty()) {
			Papel papel = papelRepository.findByNome("ROLE_USER");
			List<Papel> papeis = new ArrayList<Papel>();
			papeis.add(papel);
			usuario.setPapeis(papeis);
		}

		// Verifica se o usuário já existe
		Optional<Usuario> usuarioExiste = usuarioRepository.findByLogin(usuario.getLogin());
		if (usuarioExiste.isPresent()) {
			return "redirect:/usuario/cadastro/falha";
		}

		usuario.setAtivo(true);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioService.salvar(usuario);
		return "redirect:/usuario/cadastro/sucesso";
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
