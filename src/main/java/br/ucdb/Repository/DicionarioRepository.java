package br.ucdb.Repository;

import br.ucdb.model.Dicionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DicionarioRepository extends JpaRepository<Dicionario, Integer> {

}
