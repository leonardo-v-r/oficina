package web.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.oficina.model.Equipamento;
import web.oficina.model.StatusEquipamento;
import web.oficina.repository.helper.equipamento.EquipamentoQueries;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>, EquipamentoQueries {

	List<Equipamento> findByStatus(StatusEquipamento status);
}