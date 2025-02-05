package mai.administracaousuarios.service;

import mai.administracaousuarios.model.Usuario;
import mai.administracaousuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAuthenticationService implements AuthenticationProvider {

    UserService userService;
    PasswordEncoder passwordEncoder;
    UsuarioRepository usuarioRep;

    @Autowired
    public UsuarioAuthenticationService(UserService userService, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRep) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRep = usuarioRep;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String password = auth.getCredentials().toString();

        Usuario usuario = usuarioRep.findByLogin(login).orElse(null);

        if(usuario == null){
            return null;
        }

        UserDetails userDetails = userService.loadUserByUsername(login);

        if(!passwordEncoder.matches(password + usuario.getSalt(), usuario.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
