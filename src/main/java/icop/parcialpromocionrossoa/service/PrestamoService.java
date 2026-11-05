package icop.parcialpromocionrossoa.service;

import java.util.List;

import icop.parcialpromocionrossoa.dto.RequestPrestamoDto;
import icop.parcialpromocionrossoa.dto.ResponsePrestamoDto;

public interface PrestamoService {

    ResponsePrestamoDto registrar(RequestPrestamoDto dto);

    List<ResponsePrestamoDto> listarPorSocio(Long socioId);
}
