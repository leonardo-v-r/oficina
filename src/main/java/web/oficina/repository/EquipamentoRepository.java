package web.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.oficina.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{
    
}
