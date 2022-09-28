package com.github.silviacristinaa.sacolaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.silviacristinaa.sacolaapi.models.Sacola;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {
}
