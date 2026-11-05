package icop.parcialpromocionrossoa.service;

import java.util.List;

import icop.parcialpromocionrossoa.dto.RequestSocioDto;
import icop.parcialpromocionrossoa.dto.ResponseSocioDto;

public interface SocioService {

    List<ResponseSocioDto> listar();

    ResponseSocioDto buscarPorId(Long id);

    ResponseSocioDto registrar(RequestSocioDto dto);

    void eliminar(Long id);
}
