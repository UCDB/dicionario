package br.ucdb.Repository;

import br.ucdb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "Select u from Usuario u where u.login=:userLogin")
    public Usuario buscaPorId(@Param("userLogin") String login);
}
