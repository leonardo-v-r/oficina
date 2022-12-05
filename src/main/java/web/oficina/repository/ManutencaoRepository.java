package web.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.oficina.model.Manutencao;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

}