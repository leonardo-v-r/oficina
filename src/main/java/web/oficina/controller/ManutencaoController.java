package web.oficina.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import web.oficina.model.Manutencao;
import web.oficina.model.PrioridadeManutencao;
import web.oficina.model.StatusEquipamento;
import web.oficina.model.Usuario;
import web.oficina.repository.UsuarioRepository;
import web.oficina.service.EquipamentoService;
import web.oficina.service.ManutencaoService;

@Controller
@RequestMapping("/manutencao")
public class ManutencaoController {
	private static final Logger logger = LoggerFactory.getLogger(ManutencaoController.class);

	@Autowired
	private ManutencaoService manutencaoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EquipamentoService equipamentoService;

	@PostMapping("/abrircadastrar")
	public String abrirCadastro(Model model, Equipamento equipamento, Manutencao manutencao) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getPrincipal().toString();
		List<PrioridadeManutencao> prioridades = Arrays.asList(PrioridadeManutencao.values());
		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuario", usuarios);
		model.addAttribute("prioridade", prioridades);
		return "manutencao/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Manutencao manutencao, BindingResult resultado, Equipamento equipamento,
			Model model) {
		if (resultado.hasErrors()) {
			logger.info("O equipamento recebido para alterar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			List<PrioridadeManutencao> prioridades = Arrays.asList(PrioridadeManutencao.values());
			List<Usuario> usuarios = usuarioRepository.findAll();
			model.addAttribute("usuario", usuarios);
			model.addAttribute("prioridade", prioridades);
			return "manutencao/cadastrar";
		} else {
			equipamento.setStatus(StatusEquipamento.MANUTENCAO);
			equipamentoService.alterar(equipamento);
			manutencao.setEquipamento(equipamento);
			manutencaoService.salvar(manutencao);
			return "redirect:/manutencao/cadastro/sucesso";
		}

	}

	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Cadastro de manutenção efetuado com sucesso.",
				TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "equipamento/pesquisar";
	}
}
