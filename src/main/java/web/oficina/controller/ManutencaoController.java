package web.oficina.controller;

import java.util.Arrays;
import java.util.List;

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
import web.oficina.model.StatusManutencao;
import web.oficina.model.Usuario;
import web.oficina.model.filter.ManutencaoFilter;
import web.oficina.pagination.PageWrapper;
import web.oficina.repository.ManutencaoRepository;
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
	private ManutencaoRepository manutencaoRepository;

	@Autowired
	private EquipamentoService equipamentoService;

	@PostMapping("/abrircadastrar")
	public String abrirCadastro(Model model, Equipamento equipamento, Manutencao manutencao) {
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
			manutencao.setSituacao(StatusManutencao.PENDENTE);
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
	
	@GetMapping("/pesquisarminhas")
	public String pesquisar( Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {

		Page<Manutencao> pagina = manutencaoRepository.pesquisar(pageable);
		PageWrapper<Manutencao> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "manutencao/mostrarminhas";
	}
	
	@PostMapping("/abrirtrabalhar")
	public String remover(Manutencao manutencao) {
		manutencao.setSituacao(StatusManutencao.EM_ANDAMENTO);
		manutencaoService.alterar(manutencao);
		return "redirect:/manutencao/trabalhar/sucesso";
	}
	
	@GetMapping("/trabalhar/sucesso")
	public String mostrarMensagemRemoverSucesso(Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Trabalho iniciado com sucesso.",
				TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "/index";
	}
}
