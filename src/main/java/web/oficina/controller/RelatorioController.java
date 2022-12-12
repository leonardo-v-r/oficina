package web.oficina.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oficina.service.RelatorioService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	private static final Logger logger = LoggerFactory.getLogger(RelatorioController.class);
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/todosequipamentos")
	public ResponseEntity<byte[]> gerarRelatorio() {
		logger.trace("Entrou em gerarRelatorio");
		logger.debug("Gerando relatório");
		
		byte[] relatorio = relatorioService.gerarRelatorio();
		
		logger.debug("Relatório gerado");
		logger.debug("Retornando o relatório");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorioequipamentos.pdf")
				.body(relatorio);
	}
	
}