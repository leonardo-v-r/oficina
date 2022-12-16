package web.oficina.repository.helper.manutencao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.oficina.model.Equipamento;
import web.oficina.model.Manutencao;
import web.oficina.model.Usuario;
import web.oficina.model.filter.EquipamentoFilter;
import web.oficina.model.filter.ManutencaoFilter;
import web.oficina.model.filter.UsuarioFilter;

public interface ManutencaoQueries {

	Page<Manutencao> pesquisar( Pageable pageable);
	
}