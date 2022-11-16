package web.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.oficina.model.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{
    
}
