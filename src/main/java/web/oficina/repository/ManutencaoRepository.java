package web.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.oficina.model.Equipamento;
import web.oficina.model.Manutencao;
import web.oficina.model.StatusEquipamento;
import web.oficina.model.Usuario;
import web.oficina.repository.helper.manutencao.ManutencaoQueries;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>, ManutencaoQueries {
	
	List<Manutencao> findByUsuario(Usuario usuario);

}