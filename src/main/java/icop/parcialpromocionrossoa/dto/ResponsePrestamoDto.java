package icop.parcialpromocionrossoa.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePrestamoDto {
    private Long id;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private Boolean devuelto;
    private String nombreSocio;
}
