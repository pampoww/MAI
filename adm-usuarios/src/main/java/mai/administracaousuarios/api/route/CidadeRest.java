package mai.administracaousuarios.api.route;

import jakarta.validation.Valid;
import mai.administracaousuarios.model.Cidade;
import mai.administracaousuarios.repository.CidadeRepository;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeRest {

    @Autowired
    private CidadeRepository cidadeRep;
    private final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Cidade>> findAll() {
        List<Cidade> cidades = cidadeRep.findAll();
        if (cidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(cidades);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable String id) {
        Optional<Cidade> cidade = cidadeRep.findById(id);
        if (cidade.isPresent()) {
            return ResponseEntity.ok().body(cidade.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Cidade> put(@PathVariable String id, @Valid @RequestBody Cidade body) {

        try {
            Cidade cidade = cidadeRep.findById(id).get();
            cidade.setNome(body.getNome());
            cidadeRep.save(cidade);
            return ResponseEntity.ok().body(cidade);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
