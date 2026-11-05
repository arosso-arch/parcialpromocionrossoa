package icop.parcialpromocionrossoa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import icop.parcialpromocionrossoa.dto.RequestSocioDto;
import icop.parcialpromocionrossoa.dto.ResponseSocioDto;
import icop.parcialpromocionrossoa.entity.Socio;
import icop.parcialpromocionrossoa.exception.RecursoNoEncontradoException;
import icop.parcialpromocionrossoa.exception.ReglaNegocioException;
import icop.parcialpromocionrossoa.repository.SocioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocioServiceImpl implements SocioService {

    private final SocioRepository socioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseSocioDto> listar() {
        return socioRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseSocioDto buscarPorId(Long id) {
        return toDto(obtenerSocio(id));
    }

    @Override
    @Transactional
    public ResponseSocioDto registrar(RequestSocioDto dto) {
        if (socioRepository.existsByDni(dto.getDni())) {
            throw new ReglaNegocioException("Ya existe un socio con el DNI: " + dto.getDni());
        }
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());
        socio.setDni(dto.getDni());
        return toDto(socioRepository.save(socio));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Socio socio = obtenerSocio(id);
        if (!socio.getPrestamos().isEmpty()) {
            throw new ReglaNegocioException(
                    "No se puede eliminar el socio con id: " + id + " porque tiene préstamos asociados");
        }
        socioRepository.delete(socio);
    }

    private Socio obtenerSocio(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));
    }

    private ResponseSocioDto toDto(Socio socio) {
        return new ResponseSocioDto(socio.getNombre() + " " + socio.getApellido(), socio.getDni());
    }
}
