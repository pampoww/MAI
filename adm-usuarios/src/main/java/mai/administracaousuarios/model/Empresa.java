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
import mai.administracaousuarios.model.enums.TipoPlano;

@Entity
@Table(name = "tb_mai_empresa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_empresa", nullable = false)
    private String idEmpresa;
    @Setter
    @Column(name = "nm_empresa", nullable = false, unique = true)
    @NotEmpty(message = "{empresa.nome.notEmpty}")
    @Size(max = 50, min = 4, message = "{empresa.nome.size}")
    private String nome;

    @Setter
    @Column(name = "ct_telefone", unique = true)
    @Size(max = 9, min = 9, message = "{empresa.telefone.size}")
    private String telefone;

    @Setter
    @Column(name = "st_site", unique = true)
    @Size(min = 4, message = "{empresa.site.size}")
    private String site;

    @Setter
    @Column(name = "nr_cnpj", nullable = false, unique = true)
    @NotEmpty(message = "{empresa.cnpj.notEmpty}")
    @Size(max = 14, min = 14, message = "{empresa.cnpj.size}")
    private String cnpj;

    @Setter
    @Column(name = "tp_tipo_plano", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    @Setter
    @Column(name = "bl_pagamento")
    private Boolean pago;

    @Setter
    @Valid
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @NotNull(message = "The attribute usuario cannot be null")
    private Usuario usuario;
    @Setter
    @Valid
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_logradouro")
    @NotNull(message = "The attribute logradouro cannot be null")
    private Logradouro logradouro;
}
