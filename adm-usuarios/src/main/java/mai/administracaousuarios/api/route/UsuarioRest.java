package mai.administracaousuarios.api.route;

import jakarta.validation.Valid;
import mai.administracaousuarios.model.Usuario;
import mai.administracaousuarios.repository.UsuarioRepository;
import mai.administracaousuarios.project.security.Encrypt;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioRest {

    @Autowired
    private UsuarioRepository usuarioRep;

    private final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Usuario>> findAll() {

        List<Usuario> usuarios = usuarioRep.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario body) {
        String[] encrypted = Encrypt.encryptPassword(body.getSenha());

        body.setSenha(encrypted[0]);
        body.setSalt(encrypted[1]);

        Usuario usuario = usuarioRep.save(body);
        return ResponseEntity.created(null).body(usuario);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable String id) {
        try{
            usuarioRep.findById(id).get();
            usuarioRep.deleteById(id);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id) {
        try {
            Usuario  usuario = usuarioRep.findById(id).get();
            return ResponseEntity.ok().body(usuario);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Usuario> put(@PathVariable String id, @Valid @RequestBody Usuario body) {
        try{
            Usuario usuario = usuarioRep.findById(id).get();
            String[] encrypt = Encrypt.encryptPassword(body.getSenha());
            usuario.setLogin(body.getLogin());
            usuario.setSenha(encrypt[0]);
            usuario.setSalt(encrypt[1]);
            usuarioRep.save(usuario);
            return ResponseEntity.ok().body(usuario);
        }catch(NoSuchElementException e){
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
