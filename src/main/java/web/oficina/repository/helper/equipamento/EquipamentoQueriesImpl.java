package web.oficina.repository.helper.equipamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.oficina.model.Equipamento;
import web.oficina.model.filter.EquipamentoFilter;


public class EquipamentoQueriesImpl implements EquipamentoQueries {
	
	@PersistenceContext
	private EntityManager manager;
		
	@Override
	public Page<Equipamento> pesquisar(EquipamentoFilter filtro, Pageable pageable) {
		
		return null;
	}
		
}