package mai.administracaousuarios.repository;

import mai.administracaousuarios.model.Funcionario;
import mai.administracaousuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

    Optional<Funcionario> findByUsuario(Usuario usuario);

}
