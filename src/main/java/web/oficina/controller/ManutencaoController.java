package web.oficina.controller;

import java.util.Arrays;
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
import web.oficina.model.Manutencao;
import web.oficina.model.PrioridadeManutencao;
import web.oficina.model.StatusEquipamento;
import web.oficina.model.Usuario;
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
	private ManutencaoRepository manutencaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EquipamentoService equipamentoService;

	private Equipamento equipamentoParaManutencao;

	@PostMapping("/abrircadastrar")
	public String abrirCadastro(Model model, Equipamento equipamento, Manutencao manutencao) {
		List<PrioridadeManutencao> prioridades = Arrays.asList(PrioridadeManutencao.values());
		List<Usuario> usuarios = usuarioRepository.findAll();
		equipamentoParaManutencao = equipamento;
		model.addAttribute("usuario", usuarios);
		model.addAttribute("prioridade", prioridades);
		System.out.println(equipamentoParaManutencao);
		return "manutencao/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Manutencao manutencao, BindingResult resultado) {
		manutencao.setEquipamento(equipamentoParaManutencao);
		if (resultado.hasErrors()) {
			logger.info("O equipamento recabido para alterar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "equipamento/cadastrar";
		} else {
			equipamentoParaManutencao.setStatus(StatusEquipamento.MANUTENCAO);
			manutencaoService.salvar(manutencao);
			equipamentoService.alterar(equipamentoParaManutencao);
			return "redirect:/manutencao/cadastro/sucesso";
		}

	}

	@GetMapping("/manutencao/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		NotificacaoAlertify notificacao = new NotificacaoAlertify("Cadastro de manutenção efetuado com sucesso.",
				TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "equipamento/pesquisar";
	}
}
