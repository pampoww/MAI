package mai.administracaousuarios.repository;

import mai.administracaousuarios.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {

    List<Empresa> findByOrderByNomeAsc();

}
