package mai.administracaousuarios.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mai.administracaousuarios.model.Empresa;
import mai.administracaousuarios.model.interfaces.InterfaceDTOMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaTelDTO implements InterfaceDTOMapper<Empresa, EmpresaTelDTO> {
    @NotBlank
    @Size(max = 30, min = 10, message = "The size must be between 10 and 30")
    private  String telefone;

    @Override
    public void toEntity(Empresa emp) {
        emp.setTelefone(this.getTelefone());
    }
}
