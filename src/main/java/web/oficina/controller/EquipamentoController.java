package web.oficina.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oficina.model.Equipamento;
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
	public String cadastrar(Equipamento equipamento) {
		equipamentoService.salvar(equipamento);
		return "redirect:/equipamento/cadastro/sucesso";
	}
	
	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Cadastro de equipamento efetuado com sucesso.");
		return "index";
	}
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Equipamento equipamento) {
		return "equipamento/alterar";
	}
}
