package mai.administracaousuarios.api.controller;

import jakarta.validation.Valid;
import mai.administracaousuarios.model.Empresa;
import mai.administracaousuarios.model.enums.Roles;
import mai.administracaousuarios.model.enums.TipoPlano;
import mai.administracaousuarios.project.security.Encrypt;
import mai.administracaousuarios.repository.EmpresaRepository;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class CriarEmpresaController {

    @Autowired
    private EmpresaRepository empresaRep;

    private final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);


    @GetMapping("/criar-empresa")
    public ModelAndView criarEmpresa() {
        var mv = new ModelAndView("criar_empresa");

        mv.addObject("tipoPlano", TipoPlano.values());
        mv.addObject("empresa", new Empresa());

        return mv;
    }

    @PostMapping("/inserir-empresa")
    public ModelAndView inserirEmpresa(@Valid Empresa novaEmpresa, BindingResult bd) {

        if(bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("criar_empresa");
            mv.addObject("tipoPlano", TipoPlano.values());
            mv.addObject("empresa", novaEmpresa);
            return mv;
        }else{
            String[] encrypted = Encrypt.encryptPassword(novaEmpresa.getUsuario().getSenha());

            Boolean randomBoolean = new Random().nextBoolean();
            TipoPlano randomTipoPlano = TipoPlano.values()[new Random().nextInt(TipoPlano.values().length)];

            novaEmpresa.getUsuario().setSenha(encrypted[0]);
            novaEmpresa.getUsuario().setSalt(encrypted[1]);
            novaEmpresa.getUsuario().setRole(Roles.USER);
            novaEmpresa.setPago(randomBoolean);
            novaEmpresa.setTipoPlano(randomTipoPlano);

            try{
                empresaRep.save(novaEmpresa);

                return new ModelAndView("redirect:/empresas");
            }catch (DataIntegrityViolationException e){
                logger.error(e.getMessage());
                ModelAndView mv = new ModelAndView("criar_empresa");
                mv.addObject("tipoPlano", TipoPlano.values());
                mv.addObject("empresa", novaEmpresa);

                bd.addError(new ObjectError("empresa", "Verify the data and try again"));
                return mv;
            }

        }

    }

}
