package br.ucdb.Repository;

import br.ucdb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "select u from Uuario u where u.cpf=:cpf")
    public Usuario buscaCpf(@Param("cpf") String cpf);

    @Query(value = "select u from Usuario u where u.nome=:nome")
    public Usuario buscaNome (@Param("nome") String nome);

    @Query("select u from Usuario u order by u.login")
    public List<Usuario> buscarUsuarios();
}
