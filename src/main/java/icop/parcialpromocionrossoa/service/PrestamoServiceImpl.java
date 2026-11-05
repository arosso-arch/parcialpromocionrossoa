package icop.parcialpromocionrossoa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import icop.parcialpromocionrossoa.dto.RequestPrestamoDto;
import icop.parcialpromocionrossoa.dto.ResponsePrestamoDto;
import icop.parcialpromocionrossoa.entity.Prestamo;
import icop.parcialpromocionrossoa.entity.Socio;
import icop.parcialpromocionrossoa.exception.RecursoNoEncontradoException;
import icop.parcialpromocionrossoa.exception.ReglaNegocioException;
import icop.parcialpromocionrossoa.repository.PrestamoRepository;
import icop.parcialpromocionrossoa.repository.SocioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final SocioRepository socioRepository;

    @Override
    @Transactional
    public ResponsePrestamoDto registrar(RequestPrestamoDto dto) {
        Socio socio = obtenerSocio(dto.getSocioId());

        if (prestamoRepository.existsByTituloLibroAndDevueltoFalse(dto.getTituloLibro())) {
            throw new ReglaNegocioException("El libro " + dto.getTituloLibro() + " ya se encuentra prestado");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setTituloLibro(dto.getTituloLibro());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setDevuelto(false);
        prestamo.setSocio(socio);
        socio.getPrestamos().add(prestamo);

        return toDto(prestamoRepository.save(prestamo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponsePrestamoDto> listarPorSocio(Long socioId) {
        Socio socio = obtenerSocio(socioId);
        return socio.getPrestamos()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private Socio obtenerSocio(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));
    }

    private ResponsePrestamoDto toDto(Prestamo prestamo) {
        Socio socio = prestamo.getSocio();
        return new ResponsePrestamoDto(
                prestamo.getId(),
                prestamo.getTituloLibro(),
                prestamo.getFechaPrestamo(),
                prestamo.getDevuelto(),
                socio.getNombre() + " " + socio.getApellido());
    }
}
