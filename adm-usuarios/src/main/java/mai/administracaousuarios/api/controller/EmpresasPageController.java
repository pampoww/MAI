package mai.administracaousuarios.api.controller;

import mai.administracaousuarios.model.Empresa;
import mai.administracaousuarios.model.Funcionario;
import mai.administracaousuarios.model.Usuario;
import mai.administracaousuarios.repository.EmpresaRepository;
import mai.administracaousuarios.repository.FuncionarioRepository;
import mai.administracaousuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class EmpresasPageController {
    @Autowired
    private FuncionarioRepository funcionarioRep;
    @Autowired
    private EmpresaRepository empresaRep;
    @Autowired
    private UsuarioRepository usuarioRep;

    @GetMapping("/empresas")
    public ModelAndView empresas() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Usuario usuario = usuarioRep.findByLogin(username).orElse(null);

        var mv = new ModelAndView("empresas_page");

        if (usuario == null){
            return mv;
        }

        Optional<Funcionario> funcionario = funcionarioRep.findByUsuario(usuario);
        List<Empresa> empresas = empresaRep.findByOrderByNomeAsc();

        mv.addObject("func", funcionario.orElse(null));
        mv.addObject("empresas", empresas);

        return mv;
    }

    @GetMapping("/adm/empresa/{id}/delete")
    public String deletarEmpresa (@PathVariable String id) {
        empresaRep.deleteById(id);

        return "redirect:/empresas";

    }

}
