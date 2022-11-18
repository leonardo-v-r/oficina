package web.oficina.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oficina.model.Equipamento;
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

	@GetMapping("/abrircadastrar")
	public String abrirCadastro(Equipamento equipamento) {
		return "equipamento/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Equipamento equipamento) {
		equipamentoService.salvar(equipamento);
		return "redirect:/equipamento/cadastro/sucesso";
	}
	
	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Cadastro de equipamento efetuado com sucesso.");
		return "index";
	}
}
