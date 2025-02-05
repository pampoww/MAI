package mai.administracaousuarios.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProdutorKafkaService {

    public static final String topico = "notificacao";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProdutorKafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensagem(String mensagem) {
        kafkaTemplate.send(topico, mensagem);
    }

}
