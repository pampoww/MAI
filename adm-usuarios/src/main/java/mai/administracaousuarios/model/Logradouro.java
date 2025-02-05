package mai.administracaousuarios.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_mai_logradouro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Logradouro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_logradouro", length = 50, nullable = false)
    private String idLogradouro;
    @Setter
    @Column(name = "nr_cep", length = 8, nullable = false)
    @NotEmpty(message = "{logradouro.cep.notEmpty}")
    @Size(max = 8, min = 8, message = "{logradouro.cep.size}")
    private String cep;

    @Setter
    @Valid
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cidade")
    @NotNull(message = "The attribute cidade cannot be null or blank")
    private Cidade cidade;
}
