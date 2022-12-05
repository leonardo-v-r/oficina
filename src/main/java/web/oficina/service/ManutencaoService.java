package web.oficina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.oficina.model.Manutencao;
import web.oficina.repository.ManutencaoRepository;

@Service
public class ManutencaoService {

	@Autowired
	private ManutencaoRepository manutencaoRepository;
	
	@Transactional
	public void salvar(Manutencao manutencao) {
		manutencaoRepository.save(manutencao);
	}
	
	@Transactional
	public void alterar(Manutencao manutencao) {
		manutencaoRepository.save(manutencao);
	}
}
