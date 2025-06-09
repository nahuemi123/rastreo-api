package com.miproyecto.rastreo_api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/localizacion")   
class LocalizacionController {
  @Autowired
    private UbicacionRepository ubicacionRepository;

    // --- Endpoint para OBTENER una ubicación ---
    // URL: GET http://localhost:8080/api/localizacion/123456789
    @GetMapping("/{numero}")
    public ResponseEntity<UbicacionData> obtenerUbicacion(@PathVariable String numero) {
        System.out.println(">>> Petición GET recibida para el número: " + numero);
        
        Optional<UbicacionData> ubicacionOptional = ubicacionRepository.findTopByNumeroOrderByFechaHoraDesc(numero);

        if (ubicacionOptional.isPresent()) {
            System.out.println(">>> Ubicación encontrada: " + ubicacionOptional.get());
            return ResponseEntity.ok(ubicacionOptional.get());
        } else {
            System.out.println(">>> No se encontró ubicación para el número: " + numero);
            return ResponseEntity.notFound().build();
        }
    }

    // --- Endpoint para GUARDAR una ubicación ---
    // URL: POST http://localhost:8080/api/localizacion
    @PostMapping
    public ResponseEntity<UbicacionData> guardarUbicacion(@RequestBody UbicacionData ubicacion) {
        System.out.println(">>> Petición POST recibida para guardar: " + ubicacion);
        
        if (ubicacion.getNumero() == null || ubicacion.getNumero().isBlank()) {
            System.out.println(">>> Petición rechazada: el número es nulo o vacío.");
            return ResponseEntity.badRequest().build();
        }

        ubicacion.setFechaHora(LocalDateTime.now());
        UbicacionData ubicacionGuardada = ubicacionRepository.save(ubicacion);
        
        System.out.println(">>> Ubicación guardada con éxito: " + ubicacionGuardada);
        return ResponseEntity.ok(ubicacionGuardada);
    }
}
