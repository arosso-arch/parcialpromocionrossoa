package icop.parcialpromocionrossoa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import icop.parcialpromocionrossoa.dto.RequestPrestamoDto;
import icop.parcialpromocionrossoa.dto.ResponsePrestamoDto;
import icop.parcialpromocionrossoa.service.PrestamoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<ResponsePrestamoDto> registrar(@RequestBody RequestPrestamoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.registrar(dto));
    }

    @GetMapping("/socio/{id}")
    public ResponseEntity<List<ResponsePrestamoDto>> listarPorSocio(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.listarPorSocio(id));
    }
}
