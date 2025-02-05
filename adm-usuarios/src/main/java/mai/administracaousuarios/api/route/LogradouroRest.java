package mai.administracaousuarios.api.route;

import jakarta.validation.Valid;
import mai.administracaousuarios.model.Logradouro;
import mai.administracaousuarios.repository.LogradouroRepository;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/logradouro")
public class LogradouroRest {

    @Autowired
    private LogradouroRepository logradouroRep;
    private final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Logradouro>> findAll() {
        List<Logradouro> logradouros = logradouroRep.findAll();
        if(logradouros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(logradouros);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Logradouro> findById(@PathVariable String id) {
        try {
            Logradouro logradouro = logradouroRep.findById(id).get();
            return ResponseEntity.ok().body(logradouro);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Logradouro> put(@PathVariable String id, @Valid @RequestBody Logradouro body) {
        try{
            Logradouro logradouro = logradouroRep.findById(id).get();
            logradouro.setCep(body.getCep());
            logradouroRep.save(logradouro);
            return ResponseEntity.ok().body(logradouro);
        }catch(NoSuchElementException e){
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
