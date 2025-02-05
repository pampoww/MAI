package mai.administracaousuarios.api.route;

import jakarta.validation.Valid;
import mai.administracaousuarios.model.Empresa;
import mai.administracaousuarios.model.Funcionario;
import mai.administracaousuarios.model.Usuario;
import mai.administracaousuarios.model.enums.TipoPlano;
import mai.administracaousuarios.repository.EmpresaRepository;
import mai.administracaousuarios.repository.FuncionarioRepository;
import mai.administracaousuarios.repository.UsuarioRepository;
import mai.administracaousuarios.project.security.Encrypt;
import mai.administracaousuarios.service.TokenService;
import mai.administracaousuarios.service.UsuarioAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/")
public class AuthRest {

    @Autowired
    UsuarioRepository usuarioRep;

    @Autowired
    UsuarioAuthenticationService usuarioAuthenticationProvider;

    @Autowired
    EmpresaRepository empresaRep;

    @Autowired
    FuncionarioRepository  funcionarioRep;

    TokenService tokenService = new TokenService();

    @PostMapping(value = "/register/empresa")
    public ResponseEntity<Empresa> registerEmpresa(@Valid @RequestBody Empresa body) {
        String[] encrypted = Encrypt.encryptPassword(body.getUsuario().getSenha());

        Boolean randomBoolean = new Random().nextBoolean();
        TipoPlano randomTipoPlano = TipoPlano.values()[new Random().nextInt(TipoPlano.values().length)];

        body.getUsuario().setSenha(encrypted[0]);
        body.getUsuario().setSalt(encrypted[1]);
        body.setPago(randomBoolean);
        body.setTipoPlano(randomTipoPlano);

        Empresa empresa = empresaRep.save(body);

        return ResponseEntity.created(null).body(empresa);
    }

    @PostMapping(value = "/register/funcionario")
    public ResponseEntity<Funcionario> registerFucionario(@Valid @RequestBody Funcionario body) {

        String[] encrypted = Encrypt.encryptPassword(body.getUsuario().getSenha());

        body.getUsuario().setSenha(encrypted[0]);
        body.getUsuario().setSalt(encrypted[1]);

        Funcionario funcionario = funcionarioRep.save(body);

        return ResponseEntity.created(null).body(funcionario);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody  Usuario body) {

        Optional<Usuario> usuarioOp = usuarioRep.findByLogin(body.getLogin());

        if (usuarioOp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOp.get();

        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(body.getLogin(), body.getSenha() + usuario.getSalt());
        var auth = this.usuarioAuthenticationProvider.authenticate(userPass);

        String token = tokenService.createToken((Usuario) auth.getPrincipal());

        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("token", token);

        return ResponseEntity.ok().body(responseMap);
    }
}
