package mai.administracaousuarios.model.enums;

import lombok.Getter;

@Getter
public enum TipoPlano {
    BASIC("BASIC"),
    PREMIUM("PREMIUM");

    private final String tipo;
    TipoPlano(String tipo) {
        this.tipo = tipo;
    }
}
