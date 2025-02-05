package mai.administracaousuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_mai_funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_funcionario", length = 50, nullable = false)
    private String idFuncionario;
    @Column(name = "nm_funcionario")
    @NotBlank(message = "The attribute nome cannot be null or blank")
    @Size(max = 50, min = 4, message = "The name length must be between 4 and 50")
    private String nome;
    @Column(name = "nm_cargo")
    @NotBlank(message = "The atribute cargo cannot be null or blank")
    @Size(max = 50, min = 5, message = "The cargo length must be between 4 and 50")
    private String cargo;
    @Column(name = "nm_email")
    @NotBlank(message = "The atribute email cannot be null or blank")
    @Size(max = 50, min = 10, message = "The cargo length must be between 10 and 50")
    private String email;
    @Column(name = "img_imagem_perfil")
//    @NotBlank(message = "The atribute email cannot be null or blank")
    private String imagemPerfil;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_usuario")
    @NotNull(message = "The attribute usuario cannot be null")
    private Usuario usuario;
}
