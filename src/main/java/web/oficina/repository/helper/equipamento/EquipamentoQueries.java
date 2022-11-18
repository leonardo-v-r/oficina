package web.oficina.repository.helper.equipamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.oficina.model.Equipamento;
import web.oficina.model.filter.EquipamentoFilter;


public interface EquipamentoQueries {

	Page<Equipamento> pesquisar(EquipamentoFilter equipamento, Pageable pageable);
	
}