package br.ucdb.Repository;

import br.ucdb.model.Dicionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DicionarioRepository extends JpaRepository<Dicionario, Integer> {

    @Query(value = "Select dic from Dicionario dic where dic.palavra=:palavra")
    public Dicionario buscaPalavra(@Param("palavra") String palavra);


}
