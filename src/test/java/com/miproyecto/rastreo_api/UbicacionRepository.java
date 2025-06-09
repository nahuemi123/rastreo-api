/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.miproyecto.rastreo_api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UbicacionRepository extends JpaRepository<UbicacionData, Long> {

    // Este método busca la última ubicación (Top 1) para un número,
    // ordenando por fecha de forma descendente.
    Optional<UbicacionData> findTopByNumeroOrderByFechaHoraDesc(String numero);
}