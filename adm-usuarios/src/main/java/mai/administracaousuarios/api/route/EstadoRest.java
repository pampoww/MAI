package mai.administracaousuarios.api.route;

import jakarta.validation.Valid;
import mai.administracaousuarios.model.Estado;
import mai.administracaousuarios.repository.EstadoRepository;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/estado")
public class EstadoRest {

    @Autowired
    private EstadoRepository estadoRep;
    private final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> estados = estadoRep.findAll();
        if(estados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(estados);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Estado> findById(@PathVariable String id) {
        try {
            Estado estado = estadoRep.findById(id).get();
            return ResponseEntity.ok().body(estado);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Estado> put(@PathVariable String id, @Valid @RequestBody Estado body) {
        try{
            Estado estado = estadoRep.findById(id).get();
            estado.setNome(body.getNome());
            estado.setSigla(body.getSigla());
            estadoRep.save(estado);
            return ResponseEntity.ok().body(estado);
        }catch(NoSuchElementException e){
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
