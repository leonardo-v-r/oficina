package web.oficina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.oficina.model.Equipamento;
import web.oficina.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Transactional
	public void salvar(Equipamento equipamento) {
		equipamentoRepository.save(equipamento);
	}
	
	@Transactional
	public void alterar(Equipamento equipamento) {
		equipamentoRepository.save(equipamento);
	}
}
