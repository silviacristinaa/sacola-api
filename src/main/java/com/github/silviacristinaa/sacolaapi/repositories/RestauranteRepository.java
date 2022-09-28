package com.github.silviacristinaa.sacolaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.silviacristinaa.sacolaapi.models.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
