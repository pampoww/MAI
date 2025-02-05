package mai.administracaousuarios.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tb_mai_cidade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cidade", length = 50)
    private String idCidade;
    @Setter
    @Column(name = "nm_cidade", length = 50)
    @NotNull(message = "{cidade.nome.notNull}")
    private String nome;

    @Setter
    @Valid
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estado", nullable = false)
    @NotNull(message = "The attribute cannot be null")
    private Estado estado;
}
