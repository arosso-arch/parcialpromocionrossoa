package icop.parcialpromocionrossoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSocioDto {
    private String nombreCompleto;
    private String dni;
}
