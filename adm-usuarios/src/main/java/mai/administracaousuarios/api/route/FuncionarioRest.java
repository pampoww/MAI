package mai.administracaousuarios.api.route;

import jakarta.validation.Valid;
import mai.administracaousuarios.model.Funcionario;
import mai.administracaousuarios.repository.FuncionarioRepository;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioRest {

    @Autowired
    private FuncionarioRepository funcionarioRep;
    private final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> funcionarios = funcionarioRep.findAll();

        if(funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable String id) {
        Optional<Funcionario> funcionario = funcionarioRep.findById(id);

        if(funcionario.isPresent()) {
            return ResponseEntity.ok(funcionario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value ="/delete/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable String id, @Valid @RequestBody Funcionario body) {
        Optional<Funcionario> funcionario = funcionarioRep.findById(id);

        if(funcionario.isPresent()) {
            funcionarioRep.delete(funcionario.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
