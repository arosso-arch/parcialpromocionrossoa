package icop.parcialpromocionrossoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icop.parcialpromocionrossoa.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    boolean existsByTituloLibroAndDevueltoFalse(String tituloLibro);
}
