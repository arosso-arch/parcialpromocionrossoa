package icop.parcialpromocionrossoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icop.parcialpromocionrossoa.entity.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    boolean existsByDni(String dni);
}
