package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
