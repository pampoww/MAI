package mai.administracaousuarios.api.controller;

import mai.administracaousuarios.model.Empresa;
import mai.administracaousuarios.repository.EmpresaRepository;
import mai.administracaousuarios.service.ProdutorKafkaService;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MensagemEmpresaController {

    private final EmpresaRepository empresaRep;
    private final ProdutorKafkaService produtorKafka;

    public MensagemEmpresaController(EmpresaRepository empresaRep, ProdutorKafkaService produtorKafka) {
        this.empresaRep = empresaRep;
        this.produtorKafka = produtorKafka;
    }

    @GetMapping("/empresas/{idEmpresa}/mensagem")
    public ModelAndView mensagemEmpresa(@PathVariable String idEmpresa) {
        Empresa empresa = empresaRep.findById(idEmpresa).orElse(null);

        if (empresa == null) {
            return new ModelAndView("redirect:/empresas");
        }

        ModelAndView mv = new ModelAndView("mensagem_empresa_page");

        mv.addObject("empresa", empresa);

        return mv;
    }

    @PostMapping("/empresas/{idEmpresa}/mensagem")
    public String mensagemEmpresa(@PathVariable String idEmpresa, @RequestParam(name = "mensagem") String mensagem, Model model) {
        produtorKafka.enviarMensagem(mensagem);

        return "redirect:/empresas/" + idEmpresa + "/mensagem?enviada=true";
    }

}
