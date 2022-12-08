package web.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.oficina.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {

    public Papel findByNome(String nome);
}

