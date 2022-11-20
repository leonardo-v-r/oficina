package web.oficina.controller;

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
import web.oficina.model.StatusEquipamento;
import web.oficina.model.filter.EquipamentoFilter;
import web.oficina.pagination.PageWrapper;
import web.oficina.repository.EquipamentoRepository;
import web.oficina.service.EquipamentoService;

@Controller
@RequestMapping("/equipamento")
public class EquipamentoController {

	private static final Logger logger = LoggerFactory.getLogger(EquipamentoController.class);

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	@Autowired
	private EquipamentoService equipamentoService;

	@GetMapping("/abrirpesquisar")
	public String abrirPesquisa() {
		return "equipamento/pesquisar";
	}

	@GetMapping("/pesquisar")
	public String pesquisar(EquipamentoFilter filtro, Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {

		Page<Equipamento> pagina = equipamentoRepository.pesquisar(filtro, pageable);
		PageWrapper<Equipamento> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "equipamento/mostrartodos";
	}

	@GetMapping("/abrircadastrar")
	public String abrirCadastro(Equipamento equipamento) {
		return "equipamento/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Equipamento equipamento, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("O equipamento recabido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "equipamento/cadastrar";
		} else {
			equipamentoService.salvar(equipamento);
			return "redirect:/equipamento/cadastro/sucesso";
		}
	}

	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Equipamento equipamento, Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Cadastro de equipamento efetuado com sucesso.",
				TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "equipamento/cadastrar";
	}

	@PostMapping("/abriralterar")
	public String abrirAlterar(Equipamento equipamento) {
		System.out.println(equipamento);
		return "equipamento/alterar";
	}

	@PostMapping("/alterar")
	public String alterar(@Valid Equipamento equipamento, BindingResult resultado) {
		if (resultado.hasErrors()) {
			logger.info("O equipamento recabido para alterar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "equipamento/alterar";
		} else {
			equipamentoService.alterar(equipamento);
			return "redirect:/equipamento/alterar/sucesso";
		}
	}

	@GetMapping("/alterar/sucesso")
	public String mostrarMensagemAlterarSucesso(Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Alteração efetuada com sucesso.", TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "equipamento/pesquisar";
	}

	@PostMapping("/remover")
	public String remover(Equipamento equipamento) {
		equipamento.setStatus(StatusEquipamento.EXCLUIDO);
		equipamentoService.alterar(equipamento);
		return "redirect:/equipamento/remover/sucesso";
	}

	@GetMapping("/remover/sucesso")
	public String mostrarMensagemRemoverSucesso(Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Remoção (EXCLUIDO) efetuada com sucesso.",
				TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "equipamento/pesquisar";
	}
}
