package mai.administracaousuarios.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorKafkaService {

    @KafkaListener(topics = ProdutorKafkaService.topico, groupId = "notificacao/funcionario")
    public void receberMensagem(String mensagem) {
        System.out.println(mensagem);
    }

}
