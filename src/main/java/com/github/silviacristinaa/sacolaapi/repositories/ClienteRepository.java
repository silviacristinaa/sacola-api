package com.github.silviacristinaa.sacolaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.silviacristinaa.sacolaapi.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
