package mai.administracaousuarios.service;

import mai.administracaousuarios.model.Usuario;
import mai.administracaousuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    UsuarioRepository usuarioRep;

    public UserService(UsuarioRepository usuarioRep) {
        this.usuarioRep = usuarioRep;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRep.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("No user found"));

        return new User(login, usuario.getPassword(), usuario.getAuthorities());
    }
}
